package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import view.panels.SidebarPanel;
import controller.AbstractTestAction;
import domain.facade.CompetentieTesterFacade;


public class AdminMainView extends JFrame {
	private static final long serialVersionUID = 1L;
	private JMenuBar menuBar;
	private CompetentieTesterFacade facade;
	private SidebarPanel sidebarPanel;
	private Container shownPanel = new JPanel();
	
	public AdminMainView(List<AbstractTestAction> editActions, List<AbstractTestAction> fileActions, List<AbstractTestAction> settingsActions, JPanel initPanel){
		this.sidebarPanel = new SidebarPanel(editActions);		
		
		this.add(sidebarPanel, BorderLayout.WEST);

		this.setContentPane(initPanel);

		
		createMenuBar(editActions, fileActions, settingsActions);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
	}
	  
	/*
	 * TOP BAR
	 */
	private void createMenuBar(List<AbstractTestAction> editActions, List<AbstractTestAction> fileActions, List<AbstractTestAction> settingsActions) {
		menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		for(AbstractTestAction action : fileActions){
			fileMenu.add(new JMenuItem(action));
		}
		
		JMenu editMenu = new JMenu("Edit");
		for(AbstractTestAction action : editActions){
			editMenu.add(new JMenuItem(action));
		}
		JMenu settingsMenu = new JMenu("Settings");
		for(AbstractTestAction action : settingsActions){
			settingsMenu.add(new JMenuItem(action));
		}
		
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(settingsMenu);
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
