package view;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import controller.AbstractTestAction;
import domain.facade.AdministratorFacade;
import domain.facade.CompetentieTesterFacade;


public class MainView extends JFrame {
	private static final long serialVersionUID = 1L;
	private JMenuBar menuBar;
	private AdministratorFacade facade;

	public MainView(List<AbstractTestAction> actions){
		createMenuBar(actions);
    	setSize(new Dimension(600,400));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
	}
	
	private void createMenuBar(List<AbstractTestAction> actions) {
		menuBar = new JMenuBar();
		for(AbstractTestAction action : actions){
			JMenuItem menuItem = new JMenuItem(action);
			menuBar.add(menuItem);
		}
		setJMenuBar(menuBar);
	}

	public void setMainPanel(JPanel mainPanel) {
		setContentPane(mainPanel);
	}

	public void setService(AdministratorFacade facade) {
		this.facade = facade;
		facade.read();
	}
	
	public CompetentieTesterFacade getService() {
		return facade;
	}
}
