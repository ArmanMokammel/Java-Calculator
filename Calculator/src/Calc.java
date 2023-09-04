import javax.swing.JTextField;

public class Calc {
	
	static double calcNum = 0;
	static double result = 0;
    static int hisCount = 0;
	static boolean proceedCalc = false;
    static boolean isChain = false;
    static String operator;
    
    
    public static void calculate(JTextField input, String op)
	{
		switch(op) {
			case "%":				
				calcNum = Double.parseDouble(input.getText()) / 100;
				input.setText(Double.toString(calcNum));
				calculate(input, operator);
				addToHistory(" = " + result);
				break;
			case "÷":
				result /= Double.parseDouble(input.getText());
				addToHistory(op + calcNum);
				break;
			case "×":
				result *= Double.parseDouble(input.getText());
				addToHistory(op + calcNum);
				break;
			case "-":
				result -= Double.parseDouble(input.getText());
				addToHistory(op + calcNum);
				break;
			case "+":
				result += Double.parseDouble(input.getText());
				addToHistory(op + calcNum);
				break;
		}
	}
	
	public static void addToHistory(String s) {
		if(hisCount > 4) {
			UIFrame.his[0].setText(UIFrame.his[1].getText());
			UIFrame.his[1].setText(UIFrame.his[2].getText());
			UIFrame.his[2].setText(UIFrame.his[3].getText());
			UIFrame.his[3].setText(UIFrame.his[4].getText());
			UIFrame.his[4].setText(s);
			hisCount = 4;
		}
		else {
			UIFrame.his[hisCount].setText(UIFrame.his[hisCount].getText() + s);
		}
	}
}
