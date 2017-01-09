package sk.upjs.ics.lyz_skola;

import Dao.*;
import Entity.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

public class MysqlHodinaDaoTest {
    
    @Test
    public void testPridajHodinu() {
        MysqlHodinaDao daoH = new MysqlHodinaDao(ObjectFactory.INSTANCE.getJdbcTemplate());
        List<Hodina> hodiny = daoH.podlaDatumu("2016-12-14");
        MysqlInstruktorDao daoI = new MysqlInstruktorDao(ObjectFactory.INSTANCE.getJdbcTemplate());
        List<Instruktor> instruktori = daoI.podlaPriezviska("Seno");
        Instruktor instruktor = instruktori.get(0);
        MysqlZakaznikDao daoZ = new MysqlZakaznikDao(ObjectFactory.INSTANCE.getJdbcTemplate());
        List<Zakaznik> zakaznici = daoZ.podlaPriezviska("Kozak");
        Zakaznik zakaznik = zakaznici.get(0);
        Hodina hodina = new Hodina();
        hodina.setInstruktor(instruktor);
        hodina.setZakaznik(zakaznik);
        hodina.setDatum("2016-12-14");
        daoH.pridajHodinu(hodina);
        List<Hodina> noveHodiny = daoH.podlaDatumu("2016-12-14");
        Assert.assertTrue(hodiny.size() + 1 == noveHodiny.size());
        for(Hodina novaHodina: noveHodiny){
            if(novaHodina.equals(hodina)){
                daoH.vymazHodinu(hodina.getId());
            }
        }
    }

    @Test
    public void testPodlaDatumu() throws ParseException {
        MysqlHodinaDao dao = new MysqlHodinaDao(ObjectFactory.INSTANCE.getJdbcTemplate());
        List<Hodina> hodiny = dao.podlaDatumu("2016-12-15");
        assertTrue(hodiny.size() > 0);
    }
    
    @Test
    public void testObsadenost() throws ParseException{
        String datum = "2016-12-15";
        DateFormat timeFormat = new SimpleDateFormat("HH:mm");
        java.sql.Time casOd = new java.sql.Time(timeFormat.parse("09:00").getTime());
        java.sql.Time casDo = new java.sql.Time(timeFormat.parse("12:00").getTime());
        Long id = 3L;
        MysqlHodinaDao dao = new MysqlHodinaDao(ObjectFactory.INSTANCE.getJdbcTemplate());
        List<Long> vystup = dao.obsadenost(datum,casOd,casDo,id);
        
        Assert.assertEquals(1, vystup.size());
    }
    
    @Test
    public void testOduceneHodiny(){
        MysqlHodinaDao dao = new MysqlHodinaDao(ObjectFactory.INSTANCE.getJdbcTemplate());
        Long pocet = dao.oduceneHodiny();
        Long odpoved = 4L;
        Assert.assertEquals(odpoved, pocet);
    }
    
    @Test
    public void testVymazHodinu(){
        MysqlHodinaDao dao = new MysqlHodinaDao(ObjectFactory.INSTANCE.getJdbcTemplate());
        List<Hodina> hodiny = dao.podlaDatumu("2016-12-14");
        Hodina hodina = hodiny.get(0);
        dao.vymazHodinu(hodina.getId());
        List<Hodina> hodiny1 = dao.podlaDatumu("2016-12-14");
        Assert.assertTrue(hodiny.size() == hodiny1.size() + 1);
        dao.pridajHodinu(hodina);
    }
    
    @Test
    public void testZmenaStavu(){
        MysqlHodinaDao dao = new MysqlHodinaDao(ObjectFactory.INSTANCE.getJdbcTemplate());
        List<Hodina> hodiny = dao.podlaDatumu("2016-12-14");
        Hodina hodina = hodiny.get(0);
        boolean stav = hodina.isStav();
        dao.zmenaStavu(hodina);
        boolean novy_stav = hodina.isStav();
        Assert.assertEquals(stav, novy_stav);
        dao.zmenaStavu(hodina);
    }
    
    @Test
    public void testOduceneInstruktHodiny(){
        MysqlHodinaDao dao = new MysqlHodinaDao(ObjectFactory.INSTANCE.getJdbcTemplate());
        MysqlInstruktorDao daoInst = new MysqlInstruktorDao(ObjectFactory.INSTANCE.getJdbcTemplate());
        List<Instruktor> instruktori = daoInst.podlaPriezviska("Seno");
        Instruktor instruktor = instruktori.get(0);
        Long pocet = dao.oduceneInstruktHodiny(instruktor);
        Long odpoved = 2L;
        Assert.assertEquals(odpoved, pocet);
    }
}
