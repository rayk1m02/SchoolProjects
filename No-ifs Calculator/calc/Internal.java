package calc;

import java.text.DecimalFormat;

public class Internal {
	
	/*
	 * Calculator 
	 */
	public CalculatorFace face;
	
	/*
	 * State of calculator
	 */
	public State state;
	
	/*
	 * Operation selected
	 */
	public Operator oper;
	
	/*
	 * Operand1 and Operand2
	 */
	public double val1, val2;
	
	/*
	 * String Operand1 and Operand2
	 * Aids with setting decimal
	 */
	public String decVal1, decVal2;
	
	/*
	 * To format output 
	 */
	public DecimalFormat form;
	
	/*
	 * Constructor 
	 * @param face Calculator to interact with
	 */
	public Internal(CalculatorFace face) {
		this.face = face;
		this.state = new FirstState(this);
		this.form = new DecimalFormat("#####.#####");
	}

	/*
	 * Set the operator based on state
	 * @param oper the operator selected
	 */
	public void setOper(Operator oper) {
		this.state.setOperator(oper);
	}
	
	/*
	 * Set the val based on state
	 * @param num the digit selected
	 */
	public void setVal(String num) {
		face.writeToScreen(this.state.setVal(this, num));
	}
	
	/*
	 * Set the calculator State
	 * @param state the State to set to
	 */
	public void setState(State state) {
		this.state = state;
	}
	
	/*
	 * Execute the operation based on state
	 */
	public void execute() {
		face.writeToScreen(form.format(
				this.state.execute(val1, val2)));
	}
	
	/*
	 * Display on calculator 
	 */
	public void display(String str) {
		face.writeToScreen(str);
	}
	
	/*
	 * Clear calculator
	 * Reset State
	 */
	public void clear() {
		this.setState(new FirstState(this));
		this.val1 = 0.0; 
		this.val2 = 0.0;
		face.writeToScreen("");
	}
	
	/*
	 * Set decimal to value based on state
	 */
	public void setDec() {
		face.writeToScreen(this.state.setDec());
	}
	
	/*
	 * Set P/M on value based on state
	 */
	public void setPM() {
		face.writeToScreen(this.state.setPM());
	}
	
}