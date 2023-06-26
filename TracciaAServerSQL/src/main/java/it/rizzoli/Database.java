package it.rizzoli;

import com.google.gson.Gson;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private static Database instance ;
    private Connection connection;
    private Command command;
    private String url = "jdbc:mysql://localhost/esamejava?user=root&password=";

    private Database() {
        this.command = new Command();
        try {
            this.connection = DriverManager.getConnection(this.url);

            System.out.println("---------------------------------");
            System.out.println("Connessione con database riuscita");
            System.out.println("---------------------------------");

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    public static synchronized Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public synchronized Command getMoreExpensive(){
        Statement stmt = null;
        ResultSet rs = null;
        Albergo albergo = new Albergo();

        String result = "";

        try {
            stmt = this.connection.createStatement();

            if (stmt.execute("SELECT descrizione, id, name, price, suite FROM alberghi WHERE price = ( SELECT MAX(price) FROM alberghi ) AND suite = 1 ORDER BY price ASC LIMIT 1;")) {
                rs = stmt.getResultSet();

                if(!rs.isBeforeFirst()){
                    this.command.setStato("Error");
                    this.command.setRisultato("Nessun dato presente");

                    return this.command;
                }else{

                    while(rs.next()){
                        String descrizione = rs.getString("descrizione");
                        int id = rs.getInt("id");
                        String name = rs.getString("name");
                        double price = rs.getDouble("price");
                        boolean suite = rs.getBoolean("suite");

                        albergo.setAll(descrizione,id,name,price,suite);
                    }

                    this.command.setStato("OK");
                    this.command.setRisultato(new Gson().toJson(albergo));

                    return this.command;
                }
            }

        }
        catch (SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                    System.out.println("SQLException: " + sqlEx.getMessage());
                    System.out.println("SQLState: " + sqlEx.getSQLState());
                    System.out.println("VendorError: " + sqlEx.getErrorCode());
                }
                rs = null;
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                    System.out.println("SQLException: " + sqlEx.getMessage());
                    System.out.println("SQLState: " + sqlEx.getSQLState());
                    System.out.println("VendorError: " + sqlEx.getErrorCode());
                }
                stmt = null;
            }
        }

        this.command.setStato("Errore");
        this.command.setRisultato("Impossibile eseguire il comando");

        return this.command;
    }

    public synchronized Command getAll(){
        Statement stmt = null;
        ResultSet rs = null;
        List<Albergo> listAlbergo = new ArrayList<>();

        String result = "";

        try {
            stmt = this.connection.createStatement();

            if (stmt.execute("SELECT descrizione, id, name, price, suite FROM alberghi;")) {
                rs = stmt.getResultSet();

                if(!rs.isBeforeFirst()){
                    this.command.setStato("Error");
                    this.command.setRisultato("Nessun dato presente");

                    return this.command;
                }else{

                    while(rs.next()){
                        String descrizione = rs.getString("descrizione");
                        int id = rs.getInt("id");
                        String name = rs.getString("name");
                        double price = rs.getDouble("price");
                        boolean suite = rs.getBoolean("suite");

                        Albergo albergo = new Albergo();
                        albergo.setAll(descrizione,id,name,price,suite);

                        listAlbergo.add(albergo);
                    }

                    this.command.setStato("OK");
                    this.command.setRisultato(new Gson().toJson(listAlbergo));

                    return this.command;
                }
            }

        }
        catch (SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                    System.out.println("SQLException: " + sqlEx.getMessage());
                    System.out.println("SQLState: " + sqlEx.getSQLState());
                    System.out.println("VendorError: " + sqlEx.getErrorCode());
                }
                rs = null;
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                    System.out.println("SQLException: " + sqlEx.getMessage());
                    System.out.println("SQLState: " + sqlEx.getSQLState());
                    System.out.println("VendorError: " + sqlEx.getErrorCode());
                }
                stmt = null;
            }
        }

        this.command.setStato("Errore");
        this.command.setRisultato("Impossibile eseguire il comando");

        return this.command;
    }

    public synchronized Command getAllSorted(){
        Statement stmt = null;
        ResultSet rs = null;
        List<Albergo> listAlbergo = new ArrayList<>();

        String result = "";

        try {
            stmt = this.connection.createStatement();

            if (stmt.execute("SELECT descrizione, id, name, price, suite FROM alberghi ORDER BY name ASC;")) {
                rs = stmt.getResultSet();

                if(!rs.isBeforeFirst()){
                    this.command.setStato("Error");
                    this.command.setRisultato("Nessun dato presente");

                    return this.command;
                }else{

                    while(rs.next()){
                        String descrizione = rs.getString("descrizione");
                        int id = rs.getInt("id");
                        String name = rs.getString("name");
                        double price = rs.getDouble("price");
                        boolean suite = rs.getBoolean("suite");

                        Albergo albergo = new Albergo();
                        albergo.setAll(descrizione,id,name,price,suite);

                        listAlbergo.add(albergo);
                    }

                    this.command.setStato("OK");
                    this.command.setRisultato(new Gson().toJson(listAlbergo));

                    return this.command;
                }
            }

        }
        catch (SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                    System.out.println("SQLException: " + sqlEx.getMessage());
                    System.out.println("SQLState: " + sqlEx.getSQLState());
                    System.out.println("VendorError: " + sqlEx.getErrorCode());
                }
                rs = null;
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                    System.out.println("SQLException: " + sqlEx.getMessage());
                    System.out.println("SQLState: " + sqlEx.getSQLState());
                    System.out.println("VendorError: " + sqlEx.getErrorCode());
                }
                stmt = null;
            }
        }

        this.command.setStato("Errore");
        this.command.setRisultato("Impossibile eseguire il comando");

        return this.command;
    }
}
