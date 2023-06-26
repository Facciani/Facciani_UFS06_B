package it.rizzoli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import com.google.gson.Gson;

public class ServerThread extends Thread{
    private Socket clientSocket;
    private BufferedReader in;
    private Command command;
    private PrintWriter out;

    private Alberghi alberghi;

    public ServerThread(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        this.command = new Command();
        this.in = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
        this.out = new PrintWriter(this.clientSocket.getOutputStream(), true);
        this.alberghi = Alberghi.getInstance();
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
            if(s.equalsIgnoreCase("more_expensive_suite")){

                this.command.setStatus("OK");
                this.command.setResult(alberghi.getMoreExprensive());

                out.println(new Gson().toJson(this.command));
                out.flush();

            } else if (s.equalsIgnoreCase("all")) {

                this.command.setStatus("OK");
                this.command.setResult(alberghi.getAllAlberghi());

                out.println(new Gson().toJson(this.command));
                out.flush();

            } else if (s.equalsIgnoreCase("all_sorted")) {

                this.command.setStatus("OK");
                this.command.setResult(alberghi.getAllAlberghiSorted());

                out.println(new Gson().toJson(this.command));
                out.flush();

            }else {

                this.command.setStatus("Error");
                this.command.setResult("Comando non valido");

                out.println(new Gson().toJson(this.command));
                out.flush();

            }
        }
        this.clientSocket.close();
    }
}
