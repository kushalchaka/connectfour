package game;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Toolkit;

public class FourFrame extends JFrame {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FourFrame frame = new FourFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FourFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(new FourPanel());
		this.setLocation(300, 0);
		this.setTitle("Match Four");
		this.setResizable(false);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("src/images/custom_java_icon.jpg"));
		this.pack();
		
	}

}

