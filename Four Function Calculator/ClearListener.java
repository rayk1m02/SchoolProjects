// Ray Kim

package calc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClearListener implements ActionListener {

	private Internal obj;
	
	/**
	 * Constructor 
	 * @param obj Internal object
	 */
	public ClearListener(Internal obj) {
		this.obj = obj;
	}
	
	/**
	 * Call clear method in Internal
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		obj.setClear();
	}

}