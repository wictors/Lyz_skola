package sk.upjs.ics.lyz_skola;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public enum ObjectFactory {
    INSTANCE;
    
    private JdbcTemplate jdbcTemplate;
    private HodinaDao hodinaDao;
    private InstruktorDao instruktorDao;
    private ZakaznikDao zakaznikDao;
    
    
    public JdbcTemplate getJdbcTemplate() {
        if (jdbcTemplate == null) {
            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setUrl("jdbc:mysql://localhost/lyz_skola?serverTimezone=UTC");
            dataSource.setUser("lyz_skola");
            dataSource.setPassword("lyzovacka");
            jdbcTemplate = new JdbcTemplate(dataSource);
        }
        return jdbcTemplate;        
    }
    
    public HodinaDao getHodinaDao() {
        if (hodinaDao == null) {
            hodinaDao = new MysqlHodinaDao(getJdbcTemplate());            
        }
        return hodinaDao;
    }
    
//    public InstruktorDao getInstruktorDao(){
//        if(instruktorDao == null){
//            instruktorDao = new MysqlInstruktorDao(getJdbcTemplate());
//        }
//        return instruktorDao;
//    }
//    
//    public ZakaznikDao getZakaznikDao(){
//        if(zakaznikDao == null){
//            zakaznikDao = new MysqlZakaznikDao(getJdbcTemplate());
//        }
//        return zakaznikDao;
//    }
    
}
