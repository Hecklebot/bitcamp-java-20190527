package ch23.j.client;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ChatClient extends Frame implements ActionListener {
  private static final long serialVersionUID = 1L;
  TextField addressTF = new TextField(20);
  TextField portTF = new TextField(4);
  Button connectBtn = new Button("connect");
  TextArea chattingPane = new TextArea();
  TextField messageTF = new TextField();
  Button sendBtn = new Button("send");
  Socket socket;
  BufferedReader in;
  PrintStream out;

  public ChatClient(String title) {
    super(title);
    this.setSize(600, 480);

    // 윈도우 관련 이벤트를 처리할 담당자를 설정한다.
    // -> 옵저버 패턴
    // -> 윈도우 이벤트가 발생했을 때, 보고를 받을 객체를 등록한다.
    this.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        try {
          in.close();
        } catch (Exception ex) {
        }
        try {
          out.close();
        } catch (Exception ex) {
        }
        try {
          socket.close();
        } catch (Exception ex) {
        }
        System.exit(0);
      }
    });

    Panel topPane = new Panel();

    topPane.add(addressTF);
    topPane.add(portTF);
    topPane.add(connectBtn);
    this.add(topPane, BorderLayout.NORTH);

    this.add(chattingPane, BorderLayout.CENTER);


    Panel bottomPane = new Panel();
    bottomPane.setLayout(new BorderLayout());
    bottomPane.add(messageTF, BorderLayout.CENTER);
    bottomPane.add(sendBtn, BorderLayout.EAST);
    this.add(bottomPane, BorderLayout.SOUTH);

    // connect 버튼에 대해 리스너 등록하기
    connectBtn.addActionListener(this);

    // send 버튼에 대해 리스너 등록하기
    sendBtn.addActionListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == sendBtn) {
      // 서버에 전송하고
      out.println(messageTF.getText() + "\n");
      // 입력창의 메세지는 지운다.
      messageTF.setText("");
      
    }

    if (e.getSource() == connectBtn) {
      connectChatServer();
    }
  }

  private void connectChatServer() {
    try {
      socket = new Socket(addressTF.getText(), Integer.parseInt(portTF.getText()));
      in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      out = new PrintStream(socket.getOutputStream());

      chattingPane.append("서버와 연결됨\n");
      // 메세지 수신을 별도의 담당자에게 맡긴다.
      new Thread(new MessageReceiver()).start();
      
      // 메세지 전송은 버튼을 누를 때 마다 메인 스레드가 처리할 것인다.
    } catch (Exception e) {
      chattingPane.append("서버연결 오류\n");
    }
  }


  class MessageReceiver implements Runnable {
    @Override
    public void run() {
      try {
        while (true) {
          String message = in.readLine();
          chattingPane.append(message + "\n");
        }
      } catch (Exception e) {
        chattingPane.append("메세지 수신 중 오류발생\n");
      }
    }
  }

  public static void main(String[] args) {
    ChatClient app = new ChatClient("비트 채팅!");
    app.setVisible(true);
  }
}



// Java 윈도우 프로그래밍
// 1) AWT
// 2) Swing
// 3) Eclipse SWT
// 4) JavaFX
