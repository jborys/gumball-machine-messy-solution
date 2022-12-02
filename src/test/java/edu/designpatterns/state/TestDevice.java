package edu.designpatterns.state;

public class TestDevice implements GumballHardwareDevice {
	private String displayLine;
	private boolean hasQuarterEjected;
	private int numGumballs;

	@Override
	public void displayLine(String line) {
		this.displayLine = line;
	}

	@Override
	public boolean dispenseGumball() {
		numGumballs--;
		return numGumballs >= 0;
	}

	@Override
	public void dispenseQuarter() {
		hasQuarterEjected = true;
	}

	public String getLine() {
		return displayLine;
	}

	public boolean hasQuarterEjected() {
		return hasQuarterEjected;
	}

	public int getCount() {
		return numGumballs;
	}

	public void addGumballs(int count) {
		numGumballs = Math.max(numGumballs, 0) + count;
	}
}
