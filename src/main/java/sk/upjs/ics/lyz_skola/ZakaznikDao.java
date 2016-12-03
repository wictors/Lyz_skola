package sk.upjs.ics.lyz_skola;

import java.util.List;

public interface ZakaznikDao {
    
    public List<Zakaznik> dajZakaznikov();
    public List<Zakaznik> utriedenyPodlaPriezviska(String priezvisko);
    public Zakaznik podlaId(Long id);
    public Zakaznik podlaPriezviska(String priezvisko);
    public void pridajZakaznika(Zakaznik zakaznik);
}
