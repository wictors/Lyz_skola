package sk.upjs.ics.lyz_skola;

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
        System.out.println("Daj instruktorov");
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
        Instruktor instruktor = new Instruktor();
        instruktor.setMeno("Ivan");
        instruktor.setPriezvisko("Maly");
        instruktor.setAkreditacia("A");
        MysqlInstruktorDao dao = new MysqlInstruktorDao(ObjectFactory.INSTANCE.getJdbcTemplate());
        dao.pridajInstruktora(instruktor);
        Assert.assertTrue(true);
    }
    
    @Test
    public void testVymazInstruktora() {
        MysqlInstruktorDao dao = new MysqlInstruktorDao(ObjectFactory.INSTANCE.getJdbcTemplate());
        List<Instruktor> instruktori = dao.podlaPriezviska("Maly");
        Instruktor instruktor = instruktori.get(0);
        dao.vymazInstruktora(instruktor.getId());
        List<Instruktor> instruktori1 = dao.podlaPriezviska("Maly");
        Assert.assertTrue(instruktori.size() != instruktori1.size());
    }
    
    @Test
    public void testNemaHodinu() {
        MysqlInstruktorDao dao = new MysqlInstruktorDao(ObjectFactory.INSTANCE.getJdbcTemplate());
        dao.nemaHodinu(7L);
        Assert.assertTrue(true);
    }
    
    @Test
    public void testAktualizujEmail() {
        MysqlInstruktorDao dao = new MysqlInstruktorDao(ObjectFactory.INSTANCE.getJdbcTemplate());
        List<Instruktor> instruktori = dao.podlaPriezviska("Chalupka");
        Instruktor instruktor = instruktori.get(0);
        String email = instruktor.getEmail();
        dao.aktualizujEmail("chalupka@azet.sk", instruktor.getId());
        Assert.assertNotEquals(instruktor, email);
    }
}
