package calc;

public abstract class State {
	
	/*
	 * Set the operand 
	 * @param obj Internal object
	 * @param num The digit selected
	 * @return String of val to display
	 */
	public abstract String setVal(Internal obj, String num);
	
	/*
	 * Set the operator
	 * @param opr The operator selected
	 */
	public abstract void setOperator(Operator opr);
	
	/*
	 * Execute the operation
	 * @param val1 Operand1
	 * @param val2 Operand2
	 * @return Value after execution
	 */
	public abstract double execute(double val1, double val2);
	
	/*
	 * Set the decimal
	 */
	public abstract String setDec();
	
	/*
	 * Set P/M
	 */
	public abstract String setPM();
	
}
