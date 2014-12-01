package application;

import java.util.ArrayList;
import java.util.List;

import view.MainView;
import view.ViewException;
import view.WelcomeView;
import view.panels.CategoryDetailPanel;
import view.panels.CategoryOverviewPanel;
import view.panels.QuestionDetailPanel;
import view.panels.QuestionOverviewPanel;
import controller.AbstractTestAction;
import controller.AdminModeAction;
import controller.CategoryDoneAction;
import controller.CategoryEditAction;
import controller.CategoryNewAction;
import controller.CategoryOverviewAction;
import controller.QuestionDoneAction;
import controller.QuestionEditAction;
import controller.QuestionNewAction;
import controller.QuestionOverviewAction;
import controller.UserModeAction;
import domain.facade.AdministratorFacade;

public class CompetentieTesterApp {
	public static void main(String[] args) throws ViewException{
		AdministratorFacade service = new AdministratorFacade();

		// Modeactions
		AdminModeAction adminAction = new AdminModeAction(service);
		UserModeAction userAction = new UserModeAction(service);
		
		
		// Categoryactions
		CategoryOverviewAction categoryOverviewAction = new CategoryOverviewAction(service);
		CategoryEditAction categoryEditAction = new CategoryEditAction(service);
		CategoryNewAction categoryNewAction = new CategoryNewAction(service);
		CategoryDoneAction categoryDoneAction = new CategoryDoneAction(service);
		
		// Categorypanels
		CategoryOverviewPanel categoryOverviewPanel = new CategoryOverviewPanel(categoryEditAction, categoryNewAction);
		CategoryDetailPanel categoryDetailPanel = new CategoryDetailPanel(categoryDoneAction);
		
		// attach panels to buttons
		categoryOverviewAction.setOverviewPanel(categoryOverviewPanel);
		categoryEditAction.setDetailPanel(categoryDetailPanel);
		categoryNewAction.setDetailPanel(categoryDetailPanel);
		categoryDoneAction.setDetailPanel(categoryDetailPanel);
		categoryDoneAction.setOverviewPanel(categoryOverviewPanel);
		
		// Questionsactions
		QuestionOverviewAction questionOverviewAction = new QuestionOverviewAction(service);
		QuestionEditAction questionEditAction = new QuestionEditAction(service);
		QuestionNewAction questionNewAction = new QuestionNewAction(service);
		QuestionDoneAction questionDoneAction = new QuestionDoneAction(service);
		
		// Questionspanels
		QuestionOverviewPanel questionOverviewPanel = new QuestionOverviewPanel(questionEditAction, questionNewAction);
		QuestionDetailPanel questionDetailPanel = new QuestionDetailPanel(questionDoneAction);
		
		// attach panels to buttons
		questionOverviewAction.setOverviewPanel(questionOverviewPanel);
		questionEditAction.setDetailPanel(questionDetailPanel);
		questionNewAction.setDetailPanel(questionDetailPanel);
		questionDoneAction.setDetailPanel(questionDetailPanel);
		questionDoneAction.setOverviewPanel(questionOverviewPanel);
		
		List<AbstractTestAction> actions = new ArrayList<AbstractTestAction>();
		actions.add(categoryOverviewAction);
		actions.add(questionOverviewAction);

		MainView mainView = new MainView(actions);
	
		categoryOverviewAction.setView(mainView);
		categoryEditAction.setView(mainView);
		categoryNewAction.setView(mainView);
		categoryDoneAction.setView(mainView);
		categoryDoneAction.setView(mainView);
		
		questionOverviewAction.setView(mainView);
		questionEditAction.setView(mainView);
		questionNewAction.setView(mainView);
		questionDoneAction.setView(mainView);
		questionDoneAction.setView(mainView);
		
		adminAction.setOverviewPanel(mainView);
		
		WelcomeView mode = new WelcomeView(userAction, adminAction);
		userAction.setView(mode);
		adminAction.setView(mode);
		mode.setVisible(true);
		
	}
}
