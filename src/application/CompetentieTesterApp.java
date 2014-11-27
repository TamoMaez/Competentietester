package application;

import java.util.ArrayList;
import java.util.List;

import view.MainView;
import view.ViewException;
import view.panels.CategoryDetailPanel;
import view.panels.CategoryOverviewPanel;
import view.panels.WelcomePanel;
import controller.AbstractTestAction;
import controller.CategoryDoneAction;
import controller.CategoryEditAction;
import controller.CategoryNewAction;
import controller.CategoryOverviewAction;
import controller.UserTypeDoneAction;
import controller.UserTypeOverviewAction;
import database.FileReader;
import database.ReadFromExcel;
import database.WriteToExcel;
import domain.facade.AdministratorFacade;

public class CompetentieTesterApp {
	public static void main(String[] args) throws ViewException{

		AdministratorFacade service = new AdministratorFacade();
		service.setReader(new ReadFromExcel());
		service.setWriter(new WriteToExcel());
		service.read("res/vragen-uit-excel.xlsx");
		
		CategoryOverviewAction categoryOverviewAction = new CategoryOverviewAction(service);
		CategoryEditAction categoryEditAction = new CategoryEditAction(service);
		CategoryNewAction categoryNewAction = new CategoryNewAction(service);
		CategoryDoneAction categoryDoneAction = new CategoryDoneAction(service);
		
		UserTypeOverviewAction userTypeOverviewAction = new UserTypeOverviewAction(service);
		UserTypeDoneAction userTypeDoneAction = new UserTypeDoneAction(service);
		
		WelcomePanel welcomePanel = new WelcomePanel(); // Nog actions adden
		
		CategoryOverviewPanel categoryOverviewPanel = new CategoryOverviewPanel(categoryEditAction, categoryNewAction);
		CategoryDetailPanel categoryDetailPanel = new CategoryDetailPanel(categoryDoneAction);
		
		categoryOverviewAction.setOverviewPanel(categoryOverviewPanel);
		categoryEditAction.setDetailPanel(categoryDetailPanel);
		categoryNewAction.setDetailPanel(categoryDetailPanel);
		categoryDoneAction.setDetailPanel(categoryDetailPanel);
		categoryDoneAction.setOverviewPanel(categoryOverviewPanel);

		userTypeOverviewAction.setWelcomePanel(welcomePanel);
		
		List<AbstractTestAction> actions = new ArrayList<AbstractTestAction>();
		actions.add(userTypeOverviewAction);
		//actions.add(categoryNewAction);

		MainView mainView = new MainView(actions);
	
		categoryOverviewAction.setView(mainView);
		categoryEditAction.setView(mainView);
		categoryNewAction.setView(mainView);
		categoryDoneAction.setView(mainView);
		categoryDoneAction.setView(mainView);
		
		mainView.setVisible(true);
		
	}
}
