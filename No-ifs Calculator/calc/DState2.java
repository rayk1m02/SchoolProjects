package calc;

public class DState2 extends State {
	
	/*
	 * Calculator Internal  
	 */
	Internal obj;
	
	/*
	 * Constructor 
	 * @param obj Internal object
	 */
	public DState2(Internal obj) {
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
		obj.decVal2 += num;
		obj.val2 = Double.parseDouble(obj.decVal2);
		return obj.form.format(obj.val2);
	}

	/*
	 * Set operator
	 * @param opr The operator selected
	 */
	@Override
	public void setOperator(Operator opr) {
		double rec = obj.oper.execute(obj.val1,  obj.val2);
		obj.display(obj.form.format(rec));
		obj.val1 = rec;
		obj.val2 = 0.0;
		obj.oper = opr;
		obj.setState(new SecondState(obj));
	}

	/*
	 * Execute the operation as second state
	 * @return the operated value
	 */
	@Override
	public double execute(double val1, double val2) {
		obj.setState(new SecondState(obj));
		return obj.state.execute(obj.val1, obj.val2);
	}

	/*
	 * Set the decimal in calculator display
	 * @return the String val 
	 */
	@Override
	public String setDec() {
		return obj.decVal2;
	}

	/*
	 * Set plus/minus for val1
	 * @return the string of val1 after operation
	 */
	@Override
	public String setPM() {
		obj.val2 *= -1;
		obj.decVal2 = obj.form.format(obj.val2);
		return obj.form.format(obj.val2);
	}

}
