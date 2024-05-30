package project;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WaitingRoomFrame extends JFrame{
	
	Client mContext;
	
	WaitingRoomFrame waitingRoonFrame;
	NewRoomMake newRoomMake;
	SpendMassage spendMassage;
	
	private Socket socket;
	private PrintWriter writer;
	private BufferedReader reader;
	
	private JList<String> friendsList;
	private JList<String> roomList;
	
	private Vector<String> friendsVector = new Vector<>();
	private Vector<String> roomVector = new Vector<>();
	
	private JLabel waitBackground;
	
	// 접속자
	private JPanel friendsPanel;
	private JScrollPane friendsscroll;
	
	// 방
	private JPanel roomsPanel;
	private JScrollPane roomsscroll;
	
	// 방만들기, 쪽지, 입장 아이콘
	private JLabel newRoom;
	private JLabel message;
	private JLabel goRoom;
	private final int ICON_WIDTH = 28;
	private final int ICON_HEIGHT = 37;
	
	// 방만들기, 쪽지, 입장 기능
	private JTextField roomName;
	private String roomId;
	private String userMsg;
	private String userId;


	public WaitingRoomFrame(Client mContext) {
		this.mContext = mContext;
		initData();
		setInitData();
		addEventListener();
	}
	
	public void initData() {
		waitBackground = new JLabel(new ImageIcon("img/waitingBg.jpg"));
		
		friendsPanel = new JPanel();
		roomsPanel = new JPanel();
		
		newRoom = new JLabel(new ImageIcon("img/newRoom.png"));
		message = new JLabel(new ImageIcon("img/msg.png"));
		goRoom = new JLabel(new ImageIcon("img/goRoom.png"));
		
		friendsList = new JList<>();
		roomList = new JList<>();
		friendsscroll = new JScrollPane(friendsList);
		roomsscroll = new JScrollPane(roomList);
		
		setTitle("WAITING ROOM");
		setSize(400, 666);
		setContentPane(waitBackground);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setIconImage(Toolkit.getDefaultToolkit().getImage("img/favRoom.png"));
	}
	
	public void setInitData() {

		
		// 메인패널 컴포넌트
		add(friendsPanel);
		friendsPanel.setBorder(new LineBorder(Color.BLACK, 3));
		friendsPanel.setBounds(15, 170, 360, 170);
		// friendsPanel.setPreferredSize(360, 170);
		friendsPanel.setBackground(Color.WHITE);
		
		friendsPanel.add(friendsscroll);
		friendsscroll.setBounds(20, 200, 400, 300);

		add(roomsPanel);
		roomsPanel.setBorder(new LineBorder(Color.BLACK, 3));
		roomsPanel.setBounds(15, 405, 360, 170);
		roomsPanel.setBackground(Color.WHITE);

		roomsPanel.add(roomsscroll);
		roomsscroll.setBounds(15, 415, 350, 155);
		
		waitBackground.add(newRoom);
		newRoom.setSize(ICON_WIDTH,ICON_HEIGHT);
		newRoom.setLocation(300, 128);
		newRoom.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스 커서 모양 변경
		
		waitBackground.add(message);
		message.setSize(ICON_WIDTH,ICON_HEIGHT);
		message.setLocation(340, 128);
		message.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스 커서 모양 변경
		
		waitBackground.add(goRoom);
		goRoom.setSize(ICON_WIDTH,ICON_HEIGHT);
		goRoom.setLocation(345, 365);
		goRoom.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스 커서 모양 변경
		
		setLayout(null);
		setResizable(false); 			// 사이즈 조절 불가
		setLocationRelativeTo(null); 	// 가운데 배치
		setVisible(true);
	}
	
	public void addEventListener() {
		newRoom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("방만들기 아이콘 클릭");
				new NewRoomMake(mContext);
			}
		});
		message.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("쪽지 아이콘 클릭");
				new SpendMassage(userMsg);
				// String makeRoomName = roomName.getText();
			}
		});
	}
	


}
