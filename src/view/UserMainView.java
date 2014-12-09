package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import view.panels.SidebarPanel;
import controller.AbstractTestAction;
import domain.facade.CompetentieTesterFacade;
import domain.facade.CompetentieTesterFacade;


public class UserMainView extends JFrame {
	private static final long serialVersionUID = 1L;
	private JMenuBar menuBar;
	private CompetentieTesterFacade facade;

	private Container shownPanel = new JPanel();
	
	public UserMainView(JPanel initPanel, List<AbstractTestAction> options){
		
		this.setContentPane(initPanel);
		
		createMenuBar(options);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
	}
	  
	/*
	 * TOP BAR
	 */
	private void createMenuBar(List<AbstractTestAction> options) {
		menuBar = new JMenuBar();
		JMenu optionsMenu = new JMenu("Options");
		
		for (AbstractTestAction option : options) {
			optionsMenu.add(new JMenuItem(option));
		}
		
		JMenu helpMenu = new JMenu("Help");
		helpMenu.add(new JMenuItem("Start test"));

		// User profile?
		// Show categories?
		
		menuBar.add(optionsMenu);
		menuBar.add(helpMenu);
		
		setJMenuBar(menuBar);
	}

	public void setMainPanel(JPanel mainPanel) {
		this.setContentPane(mainPanel);
	}
	
	@Override
	public void setContentPane(Container contentPane) {
		this.remove(shownPanel);

		this.add(contentPane);
		this.shownPanel = contentPane;
		
		this.getContentPane().revalidate();
		this.getContentPane().repaint();
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);

	}

	public void setService(CompetentieTesterFacade facade) {
		this.facade = facade;
		facade.read();
	}
	
	public CompetentieTesterFacade getService() {
		return facade;
	}
	
}
