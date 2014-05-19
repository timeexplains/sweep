package frame;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import component.MyJLabel;
import util.RandomUtil;
import cell.Cell;

/**
 * ɨ���Ƿ���Զ�н⡣�𰸣��ǵ�.����ȫ������ ��ò�Ҫ��3*3�ĸ�����ȫ�����׵�����.
 * 
 * @author zhihuaPeng
 * 
 */
public class MainFrame1 extends JFrame implements MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final int CELL_NUMBER = 8 * 8;

	public static final int ROW_NUMBER = 8;

	public static final int WIDTH = 300;

	public static final int HEIGHT = 300;

	/** ���׸��� */
	public static final int MINE_COUNT = 10;

	public static double screenWidth = Toolkit.getDefaultToolkit()
			.getScreenSize().getWidth();
	public static double screenHeight = Toolkit.getDefaultToolkit()
			.getScreenSize().getHeight();

	private static MainFrame1 mainFrame;
	private JMenu menu;
	private JButton[] button;
	private JMenuItem startGameItem;
	private Cell[] cells;
	private MyJLabel[] labels = null;

	private JMenuBar menuBar;

	private JMenuItem endGameItem;

	private boolean loaded = false;

	public MainFrame1() {
		this.setSize(WIDTH, HEIGHT);
		this.setTitle("ɨ����Ϸ");
		this.setName("ɨ����Ϸ");
		this.getContentPane().setLayout(new GridLayout(9,9));
		this.setResizable(false);
		this.setLocation((int) (screenWidth - WIDTH) / 2,
				(int) (screenHeight - HEIGHT) / 2);

		init();

		// ----------------------------------------------------------------------------------

		menu = new JMenu("�˵�");

		menuBar = new JMenuBar();

		menuBar.add(menu);

		startGameItem = new JMenuItem("��ʼ��Ϸ");
		endGameItem = new JMenuItem("������Ϸ");

		menu.add(startGameItem);
		menu.add(endGameItem);

		startGameItem.addMouseListener(this);

		

		this.getContentPane().add(menuBar);
		for(int i = 0;i<ROW_NUMBER - 1;i++){
			this.getContentPane().add(new JPanel());
		}
		// ----------------------------------------------------------------------------------
		createButtons();

		addButtonsToCells();

		addButtonsToFrame();

		// ------------------------------------------------------------------------------------

	}

	private void addButtonsToCells() {
		for (int i = 0; i < CELL_NUMBER; i++) {
			cells[i].setButton(button[i]);
		}
	}

	private void addButtonsActionListen() {

		for (int i = 0; i < CELL_NUMBER; i++) {

			final int cell = i;
			// �����Ԫ��û�е����
			if (cells[i].getState() == Cell.TYPE_UNCLICK) {
				System.out.println("cell[i]="+cells[i]+",button[i]="+button[i]+"i="+i);
				cells[i].getButton().addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						doClick(cell);

					}
				});
			}
		}

	}

	private void addButtonsToFrame() {
		for (int i = 0; i < CELL_NUMBER; i++) {
	//		GridBagConstraints c = getConstraint(i);
			getContentPane().add(button[i]);
		}
	}

	/**
	 * ������cell������
	 * 
	 * @param cell
	 */
	protected void doClick(int cell) {
		// �����һ����
		if (cells[cell].isMine()) {
			paintFailed();
		}

		else {
			// ��Χû�е���
			if (cells[cell].getNum() == 0) {
				disappearLargestCells(cell);
				refreshPaint();
			}
			// ��Χ�е���
			else {
				cells[cell].setState(Cell.TYPE_CLICKED);
				refreshPaint();
			}
			// Ӯ�ñ���
			if (isWinGame()) {
				paintWin();
			}

		}

	}

	/**
	 * ����Ӯ�ý���
	 */
	private void paintWin() {
		
		for(int i=0;i<CELL_NUMBER;i++){
			if(cells[i].isMine()){
				cells[i].setState(Cell.TYPE_CLICKED);
			}
		}
		
		refreshPaint();
		
		JOptionPane.showMessageDialog(getInstance(), "��ϲ��,Ӯ���˱���.");

	}

	/**
	 * �Ƿ�Ӯ�ñ���,���ʣ�µ�ȫ�ǵ���û�з��� return true;else return false;
	 * 
	 * @return
	 */

	private boolean isWinGame() {

		for (int i = 0; i < CELL_NUMBER; i++) {
			// ��
			if (cells[i].getState() == Cell.TYPE_UNCLICK && !cells[i].isMine()) {
				return false;
			}
		}

		return true;
	}

	/**
	 * ˢ�½���
	 */
	private void refreshPaint() {

		for (int i = 0; i < CELL_NUMBER; i++) {
		//	GridBagConstraints c = getConstraint(i);
			if (cells[i].hasNotClick()) {
				continue;
			}
			// ������������Ҫ����

			if (cells[i].isMine()) {
				labels[i] = new MyJLabel("X");
			} else {
				if (cells[i].getNum() == 0) {
					labels[i] = new MyJLabel(" ");
				} else {
					labels[i] = new MyJLabel(String.valueOf(cells[i].getNum()));
				}
			}
			this.getContentPane().remove(button[i]);
			this.getContentPane().add(labels[i],i+ROW_NUMBER);
			this.validate();
//			JComponent component = (JComponent) this.getContentPane().getComponent(i);
//			if(cells[i].isMine()){
//				component.setToolTipText("this is a mine");
//			}else{
//				component.setToolTipText("In his square,he has "+cells[i].getNum()+" mine.");
//			}
			
		}

	}

	/**
	 * �������Ŀո���,����
	 * 
	 * @param cell
	 */
	private void disappearLargestCells(int cell) {

		int row = cell / ROW_NUMBER;
		int col = cell % ROW_NUMBER;

		int beginRow = row - 1;
		int endRow = row + 1;
		int beginCol = col - 1;
		int endCol = col + 1;

		for (int tRow = beginRow; tRow <= endRow; tRow++) {
			for (int tCol = beginCol; tCol <= endCol; tCol++) {
				int pos = tRow * ROW_NUMBER + tCol;

				if (checkBounds(tRow) && checkBounds(tCol)
						&& cells[pos].hasNotClick()) {
					cells[pos].setState(Cell.TYPE_CLICKED);
					if (cells[pos].hasNoMine()) {
						disappearLargestCells(pos);
					}
				}
			}
		}

	}

	/**
	 * ���������.
	 */

	private void paintFailed() {

		JOptionPane
				.showMessageDialog(getInstance(), "You have losed the game!");
	}

	@SuppressWarnings({ "static-access", "unused" })
	private void reset() {
		this.getInstance().removeAllLabelFromFrame();
		this.getInstance().init();
		this.getInstance().addButtonsToFrame();
		this.getInstance().generateMines();
		this.getInstance().calCellsStatus();
		this.validate();

	}

	/**
	 * ����cells
	 */
	private void calCellsStatus() {

		for (int i = 0; i < CELL_NUMBER; i++) {
			int count = getMineCountOfCell(i);
			cells[i].setNum(count);

			// System.out.println(cells[i]);
		}

	}

	/**
	 * ����ø��Ӹ����ж��ٵ���
	 * 
	 * @param i
	 */
	private int getMineCountOfCell(int i) {

		if (cells[i].isMine()) {
			return Cell.PROPERTY_MINE;
		}

		int count = 0;

		int row = i / ROW_NUMBER;
		int col = i % ROW_NUMBER;

		int beginRow = row - 1;
		int endRow = row + 1;
		int beginCol = col - 1;
		int endCol = col + 1;

		for (int tRow = beginRow; tRow <= endRow; tRow++) {
			for (int tCol = beginCol; tCol <= endCol; tCol++) {
				int pos = tRow * ROW_NUMBER + tCol;
				if (checkBounds(tRow) && checkBounds(tCol)
						&& cells[pos].isMine()) {
					count++;
				}

			}

		}
		return count;
	}

	/**
	 * �Ƿ�Խ��
	 * 
	 * @param t
	 * @return
	 */

	private boolean checkBounds(int t) {
		if (t >= 0 && t < ROW_NUMBER) {
			return true;
		}
		return false;
	}

	/**
	 * ��ʼ����Ԫ��,�����ǵ���.��δ����
	 */
	private void init() {

		if(loaded){
			
			for(int i = 0;i<MainFrame.CELL_NUMBER;i++){
			
				cells[i].setMine(false);
				cells[i].setState(Cell.TYPE_UNCLICK);
			}
			
			return;
		}
		
		cells = new Cell[MainFrame.CELL_NUMBER];
		labels = new MyJLabel[MainFrame.CELL_NUMBER];

		for (int i = 0; i < MainFrame.CELL_NUMBER; i++) {
			labels[i] = null;

			cells[i] = new Cell();
			cells[i].setMine(false);
			cells[i].setState(Cell.TYPE_UNCLICK);
			
		}
	}

	/**
	 * 
	 */
	private void removeAllLabelFromFrame() {
		
		for (int i = 0; i < MainFrame.CELL_NUMBER; i++) {

			if (!cells[i].hasNotClick()) {
				this.getContentPane().remove(labels[i]);
				this.validate();
			}

		}
	}

	/**
	 * ���ɵ���
	 */
	private void generateMines() {

		for (int i = 0; i < MINE_COUNT; i++) {

			int row = RandomUtil.getRandomNum(ROW_NUMBER);
			int col = RandomUtil.getRandomNum(ROW_NUMBER);

			if (!generatedBefore(row, col)) {
				generateMine(row, col);
			}

		}

	}

	/**
	 * ����Զ����Ӯ��ô?
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
	@SuppressWarnings("unused")
	@Deprecated
	private boolean isNotNeverWinGame(int row, int col) {
		return false;
	}

	/**
	 * �Ƿ��Ѿ����ù�����
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
	private boolean generatedBefore(int row, int col) {
		int cell = row * ROW_NUMBER + col;

		if (cells[cell].isMine()) {
			return true;
		}
		return false;
	}

	/**
	 * �ŵ���
	 * 
	 * @param row
	 * @param col
	 */
	private void generateMine(int row, int col) {
		int cell = row * ROW_NUMBER + col;

		cells[cell].setMine(true);
		cells[cell].setNum(Cell.PROPERTY_MINE);

	}

	/**
	 * ����ĳ��button�Ĳ���
	 * 
	 * @param cell
	 * @return
	 */
	private GridBagConstraints getConstraint(int cell) {
		int row = cell / ROW_NUMBER;
		int col = cell % ROW_NUMBER;

		GridBagConstraints c = new GridBagConstraints();
		c.gridx = row;
		c.gridy = col + 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 100 / ROW_NUMBER;
		c.weighty = 100 / (ROW_NUMBER + 1);
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(0, 0, 0, 0);
		c.ipadx = 0;
		c.ipady = 0;

		return c;

	}

	/**
	 * ����buttons
	 */
	private void createButtons() {
		button = new JButton[MainFrame.CELL_NUMBER];

		for (int i = 0; i < MainFrame.CELL_NUMBER; i++) {
			button[i] = new JButton();
		}

	}

	/**
	 * 
	 * @return
	 */
	public static synchronized MainFrame1 getInstance() {
		if (mainFrame == null) {
			mainFrame = new MainFrame1();
		}
		return mainFrame;
	}

	/**
	 * 
	 */
	private void initFrame() {

		System.out.println("init frame begin....");

		if (!loaded) {
			generateMines();

			calCellsStatus();

			addButtonsActionListen();

			this.loaded = true;
		} else {
			System.out.println("loaded="+loaded);
			removeAllLabelFromFrame();

			System.out.println(button.length);
	
			addButtonsToFrame();
		
			
			updateButtonsUI();
			
			this.validate();
			
			init();
			generateMines();
			calCellsStatus();
			
	
			addButtonsActionListen();
		}
	}

	private void updateButtonsUI() {
	
		for(int i = 0 ;i<this.button.length;i++){
			button[i].validate();
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("mouseClicked.source=" + e.getSource());

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// ��ʼ��Ϸ
		System.out.println("\n\nmousePressed.source=" + e.getSource());

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println("\n\nmouseReleased.source=" + e.getSource());
		if (e.getSource() == startGameItem) {

			initFrame();
		}
		// ������Ϸ
		if (e.getSource() == endGameItem) {
			// initFrame();
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		System.out.println("mouseEntered.source=" + e.getSource());

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

}
