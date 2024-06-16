package project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JTextArea;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data
public class Server {

	// 접속한 유저
	private Vector<ConnectedUser> connectedUsers = new Vector<>();

	// 방
	private Vector<MyRoom> myRooms = new Vector<>();

	private ServerFrame serverFrame;
	LoginFrame loginFrame;

	private String userId;
	private String roomId;

	private JTextArea mainBoard;

	// 포트 번호 지정
	private static final int PORT = 10001;

	// 소켓 생성
	private ServerSocket serverSocket;
	private Socket socket;

	private PrintWriter writer;
	private BufferedReader reader;

	// 프로토콜
	private String protocol;
	private String from;
	private String message;

	private boolean roomCheck;

	public Server() {
		serverFrame = new ServerFrame(this);
		roomCheck = true;
		mainBoard = serverFrame.getMainBoard();
		serverInfo("[서버 입장]");
		startServer();
	}

	public void startServer() {
		try {
			serverSocket = new ServerSocket(PORT);

			connectClient();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 서버 대기
	private void connectClient() {

		new Thread(() -> {

			while (true) {
				try {
					socket = serverSocket.accept();
					// serverInfo(userId + "님이 접속하셨습니다 !");

					ConnectedUser user = new ConnectedUser(socket);
					// MyRoom room = new MyRoom(roomId, user);
					// user.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}).start();

	}

	private void broadCast(String msg) {
		for (int i = 0; i < connectedUsers.size(); i++) {
			ConnectedUser user = connectedUsers.elementAt(i);
			user.getWriter().println(msg);
		}
	}

	private void receiveRoom() {
		try {
			roomId = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 서버 창에 띄우는 알림
	public void serverInfo(String str) {
		try {
			mainBoard.append(str + "\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 여러명의 친구들
	@Getter
	@Setter
	private class ConnectedUser {

		private Socket socket;

		private PrintWriter writer;
		private BufferedReader reader;

		private String userId;
		private String myRoomName;

		public ConnectedUser(Socket socket) {
			this.socket = socket;
			connectIO();
		}

		private void connectIO() {
			try {
				writer = new PrintWriter(socket.getOutputStream(), true);
				reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

				sendInfo();
				readThread();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		private void sendInfo() {
			try {
				userId = reader.readLine();
				serverInfo("[" + userId + "]" + " 님이 접속하셨습니다 !");

				newFriends();
				connectedUser();
				madeRoom();
				//myRoomName = reader.readLine();
				

				//makeRoom();

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		private void connectedUser() {
			for (int i = 0; i < connectedUsers.size(); i++) {
				ConnectedUser user = connectedUsers.elementAt(i);
				writer.println("ConnectedUser/" + user.userId);
			}
		}

		public void newFriends() {
			connectedUsers.add(this);
			broadCast("newFriends/" + userId);
		}
		public void madeRoom() {
			for (int i = 0; i < myRooms.size(); i++) {
				MyRoom myRoom = myRooms.elementAt(i);
				writer.println("MadeRoom/" + myRoom.roomName);
			}
		}

		private void readThread() {

			new Thread(() -> {

				while (true) {
					try {
						String str = reader.readLine();
						checkProtocol(str);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			}).start();

		}

		private void checkProtocol(String str) {
			StringTokenizer tokenizer = new StringTokenizer(str, "/");

			protocol = tokenizer.nextToken();
			from = tokenizer.nextToken();

			if (protocol.equals("MakeRoom")) {
				makeRoom();
			}
		}

		public void makeRoom() {

				myRoomName = from;
				MyRoom myRoom = new MyRoom(from, this);
				myRooms.add(myRoom);

				newRoom();
				writer.println("MakeRoom/" + from);
				serverInfo( userId + " 님이 " + "[ " + from + " ]" + " 방을 만들었습니다 !");
		}

		

		public void newRoom() {
			broadCast("NewRoom/" + from);
		}

	}// end of ConnectedUser class

	// 방
	@Data
	private class MyRoom {

		private String roomName;

		private Vector<ConnectedUser> myRoom = new Vector<>();

		public MyRoom(String roomName, ConnectedUser connectedUser) {
			this.roomName = roomName;
			this.myRoom.add(connectedUser);
			connectedUser.myRoomName = roomName;
		}

		private void roomBroadCast(String msg) {
			for (int i = 0; i < myRoom.size(); i++) {
				ConnectedUser user = myRoom.elementAt(i);

				user.getWriter().println(msg);
			}
		}

		private void addUser(ConnectedUser connectedUser) {
			myRoom.add(connectedUser);
		}

	}// end of MyRoom class

	public static void main(String[] args) {
		new Server();
	}

}// end of class
