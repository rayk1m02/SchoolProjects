package calc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MultiplyListener implements ActionListener {

	private Internal obj;
	
	/**
	 * Constructor
	 * @param obj Internal object
	 */
	public MultiplyListener (Internal obj) {
		this.obj = obj;
	}
	
	/**
	 * Call multiply method in Internal
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		obj.setMultiply();
	}

}
