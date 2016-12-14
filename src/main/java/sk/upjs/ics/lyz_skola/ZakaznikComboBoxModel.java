package sk.upjs.ics.lyz_skola;

import Dao.ZakaznikDao;
import Entity.Zakaznik;
import java.util.List;
import javax.swing.DefaultComboBoxModel;

public class ZakaznikComboBoxModel extends DefaultComboBoxModel<Zakaznik> {

    ZakaznikDao zakaznikDao = ObjectFactory.INSTANCE.getZakaznikDao();
    
    public ZakaznikComboBoxModel() {
        refresh();
    }

    private void refresh() {
        removeAllElements();
        List<Zakaznik> zakaznici = zakaznikDao.dajZakaznikov();
        for (Zakaznik zakaznik:zakaznici){
            addElement(zakaznik);
        }
    }
    
    
}
