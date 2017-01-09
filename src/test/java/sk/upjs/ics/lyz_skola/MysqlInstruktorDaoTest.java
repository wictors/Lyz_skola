package sk.upjs.ics.lyz_skola;

import Dao.*;
import Entity.*;
import java.util.List;
import org.junit.Assert;
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
        Instruktor instruktor = new Instruktor();
        instruktor.setMeno("Ivan");
        instruktor.setPriezvisko("Maly");
        instruktor.setEmail("Maly@gmail.com");
        instruktor.setTyp("Lyze");
        instruktor.setAkreditacia("A");
        instruktor.setHeslo("heslo");
        dao.pridajInstruktora(instruktor);
        List<Instruktor> instruktori = dao.podlaPriezviska("Maly");
        Instruktor novyInstruktor = instruktori.get(0);
        assertTrue(instruktori.size() > 0);      
        dao.vymazInstruktora(novyInstruktor.getId());
    }
    
    @Test
    public void testPodlaAkreditacie() {
        MysqlInstruktorDao dao = new MysqlInstruktorDao(ObjectFactory.INSTANCE.getJdbcTemplate());
        Instruktor instruktor = new Instruktor();
        instruktor.setMeno("Ivan");
        instruktor.setPriezvisko("Maly");
        instruktor.setEmail("Maly@gmail.com");
        instruktor.setTyp("Lyze");
        instruktor.setAkreditacia("A");
        instruktor.setHeslo("heslo");
        dao.pridajInstruktora(instruktor);
        List<Instruktor> instruktori = dao.podlaAkreditacie("A");
        assertTrue(instruktori.size() > 0);   
        for(Instruktor novyInstruktor: instruktori){
            if(novyInstruktor.getPriezvisko().equals("Maly")){
                dao.vymazInstruktora(novyInstruktor.getId());
            }
        }
    }
    
    @Test
    public void testPodlaTypu() {
        MysqlInstruktorDao dao = new MysqlInstruktorDao(ObjectFactory.INSTANCE.getJdbcTemplate());
        Instruktor instruktor = new Instruktor();
        instruktor.setMeno("Ivan");
        instruktor.setPriezvisko("Maly");
        instruktor.setEmail("Maly@gmail.com");
        instruktor.setTyp("Lyze");
        instruktor.setAkreditacia("A");
        instruktor.setHeslo("heslo");
        dao.pridajInstruktora(instruktor);
        List<Instruktor> instruktori = dao.podlaTypu("Lyze");
        assertTrue(instruktori.size() > 0);   
        for(Instruktor novyInstruktor: instruktori){
            if(novyInstruktor.getPriezvisko().equals("Maly")){
                dao.vymazInstruktora(novyInstruktor.getId());
            }
        }
    }
    
    @Test
    public void testPridajInstruktora() {
        MysqlInstruktorDao dao = new MysqlInstruktorDao(ObjectFactory.INSTANCE.getJdbcTemplate());
        List<Instruktor> instruktori = dao.dajInstruktorov();
        Instruktor instruktor = new Instruktor();
        instruktor.setMeno("Ivan");
        instruktor.setPriezvisko("Maly");
        instruktor.setEmail("Maly@gmail.com");
        instruktor.setTyp("Lyze");
        instruktor.setAkreditacia("A");
        instruktor.setHeslo("heslo");
        dao.pridajInstruktora(instruktor);
        List<Instruktor> noviInstruktori = dao.dajInstruktorov();
        Assert.assertTrue(instruktori.size() < noviInstruktori.size());
        List<Instruktor> noviInstruktor = dao.podlaPriezviska("Maly");
        Instruktor novyInstruktor = noviInstruktor.get(0);
        dao.vymazInstruktora(novyInstruktor.getId());
    }
    
    @Test
    public void testVymazInstruktora() {
        MysqlInstruktorDao dao = new MysqlInstruktorDao(ObjectFactory.INSTANCE.getJdbcTemplate());
        Instruktor instruktor = new Instruktor();
        instruktor.setMeno("Ivan");
        instruktor.setPriezvisko("Maly");
        instruktor.setEmail("Maly@gmail.com");
        instruktor.setTyp("Lyze");
        instruktor.setAkreditacia("A");
        instruktor.setHeslo("heslo");
        dao.pridajInstruktora(instruktor);
        List<Instruktor> instruktori = dao.dajInstruktorov();
        
        List<Instruktor> noviInstruktori = dao.podlaPriezviska("Maly");
        Instruktor novyInstruktor = noviInstruktori.get(0);
        dao.vymazInstruktora(novyInstruktor.getId());
        List<Instruktor> vymazanyInstruktor = dao.dajInstruktorov();
        Assert.assertTrue(instruktori.size() > vymazanyInstruktor.size());
    }
    
    @Test
    public void testPodlaEmailu() {
        MysqlInstruktorDao dao = new MysqlInstruktorDao(ObjectFactory.INSTANCE.getJdbcTemplate());
        Instruktor instruktor = new Instruktor();
        instruktor.setMeno("Ivan");
        instruktor.setPriezvisko("Maly");
        instruktor.setEmail("Maly@gmail.com");
        instruktor.setTyp("Lyze");
        instruktor.setAkreditacia("A");
        instruktor.setHeslo("heslo");
        dao.pridajInstruktora(instruktor);
        Instruktor novyInstruktor = dao.podlaEmailu("Maly@gmail.com");
        assertTrue(novyInstruktor != null);   
        dao.vymazInstruktora(novyInstruktor.getId());
    }
    
    @Test
    public void testNemaHodinu() {
        MysqlInstruktorDao dao = new MysqlInstruktorDao(ObjectFactory.INSTANCE.getJdbcTemplate());
        Instruktor instruktor = new Instruktor();
        instruktor.setMeno("Ivan");
        instruktor.setPriezvisko("Maly");
        instruktor.setEmail("Maly@gmail.com");
        instruktor.setTyp("Lyze");
        instruktor.setAkreditacia("A");
        instruktor.setHeslo("heslo");
        dao.pridajInstruktora(instruktor);
        boolean nema = dao.nemaHodinu(instruktor.getId());
        Assert.assertTrue(nema == true);
        List<Instruktor> instruktori = dao.podlaPriezviska("Maly");
        Instruktor novyInstruktor = instruktori.get(0);     
        dao.vymazInstruktora(novyInstruktor.getId());
    }
    
    @Test
    public void testAktualizujAkreditaciu() {
        MysqlInstruktorDao dao = new MysqlInstruktorDao(ObjectFactory.INSTANCE.getJdbcTemplate());
        Instruktor instruktor = new Instruktor();
        instruktor.setMeno("Ivan");
        instruktor.setPriezvisko("Maly");
        instruktor.setEmail("Maly@gmail.com");
        instruktor.setTyp("Lyze");
        instruktor.setAkreditacia("A");
        instruktor.setHeslo("heslo");
        dao.pridajInstruktora(instruktor);
        String akreditacia = instruktor.getAkreditacia();
        dao.aktualizujAkreditaciu(instruktor.getId(), "B");
        Assert.assertNotEquals(instruktor, akreditacia);
        List<Instruktor> instruktori = dao.podlaPriezviska("Maly");
        Instruktor novyInstruktor = instruktori.get(0);     
        dao.vymazInstruktora(novyInstruktor.getId());
    }
    
    @Test
    public void testAktualizujTyp() {
        MysqlInstruktorDao dao = new MysqlInstruktorDao(ObjectFactory.INSTANCE.getJdbcTemplate());
        Instruktor instruktor = new Instruktor();
        instruktor.setMeno("Ivan");
        instruktor.setPriezvisko("Maly");
        instruktor.setEmail("Maly@gmail.com");
        instruktor.setTyp("Lyze");
        instruktor.setAkreditacia("A");
        instruktor.setHeslo("heslo");
        dao.pridajInstruktora(instruktor);
        String typ = instruktor.getTyp();
        dao.aktualizujTyp(instruktor.getId(), "Dual");
        Assert.assertNotEquals(instruktor, typ);
        List<Instruktor> instruktori = dao.podlaPriezviska("Maly");
        Instruktor novyInstruktor = instruktori.get(0);     
        dao.vymazInstruktora(novyInstruktor.getId());
    }
}
