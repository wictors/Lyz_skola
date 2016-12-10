package sk.upjs.ics.lyz_skola;

import javax.swing.DefaultComboBoxModel;

public class TypComboBoxModel extends DefaultComboBoxModel<String>{
    public TypComboBoxModel() {
        refresh();
    }

    private void refresh() {
        removeAllElements();
        addElement("Lyze");
        addElement("Snowboard");
        addElement("Dual");
    }
}
