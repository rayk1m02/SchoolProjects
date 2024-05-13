package calc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MinusListener implements ActionListener {

	private Internal obj;
	
	/**
	 * Constructor
	 * @param obj Internal object
	 */
	public MinusListener(Internal obj) {
		this.obj = obj;
	}
	
	/**
	 * Call minus method in Internal
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		obj.setMinus();
	}

}
