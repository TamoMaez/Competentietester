package view.panels;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import controller.*;

public class SidebarPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private GridBagConstraints constraints = new GridBagConstraints();
	
	public SidebarPanel(List<AbstractTestAction> actions) {
		initConstraints();
		setPreferredSize(new Dimension(120, 0));
		
		this.setBackground(Color.gray);
		
		createMenu(actions);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
	}

	private void createMenu(List<AbstractTestAction> actions) {
	    JMenuBar menuBar = new VerticalMenuBar();

	    for (AbstractTestAction action : actions) {
	    	JMenuItem item = new JMenuItem(action);
	    	item.setPreferredSize(new Dimension(118, 50));
	    	item.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.lightGray));

	    	menuBar.add(item);
	    }
	    this.add(menuBar);
		
	}

	private void initConstraints() {
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
	
	
	class VerticalMenuBar extends JMenuBar {
		private final LayoutManager grid = new GridLayout(0,1);
		public VerticalMenuBar() {
			setLayout(grid);
		}
	}


}
