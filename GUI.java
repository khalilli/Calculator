import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.rmi.RemoteException;

public class GUI implements ActionListener {
	//object of the Calculator interface
	Calculator calc;

    static JFrame frame;
    static JTextField textField;
    JButton[] numberButtons = new JButton[10];
    JButton[] functionButtons = new JButton[6];
    JButton addButton, subButton, mulButton, divButton;
    JButton equButton, clrButton;
    JPanel panel;

    //Font design
    Font myFont = new Font(Font.DIALOG, Font.BOLD|Font.ITALIC , 30);
    
    //String num1, num2, op : string values to store the numbers and operator
    //                        from the textField
    String num1, op, num2;
    //int n1, n2 : integers to store the converted values of num1 and num2
    //             and pass them to methods of Calculator interface
    //int res: integer value to store the result of calculation
    int n1, n2, res;
    
    GUI(){
    	
    	num1 = num2 = op = "";
    	
        frame = new JFrame("Calc");
        textField = new JTextField();
        
        //JFrame design
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(300,10,500,700);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setTitle("Calculator");
        frame.setBackground(new Color(110, 13, 37));

        //JTextField design
        textField.setEditable(false);
        textField.setBounds(50,30,400,80);
        textField.setFont(myFont);
        textField.setEditable(false);
        textField.setBackground(new Color(226, 209, 209));

        //creating function buttons
        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        equButton = new JButton("Calculate");
        clrButton = new JButton("Clr");

        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = clrButton;
        functionButtons[5] = equButton;

        for (int i = 0; i < 6; i++){
            functionButtons[i].addActionListener(this);
            //JButton design for function buttons
            functionButtons[i].setFont(myFont);
            functionButtons[i].setFocusable(false);
            functionButtons[i].setBackground(new Color(167, 152, 139));
        }

        for (int i = 0; i < 10; i++) {
        	//creating number buttons
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            //JButton design for number buttons
            numberButtons[i].setFont(myFont);
            numberButtons[i].setFocusable(false);
            numberButtons[i].setBackground(new Color(205, 159, 161));
        }

        clrButton.setBackground(new Color(195, 142, 144));
        equButton.setBounds(50,550,400,80);

        panel = new JPanel();
        //JPanel design
        panel.setBounds(50, 130, 400, 400);
        panel.setLayout(new GridLayout(4, 4, 10, 10));
        panel.setBackground(new Color(218, 209, 199));

        //adding elements to the panel
        panel.add(addButton);
        panel.add(subButton);
        panel.add(mulButton);
        panel.add(divButton);
        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(numberButtons[0]);
        panel.add(clrButton);

        //adding elements to the frame
        frame.add(equButton);
        frame.add(panel);
        frame.add(textField);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent actionEvent) {
    	//returns the command string associated with the action
    	String strAct = actionEvent.getActionCommand();
    	 
    	//if user enters the number 
    	if ((strAct.charAt(0) >= '0' && strAct.charAt(0) <= '9')) {
    		
			if (op.equals("")) { num1 = num1 + strAct; }
			else { num2 = num2 + strAct; }

			textField.setText(num1 + op + num2);
		}
    	//if user presses the 'Calculate' button
    	else if (actionEvent.getSource() == equButton) {

    		//Store converted numbers from String to int 
    		n1 = Integer.parseInt(num1);
    		n2 = Integer.parseInt(num2);
    		if (op.equals("+")) {
    			try {
    				//Pass inreger values to the methods of Calculator interface
    				res = calc.sum(n1, n2);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
       			textField.setText(num1 + op + num2 + "=" + res);
       			//the first number takes the value of the result for later calculations
        		num1 = Integer.toString(res);
        		op = num2 = "";
    		}
			else if (op.equals("-")) {
				try {
					res = calc.subtraction(n1, n2);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				if (res < 0) {
					//error message in case of negative result 
	    			textField.setText("Only positive integers!");
	    			num1 = num2 = op = "";
				}
				else {
					textField.setText(num1 + op + num2 + "=" + res);
					num1 = Integer.toString(res);	
					op = num2 = "";
				}
			}
			else if (op.equals("*")) {
    			try {
    				res = calc.product(n1, n2);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
    			textField.setText(num1 + op + num2 + "=" + res);
				num1 = Integer.toString(res);	
				op = num2 = "";
			}
    		//case of division
			else {
				if(n2 == 0) {
					//error message in case of division by zero 
					textField.setText("Cannot divide by Zero!");
	    			num1 = num2 = op = "";
				}
				else {
					try {
						res = calc.division(n1, n2);
					} catch (RemoteException e) {
						e.printStackTrace();
					}
					textField.setText(num1 + op + num2 + "=" + res);
					num1 = Integer.toString(res);	
					op = num2 = "";
				}
			}
    	}	
    	//if user press 'Clr' button to clear the textField expression
    	else if (actionEvent.getSource() == clrButton) {
    		num1 = num2 = op = "";
    		textField.setText(num1 + op + num2);
    	}
    	//if user enters an operator 
    	else {
			if (op.equals("") || num2.equals("")) {
				if(!num1.equals(""))
					op = strAct;
			}
				textField.setText(num1 + op + num2); 
		}
    }
}
