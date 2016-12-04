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
        System.out.println("pridajHodinu");
        Hodina hodina = null;
        MysqlHodinaDao instance = null;
        instance.pridajHodinu(hodina);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testPodlaDatumu() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dnes = dateFormat.format(new Date());   
        MysqlHodinaDao dao = new MysqlHodinaDao(ObjectFactory.INSTANCE.getJdbcTemplate());
        List<Hodina> hodiny = dao.podlaDatumu(dnes);
        
        Assert.assertEquals(2, hodiny.size());
    }
    
    @Test
    public void testObsadenost() throws ParseException{
        String datum = "2016-11-25";
        DateFormat timeFormat = new SimpleDateFormat("HH:mm");
        java.sql.Time casOd = new java.sql.Time(timeFormat.parse("08:00").getTime());
        java.sql.Time casDo = new java.sql.Time(timeFormat.parse("11:00").getTime());
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
    
}
