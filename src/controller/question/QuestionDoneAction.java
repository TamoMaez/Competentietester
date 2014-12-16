package controller.question;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import view.ViewException;
import view.panels.questions.QuestionDetailPanel;
import view.panels.questions.QuestionOverviewPanel;
import controller.AbstractTestAction;
import domain.YesNoQuestion;
import domain.facade.CompetentieTesterFacade;

public class QuestionDoneAction extends AbstractTestAction {

	private static final long serialVersionUID = 1L;
	private QuestionDetailPanel detailPanel;
	private QuestionOverviewPanel overviewPanel;

	public QuestionDoneAction(CompetentieTesterFacade service) {
		super(service);
	}

	public void setDetailPanel(QuestionDetailPanel questionDetailPanel) {
		this.detailPanel = questionDetailPanel;

	}

	public void setOverviewPanel(QuestionOverviewPanel questionOverviewPanel) {
		this.overviewPanel = questionOverviewPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Save")){

			try {
				getDetailPanel().setQuestionTitle();
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "Save a descent questiontitle");
				return;
			}
			try {
				getOverviewPanel().update();
			} catch (ViewException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			getService().writeToCurrentFile();;
		}
		
		// check vraag 
		Map<String, Component> errors = new HashMap<>();
		if(getDetailPanel().getQuestion().getCategories().size() < 1){
			errors.put(new String("Add a category"), getDetailPanel().getCategoryTable());
		}
		if(getDetailPanel().getQuestion().getOptions().size() < 1){
			errors.put(new String("Add an option "), getDetailPanel().getOptionTable());
		} else if(getDetailPanel().getQuestion().getCorrectOptions().size() < 1){
			errors.put(new String("Check the correct option "), getDetailPanel().getOptionTable());
		} else if(getDetailPanel().getQuestion() instanceof YesNoQuestion && 
				getDetailPanel().getQuestion().getCorrectOptions().size() > 1){
			errors.put(new String("Uncheck yes or no"), getDetailPanel().getOptionTable());
		}
		if(errors.size() >= 1){
			for (Map.Entry<String, Component> entry : errors.entrySet()){
				JOptionPane.showMessageDialog(entry.getValue(), entry.getKey());
			}
			return;
		}
		
		setPanelAsContentForView(getOverviewPanel());	
	}

	private QuestionOverviewPanel getOverviewPanel() {
		return overviewPanel;
	}

	private QuestionDetailPanel getDetailPanel() {
		return detailPanel;
	}

}
