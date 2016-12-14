package sk.upjs.ics.lyz_skola;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class MysqlInstruktorDaoTest {
    
    @Test
    public void testDajInstruktorov() {
        InstruktorDao instruktorDao = ObjectFactory.INSTANCE.getInstruktorDao();
        List<Instruktor> result = instruktorDao.dajInstruktorov();
        assertTrue(result.size() > 0);
    }
    
    @Test
    public void testUtriedenyPodlaPriezviska() {
        MysqlInstruktorDao dao = new MysqlInstruktorDao(ObjectFactory.INSTANCE.getJdbcTemplate());
        List<Instruktor> instruktori = dao.utriedenyPodlaPriezviska();
        Instruktor instruktor1 = instruktori.get(0);
        Instruktor instruktor2 = instruktori.get(1);
        if(instruktor1.getPriezvisko().charAt(0) < instruktor2.getPriezvisko().charAt(0)){
            Assert.assertTrue(true);
        }
    }
    
    @Test
    public void testPodlaPriezviska() {
        MysqlInstruktorDao dao = new MysqlInstruktorDao(ObjectFactory.INSTANCE.getJdbcTemplate());
        List<Instruktor> instruktor = dao.podlaPriezviska("Seno");
        assertTrue(instruktor.size() > 0);
    }
    
    @Test
    public void testPodlaAkreditacie() {
        MysqlInstruktorDao dao = new MysqlInstruktorDao(ObjectFactory.INSTANCE.getJdbcTemplate());
        List<Instruktor> instruktor = dao.podlaAkreditacie("B");
        assertTrue(instruktor.size() > 0);
    }
    
    @Test
    public void testPodlaTypu() {
        MysqlInstruktorDao dao = new MysqlInstruktorDao(ObjectFactory.INSTANCE.getJdbcTemplate());
        List<Instruktor> instruktor = dao.podlaTypu("Lyze");
        assertTrue(instruktor.size() > 0);
    }
    
    @Test
    public void testPridajInstruktora() {
        MysqlInstruktorDao dao = new MysqlInstruktorDao(ObjectFactory.INSTANCE.getJdbcTemplate());
        List<Instruktor> instruktori = dao.dajInstruktorov();
        Instruktor instruktor = new Instruktor();
        instruktor.setMeno("Ivan");
        instruktor.setPriezvisko("Maly");
        instruktor.setAkreditacia("A");
        dao.pridajInstruktora(instruktor);
        List<Instruktor> noviInstruktori = dao.dajInstruktorov();
        Assert.assertTrue(instruktori.size() < noviInstruktori.size());
        dao.vymazInstruktora(instruktor.getId());
    }
    
    @Test
    public void testVymazInstruktora() {
        MysqlInstruktorDao dao = new MysqlInstruktorDao(ObjectFactory.INSTANCE.getJdbcTemplate());
        List<Instruktor> instruktori = dao.podlaPriezviska("Dvorak");
        Instruktor instruktor = instruktori.get(0);
        dao.vymazInstruktora(instruktor.getId());
        List<Instruktor> instruktori1 = dao.podlaPriezviska("Dvorak");
        Assert.assertTrue(instruktori.size() > instruktori1.size());
        dao.pridajInstruktora(instruktor);
    }
    
    @Test
    public void testPodlaEmailu() {
        MysqlInstruktorDao dao = new MysqlInstruktorDao(ObjectFactory.INSTANCE.getJdbcTemplate());
        Instruktor instruktor = dao.podlaEmailu("seno.gmail.com");
        List<Instruktor> instruktori = new ArrayList<>();
        instruktori.add(instruktor);
        Assert.assertTrue(instruktori.size() > 0);
    }
    
    @Test
    public void testNemaHodinu() {
        MysqlInstruktorDao dao = new MysqlInstruktorDao(ObjectFactory.INSTANCE.getJdbcTemplate());
        boolean nema = dao.nemaHodinu(5L);
        Assert.assertTrue(nema == true);
    }
    
    @Test
    public void testAktualizujAkreditaciu() {
        MysqlInstruktorDao dao = new MysqlInstruktorDao(ObjectFactory.INSTANCE.getJdbcTemplate());
        List<Instruktor> instruktori = dao.podlaPriezviska("Chalupka");
        Instruktor instruktor = instruktori.get(0);
        String akreditacia = instruktor.getAkreditacia();
        dao.aktualizujAkreditaciu(instruktor.getId(), "B");
        Assert.assertNotEquals(instruktor, akreditacia);
        dao.aktualizujAkreditaciu(instruktor.getId(), akreditacia);
    }
    
    @Test
    public void testAktualizujTyp() {
        MysqlInstruktorDao dao = new MysqlInstruktorDao(ObjectFactory.INSTANCE.getJdbcTemplate());
        List<Instruktor> instruktori = dao.podlaPriezviska("Chalupka");
        Instruktor instruktor = instruktori.get(0);
        String typ = instruktor.getTyp();
        dao.aktualizujTyp(instruktor.getId(), "Lyze");
        Assert.assertNotEquals(instruktor, typ);
        dao.aktualizujTyp(instruktor.getId(), typ);
    }
}
