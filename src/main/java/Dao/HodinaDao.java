package Dao;

import Entity.Instruktor;
import Entity.Hodina;
import java.sql.Time;
import java.util.List;

public interface HodinaDao {

    public void pridajHodinu(Hodina hodina);
    public List<Hodina> podlaDatumu(String datum);
    public List<Long> obsadenost(String datum, Time od, Time po, Long id);
    public Long oduceneHodiny();
    public void vymazHodinu(Long id);
    public void zmenaStavu (Hodina hodina);
    public Long oduceneInstruktHodiny(Instruktor instruktor);
}
