package automat;

public class Rule {
	private Zustand next;
	private String stackVaue;
	private byte action;
	private String actionValue;
	private char inputC;
	
	public Rule(Zustand next, String stackVaue, byte action, String actionValue,char inputC) {
		super();
		this.next = next;
		this.stackVaue = stackVaue;
		this.action = action;
		this.actionValue = actionValue;
		this.inputC = inputC;
	}
	public char getInputC() {
		return inputC;
	}
	public void setInputC(char inputC) {
		this.inputC = inputC;
	}
	public Zustand getNext() {
		return next;
	}
	public void setNext(Zustand next) {
		this.next = next;
	}
	public String getStackVaue() {
		return stackVaue;
	}
	public void setStackVaue(String stackVaue) {
		this.stackVaue = stackVaue;
	}
	public byte getAction() {
		return action;
	}
	public void setAction(byte action) {
		this.action = action;
	}
	public String getActionValue() {
		return actionValue;
	}
	public void setActionValue(String actionValue) {
		this.actionValue = actionValue;
	}
}
