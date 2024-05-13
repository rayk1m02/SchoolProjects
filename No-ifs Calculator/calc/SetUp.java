package calc;

/**
 * SetUp
 * 
 * Class to set up and start the calculator, plus
 * facilities for test-driving the calculator.
 *
*/
public class SetUp {

	/**
	 * Method for initializing the calculator internals and
	 * connecting them to the calculator face.
	 * @param face The component representing the user interface of 
	 * the calculator. 
	 */
	public static void setUpCalculator(CalculatorFace face) {
		
		/*
		 * Connect Internal to CalculatorFace
		 */
		Internal obj = new Internal(face);
		
		/*
		 * NumActionListeners
		 */
		for (int i = 0; i < 10; i++) {
			face.addNumberActionListener(i, new NumListener(obj, i));
		}
		
		// Action Listeners
		
		face.addActionListener('+', (e) -> obj.setOper((val1, val2) -> val1 + val2));
		
		face.addActionListener('-', (e) -> obj.setOper((val1, val2) -> val1 - val2));
		
		face.addActionListener('/', (e) -> obj.setOper((val1, val2) -> val1 / val2));
		
		face.addActionListener('*', (e) -> obj.setOper((val1, val2) -> val1 * val2));
		
		face.addActionListener('=', (e) -> obj.execute());
		
		face.addActionListener('C', (e) -> obj.clear());
		
		face.addActionListener('.', (e) -> obj.setDec());
		
		face.addActionListener('±', (e) -> obj.setPM());
		
	}
	
	
	/**
	 * This main method is for your testing of your calculator.
	 * It will *not* be used during grading. Any changes you make
	 * to this method will be ignored at grading.
	 */
	public static void main(String[] args) {
		setUpCalculator(new PlainCalculatorFace());
	}

}
