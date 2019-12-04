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
    private val constraints = DriveConstraints(48.0, 50.0, 0.0, 180.0.toRadians, 180.0.toRadians, 0.0)
    private val max_constraints = DriveConstraints(48.0, 50.0, 0.0, 180.0.toRadians, 180.0.toRadians, 0.0)


    fun createTrajectory(): ArrayList<Trajectory> {
        return buildSplineTrajectory()

        //return buildFoundaton2Trajectory()

        // return buildStrafeTrajectory()

        // return buildFoundatonTrajectory()
    }

    fun drawOffbounds1() {
        GraphicsUtil.fillRect(Vector2d(0.0, -63.0), 18.0, 18.0, false) // robot against the wall
    }

    fun drawOffbounds2() {
        GraphicsUtil.fillRect(Vector2d(0.0, -24.0), 12.0, 8.0,true) // block against the bridge
        GraphicsUtil.fillFoundationRect(Vector2d(60.0, -45.0), 34.5, 18.5)
    }

    fun buildFoundatonTrajectory(): ArrayList<Trajectory> {
        val list = ArrayList<Trajectory>()
        val myPose = Pose2d(15.0, -63.0, Math.PI/2)
        var builder0 = TrajectoryBuilder(myPose, constraints)
        builder0.splineTo(Pose2d(53.0, -31.0, Math.PI/2))
            .reverse()
            .splineTo( Pose2d(35.0, -56.0, 0.0))
            .splineTo( Pose2d(5.0, -40.0, 0.0))
        list.add(builder0.build())

        return list
    }

    fun buildFoundaton2Trajectory(): ArrayList<Trajectory> {
        val list = ArrayList<Trajectory>()
        val myPose = Pose2d(61.0, -36.0, Math.PI)
        var builder0 = TrajectoryBuilder(myPose, max_constraints)
        builder0.splineTo(Pose2d(0.0, -40.0, Math.PI))
            .lineTo(Vector2d(-27.0,-40.0))
            .strafeTo(Vector2d(-27.0,-36.0))
            .reverse()
            .splineTo(Pose2d(0.0, -42.0, Math.PI))
            .splineTo(Pose2d(51.0, -45.0, Math.PI/2))
            .addMarker (4.0, { println("test marker 0....")})
            .addMarker (Vector2d(49.0,36.0), { println("test marker spatial....")})

        list.add(builder0.build())

        builder0 = TrajectoryBuilder(Pose2d(53.0, -31.0, Math.PI/2), constraints)
        builder0
            .reverse()
            .splineTo( Pose2d(35.0, -58.0, 0.0))
            .splineTo( Pose2d(5.0, -40.0, 0.0))

            //.lineTo( Vector2d(5.0, -40.0))
        list.add(builder0.build())

        return list
    }

    fun buildSplineTrajectory(): ArrayList<Trajectory> {
        val list = ArrayList<Trajectory>()
        val startPose = Pose2d(-33.0, -63.0, 180.0.toRadians)

        var builder1 = TrajectoryBuilder(startPose, constraints)

        builder1
 //           .strafeTo( Vector2d(-33.0, -36.0))
 //           .lineTo(Vector2d(-59.0,-36.0))
            .strafeTo( Vector2d(-59.0, -36.0))
            .addMarker { println("grab stone 1") }
            //builder1.splineTo(Pose2d(-59.0,-36.0, 180.0.toRadians))

        // 1. get first stone
        list.add(builder1.build())

        builder1 = TrajectoryBuilder(Pose2d(-59.0, -36.0, 180.0.toRadians), constraints)

        builder1
            .reverse()
            .splineTo(Pose2d(0.0, -41.0, 180.0.toRadians))
            .splineTo(Pose2d(49.0,-35.0, 180.0.toRadians))
            .addMarker { println("release stone 1") }

        // 2. place first stone
        //list.add(builder1.build())

        //builder1 = TrajectoryBuilder(Pose2d(49.0, -35.0, 180.0.toRadians), constraints)

        builder1
            .reverse()
            .splineTo(Pose2d(0.0,-38.0, 180.0.toRadians))
            .splineTo(Pose2d(-35.0,-36.0, 180.0.toRadians))
            .addMarker { println("grab stone 2") }

        // 3. get second stone
        list.add(builder1.build())

        builder1 = TrajectoryBuilder(Pose2d(-35.0, -36.0, 180.0.toRadians), constraints)
        builder1.reverse()
            .splineTo(Pose2d(0.0,-41.0, 180.0.toRadians))
            .splineTo(Pose2d(55.0,-35.0, 180.0.toRadians))
            .addMarker { println("release stone 2") }

        // 4. place second stone
//        list.add(builder1.build())

//        builder1 = TrajectoryBuilder(Pose2d(55.0, -35.0, 180.0.toRadians), constraints)
        builder1
            .reverse()
            .splineTo(Pose2d(0.0,-38.0, 180.0.toRadians))
            .lineTo(Vector2d(-18.0,-41.0))
            .strafeTo(Vector2d(-18.0,-36.0))
            .addMarker { println("grab stone 3") }

        // 5. get 3rd stone
        list.add(builder1.build())

        builder1 = TrajectoryBuilder(Pose2d(-18.0, -36.0, 180.0.toRadians), constraints)
        builder1.reverse()
            .strafeTo(Vector2d(-18.0,-42.0))
            .lineTo(Vector2d(0.0,-42.0))
            .splineTo(Pose2d(61.0,-35.0, 180.0.toRadians))
            .addMarker { println("release stone 3") }

        // 6. place 3rd stone
 //       list.add(builder1.build())

 //       builder1 = TrajectoryBuilder(Pose2d(61.0, -35.0, 180.0.toRadians), constraints)
        builder1.reverse()
            .splineTo(Pose2d(0.0,-38.0, 180.0.toRadians))
            .lineTo(Vector2d(-43.0,-41.0))
            .strafeTo(Vector2d(-43.0,-35.0))
            .addMarker { println("grab stone 4") }

        // 7. get 4th stone
        list.add(builder1.build())

        builder1 = TrajectoryBuilder(Pose2d(-43.0, -35.0, 180.0.toRadians), constraints)
        builder1.reverse()
            .splineTo(Pose2d(0.0,-41.0, 180.0.toRadians))
            .splineTo(Pose2d(55.0,-35.0, 180.0.toRadians))
            .addMarker { println("release stone 4") }

        // 8. place 4th stone
        //list.add(builder1.build())

        //var myPose = Pose2d(55.0, -35.0, Math.PI)
        //var builder0 = TrajectoryBuilder(myPose, max_constraints)
        //builder0
            builder1.reverse()
                .splineTo(Pose2d(0.0, -40.0, Math.PI))
            .lineTo(Vector2d(-27.0,-40.0))
            .strafeTo(Vector2d(-27.0,-36.0))
            .reverse()
            .splineTo(Pose2d(0.0, -42.0, Math.PI))
            .splineTo(Pose2d(51.0, -45.0, Math.PI/2))
 //           .addMarker (4.0, { println("release stone ...")})
            .reverse()
            .lineTo(Vector2d(51.0, -31.0))
            .addMarker ({ println("lock foundation ...")})

        list.add(builder1.build())

        var myPose = Pose2d(51.0, -31.0, Math.PI/2)
        var builder0 = TrajectoryBuilder(myPose, max_constraints)

        builder0.reverse()
            .splineTo( Pose2d(35.0, -58.0, 0.0))
            .addMarker ({ println("release foundation ...")})
            .splineTo( Pose2d(5.0, -40.0, 0.0))
//            .addMarker (Vector2d(49.0,36.0), { println("test marker spatial....")})

        // 9. get and place 5th stone and move foundation and park
        list.add(builder0.build())

        return list
    }

    fun buildStrafeTrajectory():ArrayList<Trajectory> {
        val list = ArrayList<Trajectory>()
        val startPose = Pose2d(-33.0, -63.0, 180.0.toRadians)

        var builder1 = TrajectoryBuilder(startPose, constraints)

        builder1.strafeTo( Vector2d(-33.0, -36.0))
            .lineTo(Vector2d(-59.0,-36.0))

        // 1.
        list.add(builder1.build())

        builder1 = TrajectoryBuilder(Pose2d(-59.0, -36.0, 180.0.toRadians), constraints)

        builder1
            .strafeTo(Vector2d(-59.0,-42.0))
            .reverse()
            .lineTo(Vector2d(49.0,-42.0))
            .strafeTo(Vector2d(49.0,-35.0))

        // 2.
        list.add(builder1.build())

        builder1 = TrajectoryBuilder(Pose2d(49.0, -35.0, 180.0.toRadians), constraints)

        builder1
            .strafeTo(Vector2d(49.0, -40.0))
            .lineTo(Vector2d(-35.0, -40.0))
            .strafeTo(Vector2d(-35.0, -36.0))

        // 3.
        list.add(builder1.build())

        builder1 = TrajectoryBuilder(Pose2d(-35.0, -36.0, 180.0.toRadians), constraints)
        builder1.reverse()
            .strafeTo(Vector2d(-35.0, -42.0))
            .lineTo(Vector2d(55.0,-42.0))
            .strafeTo(Vector2d(55.0, -35.0))

        // 4.
        list.add(builder1.build())

        builder1 = TrajectoryBuilder(Pose2d(55.0, -35.0, 180.0.toRadians), constraints)
        builder1
            .strafeTo(Vector2d(55.0, -40.0))
            .lineTo(Vector2d(-18.0, -40.0))

        // 5.
        list.add(builder1.build())

        builder1 = TrajectoryBuilder(Pose2d(-18.0, -36.0, 180.0.toRadians), constraints)
        builder1.reverse()
            .strafeTo(Vector2d(-18.0,-42.0))
            .lineTo(Vector2d(61.0,-42.0))
            .strafeTo(Vector2d(61.0,-35.0))

        // 6.
        list.add(builder1.build())

        builder1 = TrajectoryBuilder(Pose2d(61.0, -35.0, 180.0.toRadians), constraints)
        builder1
            .splineTo(Pose2d(0.0,-40.0, 180.0.toRadians))
            .lineTo(Vector2d(-27.0,-40.0))
            .strafeTo(Vector2d(-27.0,-36.0))

        // 7.
        list.add(builder1.build())

        builder1 = TrajectoryBuilder(Pose2d(-27.0, -36.0, 180.0.toRadians), constraints)
        builder1
            .reverse()
            .strafeTo(Vector2d(-27.0, -42.0))
            .lineTo(Vector2d(51.0,-42.0))
            .strafeTo(Vector2d(51.0, -33.0))

            .splineTo(Pose2d(55.0,-40.0, 90.0.toRadians))
            .lineTo(Vector2d(55.0, -31.0))
            .lineTo(Vector2d(55.0, -28.0))
            .splineTo(Pose2d(44.0,-50.0, 0.0.toRadians))
            .strafeTo(Vector2d(0.0, -42.0))
//            .splineTo(Pose2d(0.0,-36.0, 0.0.toRadians))
//
//        // 8.
        list.add(builder1.build())

        return list
    }
}

val Double.toRadians get() = (Math.toRadians(this))

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