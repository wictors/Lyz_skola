package Dao;

import Dao.InstruktorDao;
import Entity.Instruktor;
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
    public List<Instruktor> utriedenyPodlaPriezviska() {
        String sql = "SELECT * FROM lyz_skola.instruktor ORDER BY priezvisko";
        BeanPropertyRowMapper<Instruktor> mapper = BeanPropertyRowMapper.newInstance(Instruktor.class);
        return jdbcTemplate.query(sql, mapper);
    }
    
    @Override
    public List<Instruktor> podlaPriezviska(String priezvisko) {
        String sql = "SELECT * FROM instruktor WHERE priezvisko = ?";
        BeanPropertyRowMapper<Instruktor> mapper = BeanPropertyRowMapper.newInstance(Instruktor.class);
        return jdbcTemplate.query(sql, mapper, priezvisko);
    }
    
    @Override
    public List<Instruktor> podlaAkreditacie(String akreditacia) {
        String sql = "SELECT * FROM instruktor WHERE akreditacia = ?";
        BeanPropertyRowMapper<Instruktor> mapper = BeanPropertyRowMapper.newInstance(Instruktor.class);      
        return jdbcTemplate.query(sql,mapper,akreditacia);
    }
    
    @Override
    public List<Instruktor> podlaTypu(String typ) {
        String sql = "SELECT * FROM instruktor WHERE typ = ?";
        BeanPropertyRowMapper<Instruktor> mapper = BeanPropertyRowMapper.newInstance(Instruktor.class);      
        return jdbcTemplate.query(sql,mapper,typ);
    }

    @Override
    public void pridajInstruktora(Instruktor instruktor) {
        jdbcTemplate.update("INSERT INTO instruktor(meno, priezvisko, email,"
                + "akreditacia, typ, heslo) VALUES(?,?,?,?,?,?)", instruktor.getMeno(),
                instruktor.getPriezvisko(), instruktor.getEmail(), instruktor.getAkreditacia(),
                instruktor.getTyp(), instruktor.getHeslo());
    }

    @Override
    public void vymazInstruktora(Long id) {
        String sql = "DELETE FROM instruktor WHERE id = ?";
        jdbcTemplate.update(sql,id);
    }

    @Override
    public Instruktor podlaEmailu(String email) {
        String sql = "SELECT * FROM instruktor WHERE email = ?";
        BeanPropertyRowMapper<Instruktor> mapper = BeanPropertyRowMapper.newInstance(Instruktor.class);      
        List<Instruktor> instruktori = jdbcTemplate.query(sql,mapper, email);
        if(instruktori.isEmpty()){
            return null;
        }else{
            Instruktor instruktor = instruktori.get(0);
            return instruktor;    
        }        
    }

    @Override
    public boolean nemaHodinu(Long id) {
        String sql = "SELECT count(*) FROM hodina WHERE instruktor = ?";
        Long pocet = jdbcTemplate.queryForObject(sql,Long.class,id);
        if(pocet == 0L){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void aktualizujAkreditaciu(Long id, String akreditacia) {
        String sql = "UPDATE instruktor SET akreditacia = ? WHERE id = ?";
        jdbcTemplate.update(sql,akreditacia,id);
    }

    @Override
    public void aktualizujTyp(Long id, String typ) {
        String sql = "UPDATE instruktor SET typ = ? WHERE id = ?";
        jdbcTemplate.update(sql,typ,id);       
    }
    
}
