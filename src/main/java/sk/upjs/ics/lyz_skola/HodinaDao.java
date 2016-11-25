package sk.upjs.ics.lyz_skola;

import java.util.List;

public interface HodinaDao {

    public void pridajHodinu(Hodina hodina);
    public List<Hodina> podlaDatumu(String datum);
}
