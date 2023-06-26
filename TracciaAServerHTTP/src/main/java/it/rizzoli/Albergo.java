package it.rizzoli;

public class Albergo {
    private String descrizione;
    private int id;
    private String name;
    private double price;

    private boolean suite;

    public Albergo(String descrizione, int id, String name, double price, boolean suite) {
        this.descrizione = descrizione;
        this.id = id;
        this.name = name;
        this.price = price;
        this.suite = suite;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isSuite() {
        return suite;
    }

    public void setSuite(boolean suite) {
        this.suite = suite;
    }

    @Override
    public String toString() {
        return "<tr><td>"+ this.descrizione+"</td><td>"+this.id+"</td><td>"+this.name+"</td><td>"+this.price+"</td><td>"+this.suite+"</td></tr>";
    }
}
