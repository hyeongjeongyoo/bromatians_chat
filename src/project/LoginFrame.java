package project;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import lombok.Getter;

@Getter
public class LoginFrame extends JFrame {
	
	private Client mContext;

	LoginFrame loginFrame;
	LoginMessage loginMessage;
	
	// backgrund
	private JLabel backgroundLogin;

	// 컴포넌트
	private JLabel mainDogImg;
	private JTextField id;
	private JLabel logo;
	private JLabel logoBtn;
	private JLabel okBtn;

	// 버튼 크기
	private final int BTN_WIDTH = 249;
	private final int BTN_HEIGHT = 44;

	// 폰트 설정
	Font font = new Font("New Walt Disney UI", Font.BOLD, 20);
	Font font2 = new Font("Noto Sans KR", Font.BOLD, 18);
	Font font3 = new Font("Wicked Mouse", Font.BOLD, 18);

	public LoginFrame(Client mContext) {
		this.mContext = mContext;
		initData();
		setInitLayout();
		addEventListener();
	}

	public void initData() {
		loginFrame = this;
		backgroundLogin = new JLabel(new ImageIcon("img/backgroundBg.jpg"));
		mainDogImg = new JLabel(new ImageIcon("img/mainDogImg.png"));

		logo = new JLabel(new ImageIcon("img/mainLogo.png"));
		id = new JTextField("ID",5);
		logoBtn = new JLabel(new ImageIcon("img/mainLogin.png"));
		

		setTitle("BROMATIANS CHAT");
		setSize(400, 666);
		setContentPane(backgroundLogin);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setIconImage(Toolkit.getDefaultToolkit().getImage("img/fav.png"));
	}

	public void setInitLayout() {
		setLayout(null);
		setResizable(false); 			// 사이즈 조절 불가
		setLocationRelativeTo(null); 	// 가운데 배치
		setVisible(true);

		backgroundLogin.add(mainDogImg);
		mainDogImg.setSize(161, 161);
		mainDogImg.setLocation(115, 68);

		backgroundLogin.add(logo);
		logo.setSize(301, 118);
		logo.setLocation(45, 270);

		backgroundLogin.add(logoBtn);
		logoBtn.setSize(BTN_WIDTH, BTN_HEIGHT);
		logoBtn.setLocation(70, 490);
		logoBtn.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스 커서 모양 변경

		backgroundLogin.add(id);
		id.setFont(font2);
		id.setBorder(null);
		id.setSize(BTN_WIDTH - 10, BTN_HEIGHT - 10);
		id.setLocation(70, 435);
	}

	public void addEventListener() {
		logoBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mContext.setUserId(id.getText());
				
				if(mContext.getUserId().length() < 6) {
					new LoginMessage(mContext.getUserId());
					mContext.startClient();
				} else {
					// TODO 입력 초과시 이미지 추가
				}
				
			}
		});

	}
	
	
	
	
	class LoginMessage extends JFrame{
		
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
					loginFrame.setVisible(false);
					new WaitingRoonFrame();
				}
			});
		}





		
	}
	

}// end of class
