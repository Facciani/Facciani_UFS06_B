package it.rizzoli;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Alberghi {

    private final String OpenTable = "<html><body><table>\n" +
            "  <tr>\n" +
            "    <th>Prezzo</th>\n" +
            "    <th>Marca</th>\n" +
            "    <th>KM</th>\n" +
            "    <th>Voto</th>\n" +
            "  </tr>";
    private final String CloseTable = "</html></body></table>";

    public String getOpenTable() {
        return OpenTable;
    }

    public String getCloseTable() {
        return CloseTable;
    }

    private static Alberghi instance ;


    public static synchronized Alberghi getInstance() {
        if (instance == null) {
            instance = new Alberghi();
        }
        return instance;
    }

    private List<Albergo> listaAlberghi;
    private Command command;

    public Alberghi(){
        this.listaAlberghi = new ArrayList<Albergo>();
        this.command = new Command();
    }

    public void add(Albergo albergo){
        this.listaAlberghi.add(albergo);
    }

    public String getAllAlberghi(){

        String result = "";

        for (Albergo albergo:
                this.listaAlberghi) {
            result += albergo.toString();
        }

        return result;

    }

    public String getAllAlberghiSorted(){

        List<Albergo> tempList = new ArrayList<>(this.listaAlberghi);

        tempList.sort((o1,o2)-> o1.getName().compareTo(o2.getName()));

        String result = "";

        for (Albergo albergo:
                tempList) {
            result += albergo.toString();
        }

        return result;

    }

    public String getMoreExprensive(){

        double max = 0;
        Albergo albergo = null;

        for (Albergo a:
                this.listaAlberghi) {
            if(a.getPrice() > max && a.isSuite()){
                max = a.getPrice();
                albergo = a;
            }
        }

        if(albergo == null){
            return "<p>Nessun albergo in elenco</p>";
        }else{
            String result = "";
            result = albergo.toString();
            return result;
        }
    }
}
