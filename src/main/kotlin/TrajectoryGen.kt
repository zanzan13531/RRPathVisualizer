import com.acmerobotics.roadrunner.geometry.Pose2d
import com.acmerobotics.roadrunner.geometry.Vector2d
import com.acmerobotics.roadrunner.path.heading.ConstantInterpolator
import com.acmerobotics.roadrunner.path.heading.HeadingInterpolator
import com.acmerobotics.roadrunner.path.heading.SplineInterpolator
import com.acmerobotics.roadrunner.trajectory.Trajectory
import com.acmerobotics.roadrunner.trajectory.TrajectoryBuilder
import com.acmerobotics.roadrunner.trajectory.constraints.DriveConstraints

object TrajectoryGen {
    private val constraints = DriveConstraints(42.0, 30.0, 0.0, 180.0.toRadians, 180.0.toRadians, 0.0)


    fun createTrajectory(): ArrayList<Trajectory> {
        val list = ArrayList<Trajectory>()

        val startPose = Pose2d(-33.0, -63.0, 180.0.toRadians)
        //val startPose = Pose2d(50.0, -35.0, 180.0.toRadians)
//        val startPose = Pose2d(-18.0, -35.0, 180.0.toRadians)
        val builder1 = TrajectoryBuilder(startPose, constraints)

        builder1.strafeRight(25.75)
            .forward(28.0)
            .reverse()
            .splineTo(Pose2d(0.0,-41.0, 180.0.toRadians))
            .splineTo(Pose2d(50.0,-36.0, 180.0.toRadians))
            .reverse()
            .splineTo(Pose2d(0.0,-39.0, 180.0.toRadians))
            .splineTo(Pose2d(-35.0,-36.0, 180.0.toRadians))
            .reverse()
            .splineTo(Pose2d(0.0,-41.0, 180.0.toRadians))
            .splineTo(Pose2d(50.0,-36.0, 180.0.toRadians))
            .reverse()
            .splineTo(Pose2d(8.0,-42.0, 180.0.toRadians))
            .splineTo(Pose2d(-18.0,-36.0, 180.0.toRadians))
            .reverse()
            .splineTo(Pose2d(5.0,-42.0, 180.0.toRadians))
            .splineTo(Pose2d(52.0,-35.0, 180.0.toRadians))
            .reverse()
            .splineTo(Pose2d(0.0,-39.0, 180.0.toRadians))
            .splineTo(Pose2d(-27.0,-35.0, 180.0.toRadians))
            .reverse()
            .splineTo(Pose2d(0.0,-41.0, 180.0.toRadians))
            .splineTo(Pose2d(52.0,-33.0, 180.0.toRadians))
            .reverse()
            .splineTo(Pose2d(55.0,-31.0, 90.0.toRadians))
            .reverse()
            .splineTo(Pose2d(35.0,-56.0, 0.0.toRadians))
            .reverse()
            .splineTo(Pose2d(48.0,-56.0, 0.0.toRadians))
            .reverse()
            .splineTo(Pose2d(0.0,-41.0, 0.0.toRadians))


        builder1
    //        .splineTo(Pose2d(8.0,-42.0, 180.0.toRadians))
    //        .splineTo(Pose2d(-18.0,-36.0, 180.0.toRadians))
//            .reverse()
//            .splineTo(Pose2d(3.0,-44.0, 180.0.toRadians))
//            .splineTo(Pose2d(50.0,-35.0, 180.0.toRadians))


        // dump routine
        /*builder
            .setReversed(true)
            .splineTo(Pose2d(-12.0, -42.0, 180.0.toRadians))// y = -39 is halfway between the skybridge and partner with 6" on either side, y = -42 gives 3" of room for other robot
            .splineTo(Pose2d(28.0, -42.0, 180.0.toRadians))
            .splineTo(Pose2d(48.0, -33.0, -90.0.toRadians))
            .lineTo(Vector2d(48.0, -26.0), ConstantInterpolator(-90.0.toRadians))*/


        // weird reversed profiles
        /* builder
             .setReversed(true)
             .splineTo(Pose2d(-12.0, -42.0, 180.0.toRadians), SplineInterpolator(180.0.toRadians, startPose.heading))// y = 39 is halfway between the skybridge and partner with 6" on either side
             .splineTo(Pose2d(28.0, -42.0, 180.0.toRadians), SplineInterpolator(180.0.toRadians, 180.0.toRadians))
             .splineTo(Pose2d(48.0, -33.0, -90.0.toRadians), SplineInterpolator(180.0.toRadians, -90.0.toRadians))
             .lineTo(Vector2d(48.0, -26.0), ConstantInterpolator(-90.0.toRadians))*/


        list.add(builder1.build())
        return list
    }

    fun drawOffbounds() {
        GraphicsUtil.fillRect(Vector2d(-12.0, -63.0), 18.0, 18.0) // robot against the wall

        GraphicsUtil.fillRect(Vector2d(0.0, -24.0), 12.0, 8.0) // robot against the wall
    }
}

val Double.toRadians get() = (Math.toRadians(this))