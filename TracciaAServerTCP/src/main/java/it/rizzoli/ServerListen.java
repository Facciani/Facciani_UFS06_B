package it.rizzoli;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListen extends Thread{

    private Socket clientSocket;
    private ServerSocket serverSocket;

    public ServerListen(ServerSocket serverSocket) {
        this.clientSocket = null;
        this.serverSocket = serverSocket;
    }

    @Override
    public void run() {
        while (true){
            try {
                this.clientSocket = serverSocket.accept();
                System.out.println(this.clientSocket.getInetAddress() + " : Connesso");
                ServerThread thread = new ServerThread(this.clientSocket);
                thread.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
