package edu.designpatterns.state;

public interface GumballHardwareDevice {
	/**
	 * This method will display the message parameter on the gumball machine's
	 * hardware display.
	 * 
	 * @param message
	 */
	void displayLine(String message);

	/**
	 * This method will force the gumball machine's hardware to release a
	 * gumball. If it was successfully able to dispense a gumball, it returns
	 * true, otherwise it returns false.
	 * 
	 * @return boolean {true on success, otherwise false}
	 */
	boolean dispenseGumball();

	/**
	 * This method will force the gumball machine hardware to release a quarter
	 * to the coin return slot. Calling this multiple times will release
	 * multiple quarters.
	 */
	void dispenseQuarter();
}
