TODOS
-----

 * Code and test Subsystems
    * Claw
       * !!FINISH THE HARDWARE!!
       * Yell at the mech team
    * Joystick
       * Figure out what the control scheme should be
    * Vision
       * See below
 * Set up Commands
    * Autonomous
       * Move to the box
       * Grab the box
       * Move to the auto zone
    * Teleoperated
       * End autonomous mode
       * Give drive control to joystick
 * Figure out who the drivers are
    * Work together to create a driving scheme

### Vision ###

Useful Brandon, Ana, Ben, Other Nathan, Rohan

I envision the code as:

```java
public class VisionSubsystem {
	public VisionSubsystem(/* args of some sort */) { ... }
	
	public boolean canSeeBox() { ... }
	public double getBoxDirection() { ... }
	public double getBoxDistance() { ... }
	public void repollCamera() { ... }
}
```

`canSeeBox`, `getBoxDirection`, and `getBoxDistance` should operate on a cached
frame. `repollCamera` should get a new frame, analyze, and cache it. Alternatively:

```java
public class VisionSubsystem {
	public static class BoxParams {
		public double direction, distance;
		// Direction in degrees from the centre, with right being positive
		// Distance in feet
	}

	public VisionSubsystem(/* args of some sort */) { ... }
	
	public BoxParams pollCamera() { ... } // can return null if no box found
}
```