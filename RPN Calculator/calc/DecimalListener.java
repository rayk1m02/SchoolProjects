// Ray Kim

package calc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DecimalListener implements ActionListener {

	private Internal obj;
	
	/**
	 * Constructor
	 * @param obj Internal object
	 */
	public DecimalListener(Internal obj) {
		this.obj = obj;
	}
	
	/**
	 * Call decimal method in Internal
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		obj.setDecimal();
	}

}
