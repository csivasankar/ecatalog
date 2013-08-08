package org.cisco.catalog.util;

public class Sort {

	private String feild;
	private Direction dir;

	public Sort(Direction dir, String feild) {
		this.dir = dir;
		this.feild = feild;
	}

	public String getFeild() {
		return feild;
	}

	public void setFeild(String feild) {
		this.feild = feild;
	}

	public Direction getDir() {
		return dir;
	}

	public void setDir(Direction dir) {
		this.dir = dir;
	}

	public static enum Direction {
		ASC, DESC;
	}
}
