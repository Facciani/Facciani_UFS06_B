package it.rizzoli;

public class Command {
    private String stato;
    private String risultato;

    public Command() {
        this.stato = "";
        this.risultato = "";
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public String getRisultato() {
        return risultato;
    }

    public void setRisultato(String risultato) {
        this.risultato = risultato;
    }
}
