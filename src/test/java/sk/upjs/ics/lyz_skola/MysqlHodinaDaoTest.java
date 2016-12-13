package sk.upjs.ics.lyz_skola;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class MysqlHodinaDaoTest {
    
    @Test
    public void testPridajHodinu() {
        MysqlInstruktorDao daoI = new MysqlInstruktorDao(ObjectFactory.INSTANCE.getJdbcTemplate());
        List<Instruktor> instruktori = daoI.podlaPriezviska("Seno");
        Instruktor instruktor = instruktori.get(0);
        MysqlZakaznikDao daoZ = new MysqlZakaznikDao(ObjectFactory.INSTANCE.getJdbcTemplate());
        List<Zakaznik> zakaznici = daoZ.podlaPriezviska("Kozak");
        Zakaznik zakaznik = zakaznici.get(0);
        Hodina hodina = new Hodina();
        hodina.setInstruktor(instruktor);
        hodina.setZakaznik(zakaznik);
        hodina.setDatum("2016-12-31");
        MysqlHodinaDao daoH = new MysqlHodinaDao(ObjectFactory.INSTANCE.getJdbcTemplate());
        daoH.pridajHodinu(hodina);
        Assert.assertTrue(true);
    }

    @Test
    public void testPodlaDatumu() throws ParseException {
        MysqlHodinaDao dao = new MysqlHodinaDao(ObjectFactory.INSTANCE.getJdbcTemplate());
        List<Hodina> hodiny = dao.podlaDatumu("2016-11-29");
        assertTrue(hodiny.size() > 0);
    }
    
    @Test
    public void testObsadenost() throws ParseException{
        String datum = "2016-11-29";
        DateFormat timeFormat = new SimpleDateFormat("HH:mm");
        java.sql.Time casOd = new java.sql.Time(timeFormat.parse("11:00").getTime());
        java.sql.Time casDo = new java.sql.Time(timeFormat.parse("16:00").getTime());
        Long id = 1L;
        MysqlHodinaDao dao = new MysqlHodinaDao(ObjectFactory.INSTANCE.getJdbcTemplate());
        List<Long> vystup = dao.obsadenost(datum,casOd,casDo,id);
        
        Assert.assertEquals(1, vystup.size());
    }
    
    @Test
    public void testOduceneHodiny(){
        MysqlHodinaDao dao = new MysqlHodinaDao(ObjectFactory.INSTANCE.getJdbcTemplate());
        Long pocet = dao.oduceneHodiny();
        Long odpoved = 2L;
        Assert.assertEquals(odpoved, pocet);
    }
    
    @Test
    public void testVymazHodinu(){
        MysqlHodinaDao dao = new MysqlHodinaDao(ObjectFactory.INSTANCE.getJdbcTemplate());
        List<Hodina> hodiny = dao.podlaDatumu("2016-11-29");
        Hodina hodina = hodiny.get(0);
        dao.vymazHodinu(hodina.getId());
        List<Hodina> hodiny1 = dao.podlaDatumu("2016-11-29");
        Assert.assertTrue(hodiny.size() != hodiny1.size());
    }
}
