package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.JTable;

public abstract class DeleteAction extends MouseAdapter{

	private JPanel panel;

	public DeleteAction() {
		super();
	}

	public void mouseClicked(MouseEvent evt) {

		JTable table = (JTable)(evt.getSource());
		int row = table.getSelectedRow();
		int column = table.getSelectedColumn();

		if(column == table.getColumnCount()-1){
			deleteRow(table, row);
		}		
	}

	protected abstract void deleteRow(JTable table, int row);

	protected JPanel getPanel() {
		return panel;
	}
	
	public void setPanel(JPanel panel){
		this.panel = panel;
	}

}
