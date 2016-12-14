package sk.upjs.ics.lyz_skola;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
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
        List<Zakaznik> zakaznik = dao.podlaTelefonu(944789456);
        assertTrue(zakaznik.size() > 0);
    }

    @Test
    public void testPodlaPriezviska() {
        MysqlZakaznikDao dao = new MysqlZakaznikDao(ObjectFactory.INSTANCE.getJdbcTemplate());
        List<Zakaznik> zakaznik = dao.podlaPriezviska("Kozak");
        assertTrue(zakaznik.size() > 0);
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
        dao.vymazZakaznika(zakaznik.getId());
    }

    @Test
    public void testAktualizujPoznamka() {
        MysqlZakaznikDao dao = new MysqlZakaznikDao(ObjectFactory.INSTANCE.getJdbcTemplate());
        List<Zakaznik> zakaznici = dao.podlaId(1L);
        Zakaznik zakaznik = zakaznici.get(0);
        String poznamka = zakaznik.getPoznamka();
        dao.aktualizujPoznamka("Pokrocily", 1L);
        Assert.assertNotEquals(zakaznik, poznamka);
        dao.aktualizujPoznamka(poznamka, zakaznik.getId());
    }

    @Test
    public void testAktualizujTelefon() {
        MysqlZakaznikDao dao = new MysqlZakaznikDao(ObjectFactory.INSTANCE.getJdbcTemplate());
        List<Zakaznik> zakaznici = dao.podlaId(1L);
        Zakaznik zakaznik = zakaznici.get(0);
        int telefon = zakaznik.getTelefon();
        dao.aktualizujTelefon(944888111, 1L);
        Assert.assertNotEquals(zakaznik, telefon);
        dao.aktualizujTelefon(telefon, zakaznik.getId());
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
        List<Zakaznik> zakaznici = dao.podlaId(4L);
        Zakaznik zakaznik = zakaznici.get(0);
        dao.vymazZakaznika(4L);
        List<Zakaznik> zakaznici1 = dao.podlaId(4L);
        Assert.assertTrue(zakaznici.size() == zakaznici1.size() + 1);
        dao.pridajZakaznika(zakaznik);
    }
    
    @Test
    public void testNemaHodinu() {
        MysqlZakaznikDao dao = new MysqlZakaznikDao(ObjectFactory.INSTANCE.getJdbcTemplate());
        boolean stav = dao.nemaHodinu(5L);
        Assert.assertTrue(stav == true);
    }
}
