package calc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlusMinusListener implements ActionListener {
	
	private Internal obj;
	
	/**
	 * Constructor
	 * @param obj Internal object
	 */
	public PlusMinusListener(Internal obj) {
		this.obj = obj;
	}
	
	/**
	 * Call plus/minus method in Internal
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		obj.setPlusMinus();
	}

}
