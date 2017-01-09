package Form;

import Dao.HodinaDao;
import Entity.Hodina;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.table.AbstractTableModel;
import sk.upjs.ics.lyz_skola.ObjectFactory;

public class HodinyTableModel extends AbstractTableModel {
    
    private HodinaDao hodinaDao = ObjectFactory.INSTANCE.getHodinaDao();
    
    private static final String[] NAZVY_STLPCOV = { "Datum", "Od", "Do", "Instruktor", "Zakaznik", "Typ", "Stav" };
    
    private static final int POCET_STLPCOV = NAZVY_STLPCOV.length;
    
    private String datum;
    
    
    @Override
    public int getRowCount() {
        return hodinaDao.podlaDatumu(datum).size();
    }

    @Override
    public int getColumnCount() {
        return POCET_STLPCOV;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Hodina hodina = hodinaDao.podlaDatumu(datum).get(rowIndex);
        switch (columnIndex) {
            case 0:
                String datum = hodina.getDatum();
                if(datum == null){
                    return "Neexistuje";
                }
                return datum;
            case 1:
                String od = hodina.getOd().toString();
                if(od == null){
                    return "Neexistuje"; 
                }
                return od;
            case 2:
                String po = hodina.getPo().toString();
                if(po == null){
                    return "Neexistuje";
                }
                return po;
            case 3:
                String instruktor = hodina.getInstruktor().getPriezvisko();
                if(instruktor == null){
                    return "Neexistuje";
                }
                return instruktor; 
            case 4:
                String zakaznik = hodina.getZakaznik().getPriezvisko();
                if(zakaznik == null){
                    return "Neexistuje";
                }
                return zakaznik;
            case 5:
                String typ = hodina.getTyp();
                if (typ == null){
                    return "ziadny";
                }
                return typ;
            case 6:
                return hodina.isStav();
            default:
                return "???";
        }
    }

    @Override
    public String getColumnName(int column) {
        return NAZVY_STLPCOV[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 6) {
            return Boolean.class;
        }
        return super.getColumnClass(columnIndex);
    }
    
    public Hodina vratHodinu(int row){
        return hodinaDao.podlaDatumu(datum).get(row);
    }
    
    
    
    void aktualizovat(String nastavenyDatum){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if(nastavenyDatum == null){  
            datum = dateFormat.format(new Date());   
        }else{  
            datum = nastavenyDatum;            
        }
        fireTableDataChanged();
    }
}
