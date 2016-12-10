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
    public List<Zakaznik> utriedenyPodlaPriezviska() {
        String sql = "SELECT * FROM lyz_skola.zakaznik order by priezvisko";
        BeanPropertyRowMapper<Zakaznik> mapper = BeanPropertyRowMapper.newInstance(Zakaznik.class);
        return jdbcTemplate.query(sql, mapper);
    }

    @Override
    public List<Zakaznik> podlaTelefonu(int telefon) {
        String sql = "SELECT * from zakaznik WHERE telefon = ?";
        BeanPropertyRowMapper<Zakaznik> mapper = BeanPropertyRowMapper.newInstance(Zakaznik.class);
        return jdbcTemplate.query(sql, mapper,telefon);
    }

    @Override
    public List<Zakaznik> podlaPriezviska(String priezvisko) {
        String sql = "SELECT * FROM zakaznik WHERE priezvisko = ?";
        BeanPropertyRowMapper<Zakaznik> mapper = BeanPropertyRowMapper.newInstance(Zakaznik.class);
        
        return jdbcTemplate.query(sql,mapper,priezvisko);
    }

    @Override
    public void pridajZakaznika(Zakaznik zakaznik) {
        String sql = "INSERT INTO zakaznik (meno, priezvisko, poznamka, telefon, email)"
                + " VALUES(?,?,?,?,?)";
        jdbcTemplate.update(sql,zakaznik.getMeno(),zakaznik.getPriezvisko(),zakaznik.getPoznamka(),
                zakaznik.getTelefon(),zakaznik.getEmail());
    }

    @Override
    public void aktualizujPoznamka(String poznamka, Long id) {
        String sql = "UPDATE zakaznik SET poznamka = ? WHERE id = ?";
        jdbcTemplate.update(sql,poznamka,id);
    }

    @Override
    public void aktualizujTelefon(int telefon, Long id) {
        String sql = "UPDATE zakaznik SET telefon = ? WHERE id = ?";
        jdbcTemplate.update(sql,telefon,id);
    }

    @Override
    public List<Zakaznik> podlaId(Long id) {
        String sql = "SELECT * FROM zakaznik WHERE id = ?";
        BeanPropertyRowMapper<Zakaznik> mapper = BeanPropertyRowMapper.newInstance(Zakaznik.class);
        
        return jdbcTemplate.query(sql,mapper,id);
    }

    @Override
    public void vymazZakaznika(Long id) {
        String sql = "DELETE FROM zakaznik WHERE id = ?";
        jdbcTemplate.update(sql,id);
    }

    @Override
    public boolean nemaHodinu(Long id) {
        String sql = "SELECT count(*) FROM hodina WHERE zakaznik = ?";
        BeanPropertyRowMapper<Hodina> mapper = BeanPropertyRowMapper.newInstance(Hodina.class);
        Long pocet = jdbcTemplate.queryForObject(sql, Long.class,id);
        if(pocet == 0L){
            return true;
        }else{
            return false;
        }
        

    }
    
    
}
