package it.rizzoli;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Hello world!
 *
 */
public class App 
{
    static final int portNumber = 1234;
    static final Database database = Database.getInstance();
    public static void main( String[] args )
    {


        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(portNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("----------------");
        System.out.println("Server avviato");
        System.out.println("---------------");

        ServerListen sl = new ServerListen(serverSocket);
        sl.start();
    }
}
