package sk.upjs.ics.lyz_skola;

public class Instruktor {
    
    private Long id;
    private String meno;
    private String priezvisko;
    private String email;
    private String akreditacia;
    private String typ;
    private String heslo;

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public String getHeslo() {
        return heslo;
    }

    public void setHeslo(String heslo) {
        this.heslo = heslo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMeno() {
        return meno;
    }

    public void setMeno(String meno) {
        this.meno = meno;
    }

    public String getPriezvisko() {
        return priezvisko;
    }

    public void setPriezvisko(String priezvisko) {
        this.priezvisko = priezvisko;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAkreditacia() {
        return akreditacia;
    }

    public void setAkreditacia(String akreditacia) {
        this.akreditacia = akreditacia;
    }    
}
