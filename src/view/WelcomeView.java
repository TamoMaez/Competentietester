package view;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.AbstractTestAction;

public class WelcomeView extends JFrame {
	private static final long serialVersionUID = 1L;
	JPanel userTypeButtons = new JPanel();
	
	//Buttons
	JButton usrBtn = new JButton("User");
	JButton adminBtn = new JButton("Admin");

	public WelcomeView(AbstractTestAction userAction, AbstractTestAction adminAction){
        
        //Adding to JFrame
        userTypeButtons.add(usrBtn);
        userTypeButtons.add(adminBtn);
        add(userTypeButtons);
        
        // JFrame properties
        setSize(new Dimension(300,100));
    	createButtons(userAction, adminAction);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Choose mode");
        userTypeButtons.setLayout(new GridLayout(0,2));
        setLocationRelativeTo(null);
        setResizable(false);
	}
	
	private void createButtons(AbstractTestAction userAction, AbstractTestAction adminAction) {
		usrBtn.setAction(userAction);
		adminBtn.setAction(adminAction);
	}

	public void setMainPanel(JPanel mainPanel) {
		setContentPane(mainPanel);
	}
}