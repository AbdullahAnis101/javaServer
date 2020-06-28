package Greet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * two way communication application where the client greets the server and the server responds
 */
public class GreetServer {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void start(int port) throws IOException {
        /**
         * Creates a new server socket. A server socket waits for requests to come in over a network. It performs based on that request, and then possibly returns a result to the requester.
         */
        serverSocket = new ServerSocket(port);
        /**
         * clientSocket listens for a communication to be made to this socket and then accepts it.
         */
        clientSocket = serverSocket.accept();
        out = new PrintWriter(clientSocket.getOutputStream(),true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String greeting = in.readLine();
        if ("hello server".equals(greeting)){
            out.println("hello client");
        }else{
            out.println("unrecognised greeting");
        }

    }

    public void stop() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }

    public static void main(String [] args) throws IOException {
        GreetServer server = new GreetServer();
        server.start(6666);
    }
}
