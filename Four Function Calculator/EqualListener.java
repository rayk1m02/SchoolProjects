// Ray Kim

package calc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EqualListener implements ActionListener {

	private Internal obj;
	
	/**
	 * Constructor
	 * @param obj Internal object
	 */
	public EqualListener(Internal obj) {
		this.obj = obj;
	}
	
	/**
	 * Call equals method in Internal
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		obj.setEquals();
	}

}