package sk.upjs.ics.lyz_skola;

import java.sql.Time;
import java.util.Date;

public class Hodina {

    private Long id;
    private String datum;
    private Time od;
    private Time po;
    private Zakaznik zakaznik;
    private Instruktor instruktor;
    private String typ;
    private boolean stav;

    public boolean isStav() {
        return stav;
    }

    public void setStav(boolean stav) {
        this.stav = stav;
    }
    

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }
    
    public Time getOd() {
        return od;
    }

    public void setOd(Time od) {
        this.od = od;
    }

    public Time getPo() {
        return po;
    }

    public void setPo(Time po) {
        this.po = po;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public Zakaznik getZakaznik() {
        return zakaznik;
    }

    public void setZakaznik(Zakaznik zakaznik) {
        this.zakaznik = zakaznik;
    }

    public Instruktor getInstruktor() {
        return instruktor;
    }

    public void setInstruktor(Instruktor instruktor) {
        this.instruktor = instruktor;
    }
  
}
