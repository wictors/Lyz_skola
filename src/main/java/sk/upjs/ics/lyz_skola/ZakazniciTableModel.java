package sk.upjs.ics.lyz_skola;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ZakazniciTableModel extends AbstractTableModel {
    
    private ZakaznikDao zakaznikDao = ObjectFactory.INSTANCE.getZakaznikDao();
    
    private static final String[] NAZVY_STLPCOV = { "ID", "Meno", "Priezvisko", "Poznamka", "Telefon", "Email", };
    
    private static final int POCET_STLPCOV = NAZVY_STLPCOV.length;
    
    private List<Zakaznik> zakaznici = new ArrayList<Zakaznik>();
    

    @Override
    public int getRowCount() {
        return zakaznici.size();
    }

    @Override
    public int getColumnCount() {
        return POCET_STLPCOV;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Zakaznik zakaznik = zakaznici.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return zakaznik.getId();
            case 1:
                String meno = zakaznik.getMeno();
                if(meno == null || meno.equals("")){
                    return "Ziadne";
                }
                return meno ;
            case 2:
                String priezvisko = zakaznik.getPriezvisko();
                if(priezvisko == null || priezvisko.equals("")){
                    return "Ziadne";
                }
                return priezvisko;
            case 3:
                String poznamka = zakaznik.getPoznamka();
                if(poznamka == null || poznamka.equals("")){
                    return "Ziadna";
                }
                return poznamka; 
            case 4:
                int telefon = zakaznik.getTelefon();
                if(telefon == 0){
                    return "Ziadny";
                }
                return telefon;
            case 5:
                String email = zakaznik.getEmail();
                if (email == null || email.equals("")){
                    return "Ziadny";
                }
                return email;
            default:
                return "???";
        }
    }

    @Override
    public String getColumnName(int column) {
        return NAZVY_STLPCOV[column];
    }
    
    void aktualizovat(int vstup, String priezvisko, int telefon, Long id){
        if(vstup == 0){
            zakaznici = zakaznikDao.dajZakaznikov();
        }
        if(vstup == 1){
            zakaznici = zakaznikDao.utriedenyPodlaPriezviska();
        }
        if(vstup == 2){
            zakaznici = zakaznikDao.podlaPriezviska(priezvisko);
        }
        if(vstup == 3){
            zakaznici = zakaznikDao.podlaTelefonu(telefon);
        }
        if(vstup == 4){
            zakaznici = zakaznikDao.podlaId(id);
        }
        fireTableDataChanged();
    }
    
    
    
}
