/**
 * 
 * @author Ruben Thielemans, Tamo Maes, Georges Petrofski & Sam Hendrickx
 *
 */
package controller;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;

public class DeleteCursorAdapter extends MouseAdapter {

	 public DeleteCursorAdapter() {
		super();
	}

	public void mouseMoved (MouseEvent e) {
       // Return the pixel position of the mouse cursor
       // hotspot.

       Point p = e.getPoint ();

       // Convert the pixel position to the zero-based
       // column index of the table column over which the
       // mouse cursor hotspot is located. The result is a
       // view-based column index. If that index refers to
       // the leftmost column, display a crosshair cursor.
       // Otherwise, display a hand cursor.
       
       JTable jt = (JTable) e.getSource();

       if (jt.columnAtPoint (p) == jt.getColumnCount()-1)
         jt.setCursor (Cursor.getPredefinedCursor
        		 (Cursor.HAND_CURSOR));
       else
         jt.setCursor (Cursor.getPredefinedCursor
        		 (Cursor.DEFAULT_CURSOR));
     }
}
