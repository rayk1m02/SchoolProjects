// Ray Kim

package calc;

/**
 * SetUp
 * 
 * Class to set up and start the calculator, plus
 * facilities for test-driving the calculator.
 *
 * @author Thomas VanDrunen
 * CS 245, Wheaton College
 * June 27, 2014
*/
public class SetUp {

	/**
	 * Method for initializing the calculator internals and
	 * connecting them to the calculator face.
	 * @param face The component representing the user interface of 
	 * the calculator. 
	 */
	public static void setUpCalculator(CalculatorFace face) {
		
		Internal obj = new Internal(face);
		
		for (int i=0; i<10; i++) {
			face.addNumberActionListener(i, new NumListener(obj, i));
		}
		
		face.addActionListener('=', new EqualListener(obj));
		face.addActionListener('C', new ClearListener(obj));
		face.addActionListener('+', new PlusListener(obj));
		face.addActionListener('-', new MinusListener(obj));
		face.addActionListener('*', new MultiplyListener(obj));
		face.addActionListener('/',  new DivideListener(obj));
		face.addActionListener('.', new DecimalListener(obj));
		face.addActionListener('±', new PlusMinusListener(obj));
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