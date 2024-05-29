package project;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import lombok.Getter;

@Getter
public class LoginMessage extends JFrame{
	
	// background
	private JLabel backgroundLogin;
	
	// button
	private JLabel okBtn;
	
	// id 값을 받음
	private JLabel idName;
	String id;
	
	Font font = new Font("Noto Sans KR", Font.BOLD, 14);
	
	public LoginMessage(String id) {
		this.id = id;
		initData();
		setInitLayout();
		addEventListener();
	}
	

	public void initData() {
		backgroundLogin = new JLabel(new ImageIcon("img/loginMsg.jpg"));
		okBtn = new JLabel(new ImageIcon("img/okBtn.jpg"));
		idName = new JLabel(id + " 님 환영합니다 !");
		
		setTitle("Welcome");
		setSize(280, 190);
		setContentPane(backgroundLogin);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("img/fav.png"));
	}
	
	public void setInitLayout() {
		setLayout(null);
		setResizable(false); 			// 사이즈 조절 불가
		setLocationRelativeTo(null); 	// 가운데 배치
		setVisible(true);
		
		backgroundLogin.add(okBtn);
		okBtn.setSize(67,27);
		okBtn.setLocation(100, 110);
		okBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		backgroundLogin.add(idName);
		idName.setFont(font);
		idName.setSize(300, 50);
		idName.setLocation(70, 35);
	}
	
	public void addEventListener() {
		okBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				System.out.println("접속성공");
			}
		});
	}




}// end of class
