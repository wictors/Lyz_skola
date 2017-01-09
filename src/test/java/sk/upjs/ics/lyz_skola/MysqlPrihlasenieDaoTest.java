package sk.upjs.ics.lyz_skola;

import Dao.*;
import Entity.*;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

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
        List<Prihlasenie> prihlasenia = dao.dajPrihlasenia();
        Prihlasenie prihlasenie = new Prihlasenie();
        prihlasenie.setEmail("test");
        prihlasenie.setDatum("2016-10-12");
        dao.ulozPrihlasenie(prihlasenie);
        List<Prihlasenie> novePrihlasenia = dao.dajPrihlasenia();
        Assert.assertTrue(prihlasenia.size() + 1 == novePrihlasenia.size());
    }
}
