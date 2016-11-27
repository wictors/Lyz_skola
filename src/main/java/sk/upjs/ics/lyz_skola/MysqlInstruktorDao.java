package sk.upjs.ics.lyz_skola;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

public class MysqlInstruktorDao implements InstruktorDao {

    private JdbcTemplate jdbcTemplate;

    public MysqlInstruktorDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
            
    @Override
    public List<Instruktor> dajInstruktorov() {
        String sql = "SELECT * FROM instruktor";
        BeanPropertyRowMapper<Instruktor> mapper = BeanPropertyRowMapper.newInstance(Instruktor.class);
        return jdbcTemplate.query(sql, mapper);
    }

    @Override
    public void pridajInstruktora(Instruktor instruktor) {
        jdbcTemplate.update("INSERT INTO instruktor(meno, priezvisko, email,"
                + "akreditacia, typ, heslo) VALUES(?,?,?)", instruktor.getMeno(),
                instruktor.getPriezvisko(), instruktor.getEmail(), instruktor.getAkreditacia(),
                instruktor.getTyp(), instruktor.getHeslo());
    }

    @Override
    public List<Instruktor> podlaPriezviska(String priezvisko) {
        String sql = "SELECT * FROM hodina WHERE priezvisko = ?";
        BeanPropertyRowMapper<Instruktor> mapper = BeanPropertyRowMapper.newInstance(Instruktor.class);
        return jdbcTemplate.query(sql, mapper, priezvisko);
    }

    @Override
    public Instruktor podlaId(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
