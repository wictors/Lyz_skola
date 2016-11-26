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
    
    private static final String[] NAZVY_STLPCOV = { "Datum", "Od", "Do", "Instruktor", "Zakaznik", "Typ" };
    
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
        String menoI = hodinaDao.menaInstruktorov(datum).get(rowIndex);
        String menoZ = hodinaDao.menaZakaznikov(datum).get(rowIndex);
        switch (columnIndex) {
            case 0:
                return hodina.getDatum();
            case 1:
                return hodina.getOd();
            case 2:
                return hodina.getPo();
            case 3:
                return menoI; 
            case 4:
                return menoZ;
            case 5:
                String typ = hodina.getTyp();
                if (typ == null){
                    return "ziadny";
                }
                return typ;
            default:
                return "???";
        }
    }

    @Override
    public String getColumnName(int column) {
        return NAZVY_STLPCOV[column];
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
