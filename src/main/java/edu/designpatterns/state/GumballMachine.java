package edu.designpatterns.state;

public class GumballMachine {
	private final GumballHardwareDevice device;
	private boolean soldOut = true;
	private boolean hasQuarter = false;

	public GumballMachine(GumballHardwareDevice device) {
		this.device = device;
		device.displayLine(Messages.SO_START);
	}

	public void insertQuarter() {
		if (soldOut) {
			device.displayLine(Messages.SO_QUART);
			device.dispenseQuarter();
		} else if (hasQuarter) {
			device.displayLine(Messages.HQ_QUART);
			device.dispenseQuarter();
		} else {
			hasQuarter = true;
			device.displayLine(Messages.HQ_START);
		}
	}

	public void ejectQuarter() {
		if (soldOut) {
			device.displayLine(Messages.SO_EJECT);
		} else if (hasQuarter) {
			device.displayLine(Messages.HQ_EJECT);
			device.dispenseQuarter();
			hasQuarter = false;
		} else {
			device.displayLine(Messages.NQ_EJECT);
		}
	}

	public void turnCrank() {
		if (soldOut) {
			device.displayLine(Messages.SO_CRANK);
		} else if (hasQuarter) {
			if (device.dispenseGumball()) {
				device.displayLine(Messages.NQ_START);
			} else {
				soldOut = true;
				device.displayLine(Messages.SO_START);
				device.dispenseQuarter();
			}
			hasQuarter = false;
		} else {
			device.displayLine(Messages.NQ_CRANK);
		}
	}

	public void refill() {
		if (soldOut) {
			device.displayLine(Messages.NQ_START);
			soldOut = false;
		}
	}
}
