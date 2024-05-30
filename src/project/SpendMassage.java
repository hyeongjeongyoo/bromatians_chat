package project;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpendMassage extends JFrame{

	private WaitingRoomFrame mContext;
	
	// background
	private JLabel roomBackground;
	
	// button
	private JLabel okBtn;
	
	private JTextField idRoomName;
	
	private Socket socket;
	private PrintWriter writer;
	private BufferedReader reader;
	
	// 입력한 방 값을 받음
	private JLabel msg;
	private String msgText;
	
	// 버튼 크기
	private final int BTN_WIDTH = 249;
	private final int BTN_HEIGHT = 44;
	
	Font font = new Font("Noto Sans KR", Font.BOLD, 14);
	
	public SpendMassage(String msgText) {
		this.msgText = msgText;
		initData();
		setInitLayout();
		addEventListener();
	}

	public void initData() {
		roomBackground = new JLabel(new ImageIcon("img/MassageMsg.jpg"));
		okBtn = new JLabel(new ImageIcon("img/okBtn.jpg"));
		idRoomName = new JTextField("쪽지내용",50);
		
		setTitle("Spend Massage");
		setSize(280, 190);
		setContentPane(roomBackground);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("img/favMsg.png"));
	}
	
	public void setInitLayout() {
		setLayout(null);
		setResizable(false); 			// 사이즈 조절 불가
		setLocationRelativeTo(null); 	// 가운데 배치
		setVisible(true);
		
		roomBackground.add(okBtn);
		okBtn.setSize(67,27);
		okBtn.setLocation(100, 110);
		okBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		roomBackground.add(idRoomName);
		idRoomName.setFont(font);
		idRoomName.setSize(BTN_WIDTH - 10, BTN_HEIGHT - 15);
		idRoomName.setLocation(13, 65);
	}
	
	public void addEventListener() {
		okBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				System.out.println("쪽지창 끔");
				//mContext.spendMsg();
			}
		});
	}



}
