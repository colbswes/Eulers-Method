import javax.swing.*;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;

/**
 * This class generates a window that graphs a function
 * 
 * Creates three ArrayLists based on given lists, and then graphs each of them
 * 
 * @author Colby McBride
 * @version 1.0.0
 */
public class graphPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private ArrayList<Double> list = new ArrayList<Double>();
	private ArrayList<Double> list1 = new ArrayList<Double>();
	private ArrayList<Double> list2 = new ArrayList<Double>();
	private static final int SIZE = EulerPanel.SIZE - 50;
	
	/**
	 * Constructs three ArrayLists
	 * 
	 * @param vals the first list
	 * @param vals1 the second list (h * .1)
	 * @param vals2 the third list ( h * .01)
	 */
	public graphPanel(ArrayList<Double> vals, ArrayList<Double> vals1, ArrayList<Double> vals2)  {
		for (Double num : vals) {
			list.add(num);
		}
		for (Double num : vals1) {
			list1.add(num);
		}
		for (Double num : vals2) {
			list2.add(num);
		}
	}
	
	/**
	 * Graphs three lists
	 */
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.translate(SIZE / 2, SIZE / 2);
		g2.drawLine(-SIZE / 2, 0, SIZE / 2, 0);
		g2.drawLine(0,-SIZE / 2, 0, SIZE / 2);
		g2.scale(5,5);
		g2.setColor(Color.BLUE);
		for (int i = 2; i < list.size(); i++) 
			g2.draw(new Line2D.Double(list.get(i - 2) * 11, -list.get(i - 1) * 11, list.get(i++) * 11, -list.get(i) * 11));
		g2.setColor(Color.GREEN);
		for (int i = 2; i < list1.size(); i++) 
			g2.draw(new Line2D.Double(list1.get(i - 2) * 11, -list1.get(i - 1) * 11, list1.get(i++) * 11, -list1.get(i) * 11));
		g2.setColor(Color.MAGENTA);
		for (int i = 2; i < list2.size(); i++) 
			g2.draw(new Line2D.Double(list2.get(i - 2) * 11, -list2.get(i - 1) * 11, list2.get(i++) * 11, -list2.get(i) * 11));
	}
}
