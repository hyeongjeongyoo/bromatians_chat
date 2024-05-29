package project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import javax.swing.JTextArea;


public class Server {
	
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
	
	private static Vector<PrintWriter> clientWriters = new Vector<>();
	
	public Server() {
		serverFrame = new ServerFrame(this);
		mainBoard = serverFrame.getMainBoard();
		serverInfo("[서버 입장]");
		startServer();
		
		
	}
	
	public void startServer() {
		try {
			serverSocket = new ServerSocket(PORT);
			socket = serverSocket.accept();
			
			writer = new PrintWriter(socket.getOutputStream(),true);
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			receiveId();
			serverInfo(userId + "님 접속하셨습니다 !");
			
			receiveRoomName();
			serverInfo(roomId + "방이 생성되었습니다 !");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 보낸 아이디를 받기 위한 메서드
	private void receiveId() {
		try {
			userId = reader.readLine();
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
	
	// 보낸 방이름을 받기 위한 메서드
	private void receiveRoomName() {
		try {
			roomId = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public static void main(String[] args) {
		new Server();
	}

	

}// end of class
