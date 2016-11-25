package sk.upjs.ics.lyz_skola;

import java.util.List;

public interface ZakaznikDao {
    
    public List<Zakaznik> dajZakaznikov();
    public List<Zakaznik> podlaPriezviska(String priezvisko);
    public Zakaznik podlaId(Long id);
    public void pridajZakaznika(Zakaznik zakaznik);
}
