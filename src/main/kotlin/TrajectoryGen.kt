import com.acmerobotics.roadrunner.geometry.Pose2d
import com.acmerobotics.roadrunner.geometry.Vector2d
import com.acmerobotics.roadrunner.path.heading.ConstantInterpolator
import com.acmerobotics.roadrunner.path.heading.LinearInterpolator
import com.acmerobotics.roadrunner.path.heading.SplineInterpolator
import com.acmerobotics.roadrunner.path.heading.TangentInterpolator
import com.acmerobotics.roadrunner.trajectory.Trajectory
import com.acmerobotics.roadrunner.trajectory.TrajectoryBuilder
import com.acmerobotics.roadrunner.trajectory.constraints.DriveConstraints

object TrajectoryGen {
    private val constraints = DriveConstraints(48.0, 45.0, 0.0, 180.0.toRadians, 180.0.toRadians, 0.0)


    fun createTrajectory(): ArrayList<Trajectory> {
        val list = ArrayList<Trajectory>()

        val startPose = Pose2d(-33.0, -63.0, 180.0.toRadians)
//        val startPose = Pose2d(50.0, -35.0, 90.0.toRadians)
//        val startPose = Pose2d(-18.0, -35.0, 180.0.toRadians)
        var builder1 = TrajectoryBuilder(startPose, constraints)

        builder1.strafeTo( Vector2d(-33.0, -36.0))
            .forward(26.0)
//            .lineTo(Vector2d(-59.0,-36.0))

        // 1.
        list.add(builder1.build())

        builder1 = TrajectoryBuilder(Pose2d(-59.0, -36.0, 180.0.toRadians), constraints)

        builder1
            .strafeTo(Vector2d(-59.0,-42.0))
            .reverse()
//            .lineTo(Vector2d(49.0,-42.0))
//            .strafeTo(Vector2d(49.0,-35.0))

            .splineTo(Pose2d(0.0, -41.0, 180.0.toRadians))
            .splineTo(Pose2d(49.0,-35.0, 180.0.toRadians))

        // 2.
        list.add(builder1.build())

        builder1 = TrajectoryBuilder(Pose2d(49.0, -35.0, 180.0.toRadians), constraints)

        builder1
//            .strafeTo(Vector2d(49.0, -40.0))
//            .lineTo(Vector2d(-35.0, -40.0))
//            .strafeTo(Vector2d(-35.0, -36.0))
            .splineTo(Pose2d(0.0,-41.0, 180.0.toRadians))
            .splineTo(Pose2d(-35.0,-36.0, 180.0.toRadians))

        // 3.
        list.add(builder1.build())

        builder1 = TrajectoryBuilder(Pose2d(-35.0, -36.0, 180.0.toRadians), constraints)
        builder1.reverse()
//            .strafeTo(Vector2d(-35.0, -42.0))
//            .lineTo(Vector2d(55.0,-42.0))
//            .strafeTo(Vector2d(55.0, -35.0))

            .splineTo(Pose2d(0.0,-41.0, 180.0.toRadians))
            .splineTo(Pose2d(55.0,-35.0, 180.0.toRadians))

        // 4.
        list.add(builder1.build())

        builder1 = TrajectoryBuilder(Pose2d(55.0, -35.0, 180.0.toRadians), constraints)
        builder1
//            .strafeTo(Vector2d(55.0, -40.0))
//            .lineTo(Vector2d(-18.0, -40.0))
            .splineTo(Pose2d(0.0,-41.0, 180.0.toRadians))
            .lineTo(Vector2d(-18.0,-41.0))
            .strafeTo(Vector2d(-18.0,-36.0))
        // 5.
        list.add(builder1.build())

        builder1 = TrajectoryBuilder(Pose2d(-18.0, -36.0, 180.0.toRadians), constraints)
        builder1.reverse()
            .strafeTo(Vector2d(-18.0,-42.0))
            //.lineTo(Vector2d(61.0,-42.0))
            .splineTo(Pose2d(61.0,-35.0, 180.0.toRadians))

//            .strafeTo(Vector2d(61.0,-35.0))

        // 6.
        list.add(builder1.build())

        builder1 = TrajectoryBuilder(Pose2d(61.0, -35.0, 180.0.toRadians), constraints)
        builder1
//            .strafeTo(Vector2d(61.0, -40.0))
//            .lineTo(Vector2d(-27.0, -40.0))
            .splineTo(Pose2d(0.0,-40.0, 180.0.toRadians))
            .lineTo(Vector2d(-27.0,-40.0))
            .strafeTo(Vector2d(-27.0,-36.0))
//            .splineTo(Pose2d(0.0,-40.0, 180.0.toRadians))
//            .splineTo(Pose2d(-27.0,-36.0, 180.0.toRadians))
//
        // 7.
        list.add(builder1.build())

        builder1 = TrajectoryBuilder(Pose2d(-27.0, -36.0, 180.0.toRadians), constraints)
        builder1
            .reverse()
//            .strafeTo(Vector2d(-27.0, -42.0))
              .splineTo(Pose2d(0.0,-42.0, 180.0.toRadians))
              .splineTo(Pose2d(51.0,-35.0, 180.0.toRadians))
//            .lineTo(Vector2d(51.0,-42.0))
//            .strafeTo(Vector2d(51.0, -33.0))

            .splineTo(Pose2d(55.0,-40.0, 90.0.toRadians))
            .lineTo(Vector2d(55.0, -31.0))
            .lineTo(Vector2d(55.0, -28.0))
            .splineTo(Pose2d(40.0,-50.0, 0.0.toRadians))
            .splineTo(Pose2d(0.0,-38.0, 30.0.toRadians))
//
//        // 8.
        list.add(builder1.build())

//        builder1 = TrajectoryBuilder(Pose2d(61.0, -34.0, 180.0.toRadians), constraints)
//        builder1
            //.reverse()
//            .strafeLeft(3.0)
//            .forward(4.0)

//            .splineTo(Pose2d(55.0,-31.0, 90.0.toRadians))
//            .reverse()
//            .splineTo(Pose2d(35.0,-56.0, 0.0.toRadians))
//            .reverse()
//            .splineTo(Pose2d(48.0,-56.0, 0.0.toRadians))
//            .reverse()
//            .splineTo(Pose2d(0.0,-41.0, 0.0.toRadians))

    //        .splineTo(Pose2d(8.0,-42.0, 180.0.toRadians))
    //        .splineTo(Pose2d(-18.0,-36.0, 180.0.toRadians))
//            .reverse()
//            .splineTo(Pose2d(3.0,-44.0, 180.0.toRadians))
//            .splineTo(Pose2d(50.0,-35.0, 180.0.toRadians))

//        builder1.strafeRight(25.75)
//            .forward(28.0)
//            .reverse()
//            .splineTo(Pose2d(0.0,-41.0, 180.0.toRadians))
//            .splineTo(Pose2d(50.0,-36.0, 180.0.toRadians))

        // 9.
//        list.add(builder1.build())

//        val builder2 = TrajectoryBuilder(Pose2d(51.0,-31.0, (90.0).toRadians), constraints)
//
//            builder2
//            .forward(5.0)
//            .reverse()
//                .splineTo(Pose2d(40.0,-50.0, 0.0.toRadians))
// //           .splineTo(Pose2d(40.0,-50.0, 0.0.toRadians), LinearInterpolator(90.0.toRadians, 0.0.toRadians))
////            .reverse()
////            .splineTo(Pose2d(48.0,-56.0, 0.0.toRadians))
//                .reverse()
//            .lineTo(Vector2d(0.0,-41.0))

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


        // 10.
 //       list.add(builder2.build())
        return list
    }

    fun drawOffbounds1() {
        GraphicsUtil.fillRect(Vector2d(12.0, -63.0), 18.0, 18.0, false) // robot against the wall
    }

    fun drawOffbounds2() {
        GraphicsUtil.fillRect(Vector2d(0.0, -24.0), 12.0, 8.0,true) // block against the bridge
    }
}

val Double.toRadians get() = (Math.toRadians(this))