package application;

import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.AdminMainView;
import view.UserMainView;
import view.ViewException;
import view.WelcomeView;
import view.panels.categories.CategoryDetailPanel;
import view.panels.categories.CategoryOverviewPanel;
import view.panels.EvaluationTestPanel;
import view.panels.questions.ChooseCategoryPanel;
import view.panels.questions.QuestionDetailPanel;
import view.panels.questions.QuestionOverviewPanel;
import view.panels.TestPanel;
import controller.AbstractTestAction;
import controller.AdminModeAction;
import controller.HistoryOverviewAction;
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
		
		// Other actions
		AddCategoryAction addCategoryAction = new AddCategoryAction(service);
		
		// Questionspanels
		QuestionOverviewPanel questionOverviewPanel = new QuestionOverviewPanel(questionEditAction, questionNewAction);
		QuestionDetailPanel questionDetailPanel = new QuestionDetailPanel(addCategoryAction);
		
		// attach panels to buttons
		questionOverviewAction.setOverviewPanel(questionOverviewPanel);
		questionEditAction.setDetailPanel(questionDetailPanel);
		questionNewAction.setDetailPanel(questionDetailPanel);
		questionDoneAction.setDetailPanel(questionDetailPanel);
		questionDoneAction.setOverviewPanel(questionOverviewPanel);
		
		// add category
		ChooseCategoryPanel chooseCategoryPanel = new ChooseCategoryPanel(null);
		addCategoryAction.setDetailPanel(chooseCategoryPanel);
		
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
		
		addCategoryAction.setView(adminMainView);
		
		/**
		 * USER
		 */
		
		// Test actions
		StartTestAction startTestAction = new StartTestAction(service);
		NextQuestionAction nextQuestionAction = new NextQuestionAction(service);
		TestDoneAction testDoneAction = new TestDoneAction(service);
		
		// History actions
		HistoryOverviewAction historyOverviewAction = new HistoryOverviewAction(service);
		
		// Panels
		TestPanel testPanel = new TestPanel(nextQuestionAction, testDoneAction, service);
		EvaluationTestPanel evaluationTestPanel = new EvaluationTestPanel(service);
		
		// Attach buttons to panels
		startTestAction.setTestPanel(testPanel);
		nextQuestionAction.setTestPanel(testPanel);
		testDoneAction.setEvaluationTestPanel(evaluationTestPanel);
		
		
		
		List<AbstractTestAction> testOptions = new ArrayList<>();
		testOptions.add(startTestAction);
		testOptions.add(historyOverviewAction);
		
		JPanel buttonHolder2 = new JPanel();
		// 2 possibilities
		JButton startButton = new JButton(startTestAction);
		JButton historyButton = new JButton(historyOverviewAction);	
		startButton.setPreferredSize(new Dimension(200,60));
		historyButton.setPreferredSize(new Dimension(200,60));
		buttonHolder2.add(startButton);
		buttonHolder2.add(historyButton);
		
		// Welcome text
		JLabel welcomeText = new JLabel("Welcome to the test!", JLabel.CENTER);
		welcomeText.setFont(new Font("Arial", Font.PLAIN, 50));
		welcomeText.setBorder(BorderFactory.createEmptyBorder(10, 300, 10, 10));
		
		// Welcome Panel
		JPanel selectButtonsPanel2 = new JPanel();
		selectButtonsPanel2.setLayout(new BoxLayout(selectButtonsPanel2, BoxLayout.PAGE_AXIS));
		selectButtonsPanel2.add(Box.createVerticalGlue());
		selectButtonsPanel2.add(welcomeText);
		selectButtonsPanel2.add(buttonHolder2);
		selectButtonsPanel2.add(Box.createVerticalGlue());
		
		
		UserMainView userMainView = new UserMainView(selectButtonsPanel2, testOptions);
		userAction.setOverviewPanel(userMainView);
		
		startTestAction.setView(userMainView);
		nextQuestionAction.setView(userMainView);
		testDoneAction.setView(userMainView);
		
		WelcomeView mode = new WelcomeView(userAction, adminAction);
		userAction.setView(mode);
		adminAction.setView(mode);
		
		mode.setVisible(true);
		
	}
}
