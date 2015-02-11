package org.usfirst.frc2264.misc;

public enum ObjectSize {
	YELLOW_TOTE_LONG(1), YELLOW_TOTE_SHORT(3),
	TRASH_CAN(2);
	
	public final int level;
	private ObjectSize(int level) {
		this.level = level;
	}
}
