package sk.upjs.ics.lyz_skola;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class MysqlHodinaDao implements HodinaDao {
    
    private JdbcTemplate jdbcTemplate;

    public MysqlHodinaDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void pridajHodinu(Hodina hodina) {
        jdbcTemplate.update("INSERT INTO hodina(datum,od,po,instruktor,zakaznik,typ)"
                + " VALUES(?,?,?,?,?,?)", hodina.getDatum(),hodina.getOd(),hodina.getPo(),
                + hodina.getInstruktor().getId(),hodina.getZakaznik().getId(),hodina.getTyp());
    }

    @Override
    public List<Hodina> podlaDatumu(String datum) {
        String sql = "select h.id as hodina_ID, h.datum as hodina_datum,"
                + " h.od as OD, h.po as PO, h.typ as hodina_typ,"
                + " h.stav as hodina_stav,"
                + " z.id as zakaznik_ID, z.meno as zakaznik_meno,"
                + " z.priezvisko as zakaznik_priezvisko,"
                + " z.poznamka as zakaznik_poznamka, z.telefon as zakaznik_telefon,"
                + " z.email as zakaznik_email,"
                + " i.id as instruktor_ID, i.meno as instruktor_meno,"
                + " i.priezvisko as instruktor_priezvisko, i.email as instruktor_email,"
                + " i.typ as instruktor_typ, i.akreditacia as instruktor_akreditacia"
                + " from hodina as h join instruktor as i on h.instruktor = i.id" 
                + " join zakaznik as z on h.zakaznik = z.id"
                + " WHERE datum = ?";
        return jdbcTemplate.query(sql, new HodinaRowMapper(), datum);
    }

    @Override
    public List<Long> obsadenost(String datum, Time od, Time po, Long id) {
        String sql = "select h.id as hodina_id from hodina as h"
                + " join instruktor as i on h.instruktor = i.id"
                + " where datum = ? and i.id = ? and" 
                + " ((? <= h.od and h.od < ?) or (? < h.po and h.po <= ?))";
        return jdbcTemplate.query(sql, new ObsadenostRowMapper(),datum,id,od,po,od,po);
    }
    
    private class ObsadenostRowMapper implements RowMapper<Long>{

        @Override
        public Long mapRow(ResultSet rs, int i) throws SQLException {
            Long id;
            id = rs.getLong("hodina_id");
            return id;
        }
    
}
    
    private class HodinaRowMapper implements RowMapper<Hodina> {

        @Override
        public Hodina mapRow(ResultSet rs, int i) throws SQLException {
            Hodina hodina = new Hodina();
            hodina.setId(rs.getLong("hodina_ID"));
            hodina.setDatum(rs.getString("hodina_datum"));
            hodina.setOd(rs.getTime("OD"));
            hodina.setPo(rs.getTime("PO"));
            hodina.setTyp(rs.getString("hodina_typ"));
            hodina.setStav(rs.getBoolean("hodina_stav"));
            
            Zakaznik zakaznik = new Zakaznik();
            zakaznik.setId(rs.getLong("zakaznik_ID"));
            zakaznik.setMeno(rs.getString("zakaznik_meno"));
            zakaznik.setPriezvisko(rs.getString("zakaznik_priezvisko"));
            zakaznik.setPoznamka(rs.getString("zakaznik_poznamka"));
            zakaznik.setTelefon(rs.getInt("zakaznik_telefon"));
            zakaznik.setEmail(rs.getString("zakaznik_email"));
            
            Instruktor instruktor = new Instruktor();
            instruktor.setId(rs.getLong("instruktor_ID"));
            instruktor.setMeno(rs.getString("instruktor_meno"));
            instruktor.setPriezvisko(rs.getString("instruktor_priezvisko"));
            instruktor.setEmail(rs.getString("instruktor_email"));
            instruktor.setTyp(rs.getString("instruktor_typ"));
            instruktor.setAkreditacia(rs.getString("instruktor_akreditacia"));
            
            hodina.setZakaznik(zakaznik);
            hodina.setInstruktor(instruktor);
            
            return hodina;             
        }
        
    }
}
