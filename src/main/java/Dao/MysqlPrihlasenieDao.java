package Dao;

import Dao.PrihlasenieDao;
import Entity.Prihlasenie;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;


public class MysqlPrihlasenieDao implements PrihlasenieDao {
    
    private JdbcTemplate jdbcTemplate;

    public MysqlPrihlasenieDao(JdbcTemplate jdbcTemplate) {
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
        String sql = "INSERT INTO prihlasenie(datum,email,stav) VALUES(?,?,?)";
        jdbcTemplate.update(sql,prihlasenie.getDatum(),prihlasenie.getEmail(),prihlasenie.getStav());
    }
    
}
