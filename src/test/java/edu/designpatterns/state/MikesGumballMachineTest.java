package edu.designpatterns.state;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MikesGumballMachineTest {
	private final TestDevice device = new TestDevice();
	private GumballMachine gumballMachine;

	@BeforeEach
	public void setup() {
		gumballMachine = new GumballMachine(device);
	}

	// Sold Out
	@Test
	public void initialConditionsEmptyMachineShouldDisplaySoldOutStartMessage() {
		assertDisplay(Messages.SO_START);
	}

	@Test
	public void initialConditionsEmptyMachineShouldNotDispenseQuarter() {
		assertThat(device.hasQuarterEjected()).isFalse();
	}

	@Test
	public void insertQuarterOnEmptyMachineShouldDisplaySoldOutQuarterMessage() {
		gumballMachine.insertQuarter();
		assertDisplay(Messages.SO_QUART);
	}

	@Test
	public void insertQuarterOnEmptyMachineShouldEjectQuarter() {
		gumballMachine.insertQuarter();
		assertThat(device.hasQuarterEjected()).isTrue();
	}

	@Test
	public void ejectQuarterOnEmptyMachineShouldDisplaySoldOutEjectMessage() {
		gumballMachine.ejectQuarter();
		assertDisplay(Messages.SO_EJECT);
	}

	@Test
	public void turnCrankOnEmptyMachineShouldDisplaySoldOutCrankMessage() {
		gumballMachine.turnCrank();
		assertDisplay(Messages.SO_CRANK);
	}

	// No Quarter
	@Test
	public void atStartOnRefilledMachineShouldDisplayNoQuarterStartMessage() {
		refillMachine();
		assertDisplay(Messages.NQ_START);
	}

	@Test
	public void insertQuarterOnRefilledMachineShouldDisplayHasQuarterStartMessage() {
		refillMachine();
		gumballMachine.insertQuarter();
		assertDisplay(Messages.NQ_QUART);
	}

	@Test
	public void ejectQuarterOnRefilledMachineShouldDisplayNoQuarterEjectMessage() {
		refillMachine();
		gumballMachine.ejectQuarter();
		assertDisplay(Messages.NQ_EJECT);
	}

	@Test
	public void ejectQuarterOnRefilledMachineShouldNotEjectQuarter() {
		refillMachine();
		gumballMachine.ejectQuarter();
		assertThat(device.hasQuarterEjected()).isFalse();
	}

	@Test
	public void turnCrankOnRefilledMachineShouldDisplayNoQuarterCrankMessage() {
		refillMachine();
		gumballMachine.turnCrank();
		assertDisplay(Messages.NQ_CRANK);
	}

	@Test
	public void refillOnRefilledMachineShouldNotChangeNoQuarterStartMessage() {
		refillMachine();
		gumballMachine.refill();
		assertDisplay(Messages.NQ_START);
	}

	// Has Quarter
	@Test
	public void atStartWithQuarterShouldDisplayHasQuarterStartMessage() {
		hasQuarterState();
		assertDisplay(Messages.HQ_START);
	}

	@Test
	public void insertQuarterWithQuarterShouldDisplayHasQuarterQuarterMessage() {
		hasQuarterState();
		gumballMachine.insertQuarter();
		assertDisplay(Messages.HQ_QUART);
	}

	@Test
	public void insertQuarterWithQuarterShouldEjectQuarter() {
		hasQuarterState();
		gumballMachine.insertQuarter();
		assertThat(device.hasQuarterEjected()).isTrue();
	}

	@Test
	public void ejectQuarterWithQuarterShouldDisplayHasQuarterEjectMessage() {
		hasQuarterState();
		gumballMachine.ejectQuarter();
		assertDisplay(Messages.HQ_EJECT);
	}

	@Test
	public void ejectQuarterWithQuarterShouldEjectQuarter() {
		hasQuarterState();
		gumballMachine.ejectQuarter();
		assertThat(device.hasQuarterEjected()).isTrue();
	}

	@Test
	public void turnCrankWithQuarterShouldDisplayHasQuarterCrankMessage() {
		hasQuarterState();
		gumballMachine.turnCrank();
		assertDisplay(Messages.HQ_CRANK);
	}

	@Test
	public void turnCrankWithQuarterShouldDispenseGumball() {
		hasQuarterState();
		int count = device.getCount();
		gumballMachine.turnCrank();
		assertThat(device.getCount()).isEqualTo(count - 1);
	}

	@Test
	public void refillWithQuarterShouldNotChangeHasQuarterStartMessage() {
		hasQuarterState();
		gumballMachine.refill();
		assertDisplay(Messages.HQ_START);
	}

	@Test
	public void sellingGumballShouldDisplayNoQuarterStartMessage() {
		refillMachine();
		sellGumball();
		assertDisplay(Messages.NQ_START);
	}

	// Sell Out
	@Test
	public void insertQuarterWhenSoldOutDisplaySoldOutStartMessage() {
		sellOutState();
		assertDisplay(Messages.SO_START);
	}

	@Test
	public void sellingOutShouldReturnQuarter() {
		sellOutState();
		assertThat(device.hasQuarterEjected()).isTrue();
	}

	@Test
	public void insertQuarterWhenSoldOutShouldDisplaySoldOutQuarterMessage() {
		sellOutState();
		gumballMachine.insertQuarter();
		assertDisplay(Messages.SO_QUART);
	}

	@Test
	public void ejectQuarterWhenSoldOutShouldDisplaySoldOutEjectMessage() {
		sellOutState();
		gumballMachine.ejectQuarter();
		assertDisplay(Messages.SO_EJECT);
	}

	@Test
	public void turnCrankWhenSoldOutShouldDisplaySoldOutCrankMessage() {
		sellOutState();
		gumballMachine.turnCrank();
		assertDisplay(Messages.SO_CRANK);
	}

	@Test
	public void refillWhenSoldOutShouldDisplayNoQuarterStartMessage() {
		sellOutState();
		gumballMachine.refill();
		assertDisplay(Messages.NQ_START);
	}

	// Error checks
	@Test
	public void cyclingQuarterOnEmptyMachineShouldDisplaySoldOutEjectMessage() {
		cycleQuarter();
		assertDisplay(Messages.SO_EJECT);
	}

	@Test
	public void turningCrankAfterGumballSoldShouldDisplayNoQuarterCrankMessage() {
		refillMachine();
		sellGumball();
		gumballMachine.turnCrank();
		assertDisplay(Messages.NQ_CRANK);
	}

	@Test
	public void cyclingQuarterShouldEjectQuarter() {
		refillMachine();
		cycleQuarter();
		assertThat(device.hasQuarterEjected()).isTrue();
	}

	@Test
	public void insertQuarterAfterCyclingQuarterShouldDisplayHasQuarterStartMessage() {
		refillMachine();
		cycleQuarter();
		gumballMachine.insertQuarter();
		assertDisplay(Messages.HQ_START);
	}

	@Test
	public void refillingMidCycleShouldNotEffectHasQuarter() {
		refillMachine();
		gumballMachine.insertQuarter();
		refillMachine();
		assertDisplay(Messages.HQ_START);
	}

	// Helper methods

	private void assertDisplay(String message) {
		assertThat(device.getLine()).isEqualTo(message);
	}

	private void refillMachine() {
		device.addGumballs(1);
		gumballMachine.refill();
	}

	private void hasQuarterState() {
		refillMachine();
		gumballMachine.insertQuarter();
	}

	private void sellGumball() {
		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
	}

	private void cycleQuarter() {
		gumballMachine.insertQuarter();
		gumballMachine.ejectQuarter();
	}

	private void sellOutState() {
		refillMachine();

		while (0 < device.getCount()) {
			sellGumball();
		}

		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
	}
}
