package calc;

import java.util.*;

public class Internal {
	
	/*
	 * Calculator face
	 */
	private CalculatorFace face;
	
	/*
	 * Stack
	 */
	Stack<String> stack;
	
	/*
	 * Number
	 */
	private String num;
	
	/*
	 * Check if operator was pressed before
	 */
	boolean op;
	
	/**
	 * Constructor
	 * @param face Calculator face
	 */
	public Internal(CalculatorFace face) {
		this.face = face;
		stack = new Stack<>();
		num = "";
		op = false;
	}
	
	// Before doing an operation, push the most recent number onto the stack
	// Accounts for operator with no operands error 
	public boolean push() {
		if (stack.isEmpty()) return true;
		if (num != "") stack.push(num);
		return false;
	}
	
	/*
	 * Display the operation result on calculator face
     * Push the result onto stack and clear num variable
     * @param i Number to display and store
	 */
	public void execute(int i) {
		face.writeToScreen(Integer.toString(i));
		num = Integer.toString(i);
		stack.push(num);
		num = "";
		op = true;
	}
	
	/*
	 * Set the number pressed
	 * @param i The number
	 */
	public void setNum(int i) {
		Integer.toString(i);
		num = num + i;
		face.writeToScreen(num);
	}
	
	/*
	 * Clear everything
	 */
	public void setClear() {
		stack.clear();
		face.writeToScreen("");
		num = "";
	}
	
	/*
	 * Use the plus operation
	 */
	public void setPlus() {
		if (push()) return;
		int var = Integer.parseInt(stack.pop()) + Integer.parseInt(stack.pop());
		execute(var);
	}
	
	/*
	 * Use the minus operation
	 */
	public void setMinus() {
		if (push()) return;
		int second = Integer.parseInt(stack.pop());
		int first = Integer.parseInt(stack.pop());
		execute(first - second);
	}

	/*
	 * Use the multiplication operation
	 */
	public void setMultiply() {
		if (push()) return;
		execute(Integer.parseInt(stack.pop()) * Integer.parseInt(stack.pop()));
	}
	
	/*
	 * Use the division operation
	 */
	public void setDivide() {
		if (push()) return;
		int denominator = Integer.parseInt(stack.pop());
		int numerator = Integer.parseInt(stack.pop());
		execute(numerator / denominator);
	}
	
	/*
	 * To separate numbers
	 */
	public void setDecimal() {
		face.writeToScreen(".");
		stack.push(num);
		num = "";
	}
	
	/*
	 * Negate the number depending on cases
	 * If decimal came before, pull from the stack and negate
	 */
	public void setPlusMinus() {
		if (num != "") {
			num = Integer.toString((-1) * Integer.parseInt(num));
			face.writeToScreen(num);
			stack.push(num);
			num = "";
		} else if (num == "" && !stack.isEmpty()){
			int temp = (-1) * Integer.parseInt(stack.pop());
			face.writeToScreen(Integer.toString(temp));
			stack.push(Integer.toString(temp));
		}
	}
	
}
