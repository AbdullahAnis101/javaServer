package Echo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.Assert.assertEquals;

/**
 * Echo back whatever messages it receives from clients
 */
public class EchoServer {
    EchoClient client = new EchoClient();
    public void start(int port) throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        Socket clientSocket = serverSocket.accept();
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        String inputLine;
        while((inputLine = in.readLine()) != null){
            if (".".equals(inputLine)){
                out.println("good bye");
                break;
            }
            out.println(inputLine);
        }

    }

    // initiates a connection with a server
    @Before
    public void setup() throws IOException {

client.startConnection("127.0.0.1",4444);
    }
    //release all resources
    @After
    public void tearDown() throws IOException {

        client.stopConnection();
    }
    @Test
    public void givenClient_whenServerEchosMessage_thenCorrect() throws IOException {
        EchoServer server = new EchoServer();
        server.setup();
        EchoClient client = new EchoClient();
        String resp1 = client.sendMessage("hello");
        String resp2 = client.sendMessage("world");
        String resp3 = client.sendMessage("!");
        String resp4 = client.sendMessage(".");

        assertEquals("hello", resp1);
        assertEquals("world", resp2);
        assertEquals("!",resp3);
        assertEquals("good bye", resp4);
    }


}
