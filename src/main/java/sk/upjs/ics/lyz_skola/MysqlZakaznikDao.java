package sk.upjs.ics.lyz_skola;

import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class MysqlZakaznikDao implements ZakaznikDao {
    
    private JdbcTemplate jdbcTemplate;

    public MysqlZakaznikDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    } 

    @Override
    public List<Zakaznik> dajZakaznikov() {
        String sql = "SELECT * FROM zakaznik";
        BeanPropertyRowMapper<Zakaznik> mapper = BeanPropertyRowMapper.newInstance(Zakaznik.class);
        return jdbcTemplate.query(sql, mapper);
    }

    @Override
    public List<Zakaznik> utriedenyPodlaPriezviska(String priezvisko) {
        String sql = "SELECT * FROM lyz_skola.zakaznik order by ?";
        BeanPropertyRowMapper<Zakaznik> mapper = BeanPropertyRowMapper.newInstance(Zakaznik.class);
        return jdbcTemplate.query(sql, mapper,priezvisko);
    }

    @Override
    public Zakaznik podlaId(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Zakaznik podlaPriezviska(String priezvisko) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void pridajZakaznika(Zakaznik zakaznik) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
