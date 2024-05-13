// Ray Kim

package calc;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NumListener implements ActionListener {

	private Internal obj;
	
	private int num;
	
	/**
	 * Constructor
	 * @param obj Internal object
	 * @param num Calculator number
	 */
	public NumListener(Internal obj, int num)  {
		this.obj = obj;
		this.num = num;
	}
	
	/**
	 * Call number method in Internal
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {	
		obj.setNum(num);
	}
	
}