package sk.upjs.ics.lyz_skola;

import java.util.List;
import javax.swing.DefaultComboBoxModel;

public class AkreditaciaComboBoxModel extends DefaultComboBoxModel<String> {
    
    public AkreditaciaComboBoxModel() {
        refresh();
    }

    private void refresh() {
        removeAllElements();
        addElement("A");
        addElement("B");
        addElement("C");
    }
}
