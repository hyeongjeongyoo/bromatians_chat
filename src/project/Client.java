package project;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Client{
	
	private Client mContext;
	// 프레임 창
	private LoginFrame loginFrame;
	private WaitingRoomFrame waitingRoonFrame;
	private NewRoomMake newRoomMake;
	
	private JList<String> friendsList;
	private JList<String> roomList;
	
	private Vector<String> friendsIdList = new Vector<>();
	private Vector<String> roomIdList = new Vector<>();

	private Socket socket;
	private PrintWriter writer;
	private BufferedReader reader;
	
	private String userId;
	private String roomsId;
	
	// 토크나이저
	private String protocol;
	private String from;
	private String message;

	public Client() {
		loginFrame = new LoginFrame(this);
	}

	public void startClient() {
		try {
			socket = new Socket("localhost", 10001);
			System.out.println("서버가 접속했습니다.");
			
			writer = new PrintWriter(socket.getOutputStream(),true);
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			spendId();
			readThread();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 아이디를 보내기 위한 메서드
	private void spendId() {
		
		writer.println(userId);
		
	}
	

	// 아이디를 보내기 위한 메서드
	public void spendRoom() {
		
		writer.println("newRooms/" + roomsId);
		
	}


	
	private void readThread() {
		
		new Thread(() -> {
			
			while(true) {
				try {
					String str = reader.readLine();
					
					checkProtocol(str);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}).start();
		
	}
	
	private void checkProtocol(String msg) {
		StringTokenizer tokenizer = new StringTokenizer(msg, "/");
		
		protocol = tokenizer.nextToken();
		from = tokenizer.nextToken();
		
		if(protocol.equals("newFriends")) {
			newFriends();
		}else if(protocol.equals("ConnectedUser")) {
			connectedUser();
		}else if(protocol.equals("newRooms")) {
			newRooms();
		}else if(protocol.equals("ConnectedRooms")) {
			connectedRooms();
		}
	}
	
	private void connectedUser() {
		friendsIdList.add(from);
		friendsList.setListData(friendsIdList);
	}
	
	private void connectedRooms() {
		friendsIdList.add(from);
		friendsList.setListData(friendsIdList);
	}

	public void newFriends() {
		if(!from.equals(this.userId)) {
			friendsIdList.add(from);
			friendsList.setListData(friendsIdList);
		}
	}
	
	public void newRooms() {
		if(!from.equals(this.roomsId)) {
			roomIdList.add(from);
			roomList.setListData(roomIdList);
		}
	}
	
	public void addComponent() {
		friendsList = waitingRoonFrame.getFriendsList();
		roomList = waitingRoonFrame.getRoomList();
	}
	
	
	public static void main(String[] args) {
		
		new Client();

	}// end of main

}// end of class
