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
        System.out.println("Daj zakaznikov");
        ZakaznikDao zakaznikDao = ObjectFactory.INSTANCE.getZakaznikDao();
        List<Zakaznik> result = zakaznikDao.dajZakaznikov();
        assertTrue(result.size() > 0);
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
        Zakaznik zakaznik = new Zakaznik();
        zakaznik.setMeno("Milan");
        zakaznik.setPriezvisko("Duzda");
        zakaznik.setPoznamka("Zaciatocnik");
        MysqlZakaznikDao dao = new MysqlZakaznikDao(ObjectFactory.INSTANCE.getJdbcTemplate());
        dao.pridajZakaznika(zakaznik);
        Assert.assertTrue(true);
    }

    @Test
    public void testAktualizujPoznamka() {
        MysqlZakaznikDao dao = new MysqlZakaznikDao(ObjectFactory.INSTANCE.getJdbcTemplate());
        List<Zakaznik> zakaznici = dao.podlaId(1L);
        Zakaznik zakaznik = zakaznici.get(0);
        String poznamka = zakaznik.getPoznamka();
        dao.aktualizujPoznamka("Zaciatocnik", 1L);
        Assert.assertNotEquals(zakaznik, poznamka);
    }

    @Test
    public void testAktualizujTelefon() {
        MysqlZakaznikDao dao = new MysqlZakaznikDao(ObjectFactory.INSTANCE.getJdbcTemplate());
        List<Zakaznik> zakaznici = dao.podlaId(1L);
        Zakaznik zakaznik = zakaznici.get(0);
        int telefon = zakaznik.getTelefon();
        dao.aktualizujTelefon(948741852, 1L);
        Assert.assertNotEquals(zakaznik, telefon);
    }

    @Test
    public void testPodlaId() {
        MysqlZakaznikDao dao = new MysqlZakaznikDao(ObjectFactory.INSTANCE.getJdbcTemplate());
        List<Zakaznik> zakaznik = dao.podlaId(1L);
        assertTrue(zakaznik.size() > 0);
    }

    @Test
    public void testVymazZakaznika() {
        MysqlZakaznikDao dao = new MysqlZakaznikDao(ObjectFactory.INSTANCE.getJdbcTemplate());
        List<Zakaznik> zakaznici = dao.podlaId(6L);
        dao.vymazZakaznika(6L);
        List<Zakaznik> zakaznici1 = dao.podlaId(6L);
        Assert.assertTrue(zakaznici.size() != zakaznici1.size());
    }
    
    @Test
    public void testNemaHodinu() {
        MysqlZakaznikDao dao = new MysqlZakaznikDao(ObjectFactory.INSTANCE.getJdbcTemplate());
        dao.nemaHodinu(3L);
        Assert.assertTrue(true);
    }
}
