package sk.upjs.ics.lyz_skola;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

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
                return hodina.getDatum();
            case 1:
                return hodina.getOd();
            case 2:
                return hodina.getPo();
            case 3:
                return hodina.getInstruktor().getPriezvisko(); 
            case 4:
                return hodina.getZakaznik().getPriezvisko();
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
