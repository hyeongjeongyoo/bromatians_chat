package project;

import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatFrame extends JFrame{
	
	static Client mContext;
	
	private JLabel chatBackground;
	
	// 텍스트 컴포넌트
	private JTextArea chatMessageBox;
	private JTextField chatWritBox;
	
	// 텍스트 전송
	private JLabel sendMessage;
	
	public ChatFrame(Client mContext) {
		initData();
		setInitLayout();
	}
	
	public void initData() {		
		chatBackground = new JLabel(new ImageIcon("img/chattingBg.jpg"));
		chatMessageBox = new JTextArea();
		chatWritBox = new JTextField();
		
		setTitle("CHATTING ROOM");
		setSize(400, 666);
		setContentPane(chatBackground);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setIconImage(Toolkit.getDefaultToolkit().getImage("img/favchat.png"));
	}
	
	public void setInitLayout() {
		chatBackground.add(chatMessageBox);

		chatMessageBox.setEditable(false);
		chatWritBox.setBounds(40,20,300,350);
		//chatWritBox.setBorder(new TitledBorder(new LineBorder(COLOR.BLACK,5), "ddd"));
		
		setLayout(null);
		setResizable(false); // 사이즈 조절 불가
		setLocationRelativeTo(null); // 가운데 배치
		setVisible(true);
	}
	
	public static void main(String[] args) {
		try {
			new ChatFrame(mContext);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
