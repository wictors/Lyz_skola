package sk.upjs.ics.lyz_skola;

import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;


public class MySqlPrihlasenieDao implements PrihlasenieDao {
    
    private JdbcTemplate jdbcTemplate;

    public MySqlPrihlasenieDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    

    @Override
    public List<Prihlasenie> dajPrihlasenia() {
        String sql = "SELECT * FROM prihlasenie";
        BeanPropertyRowMapper<Prihlasenie> mapper = BeanPropertyRowMapper.newInstance(Prihlasenie.class);
        return jdbcTemplate.query(sql,mapper);
    }

    @Override
    public void ulozPrihlasenie(Prihlasenie prihlasenie) {
        String sql = "INSERT INTO prihlasenie(datum,email) VALUES(?,?)";
        jdbcTemplate.update(sql,prihlasenie.getDatum(),prihlasenie.getEmail());
    }
    
}
