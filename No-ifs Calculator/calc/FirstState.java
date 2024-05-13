 package calc;

public class FirstState extends State {

	/*
	 * Calculator Internal  
	 */
	Internal obj;
	
	/*
	 * Constructor
	 * @param obj Internal object
	 */
	public FirstState(Internal obj) {
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
		obj.val1 = obj.val1 * 10 + Double.parseDouble(num);
		return obj.form.format(obj.val1);
	}
	
	/*
	 * Set operator
	 * @param opr The operator selected
	 */
	@Override
	public void setOperator(Operator oper) {
		obj.oper = oper;
		obj.setState(new SecondState(obj));
	}
	
	/*
	 * Execute the operation 
	 * @return the first operand since its first state
	 */
	@Override
	public double execute(double val1, double val2) {
		return val1;
	}

	/*
	 * Set the decimal in calculator display
	 * @return the String val 
	 */
	@Override
	public String setDec() {
		obj.decVal1 = Integer.toString((int)obj.val1) + ".";
		obj.setState(new DState1(obj));
		return Double.toString(obj.val1);
	}

	/*
	 * Set plus/minus for val1
	 * @return the string of val1 after operation
	 */
	@Override
	public String setPM() {
		obj.val1 *= -1;
		return obj.form.format(obj.val1);
	}

}
