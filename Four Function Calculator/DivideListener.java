// Ray Kim

package calc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DivideListener implements ActionListener {

	private Internal obj;
	
	/**
	 * Constructor
	 * @param obj Internal object
	 */
	public DivideListener(Internal obj) {
		this.obj = obj;
	}
	
	/**
	 * Call divide method in Internal
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		obj.setDivide();
	}

}