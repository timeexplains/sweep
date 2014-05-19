package component.table;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JTable;

/**
 * 
 * @author zhihuaPeng
 *
 */
public interface ButtonCellRender {
	
    Component getTableCellRendererComponent(JButton table, Object value,
		    boolean isSelected, boolean hasFocus, 
		    int row, int column);

}
