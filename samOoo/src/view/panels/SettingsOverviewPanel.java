package view.panels;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controller.settings.SettingsOverviewAction;
import domain.facade.CompetentieTesterFacade;
import domain.questionSelectorStrategy.QuestionSelector;
import domain.scoreCalculatorStrategy.ScoreCalculator;

public class SettingsOverviewPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private GridBagConstraints constraints = new GridBagConstraints();
	private JComboBox<QuestionSelector> questionSelectorField;
	private JComboBox<ScoreCalculator> scoreCalculatorField;
	private CompetentieTesterFacade service;
	SpinnerModel spinnerAantalVragen;
	SpinnerModel spinnerTijdPerVraag;
	
	public SettingsOverviewPanel(SettingsOverviewAction settingsOverviewAction, CompetentieTesterFacade service) {
		this.service = service;
		setLayout(new GridBagLayout());
		initConstraints();
		int row = 0;
		initListQuestionSelector(row, "Question selector: ");
		row++;
		initScoreCalculator(row, "Score selector: ");
		row++;
		initAantalVragen(row, "Aantal vragen: ");
		row++;
		initQuestionTime(row, "Tijd per vraag: ");
	}
	
	private void initListQuestionSelector(int row, String title) {
		changeConstraints(1, 1, 0, row);
		addToPanel(new JLabel(title));
		changeConstraints(1, 1, 1, row);
		questionSelectorField = new JComboBox<QuestionSelector>();
		addToPanel(questionSelectorField);
	}
	
	private void initScoreCalculator(int row, String title) {
		changeConstraints(1, 1, 0, row);
		addToPanel(new JLabel(title));
		changeConstraints(1, 1, 1, row);
		scoreCalculatorField = new JComboBox<ScoreCalculator>();
		addToPanel(scoreCalculatorField);
	}
	
	private void initAantalVragen(int row, String title) {
		changeConstraints(1, 1, 0, row);
		addToPanel(new JLabel(title));
		changeConstraints(1, 1, 1, row);
		spinnerAantalVragen = new SpinnerNumberModel(service.getNumberOfQuestions(),4,10,1);
	    JSpinner anlist = new JSpinner(spinnerAantalVragen);
	    ((JSpinner.DefaultEditor) anlist.getEditor()).getTextField().setEditable(false);
	    addToPanel(anlist);
	}
	
	private void initQuestionTime(int row, String title) {
		changeConstraints(1, 1, 0, row);
		addToPanel(new JLabel(title));
		changeConstraints(1, 1, 1, row);
		spinnerTijdPerVraag = new SpinnerNumberModel(service.getTimePerQuestion(),15,60,1);
	    JSpinner anlist = new JSpinner(spinnerTijdPerVraag);
	    ((JSpinner.DefaultEditor) anlist.getEditor()).getTextField().setEditable(false);
	    addToPanel(anlist);
	}
	
	private void initConstraints() {
		constraints.insets = new Insets(10, 10, 0, 10);
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1.0;
		constraints.weighty = 1.0;
	}
	
	protected void changeConstraints(int height, int width, int gridx, int gridy) {
		constraints.gridheight = height;
		constraints.gridwidth = width;
		constraints.gridx = gridx;
		constraints.gridy = gridy;
	}
	
	protected GridBagConstraints getConstraints() {
		return constraints;
	}
	
	protected void addToPanel(Component component) {
		add(component, getConstraints());
	}
	
	public void setSettingsReady() {
		update();
	}
	
	private void update() {	
		if (service.getQuestionSelectors() != null) {
			Vector<QuestionSelector> qs = new Vector<QuestionSelector>(service.getQuestionSelectors());
			DefaultComboBoxModel<QuestionSelector> qsModel = new DefaultComboBoxModel<QuestionSelector>(qs);
			questionSelectorField.setModel(qsModel);
			
			// Default select chosen strategy
			questionSelectorField.setSelectedItem(service.getQuestionSelector());
			
			questionSelectorField.addActionListener(
					new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							 QuestionSelector questionSelector = (QuestionSelector) questionSelectorField.getSelectedItem();
							service.setQuestionSelector(questionSelector);
						}
					}
			);
		}
		
		if (service.getScoreCalculators() != null) {
			Vector<ScoreCalculator> sc = new Vector<ScoreCalculator>(service.getScoreCalculators());
			DefaultComboBoxModel<ScoreCalculator> scModel = new DefaultComboBoxModel<ScoreCalculator>(sc);
			scoreCalculatorField.setModel(scModel);
			
			// Default score calculator chosen strategy
			scoreCalculatorField.setSelectedItem(service.getScoreCalculator());
			
			scoreCalculatorField.addActionListener(
					new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							ScoreCalculator scoreCalculator = (ScoreCalculator) scoreCalculatorField.getSelectedItem();
							service.setScoreCalculator(scoreCalculator);
						}
					}
			);
		}
		
		spinnerAantalVragen.addChangeListener(
			new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent e) {
					int aantal = (int) spinnerAantalVragen.getValue();
					try {
						service.setNumberOfQuestions(aantal);
					} catch(Exception ex) {
						spinnerAantalVragen.setValue(aantal-1);
					}
				}
			}
		);
		
		spinnerTijdPerVraag.addChangeListener(
				new ChangeListener() {
					@Override
					public void stateChanged(ChangeEvent e) {
						int aantal = (int) spinnerTijdPerVraag.getValue();
						try {
							service.setTimePerQuestion(aantal);
						} catch(Exception ex) {
							spinnerTijdPerVraag.setValue(aantal-1);
						}
					}
				}
			);
		
	}
	
	

}
