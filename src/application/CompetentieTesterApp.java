package application;

import java.util.ArrayList;
import java.util.List;

import view.AdminMainView;
import view.UserMainView;
import view.ViewException;
import view.WelcomeView;
import view.panels.categories.CategoryDetailPanel;
import view.panels.categories.CategoryOverviewPanel;
import view.panels.evaluations.HistoryOverviewPanel;
import view.panels.AdminWelcomePanel;
import view.panels.EvaluationTestPanel;
import view.panels.SettingsOverviewPanel;
import view.panels.UserWelcomePanel;
import view.panels.questions.ChooseCategoryPanel;
import view.panels.questions.QuestionDetailPanel;
import view.panels.questions.QuestionOverviewPanel;
import view.panels.TestPanel;
import controller.AbstractTestAction;
import controller.AdminModeAction;
import controller.BackToUserHomeAction;
import controller.HistoryDetailAction;
import controller.HistoryOverviewAction;
import controller.LogoutAction;
import controller.NewFileAction;
import controller.NextQuestionAction;
import controller.OpenFileAction;
import controller.SaveAsFileAction;
import controller.SaveFileAction;
import controller.StartTestAction;
import controller.TestDoneAction;
import controller.UserModeAction;
import controller.category.CategoryDoneAction;
import controller.category.CategoryEditAction;
import controller.category.CategoryNewAction;
import controller.category.CategoryOverviewAction;
import controller.question.AddCategoryAction;
import controller.question.NewQuestionCategoryAction;
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
		LogoutAction logoutAction = new LogoutAction(service);

		
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
				categoryEditAction.setPanel(categoryOverviewPanel);
				categoryNewAction.setDetailPanel(categoryDetailPanel);
				categoryDoneAction.setDetailPanel(categoryDetailPanel);
				categoryDoneAction.setOverviewPanel(categoryOverviewPanel);
				
				// Questionsactions
				QuestionOverviewAction questionOverviewAction = new QuestionOverviewAction(service);
				QuestionEditAction questionEditAction = new QuestionEditAction(service);
				QuestionNewAction questionNewAction = new QuestionNewAction(service);
				QuestionDoneAction questionDoneAction = new QuestionDoneAction(service);
				
				// Other actions
				AddCategoryAction addCategoryAction = new AddCategoryAction(service);
				
				// Questionspanels
				QuestionOverviewPanel questionOverviewPanel = new QuestionOverviewPanel(questionEditAction, questionNewAction);
				QuestionDetailPanel questionDetailPanel = new QuestionDetailPanel(addCategoryAction, questionDoneAction);
				
				// attach panels to buttons
				questionOverviewAction.setOverviewPanel(questionOverviewPanel);
				questionEditAction.setDetailPanel(questionDetailPanel);
				questionEditAction.setPanel(questionOverviewPanel);
				questionNewAction.setDetailPanel(questionDetailPanel);
				questionDoneAction.setDetailPanel(questionDetailPanel);
				questionDoneAction.setOverviewPanel(questionOverviewPanel);
		
		// new category for question
		NewQuestionCategoryAction qcAction = new NewQuestionCategoryAction(service);
		qcAction.setOverviewPanel(questionDetailPanel);
		
		// add category
		ChooseCategoryPanel chooseCategoryPanel = new ChooseCategoryPanel(qcAction);
		addCategoryAction.setDetailPanel(chooseCategoryPanel);
		addCategoryAction.setOverviewPanel(questionDetailPanel);
		qcAction.setDetailPanel(chooseCategoryPanel);
		
		// SettingsActions
		SettingsOverviewAction settingsOverviewAction = new SettingsOverviewAction(service);
		
		//SettingsPanel
		SettingsOverviewPanel settingsOverviewPanel = new SettingsOverviewPanel(settingsOverviewAction, service);
		settingsOverviewAction.setOverviewPanel(settingsOverviewPanel);
		
		
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
		fileActions.add(logoutAction);

		
		
		
		// AdminWelcomePanel
		AdminWelcomePanel adminWelcomePanel = new AdminWelcomePanel(openFileAction, newFileAction);

		
		AdminMainView adminMainView = new AdminMainView(editActions, fileActions, settingsActions, adminWelcomePanel);

		adminAction.setOverviewPanel(adminMainView);
		
		newFileAction.setView(adminMainView);
		openFileAction.setView(adminMainView);
		
		// set view for settings
		settingsOverviewAction.setView(adminMainView);

		// YOLO
		qcAction.setView(adminMainView);

		
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
		
		addCategoryAction.setView(adminMainView);
		
		/**
		 * USER
		 */
		
		// Test actions
		StartTestAction startTestAction = new StartTestAction(service);
		NextQuestionAction nextQuestionAction = new NextQuestionAction(service);
		TestDoneAction testDoneAction = new TestDoneAction(service);
		BackToUserHomeAction backToUserHomeAction = new BackToUserHomeAction(service);

		// History actions
		HistoryOverviewAction historyOverviewAction = new HistoryOverviewAction(service);
		HistoryDetailAction historyDetailAction = new HistoryDetailAction(service);
		
		HistoryOverviewPanel historyOverviewPanel = new HistoryOverviewPanel(backToUserHomeAction, historyDetailAction);

		
		
		// Panels
		UserWelcomePanel userWelcomePanel = new UserWelcomePanel(startTestAction, historyOverviewAction);
		TestPanel testPanel = new TestPanel(nextQuestionAction, testDoneAction, service);
		EvaluationTestPanel evaluationTestPanel = new EvaluationTestPanel(backToUserHomeAction, service);
		
		// Attach buttons to panels
		startTestAction.setTestPanel(testPanel);
		nextQuestionAction.setTestPanel(testPanel);
		testDoneAction.setTestPanel(testPanel);
		testDoneAction.setEvaluationTestPanel(evaluationTestPanel);
		backToUserHomeAction.setUserWelcomePanel(userWelcomePanel);
		
		
		historyOverviewAction.setOverviewPanel(historyOverviewPanel);
		historyDetailAction.setEvaluationTestPanel(evaluationTestPanel);
		
		
		// Create UserMainView
		List<AbstractTestAction> testOptions = new ArrayList<>();
		testOptions.add(startTestAction);
		testOptions.add(historyOverviewAction);
		testOptions.add(logoutAction);
		
		UserMainView userMainView = new UserMainView(userWelcomePanel, testOptions);
		userAction.setOverviewPanel(userMainView);

		backToUserHomeAction.setView(userMainView);
		startTestAction.setView(userMainView);
		nextQuestionAction.setView(userMainView);
		testDoneAction.setView(userMainView);
		
		historyOverviewAction.setView(userMainView);
		historyDetailAction.setView(userMainView);
		
		
		
		
		WelcomeView mode = new WelcomeView(userAction, adminAction);
		userAction.setView(mode);
		adminAction.setView(mode);

		
		
		// Logout
		logoutAction.addView(userMainView);
		logoutAction.addView(adminMainView);
		logoutAction.setOverviewPanel(mode);
		
		
		
		mode.setVisible(true);
		
	}
}
