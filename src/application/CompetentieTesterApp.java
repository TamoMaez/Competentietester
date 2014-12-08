package application;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import view.AdminMainView;
import view.UserMainView;
import view.ViewException;
import view.WelcomeView;
import view.panels.CategoryDetailPanel;
import view.panels.CategoryOverviewPanel;
import view.panels.QuestionDetailPanel;
import view.panels.QuestionOverviewPanel;
import controller.AbstractTestAction;
import controller.AdminModeAction;
import controller.NewFileAction;
import controller.OpenFileAction;
import controller.SaveAsFileAction;
import controller.SaveFileAction;
import controller.UserModeAction;
import controller.category.CategoryDoneAction;
import controller.category.CategoryEditAction;
import controller.category.CategoryNewAction;
import controller.category.CategoryOverviewAction;
import controller.question.QuestionDoneAction;
import controller.question.QuestionEditAction;
import controller.question.QuestionNewAction;
import controller.question.QuestionOverviewAction;
import controller.settings.SettingsOverviewAction;
import domain.facade.CompetentieTesterFacade;

public class CompetentieTesterApp {
	public static void main(String[] args) throws ViewException{
		CompetentieTesterFacade service = new CompetentieTesterFacade();

		// Modeactions
		AdminModeAction adminAction = new AdminModeAction(service);
		UserModeAction userAction = new UserModeAction(service);
		
		
		/**
		 * ADMIN
		 */
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
		
		// Settingsactions
		SettingsOverviewAction settingsOverviewAction = new SettingsOverviewAction(service);
		
		
		// EditActions
		List<AbstractTestAction> editActions = new ArrayList<AbstractTestAction>();
		editActions.add(categoryOverviewAction);
		editActions.add(questionOverviewAction);
		
		//SettingsActions
		List<AbstractTestAction> settingsActions = new ArrayList<AbstractTestAction>();
		settingsActions.add(settingsOverviewAction);
		
		// FileActions
		List<AbstractTestAction> fileActions = new ArrayList<AbstractTestAction>();
		NewFileAction newFileAction = new NewFileAction(service);
		OpenFileAction openFileAction = new OpenFileAction(service);
		newFileAction.setGoTo(questionOverviewPanel);
		openFileAction.setGoTo(questionOverviewPanel);
		
		fileActions.add(new NewFileAction(service));
		fileActions.add(new OpenFileAction(service));
		fileActions.add(new SaveFileAction(service));
		fileActions.add(new SaveAsFileAction(service));
		
		// buttonpaneel
		JPanel buttonHolder = new JPanel();
			// 2 possibilities
		JButton openBtn = new JButton(openFileAction);
		JButton newBtn = new JButton(new NewFileAction(service));	
		openBtn.setPreferredSize(new Dimension(100,40));
		newBtn.setPreferredSize(new Dimension(100,40));
		buttonHolder.add(openBtn);
		buttonHolder.add(newBtn);
			// Welcome Panel
		JPanel selectButtonsPanel = new JPanel();
		selectButtonsPanel.setLayout(new BoxLayout(selectButtonsPanel, BoxLayout.PAGE_AXIS));
		selectButtonsPanel.add(Box.createVerticalGlue());
		selectButtonsPanel.add(buttonHolder);
		selectButtonsPanel.add(Box.createVerticalGlue());
		
		AdminMainView adminMainView = new AdminMainView(editActions, fileActions, settingsActions, selectButtonsPanel);

		newFileAction.setView(adminMainView);
		openFileAction.setView(adminMainView);
		
		
		categoryOverviewAction.setView(adminMainView);
		categoryEditAction.setView(adminMainView);
		categoryNewAction.setView(adminMainView);
		categoryDoneAction.setView(adminMainView);
		categoryDoneAction.setView(adminMainView);
		
		questionOverviewAction.setView(adminMainView);
		questionEditAction.setView(adminMainView);
		questionNewAction.setView(adminMainView);
		questionDoneAction.setView(adminMainView);
		questionDoneAction.setView(adminMainView);
		
		adminAction.setOverviewPanel(adminMainView);
		
		
		
		/**
		 * USER
		 */
		UserMainView userMainView = new UserMainView();
		userAction.setOverviewPanel(userMainView);
		
		
		
		
		WelcomeView mode = new WelcomeView(userAction, adminAction);
		userAction.setView(mode);
		adminAction.setView(mode);
		
		mode.setVisible(true);
		
	}
}
