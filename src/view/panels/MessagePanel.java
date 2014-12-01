package view.panels;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MessagePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private List<String> message;
	private JLabel messageLabel;
	private GridBagConstraints constraints = new GridBagConstraints();
	private JButton testButton;
	
	public MessagePanel (Action testAction){
		setLayout(new GridBagLayout());
		initConstraints();
		int rij = 0;
		initButton(rij, testAction);
	}

	protected void initMessage(int rij) {
		messageLabel = new JLabel();
		changeConstraints(1, 1, 0, rij);
		addToPanel(messageLabel);
	}

	protected void initButton(int rij, Action action) {
		testButton = new JButton(action);
		changeConstraints(1, 1, 0, rij);		
		testButton.setAction(action);
		addToPanel(testButton);
	}

	private void initConstraints() {
		constraints.insets = new Insets(10, 10, 0, 10);
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1.0;
		constraints.weighty = 1.0;
	}

	protected void changeConstraints(int height, int width, int gridx, int gridy) {
		initConstraints();
		constraints.gridheight = height;
		constraints.gridwidth = width;
		constraints.gridx = gridx;
		constraints.gridy = gridy;
	}
	
	protected GridBagConstraints getConstraints() {
		return constraints;
	}
	
	protected void addToPanel(Component component) {
		add(component, getConstraints());
	}

	private List<String> getMessage() {
		return message;
	}

	public void setMessage(List<String> message) {
		this.message = message;
		update();
	}
	
	private void update() {
		removeAll();
		
		int rij = 0;
		for(String feedback : getMessage()){
			JLabel feedbackLabel = new JLabel(feedback);
			changeConstraints(1, 1, 0, rij);	
			addToPanel(feedbackLabel);
			rij++;
		}
		changeConstraints(1, 1, 0, rij);	
		addToPanel(testButton);
		
		repaint();
	}
}
