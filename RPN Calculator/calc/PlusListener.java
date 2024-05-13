package calc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlusListener implements ActionListener {
	
	private Internal obj;
	
	/**
	 * Constructor
	 * @param obj Internal object
	 */
	public PlusListener(Internal obj) {
		this.obj = obj;
	}
	
	/**
	 * Call plus method in Internal
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		obj.setPlus();
	}
	
}
