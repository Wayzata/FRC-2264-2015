package org.usfirst.frc2264.misc;

public enum ObjectSize {
	YELLOW_TOTE_LONG(0), YELLOW_TOTE_SHORT(2),
	TRASH_CAN(1);
	
	public final int level;
	private ObjectSize(int level) {
		this.level = level;
	}
}
