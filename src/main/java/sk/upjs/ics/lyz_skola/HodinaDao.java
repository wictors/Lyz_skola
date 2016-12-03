package sk.upjs.ics.lyz_skola;

import java.sql.Time;
import java.util.List;

public interface HodinaDao {

    public void pridajHodinu(Hodina hodina);
    public List<Hodina> podlaDatumu(String datum);
    public List<Long> obsadenost(String datum, Time od, Time po, Long id);
    
}
