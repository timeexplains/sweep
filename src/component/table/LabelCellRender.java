package component.table;

import java.awt.Component;
import javax.swing.JLabel;


/**
 * 
 * @author zhihuaPeng
 *
 */
public interface LabelCellRender {
	
    Component getTableCellRendererComponent(JLabel table, Object value,
		    boolean isSelected, boolean hasFocus, 
		    int row, int column);
}
