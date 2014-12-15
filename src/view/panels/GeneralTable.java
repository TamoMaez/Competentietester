package view.panels;

import javax.swing.JTable;
import javax.swing.table.TableModel;

import controller.DeleteCursorAdapter;

/**
 * 
 * Voegt default functionaliteiten toe aan JTable : 
 * 
 * 1) cursor naar "handje" bij hoveren over deletekolom
 * 2) forceer grootte van de kolommen
 * 3) scrollToBottom() voor bv. na het toevoegen van een rij
 *
 */

public class GeneralTable extends JTable {
	private static final long serialVersionUID = 1L;

	public GeneralTable(TableModel arg0, boolean addListener) {
		super(arg0);
		if(addListener) // 1
			addMouseMotionListener(new DeleteCursorAdapter());
	}
	
	public GeneralTable(boolean addListener){
		this(null, addListener);
	}

	// 2
	public void setColumnWidths(int... widths){
		for(int i = 0; i < widths.length; i++){
			if(widths[i] > -1){
				this.getColumnModel().getColumn(i).setPreferredWidth(widths[i]);
				this.getColumnModel().getColumn(i).setMaxWidth(widths[i]);
			}
		}
	}
	
	// 3
	public void scrollToBottom(){
		scrollRectToVisible(getCellRect(getRowCount() - 1, 0, true));
	}
}
