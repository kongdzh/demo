package config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import javafx.scene.chart.PieChart;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@Configuration
@ComponentScan("com.wonders")
public class SpringConfiguration {
    /**
     *
     * @param dataSource
     * @return
     */
    @Bean(name = "queryRunner")
    public QueryRunner createQueryRunner(DataSource dataSource) {
        return new QueryRunner(dataSource);
    }

    /**
     *
     * @return
     */
    @Bean(name = "dataSource")
    public DataSource createDataSource() {
        try {
            ComboPooledDataSource ds = new ComboPooledDataSource();
            ds.setDriverClass("com.mysql.jdbc.Driver");
            ds.setJdbcUrl("jdbc:mysql://localhost:3306/spring");
            ds.setUser("root");
            ds.setPassword("wonders");
            return ds;
        }catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
