package project;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Client{

	// 프레임 창
	private LoginFrame loginFrame;

	private Socket socket;
	private PrintWriter writer;
	private BufferedReader reader;
	
	private String userId;

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

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 아이디를 보내기 위한 메서드
	private void spendId() {
		
		writer.println(userId);
		
	}
	
	public static void main(String[] args) {
		
		new Client();

	}// end of main

}// end of class
