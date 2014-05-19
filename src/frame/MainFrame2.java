package frame;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import cell.Cell;

import component.table.MTableCellRender;
import component.table.SpecialTableModel;

public class MainFrame2 extends JFrame {

	/**
	 * 
	 */	
	private static final long serialVersionUID = 1L;
	public static final int CELL_NUMBER = 8 * 8;

	public static final int ROW_NUMBER = 8;

	public static final int WIDTH = 300;

	public static final int HEIGHT = 300;

	public static double screenWidth = Toolkit.getDefaultToolkit()
			.getScreenSize().getWidth();
	public static double screenHeight = Toolkit.getDefaultToolkit()
			.getScreenSize().getHeight();
	private static MainFrame2 mainFrame2;
	/** 地雷个数 */
	public static final int MINE_COUNT = 10;
	
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenu startGameItem;
	private JMenu endGameItem;
	private JTable table;
	private TableModel dataModel;
	
	private JButton[] button;
	private Cell[] cells;
	
	public MainFrame2(){
	   
		this.setSize(WIDTH, HEIGHT);
		this.setTitle("扫雷游戏");
		this.setName("扫雷游戏");
		
		this.getContentPane().setLayout(new GridBagLayout());
		this.setResizable(false);
		this.setLocation((int) (screenWidth - WIDTH) / 2,
				(int) (screenHeight - HEIGHT) / 2);

//-----------------------------------------------------	 	
       menuBar = new JMenuBar();
	   menu = new JMenu("菜单");
	   startGameItem = new JMenu("开始比赛 ");
	   endGameItem = new JMenu("退出比赛");
		
	   menu.add(startGameItem);
	   menu.add(endGameItem);
	   menuBar.add(menu);
	
	   JPanel topPanel = new JPanel();
	   topPanel.add(menuBar);
	   
	   this.getContentPane().add(menuBar,getTopConstraints());
//------------------------------------------------------------------------
	   dataModel = new SpecialTableModel();
	   table = new JTable();
	   
	   table.setModel(dataModel);
	   
	 //  TableCellRenderer renderer = new MTableCellRender();
	//   table.setDefaultRenderer(null, renderer );
	    
	   for(int i = 0;i< ROW_NUMBER;i++){
		  table.addColumn(new TableColumn(i, WIDTH/ROW_NUMBER, null, null));
	   }

	   for(int i = 0 ;i< CELL_NUMBER;i++){
		   table.setValueAt("1",i/ROW_NUMBER, i%ROW_NUMBER);
	   }
	   
	  JScrollPane  scrollPane = new JScrollPane(table);
	  table.setTableHeader(null);
	  table.setRowHeight(HEIGHT/11);
	  table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	  table.setRowSelectionAllowed(false);
	  table.setCellSelectionEnabled(true);
	  this.getContentPane().add(scrollPane,getCenterConstraints());
		
	}
	
	private GridBagConstraints getCenterConstraints(){
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 8;
		c.gridwidth = 8;
		c.weightx = 100;
		c.weighty =9*HEIGHT/10;
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.insets= new Insets(0,0,0,0);
		c.ipadx = 0;
		c.ipady = 0;
		
		return c;
	}
	/**'
	 * 
	 * @return
	 */
	public GridBagConstraints getTopConstraints(){
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 8;
		c.weightx = 100;
		c.weighty = HEIGHT/10;
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(0,0,5,0);
		c.ipadx = 0;
		c.ipady = 0;
		return c;
	}

	public static MainFrame2 getInstance() {
		if(mainFrame2 == null){
			mainFrame2 = new MainFrame2();
		}
		return mainFrame2;
	}

	public JButton[] getButton() {
		return button;
	}

	public void setButton(JButton[] button) {
		this.button = button;
	}

	public Cell[] getCells() {
		return cells;
	}

	public void setCells(Cell[] cells) {
		this.cells = cells;
	}
	
	
	

}
