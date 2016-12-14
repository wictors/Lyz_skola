package sk.upjs.ics.lyz_skola;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class MysqlPrihlasenieDaoTest {
    
    @Test
    public void testDajPrihlasenia() {
        PrihlasenieDao dao = ObjectFactory.INSTANCE.getPrihlasenieDao();
        List<Prihlasenie> result = dao.dajPrihlasenia();
        Assert.assertTrue(result.size() > 0);
    }
    
    @Test
    public void testUlozPrihlasenie() {
        PrihlasenieDao dao = ObjectFactory.INSTANCE.getPrihlasenieDao();
        Prihlasenie prihlasenie = new Prihlasenie();
        prihlasenie.setEmail("test");
        prihlasenie.setDatum("2016-10-12");
        List<Prihlasenie> prihlasenia = dao.dajPrihlasenia();
        dao.ulozPrihlasenie(prihlasenie);
        List<Prihlasenie> novePrihlasenia = dao.dajPrihlasenia();
        Assert.assertTrue(prihlasenia.size() + 1 == novePrihlasenia.size());
    }
}
