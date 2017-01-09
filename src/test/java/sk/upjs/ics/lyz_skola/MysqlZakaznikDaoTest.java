package sk.upjs.ics.lyz_skola;

import Dao.*;
import Entity.*;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

public class MysqlZakaznikDaoTest {

    @Test
    public void testDajZakaznikov() {
        ZakaznikDao zakaznikDao = ObjectFactory.INSTANCE.getZakaznikDao();
        List<Zakaznik> result = zakaznikDao.dajZakaznikov();
        Assert.assertTrue(result.size() > 0);
    }

    @Test
    public void testUtriedenyPodlaPriezviska() {
        MysqlZakaznikDao dao = new MysqlZakaznikDao(ObjectFactory.INSTANCE.getJdbcTemplate());
        List<Zakaznik> zakaznici = dao.utriedenyPodlaPriezviska();
        Zakaznik zakaznik1 = zakaznici.get(0);
        Zakaznik zakaznik2 = zakaznici.get(1);
        if(zakaznik1.getPriezvisko().charAt(0) < zakaznik2.getPriezvisko().charAt(0)){
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testPodlaTelefonu() {
        MysqlZakaznikDao dao = new MysqlZakaznikDao(ObjectFactory.INSTANCE.getJdbcTemplate());
        Zakaznik zakaznik = new Zakaznik();
        zakaznik.setMeno("Milan");
        zakaznik.setPriezvisko("Duzda");
        zakaznik.setPoznamka("Zaciatocnik");
        zakaznik.setTelefon(944963852);
        dao.pridajZakaznika(zakaznik);
        List<Zakaznik> zakaznici = dao.podlaTelefonu(944963852);
        Zakaznik zakaznik1 = zakaznici.get(0);
        assertTrue(zakaznici.size() > 0);
        dao.vymazZakaznika(zakaznik1.getId());
    }

    @Test
    public void testPodlaPriezviska() {
        MysqlZakaznikDao dao = new MysqlZakaznikDao(ObjectFactory.INSTANCE.getJdbcTemplate());
        Zakaznik zakaznik = new Zakaznik();
        zakaznik.setMeno("Milan");
        zakaznik.setPriezvisko("Duzda");
        zakaznik.setPoznamka("Zaciatocnik");
        dao.pridajZakaznika(zakaznik);
        List<Zakaznik> zakaznici = dao.podlaPriezviska("Duzda");
        Zakaznik zakaznik1 = zakaznici.get(0);
        assertTrue(zakaznici.size() > 0);
        dao.vymazZakaznika(zakaznik1.getId());
    }

    @Test
    public void testPridajZakaznika() {
        MysqlZakaznikDao dao = new MysqlZakaznikDao(ObjectFactory.INSTANCE.getJdbcTemplate());
        List<Zakaznik> zakaznici = dao.dajZakaznikov();
        Zakaznik zakaznik = new Zakaznik();
        zakaznik.setMeno("Milan");
        zakaznik.setPriezvisko("Duzda");
        zakaznik.setPoznamka("Zaciatocnik");
        dao.pridajZakaznika(zakaznik);
        List<Zakaznik> noviZakaznici = dao.dajZakaznikov();
        Assert.assertTrue(zakaznici.size() + 1 == noviZakaznici.size());
        List<Zakaznik> pridaniZakaznici = dao.podlaPriezviska("Duzda");
        Zakaznik pridaniZakaznik = pridaniZakaznici.get(0);
        dao.vymazZakaznika(pridaniZakaznik.getId());
    }

    @Test
    public void testAktualizujPoznamka() {
        MysqlZakaznikDao dao = new MysqlZakaznikDao(ObjectFactory.INSTANCE.getJdbcTemplate());
        Zakaznik zakaznik = new Zakaznik();
        zakaznik.setMeno("Milan");
        zakaznik.setPriezvisko("Duzda");
        zakaznik.setPoznamka("Zaciatocnik");
        dao.pridajZakaznika(zakaznik);
        String poznamka = zakaznik.getPoznamka();
        dao.aktualizujPoznamka("Pokrocily", 1L);
        Assert.assertNotEquals(zakaznik, poznamka);
        List<Zakaznik> pridaniZakaznici = dao.podlaPriezviska("Duzda");
        Zakaznik pridaniZakaznik = pridaniZakaznici.get(0);
        dao.vymazZakaznika(pridaniZakaznik.getId());
    }

    @Test
    public void testAktualizujTelefon() {
        MysqlZakaznikDao dao = new MysqlZakaznikDao(ObjectFactory.INSTANCE.getJdbcTemplate());
        Zakaznik zakaznik = new Zakaznik();
        zakaznik.setMeno("Milan");
        zakaznik.setPriezvisko("Duzda");
        zakaznik.setPoznamka("Zaciatocnik");
        zakaznik.setTelefon(948456456);
        dao.pridajZakaznika(zakaznik);
        int telefon = zakaznik.getTelefon();
        dao.aktualizujTelefon(944888111, zakaznik.getId());
        Assert.assertNotEquals(zakaznik, telefon);
        List<Zakaznik> pridaniZakaznici = dao.podlaPriezviska("Duzda");
        Zakaznik pridaniZakaznik = pridaniZakaznici.get(0);
        dao.vymazZakaznika(pridaniZakaznik.getId());
    }

    @Test
    public void testPodlaId() {
        MysqlZakaznikDao dao = new MysqlZakaznikDao(ObjectFactory.INSTANCE.getJdbcTemplate());
        List<Zakaznik> zakaznik = dao.podlaId(1L);
        Assert.assertTrue(zakaznik.size() > 0);
    }

    @Test
    public void testVymazZakaznika() {
        MysqlZakaznikDao dao = new MysqlZakaznikDao(ObjectFactory.INSTANCE.getJdbcTemplate());
        Zakaznik zakaznik = new Zakaznik();
        zakaznik.setMeno("Milan");
        zakaznik.setPriezvisko("Duzda");
        zakaznik.setPoznamka("Zaciatocnik");
        dao.pridajZakaznika(zakaznik);
        List<Zakaznik> zakaznici = dao.dajZakaznikov();
        
        List<Zakaznik> noviZakaznici = dao.podlaPriezviska("Duzda");
        Zakaznik novyZakaznik = noviZakaznici.get(0);
        dao.vymazZakaznika(novyZakaznik.getId());
        List<Zakaznik> vybraniZakaznici = dao.dajZakaznikov();
        Assert.assertTrue(zakaznici.size() > vybraniZakaznici.size());
    }
    
    @Test
    public void testNemaHodinu() {
        MysqlZakaznikDao dao = new MysqlZakaznikDao(ObjectFactory.INSTANCE.getJdbcTemplate());
        Zakaznik zakaznik = new Zakaznik();
        zakaznik.setMeno("Milan");
        zakaznik.setPriezvisko("Duzda");
        zakaznik.setPoznamka("Zaciatocnik");
        dao.pridajZakaznika(zakaznik);
        boolean stav = dao.nemaHodinu(zakaznik.getId());
        Assert.assertTrue(stav == true);
        List<Zakaznik> pridaniZakaznici = dao.podlaPriezviska("Duzda");
        Zakaznik pridaniZakaznik = pridaniZakaznici.get(0);
        dao.vymazZakaznika(pridaniZakaznik.getId());
    }
}
