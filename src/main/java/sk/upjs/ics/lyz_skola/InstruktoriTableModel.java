package sk.upjs.ics.lyz_skola;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class InstruktoriTableModel extends AbstractTableModel {
    
    private InstruktorDao instruktorDao = ObjectFactory.INSTANCE.getInstruktorDao();
    
    private static final String[] NAZVY_STLPCOV = { "Meno", "Priezvisko", "Email", "Akreditacia", "Typ" };

    private static final int POCET_STLPCOV = NAZVY_STLPCOV.length;
    
    private List<Instruktor> instruktori = new ArrayList<Instruktor>();
    
    
    @Override
    public int getRowCount() {
        return instruktori.size();
    }

    @Override
    public int getColumnCount() {
        return POCET_STLPCOV;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Instruktor instruktor = instruktori.get(rowIndex);
        switch(columnIndex){
            case 0:
                String meno = instruktor.getMeno();
                if (meno == null){
                    return "Žiadne";
                }
                return meno;
            case 1:
                String priezvisko = instruktor.getPriezvisko();
                if (priezvisko == null){
                    return "Žiadne";
                }
                return priezvisko;
            case 2:
                String email = instruktor.getEmail();
                if (email == null){
                    return "Žiadny";
                }
                return email;
            case 3:
                String akreditacia = instruktor.getAkreditacia();
                if (akreditacia == null){
                    return "Žiadna";
                }
                return akreditacia;
            case 4:
                String typ = instruktor.getTyp();
                if (typ == null){
                    return "Žiadny";
                }
                return typ;
            case 5:
                return instruktor.getId();
            default:
                return "???";
        }
    }
    
    @Override
    public String getColumnName(int column) {
        return NAZVY_STLPCOV[column];
    }
    
    void aktualizovat(int vstup, String priezvisko, String akreditacia, String typ){
        if(vstup == 0){
            instruktori = instruktorDao.dajInstruktorov();
        }
        if(vstup == 1){
            instruktori = instruktorDao.utriedenyPodlaPriezviska();
        }
        if(vstup == 2){
            instruktori = instruktorDao.podlaPriezviska(priezvisko);
        }
        if(vstup == 3){
            instruktori = instruktorDao.podlaAkreditacie(akreditacia);
        }
        if(vstup == 4){
            instruktori = instruktorDao.podlaTypu(typ);
        }
        fireTableDataChanged();
    }
}
