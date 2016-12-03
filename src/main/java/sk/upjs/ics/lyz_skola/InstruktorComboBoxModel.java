package sk.upjs.ics.lyz_skola;

import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

public class InstruktorComboBoxModel extends DefaultComboBoxModel<Instruktor> {
    
    private InstruktorDao instruktorDao = ObjectFactory.INSTANCE.getInstruktorDao();

    public InstruktorComboBoxModel() {
        refresh();
    }

    private void refresh() {
        removeAllElements();
        
        List<Instruktor> instruktori = instruktorDao.dajInstruktorov();
        for (Instruktor instrukt:instruktori){
            addElement(instrukt);
        }
    }
    
    
    
}
