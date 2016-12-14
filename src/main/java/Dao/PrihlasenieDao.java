package Dao;

import Entity.Prihlasenie;
import java.util.List;

public interface PrihlasenieDao {
    
    public List<Prihlasenie> dajPrihlasenia();
    public void ulozPrihlasenie (Prihlasenie prihlasenie);
    
}
