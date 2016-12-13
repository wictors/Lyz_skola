package sk.upjs.ics.lyz_skola;

import java.util.List;

public interface InstruktorDao {
    
    public List<Instruktor> dajInstruktorov();
    public List<Instruktor> utriedenyPodlaPriezviska();
    public List<Instruktor> podlaPriezviska(String priezvisko);
    public List<Instruktor> podlaAkreditacie(String akreditacia);
    public List<Instruktor> podlaTypu(String typ);
    public boolean nemaHodinu(Long id);
    public Instruktor podlaEmailu(String email);
    public void pridajInstruktora(Instruktor instruktor);
    public void vymazInstruktora(Long id);
    public void aktualizujAkreditaciu(Long id, String akreditacia);
    public void aktualizujTyp(Long id, String typ);
    
}
