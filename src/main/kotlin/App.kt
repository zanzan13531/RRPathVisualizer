import com.acmerobotics.roadrunner.geometry.Pose2d
import javafx.animation.KeyFrame
import javafx.animation.Timeline
import javafx.application.Application
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.canvas.Canvas
import javafx.scene.canvas.GraphicsContext
import javafx.scene.image.Image
import javafx.scene.layout.StackPane
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle

import javafx.stage.Stage
import javafx.util.Duration

class App : Application() {

    val isBlueAlliance = false

    val robotRect = Rectangle(100.0, 100.0, 10.0, 10.0)
    val startRect = Rectangle(100.0, 100.0, 10.0, 10.0)
    val endRect = Rectangle(100.0, 100.0, 10.0, 10.0)

    var startTime = Double.NaN

    var trajectories = if(isBlueAlliance) TrajectoryGenBlue.createTrajectory() else TrajectoryGen.createTrajectory()

    lateinit var fieldImage: Image
    lateinit var stage: Stage

    var activeTrajectoryIndex = 0
    val trajectoryDurations = trajectories.map { it.duration() }
    var duration = trajectoryDurations.sum()
    var numberOfTrajectories = trajectories.size

    companion object {
        var WIDTH = 0.0
        var HEIGHT = 0.0
    }

    override fun start(stage: Stage?) {
        this.stage = stage!!
        fieldImage = Image("/field.png")

        val root = Group()

        WIDTH = fieldImage.width
        HEIGHT = fieldImage.height
        GraphicsUtil.pixelsPerInch = WIDTH / GraphicsUtil.FIELD_WIDTH
        GraphicsUtil.halfFieldPixels = WIDTH / 2.0

        val canvas = Canvas(WIDTH, HEIGHT)
        val gc = canvas.graphicsContext2D
        val t1 = Timeline(KeyFrame(Duration.millis(10.0), EventHandler<ActionEvent> { run(gc) }))
        t1.cycleCount = Timeline.INDEFINITE

        stage.scene = Scene(
            StackPane(
                root
            )
        )

        root.children.addAll(canvas, startRect, endRect, robotRect)

        stage.title = "Team 12611 PathVisualizer"
        stage.isResizable = false

        trajectoryDurations.forEachIndexed { index,element -> println(" $index duration: ${"%.3f".format(element)}") }

        println("total duration ${"%.3f".format(duration)}")

        for(tr in trajectories) {
            for(m in tr.markers) {
                m.callback()
            }
        }

        stage.show()
        t1.play()
    }

    fun run(gc: GraphicsContext) {

        GraphicsUtil.gc = gc
        gc.drawImage(fieldImage, 0.0, 0.0)

        gc.lineWidth = GraphicsUtil.LINE_THICKNESS

        gc.globalAlpha = 0.5
        GraphicsUtil.setColor(Color.RED)
        if(isBlueAlliance) {
            TrajectoryGenBlue.drawOffbounds1()
            TrajectoryGenBlue.drawOffbounds2()
        } else {
            TrajectoryGen.drawOffbounds1()
            TrajectoryGen.drawOffbounds2()
            //TrajectoryGen.drawOffbounds3()
        }
        gc.globalAlpha = 1.0

        val trajectory = trajectories[activeTrajectoryIndex]

        val prevDurations: Double = {
            var x = 0.0
            for (i in 0 until activeTrajectoryIndex)
                x += trajectoryDurations[i]
            x
        }()

        if (startTime.isNaN()) {
            startTime = Clock.seconds
        }

        val time = Clock.seconds
        val profileTime = time - startTime - prevDurations
        val profile_duration = trajectoryDurations[activeTrajectoryIndex]

        val start = trajectories.first().start()
        val end = trajectories.last().end()
        val current = trajectory[profileTime]

        if (profileTime >= profile_duration) {
            activeTrajectoryIndex++
            if(activeTrajectoryIndex >= numberOfTrajectories) {
                activeTrajectoryIndex = 0
                startTime = time
            }
        }

        trajectories.forEach{
            GraphicsUtil.drawSampledPath(it.path)
        }

        GraphicsUtil.updateRobotRect(startRect, start, GraphicsUtil.END_BOX_COLOR, 0.5)
        GraphicsUtil.updateRobotRect(endRect, Pose2d(5.0,-41.0, 0.0.toRadians), GraphicsUtil.END_BOX_COLOR, 0.5)

        GraphicsUtil.updateRobotRect(robotRect, current, GraphicsUtil.ROBOT_COLOR, 0.75)
        GraphicsUtil.drawRobotVector(current)

        stage.title = "Technova Profile duration : ${"%.2f".format(profile_duration)} - time in profile ${"%.2f".format(profileTime)} - total ${"%.2f".format(duration)}"
    }
}

fun main(args: Array<String>) {
    Application.launch(App::class.java, *args)
}