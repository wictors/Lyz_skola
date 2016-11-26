package sk.upjs.ics.lyz_skola;

import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class MysqlHodinaDao implements HodinaDao {
    
    private JdbcTemplate jdbcTemplate;

    public MysqlHodinaDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void pridajHodinu(Hodina hodina) {
        jdbcTemplate.update("INSERT INTO hodina(datum,od,po,instruktor,zakaznik,typ)"
                + " VALUES(?,?,?)", hodina.getDatum(),hodina.getOd(),hodina.getPo(),
                + hodina.getInstruktor(),hodina.getZakaznik(),hodina.getTyp());
    }

    @Override
    public List<Hodina> podlaDatumu(String datum) {
        String sql = "SELECT * FROM hodina WHERE datum = ?";
        BeanPropertyRowMapper<Hodina> mapper = BeanPropertyRowMapper.newInstance(Hodina.class);
        return jdbcTemplate.query(sql, mapper, datum);
    }

    @Override
    public List<String> menaInstruktorov(String datum) {
        String sql = "select I.priezvisko from hodina as H join instruktor as I on H.instruktor = I.id"
                + " WHERE datum = ?";
        BeanPropertyRowMapper<String> mapper = BeanPropertyRowMapper.newInstance(String.class);
        return jdbcTemplate.query(sql, mapper, datum);
    }
    
}
