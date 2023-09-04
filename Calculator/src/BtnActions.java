import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class BtnActions extends Calc{
	
	public static ActionListener action(JTextField txt) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton btn = (JButton)e.getSource();
				if(!txt.getText().equals(null) && !proceedCalc && !txt.getText().equals(".")) {
					txt.setText(null);
				}
				txt.setText(txt.getText() + btn.getText());
				proceedCalc = true;
			}
		};
		
	}
	
	public static ActionListener action2(JTextField txt, JLabel lbl) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton btn = (JButton)e.getSource();
				operator = btn.getText();
				lbl.setText("(" + operator + ")");
				if(hisCount > 4 || UIFrame.his[hisCount].getText().equals(""))
					addToHistory(txt.getText());

				if(proceedCalc) {					
					if(result != 0) {
						isChain = true;
						calcNum = Double.parseDouble(txt.getText());
						calculate(txt, operator);
						txt.setText(Double.toString(result));
					}
					else {
						result = Double.parseDouble(txt.getText());
					}					
					proceedCalc = false;					
				}
			}
		};		
	}
	
	public static ActionListener action3(JTextField txt, JLabel lbl) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!txt.getText().equals(null)) {
					if(isChain) {
						calcNum = Double.parseDouble(txt.getText());
					}
					else {
						if(calcNum != 0) {
							result = calcNum;
						}
						else {
							calcNum = Double.parseDouble(txt.getText());
						}
					}
					calculate(txt, operator);
					addToHistory(" = " + result);
					txt.setText(Double.toString(result));
					lbl.setText("()");
					calcNum = 0;
					result = 0;
					operator = "";
					proceedCalc = false;
					isChain = false;
					hisCount++;
				}				
			}
		};		
	}
}
