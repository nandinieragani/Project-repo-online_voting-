package com.java.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
 
public class VotingServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(1026)) {
            System.out.println("Server is listening on port 1026");
            while (true) {
                Socket socket = serverSocket.accept();
                new VotingServerThread(socket).start();
            }
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
 
class VotingServerThread extends Thread {
    private Socket socket;
 
    public VotingServerThread(Socket socket) {
        this.socket = socket;
    }
 
    public void run() {
        try (InputStream input = socket.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(input));
             OutputStream output = socket.getOutputStream();
             PrintWriter writer = new PrintWriter(output, true)) {
 
            String text;
            while ((text = reader.readLine()) != null) {
                System.out.println("Received: " + text);
                writer.println("Echo: " + text);
            }
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
