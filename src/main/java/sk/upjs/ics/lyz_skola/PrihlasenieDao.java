package sk.upjs.ics.lyz_skola;

import java.util.List;

public interface PrihlasenieDao {
    
    public List<Prihlasenie> dajPrihlasenia();
    public void ulozPrihlasenie (Prihlasenie prihlasenie);
    
}
