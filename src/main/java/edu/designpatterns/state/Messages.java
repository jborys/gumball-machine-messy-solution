package edu.designpatterns.state;

public class Messages {
	public static String SO_START = "Sorry, the machine is sold out.";
	public static String SO_QUART = "There are no Gumballs, please pick up your Quarter.";
	public static String SO_EJECT = "This is not a Slot machine.";
	public static String SO_CRANK = "There are no Gumballs. :(";

	public static String NQ_START = "Quarter for a Gumball!";
	public static String NQ_QUART = "Turn the Crank for a Gumball!";
	public static String NQ_EJECT = "You haven't inserted a Quarter yet.";
	public static String NQ_CRANK = "You need to pay first.";

	public static String HQ_START = NQ_QUART;
	public static String HQ_QUART = "You can't insert another Quarter";
	public static String HQ_EJECT = "Pick up your Quarter from the tray";
	public static String HQ_CRANK = NQ_START;

	public static String WN_START = "You are a Winner!! Turn the Crank again for another Gumball!";
	public static String WN_QUART = "You don't need to add a Quarter. Turn the Crank for a Gumball!";
	public static String WN_EJECT = "Sorry, you don't get your Quarter back. Turn the Crank for a Gumball!";
	public static String WN_CRANK = HQ_CRANK;
}
