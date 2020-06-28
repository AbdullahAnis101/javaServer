package Echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class EchoClient {
    private Socket socket;
    private PrintWriter printWriter;
    private BufferedReader bufferedReader;

public void startConnection(String ip, int port) throws IOException {
   socket = new Socket(ip, port);
   printWriter = new PrintWriter(socket.getOutputStream(),true);
   bufferedReader = new BufferedReader (new InputStreamReader(socket.getInputStream()));
}
public String sendMessage(String msg) throws IOException {
printWriter.println(msg);
    return bufferedReader.readLine();
}
public void stopConnection() throws IOException {
    socket.close();
    printWriter.close();
    bufferedReader.close();
}
}