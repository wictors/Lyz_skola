package sk.upjs.ics.lyz_skola;

import java.sql.ResultSet;
import java.sql.SQLException;
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
        return jdbcTemplate.query(sql, new UlohaRowMapper(), datum);
    }

    @Override
    public List<String> menaZakaznikov(String datum) {
        String sql = "select Z.priezvisko from hodina as H join zakaznik as Z on H.zakaznik = Z.id"
                + " WHERE datum = ?";
        return jdbcTemplate.query(sql, new UlohaRowMapper(), datum);

    }
    
    
    private class UlohaRowMapper implements RowMapper<String> {

        @Override
        public String mapRow(ResultSet rs, int i) throws SQLException {
            String meno ="";
            meno = rs.getString("priezvisko");
            return meno;
        }
        
    }
}
