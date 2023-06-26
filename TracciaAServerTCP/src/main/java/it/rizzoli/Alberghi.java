package it.rizzoli;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;

public class Alberghi {

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

    public synchronized void add(Albergo c){
        this.listaAlberghi.add(c);
    }

    public synchronized String getAllAlberghi(){

        String json = new Gson().toJson(this.listaAlberghi);
        return json;

    }

    public synchronized String getAllAlberghiSorted(){

        List<Albergo> tempList = new ArrayList<>(this.listaAlberghi);

        tempList.sort((o1,o2)-> o1.getName().compareTo(o2.getName()));
        String json = new Gson().toJson(tempList);
        return json;

    }

    public synchronized String getMoreExprensive(){

        double max = 0;
        Albergo car = null;

        for (Albergo c:
                this.listaAlberghi) {
            if(c.getPrice() > max && c.isSuite()){
                max = c.getPrice();
                car = c;
            }
        }

        String json = new Gson().toJson(car);
        return json;
    }
}
