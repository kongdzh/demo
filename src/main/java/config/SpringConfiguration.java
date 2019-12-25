package config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import javafx.scene.chart.PieChart;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * @Configuration 当配置类（此处为我们自定义的SpringConfiguration）作为参数传入AnnotationConfigApplicationContext中时，
 *                此注解可以不写。
 * @Import 可以导入别的配置类，别的配置类里可以不加@Configuration, 也可以不扫描
 *
 * @ComponentScan 扫描目录，就跟xml里的 <context:component-scan base-package="com.wonders"></context:component-scan>
 *                  属性：value
 *
 * @PropertySource  指定properties文件位置
 *                  属性: value   关键字：classpath 表示类路径下
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan({"com.wonders", "config"})
@PropertySource("classpath:jdbcConfig.properties")
@Import(JdbcConfiguration.class)
public class SpringConfiguration {
    /**
     *
     * @param
     * @return
     */
    @Bean(name = "queryRunner")
    @Scope("prototype") //多例的
    public QueryRunner createQueryRunner() {
        return new QueryRunner();
    }
}
