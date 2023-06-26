package it.rizzoli;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread extends Thread{
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter  out;
    private Command command;
    private Database database;

    public ServerThread(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        this.in = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
        out = new PrintWriter(this.clientSocket.getOutputStream(), true);
        this.command = new Command();
        this.database = Database.getInstance();
    }

    @Override
    public void run() {
        while(!clientSocket.isClosed()){
            try {
                readWrite();
            } catch (IOException e) {
                System.out.println(this.clientSocket.getInetAddress() + " : ERROR = " + e.getMessage());
            }
        }
        System.out.println(this.clientSocket.getInetAddress() + " : Disconnesso");
    }

    private void readWrite() throws IOException{
        String s = "";
        while ((s = in.readLine()) != null) {

            System.out.println(s + " : " + clientSocket.getInetAddress());
            if(s.equalsIgnoreCase("all")){

                this.command = database.getAll();
                out.println(new Gson().toJson(this.command));
                out.flush();

            } else if (s.equalsIgnoreCase("all_sorted")) {

                this.command = database.getAllSorted();
                out.println(new Gson().toJson(this.command));
                out.flush();

            } else if (s.equalsIgnoreCase("more_expensive_suite")) {

                this.command = database.getMoreExpensive();
                out.println(new Gson().toJson(this.command));
                out.flush();

            }else {

                this.command.setStato("Errore");
                this.command.setRisultato("Comando invalido");

                out.println(new Gson().toJson(this.command));
                out.flush();

            }


        }
        this.clientSocket.close();
    }

}