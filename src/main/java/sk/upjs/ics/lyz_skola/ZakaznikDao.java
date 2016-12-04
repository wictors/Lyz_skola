package sk.upjs.ics.lyz_skola;

import java.util.List;

public interface ZakaznikDao {
    
    public List<Zakaznik> dajZakaznikov();
    public List<Zakaznik> utriedenyPodlaPriezviska();
    public List<Zakaznik> podlaTelefonu(int telefon);
    public List<Zakaznik> podlaPriezviska(String priezvisko);
    public List<Zakaznik> podlaId(Long id);
    public void vymazZakaznika(Long id);
    public void aktualizujPoznamka(String poznamka, Long id);
    public void aktualizujTelefon(int telefon, Long id);
    public void pridajZakaznika(Zakaznik zakaznik);
}
