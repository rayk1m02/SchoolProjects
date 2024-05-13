package calc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumListener implements ActionListener {

	private Internal obj;
	
	private int num;
	
	public NumListener(Internal obj, int num) {
		this.obj = obj;
		this.num = num;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		obj.setNum(num);
	}

}
