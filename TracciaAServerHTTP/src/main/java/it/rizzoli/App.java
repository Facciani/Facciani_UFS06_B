package it.rizzoli;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;

/**
 * Hello world!
 *
 */
public class App
{
    public static Alberghi alberghi = Alberghi.getInstance();

    public static void main( String[] args )
    {
        alberghi.add(new Albergo("Hotel in pieno centro di milano",1,"Grand Hotel Milano", 299.9, true));
        alberghi.add(new Albergo("Albergo in stile barocco",12,"Attico Roma", 123.50, false));
        alberghi.add(new Albergo("Vivi Bologna",2,"Bologna gold hotel", 1299.9, true));

        HttpServer server = null;
        try{
            server = HttpServer.create(new InetSocketAddress(8080), 0);
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }

        System.out.println("----------------");
        System.out.println("Server avviato");
        System.out.println("---------------");

        if(server != null){
            server.createContext("/all", new MyHandlerAll());
            server.setExecutor(null);

            server.createContext("/all_sort", new MyHandlerSorted());
            server.setExecutor(null);

            server.createContext("/more_expensive", new MyHandlerMore());
            server.setExecutor(null);

            server.start();
        }else{
            System.out.println("Impossibile avviare il server");
        }
    }
}
