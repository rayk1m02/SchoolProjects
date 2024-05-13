package calc;

public class DState1 extends State {
	
	/*
	 * Calculator Internal  
	 */
	Internal obj;
	
	/*
	 * Constructor 
	 * @param obj Internal object
	 */
	public DState1(Internal obj) {
		this.obj = obj;
	}

	/*
	 * Set operand values
	 * @param obj Internal obj
	 * @param num The digit selected
	 * @return value to display
	 */
	@Override
	public String setVal(Internal obj, String num) {
		obj.decVal1 += num;
		obj.val1 = Double.parseDouble(obj.decVal1);
		return obj.form.format(obj.val1);
	}
	
	/*
	 * Set operator
	 * @param opr The operator selected
	 */
	@Override
	public void setOperator(Operator opr) {
		obj.oper = opr;
		obj.setState(new SecondState(obj));
	}

	/*
	 * Execute the operation. 
	 * Since its first decimal state, do nothing
	 * @return the first operand
	 */
	@Override
	public double execute(double val1, double val2) {
		return obj.val1;
	}

	/*
	 * Set the decimal in calculator display
	 * @return the String val 
	 */
	@Override
	public String setDec() {
		return obj.decVal1;
	}

	/*
	 * Set plus/minus for val1
	 * @return the string of val1 after operation
	 */
	@Override
	public String setPM() {
		obj.val1 *= -1;
		obj.decVal1 = obj.form.format(obj.val1);
		return obj.form.format(obj.val1);
	}

}
