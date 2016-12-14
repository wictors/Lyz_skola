package Form;

import Dao.*;
import Entity.*;
import Dao.HodinaDao;
import javax.swing.table.AbstractTableModel;
import sk.upjs.ics.lyz_skola.ObjectFactory;


public class ZaznamPrihlaseniTableModel extends AbstractTableModel {
    
    
    private static final String[] NAZVY_STLPCOV = { "Datum","Email","Stav" };
    
    private static final int POCET_STLPCOV = NAZVY_STLPCOV.length;
    
    private PrihlasenieDao prihlasenieDao = ObjectFactory.INSTANCE.getPrihlasenieDao();
    

    @Override
    public int getRowCount() {
        return prihlasenieDao.dajPrihlasenia().size();
    }

    @Override
    public int getColumnCount() {
        return POCET_STLPCOV;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Prihlasenie prihlasenie = prihlasenieDao.dajPrihlasenia().get(rowIndex);
        switch(columnIndex){
            case 0:
                return prihlasenie.getDatum();
            case 1:
                return prihlasenie.getEmail();
            case 2:
                return prihlasenie.getStav();
            default:
                return "???";
        }
    }

    @Override
    public String getColumnName(int column) {
        return NAZVY_STLPCOV[column];
    }
    
    void aktualizovat(){
        fireTableDataChanged();
    }    
}
