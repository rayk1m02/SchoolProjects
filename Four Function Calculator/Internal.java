// Ray Kim

package calc;

import java.text.DecimalFormat;

public class Internal {
	
	/**
	 * The Calculator 
	 */
	private CalculatorFace face;
	
	/*
	 * Two operands and operator
	 */
	private String value1, value2, operator;
	
	/**
	 * To format double values
	 */
	private DecimalFormat form;
	
	/**
	 * For Plus/Minus selected
	 */
	private boolean pm;
	
	/**
	 * Constructor for Internal
	 * @param face The Calculator face
	 */
	public Internal(CalculatorFace face) {
		this.face = face;
		this.value1 = "";
		this.value2 = "";
		this.operator = "";
		this.form = new DecimalFormat("#####0.######");
		this.pm = false;
	}
	
	/**
	 * Set the values for operands
	 * If empty operator, add input to value1, else add input to value2
	 * If pm is true, make negative value
	 * @param i The number clicked by user
	 */
	public void setNum(int i) {
		Integer.toString(i);
		if (operator == "") {
			value1 = value1 + i;
			if (pm == true) {	
				value1 = "-" + value1;
				pm = false;
			}
			face.writeToScreen(value1);
		} else {
			value2 = value2 + i;
			if (pm == true) {
				value2 = "-" + value2;
				pm = false;
			}
			face.writeToScreen(value2);
		}
	}
	
	/**
	 * Set decimal when appropriate
	 */
	public void setDecimal() {
		if (value1.contains(".") && value2 == "") {
			return;
		}
		if (value2.contains(".")) {
			return;
		}
		if (!value1.contains(".")) { 
			value1 = value1 + "."; 
			face.writeToScreen(value1);
			return;
		} 
		if (!value2.contains(".")) {
			value2 = value2 + ".";
			face.writeToScreen(value2);
		}
	}
	
	/**
	 * Set operator to addition
	 */
	public void setPlus() {
		if (value1 != "" && value2 != "") {		
			value1 = this.execute(operator);	
			value2 = "";
		}
		operator = "+";
	}
	
	/**
	 * Set operator to subtraction
	 */
	public void setMinus() {
		if (value1 != "" && value2 != "") {		 
			value1 = this.execute(operator);	
			value2 = "";
		}
		operator = "-";
	}
	
	/**
	 * Set operator to multiplication
	 */
	public void setMultiply() {
		if (value1 != "" && value2 != "") {		
			value1 = this.execute(operator);	
			value2 = "";
		}
		operator = "*";
	}
	
	/**
	 * Set operator to division
	 */
	public void setDivide() {
		if (value1 != "" && value2 != "") {		
			value1 = this.execute(operator);	
			value2 = "";
		}
		operator = "/";
	}
	
	/**
	 * Make value on screen negative or positive
	 * by multiplying by -1
	 */
	public void setPlusMinus() {
		if (value1 != "" && value2 == "") {
			value1 = form.format(Double.parseDouble(value1) * (-1));
			face.writeToScreen(value1);
			pm = false;
			return;
		}
		pm = true;
	}
	
	/**
	 * Execute as long as there are two operands
	 * Result assigned to value1
	 */
	public void setEquals() {
		if (value1 != "" && value2 != "") {
			value1 = this.execute(operator);
			value2 = "";
			pm = false;
		}
	}
	
	/**
	 * Reset values and operator
	 */
	public void setClear() {
		value1 = "";
		value2 = "";
		operator = "";
		face.writeToScreen("");
	}
	
	/**
	 * Execute the operation
	 * @param operator The operator user selected
	 * @return The resulting value as a String type
	 */
	public String execute(String operator) {
		double k = 0; // double value to hold operation results
		if (operator == "+") {
			k = Double.parseDouble(value1) + Double.parseDouble(value2);
			face.writeToScreen(form.format(k));
			return Double.toString(k);
		} else if (operator == "-") {
			k = Double.parseDouble(value1) - Double.parseDouble(value2);
			face.writeToScreen(form.format(k));
			return Double.toString(k);
		} else if (operator == "*") {
			k = Double.parseDouble(value1) * Double.parseDouble(value2);
			face.writeToScreen(form.format(k));
			return Double.toString(k);
		} else if (operator == "/") {
			k = Double.parseDouble(value1) / Double.parseDouble(value2);
			face.writeToScreen(form.format(k));
			return Double.toString(k);
		}
		return "";
	}

}