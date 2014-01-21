import javax.swing.*;

/**
 * This class creates a window.
 * 
 * Creates a window and adds a EulerPanel to it.
 * 
 * @author Colby McBride
 * @version 1.0.0
 */
public class EulerDriver {

	
	public static void main(String[] args) {
		JFrame eulerFrame = new JFrame();
		eulerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		eulerFrame.setSize(EulerPanel.SIZE,EulerPanel.SIZE);
		eulerFrame.getContentPane().add(new EulerPanel());
		eulerFrame.setVisible(true);
	}
	
}

