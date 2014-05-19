package component.table;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class SpecialTableModel extends AbstractTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Vector<Vector<Object>> dataS = new Vector<Vector<Object>>();
	
	
	
	public SpecialTableModel() {
		super();
//		this(new )
	}

	@Override
	public int getRowCount() {
		return dataS.size();
	}

	@Override
	public int getColumnCount() {
		int maxColumn = 0;
		for(Vector<Object> columnData:dataS){
			 if(columnData == null){
				 continue;
			 }
			 maxColumn = columnData.size()>maxColumn?columnData.size():maxColumn;
		}
		return maxColumn;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Vector<Object> rowData = dataS.elementAt(rowIndex);
		if(rowData == null){
			return null;
		}
		return rowData.elementAt(columnIndex);
		
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		justify(rowIndex,columnIndex);
		Vector<Object> rowData = dataS.elementAt(rowIndex);
		System.out.println(rowData.size()+" "+dataS.size());
		rowData.setElementAt(aValue,columnIndex);		
		super.fireTableCellUpdated(rowIndex, columnIndex);
	}

	private void justify(int rowIndex, int columnIndex) {
		if(dataS.size()<=rowIndex){
			for(int i=dataS.size();i<=rowIndex;i++){//加入行
				Vector<Object> vec = new Vector<Object>(columnIndex+1);
				vec.setSize(columnIndex+1);
				dataS.add(i,vec);
				
			}
		}
		else{//加入列
			Vector<Object> rowData = dataS.get(rowIndex);
			
			for(int i = rowData.size();i<=columnIndex;i++){
				Object obj = new Object();
				rowData.add(obj);
			}
		}
	
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return super.isCellEditable(rowIndex, columnIndex);
	}
	
	
	
}
