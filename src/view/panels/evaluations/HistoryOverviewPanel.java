/**
 * 
 * @author Ruben Thielemans, Tamo Maes, Georges Petrofski & Sam Hendrickx
 *
 */
package view.panels.evaluations;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.util.List;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import controller.BackToUserHomeAction;
import view.ViewException;
import domain.Evaluation;


public class HistoryOverviewPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private GridBagConstraints constraints = new GridBagConstraints();
	private List<Evaluation> evaluations;
	private JTable table;
	private TableModel tableModel;
	
	public HistoryOverviewPanel(BackToUserHomeAction backToUserHomeAction, MouseAdapter editAction) throws ViewException {
		setLayout(new GridBagLayout());
		initConstraints();
		int row = 0;
		initListTitle(row);
		row++;
		initList(row, editAction);
		row++;
		initButtons(row, backToUserHomeAction);

	}
	
	private void initButtons(int row, Action action) {
		JButton btnNew = new JButton(action);
		btnNew.setPreferredSize(new Dimension(40, 40));
		changeConstraints(1, 1, 0, row);
		addToPanel(btnNew);
	}

	private void initListTitle(int rij) {
		changeConstraints(1, 1, 0, rij);
		addToPanel(new JLabel("Evaluations:"));

	}

	private void initList(int row, MouseAdapter action) {
		table = new JTable();
		table.addMouseListener(action);
		changeConstraints(1, 1, 0, row);
		addToPanel(new JScrollPane(table));
	}

	public void update() throws ViewException {
		tableModel = new HistoryTableModel(getEvaluations());
		table.setModel(tableModel);
	}

	private void initConstraints() {
		constraints.insets = new Insets(10, 10, 0, 10);
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1.0;
		constraints.weighty = 1.0;
	}

	protected GridBagConstraints getConstraints() {
		return constraints;
	}
	
	protected void addToPanel(Component component) {
		add(component, getConstraints());
	}

	protected void changeConstraints(int height, int width, int gridx, int gridy) {
		constraints.gridheight = height;
		constraints.gridwidth = width;
		constraints.gridx = gridx;
		constraints.gridy = gridy;
	}

	private List<Evaluation> getEvaluations() {
		return evaluations;
	}

	public void setEvaluations(List<Evaluation> evaluations) {
		this.evaluations = evaluations;
	}

}
