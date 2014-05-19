package cell;

import javax.swing.JButton;

/**
 * 
 * @author zhihuaPeng
 *
 */
public class Cell {
	public static final int TYPE_UNCLICK = 0;
	public static final int TYPE_CLICKED = 1;
	/**�ǵ���*/
	public static final int PROPERTY_MINE = 9 ;
	/**״̬*/
	private int state = 0;
	/***/
	private JButton button = null;
	/**�����ĵ�����Ŀ,���num��9�Ļ���ʾ�ǵ���*/
	private Integer num = null;
	
	private boolean isMine ;
	
	
	public Cell(){
		super();
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public JButton getButton() {
		return button;
	}

	public void setButton(JButton button) {
		this.button = button;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public boolean isMine() {
		return isMine;
	}

	public void setMine(boolean isMine) {
		this.isMine = isMine;
	}

	public boolean hasNoMine(){
		return num==0;
	}

	public boolean hasNotClick() {
		
		return state == TYPE_UNCLICK;
	}

	@Override
	public String toString() {
		return "Cell [state=" + state + ", button=" + button + ", num=" + num
				+ ", isMine=" + isMine + "]";
	}
	
	
}
