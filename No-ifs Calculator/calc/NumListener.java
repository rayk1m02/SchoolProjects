package calc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumListener implements ActionListener {
	
	/*
	 * Internal object
	 */
	Internal obj;
	
	/*
	 * Digit selected
	 */
	String num;
	
	/*
	 * Constructor
	 * @param obj Calculator Internal
	 * @param num Digit selected
	 */
	public NumListener(Internal obj, int num) {
		this.obj = obj;
		this.num = Integer.toString(num);
	}

	/*
	 * Set the val in Internal
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		obj.setVal(num);
	}

}
