package calc;

public class SecondState extends State {

	/*
	 * Calculator Internal  
	 */
	Internal obj;
	
	/*
	 * Constructor
	 * @param obj Internal object
	 */
	public SecondState(Internal obj) {
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
		obj.val2 = obj.val2 * 10 + Double.parseDouble(num);
		return obj.form.format(obj.val2);
	}
	
	/*
	 * Set operator
	 * @param opr The operator selected
	 */
	@Override
	public void setOperator(Operator opr) {
		// execute the operation (it should set up val1, val2, and state)
		double res = obj.state.execute(obj.val1, obj.val2);
		// keep it at second state because execute method changes it to first
		obj.setState(new SecondState(obj));
		obj.display(Double.toString(res));
		obj.oper = opr;
	}

	/*
	 * Execute the operation 
	 * @return the first operand since its first state
	 */
	public double execute(double val1, double val2) {
		double res = obj.oper.execute(val1, val2);
		// set it into val1 in case operator is selected again
		obj.val1 = res;
		obj.val2 = 0.0;
		obj.setState(new FirstState(obj));
		return res;
	}

	/*
	 * Set the decimal in calculator display
	 * @return the String val 
	 */
	@Override
	public String setDec() {
		obj.decVal2 = Integer.toString((int)obj.val2) + ".";
		obj.setState(new DState2(obj));
		return Double.toString(obj.val2);
	}

	/*
	 * Set plus/minus for val1
	 * @return the string of val1 after operation
	 */
	@Override
	public String setPM() {
		obj.val2 *= -1;
		return obj.form.format(obj.val2);
	}

}