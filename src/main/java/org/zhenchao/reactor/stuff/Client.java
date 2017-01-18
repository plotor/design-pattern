package org.zhenchao.reactor.stuff;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * http://jeewanthad.blogspot.hk/2013/02/reactor-pattern-explained-part-1.html
 *
 * @author zhenchao.wang 2017-01-18 22:26
 * @version 1.0.0
 */
public class Client {

    private String host;

    private int port;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    private void run() throws IOException {
        Socket clientSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            clientSocket = new Socket(host, port);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Unknown host: " + host);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't connect to: " + host);
            System.exit(1);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Client connected to host : " + host + " port: " + port);
        System.out.println("Type (\"Bye\" to quit)");
        System.out.println("Tell what your name is to the Server.....");

        String typeIn;
        while ((typeIn = reader.readLine()) != null) {
            out.println(typeIn);
            // Break when client says Bye.
            if (typeIn.equalsIgnoreCase("Bye")) {
                break;
            }
            System.out.println("Server says: " + in.readLine());
        }

        out.close();
        in.close();
        reader.close();
        clientSocket.close();
    }

    public static void main(String[] args) throws IOException {
        Client client = new Client("127.0.0.1", 8080);
        client.run();
    }

}
