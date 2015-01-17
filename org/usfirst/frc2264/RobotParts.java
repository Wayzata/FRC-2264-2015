package org.usfirst.frc2264;

/**
 * RobotParts is a class of constants that represent the parts on the robot. 
 */
public final class RobotParts {
	public static final class CAN_MOTORS {
		public static final int FRONT_LEFT = 1;
		public static final int REAR_LEFT = 2;
		// TODO Change these once this is actually in the robot.
		public static final int FRONT_RIGHT = 4;
		public static final int REAR_RIGHT = 6;
		public static final int LIFT = 3;
		public static final int CLAW = 5;
	};
	public static final class PWM_MOTORS {
		public static final int FRONT_LEFT = 0;
		public static final int REAR_LEFT = 1;
		public static final int FRONT_RIGHT = 2;
		public static final int REAR_RIGHT = 3;
		public static final int LIFT = 4;
		public static final int CLAW = 5;
	};
	public static final class CONTROLLERS {
		public static final int JOYSTICK = 1;
		public static final int SLIDER = 2;
	};
	public static final int[] ANALOG = new int[] {
		0, 1, 2, 3
	};
}
