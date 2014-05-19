package component.table;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import frame.MainFrame2;

/**
 * 
 * @author zhihuaPeng
 *
 */
public class MTableCellRender extends DefaultTableCellRenderer implements TableCellRenderer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		JButton jb = new JButton();	
		
		table.setValueAt(jb, row, column);
		
		return jb;
	}

}
