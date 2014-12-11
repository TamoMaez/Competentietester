package view.panels;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.ViewException;

public class WelcomePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private GridBagConstraints constraints = new GridBagConstraints();
	
	public WelcomePanel() throws ViewException { //(MouseAdapter editAction, Action newAction)
		setLayout(new GridBagLayout());
		initConstraints();
		int row = 0;
		initListTitle(row);
		row++;
		initButtons(row); // , newAction
	}

	private void initListTitle(int rij) {
		changeConstraints(1, 1, 0, rij);
		addToPanel(new JLabel("Welkom!"));
		

	}

	private void initButtons(int row) { // , Action action
		JButton btnNew = new JButton(); // action
		changeConstraints(1, 1, 2, row);
		addToPanel(btnNew);
	}

	private void initConstraints() {
		constraints.insets = new Insets(10, 10, 0, 10);
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1.0;
		constraints.weighty = 1.0;
	}

	protected GridBagConstraints getConstraints() {
		return constraints;
	}
	
	protected void addToPanel(Component component) {
		add(component, getConstraints());
	}

	protected void changeConstraints(int height, int width, int gridx, int gridy) {
		constraints.gridheight = height;
		constraints.gridwidth = width;
		constraints.gridx = gridx;
		constraints.gridy = gridy;
	}

}
