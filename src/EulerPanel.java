import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import de.congrace.exp4j.*;

/**
 * This class generates a window that asks for user input.
 * 
 * Draw text fields requesting equations and values in text fields, 
 * as well as a button to calculate and draw a function based on what was entered.
 * 
 * @author Colby McBride
 * @version 1.0.0
 */
public class EulerPanel extends JPanel{


	private static final long serialVersionUID = 1L;
	private JTextField fFunctionField;
	private JTextField gFunctionField;
	private JTextField stepField;
	private JTextField xField;
	private JTextField yField;
	private JTextField tField;
	private int n = -1;
	public static final int SIZE = 550;
	private ArrayList<Double> vals = new ArrayList<Double>();
	private ArrayList<Double> vals1 = new ArrayList<Double>();
	private ArrayList<Double> vals2 = new ArrayList<Double>();
	
	/**
	 * Constructs a panel with text fields asking for input and a button.
	 */
	public EulerPanel() {
		setSize(SIZE, SIZE);
		setLayout(new GridLayout(0,1));
		
		fFunctionField = new JTextField();
		gFunctionField = new JTextField();
		stepField = new JTextField();
		xField = new JTextField();
		yField = new JTextField();
		tField = new JTextField();
		
		JLabel fFunctionLabel = new JLabel("Enter a function f, using only x and y as variables and * to indicate multiplication");
		JLabel gFunctionLabel = new JLabel("Enter a function g, using only x and y as variables and * to indicate multiplication");
		JLabel stepLabel = new JLabel("Enter a time step value h > 0");
		JLabel xLabel = new JLabel("Enter an initial x");
		JLabel yLabel = new JLabel("Enter an initial y");
		JLabel tLabel = new JLabel("Enter a final time value T > 0");
		
		JButton calcButton = new JButton("Calculate and Draw");
		calcButton.addActionListener(new ClickListener());
		
		add(fFunctionLabel);
		add(fFunctionField);
		add(gFunctionLabel);
		add(gFunctionField);
		add(stepLabel);
		add(stepField);
		add(xLabel);
		add(xField);
		add(yLabel);
		add(yField);
		add(tLabel);
		add(tField);
		add(calcButton);
	}

	/**
	 * Performs calculations.
	 * 
	 * Upon a click, it gathers what was entered into the fields and 
	 * tries to perform Euler's method with three different step values.
	 */
	private class ClickListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			try {
				String fFunction = fFunctionField.getText();
				String gFunction = gFunctionField.getText();
				double varX = Double.parseDouble(xField.getText());
				double varY = Double.parseDouble(yField.getText());
				double h = Double.parseDouble(stepField.getText());
				double t = Double.parseDouble(tField.getText());
				doEuler(vals, fFunction, gFunction, varX, varY, h, t);
				n = 0;
				doEuler(vals1, fFunction, gFunction, varX, varY, h * .1, t);
				n = 0;
				doEuler(vals2, fFunction, gFunction, varX, varY, h * .01, t);
				graphEuler(vals, vals1, vals2);
				vals.clear();
				vals1.clear();
				vals2.clear();
				n = 0;			
			} catch (UnknownFunctionException e) {
				System.out.println(e.getMessage());
				System.out.println("Please input a valid equation");
			} catch (UnparsableExpressionException e) {
				System.out.println(e.getMessage());
				System.out.println("Please input a valid equation");
			} catch (NumberFormatException e) {
				System.out.println(e.getMessage());
				System.out.println("Please input a valid number");
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
				System.out.println("Please input a function");
			}
		}
		
		/**
		 * Performs the calculations to create points, which it adds to an array.
		 * 
		 * @param temp the ArrayList being affected
		 * @param fFunction the first function entered
		 * @param gFunction the second function entered
		 * @param varX the x value entered
		 * @param varY the y value entered
		 * @param h the time step value
		 * @param t the final time value
		 * @throws UnknownFunctionException invalid function
		 * @throws UnparsableExpressionException invalid function
		 */
		public void doEuler(ArrayList<Double> temp, String fFunction, String gFunction, double varX, double varY, double h, double t) throws UnknownFunctionException, UnparsableExpressionException {
			while (n++ * h < t) {
				Calculable yCalc = new ExpressionBuilder(fFunction).withVariable("x", varX).withVariable("y", varY).build();
				double f = yCalc.calculate() * h + varX;
				Calculable gCalc = new ExpressionBuilder(gFunction).withVariable("x", varX).withVariable("y", varY).build();
				double g = gCalc.calculate() * h + varY;
				if (n == 0) {
					temp.add(varX);
					temp.add(varY);
				}
				temp.add(f);
				temp.add(g);
				varX = f;
				varY = g;
			}	
		}
		
		/**
		 * Graphs a given array in graphPanel
		 * 
		 * @param list the first array being graphed
		 * @param list1 the second array being graphed
		 * @param list2 the third array being graphed
		 */
		public void graphEuler(ArrayList<Double> list, ArrayList<Double> list1, ArrayList<Double> list2) {
			JFrame graphFrame = new JFrame();
			graphFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			graphFrame.setSize(SIZE - 50,SIZE - 50);
			graphFrame.getContentPane().add(new graphPanel(list, list1, list2));
			graphFrame.setVisible(true);
		}
		
		
	}
	
}
