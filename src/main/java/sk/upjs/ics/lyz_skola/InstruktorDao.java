package sk.upjs.ics.lyz_skola;

import java.util.List;

public interface InstruktorDao {
    
    public List<Instruktor> dajInstruktorov();
    public void pridajInstruktora(Instruktor instruktor);
    public List<Instruktor> podlaPirezviska(String priezvisko);
    public Instruktor podlaId (Long id);
    
}
