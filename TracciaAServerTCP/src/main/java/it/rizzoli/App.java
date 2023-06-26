package it.rizzoli;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Hello world!
 *
 */
public class App 
{
    public static int portNumber = 1234;
    public static Alberghi alberghi = Alberghi.getInstance();
    public static void main( String[] args )
    {
        alberghi.add(new Albergo("Hotel in pieno centro di milano",1,"Grand Hotel Milano", 299.9, true));
        alberghi.add(new Albergo("Albergo in stile barocco",12,"Attico Roma", 123.50, false));
        alberghi.add(new Albergo("Vivi Bologna",1,"Bologna gold hotel", 1299.9, true));
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
