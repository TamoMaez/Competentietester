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
	
	public UserMainView(){
		
		
		
		this.setContentPane(new JPanel());

		
		createMenuBar();
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
	}
	  
	/*
	 * TOP BAR
	 */
	private void createMenuBar() {
		menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		fileMenu.add(new JMenuItem("Quit"));
		
		menuBar.add(fileMenu);
		
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
