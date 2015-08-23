package birt.config;

import birt.util.Constants;
import com.mysql.jdbc.Driver;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Colezea on 13/04/2015.
 */
@Configuration
@EnableJpaRepositories(basePackages = "birt.repository")
@EnableTransactionManagement
@ComponentScan(basePackages = "birt")
public class DataSourceConfig {

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws SQLException {
        LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
        lef.setDataSource(dataSource());
        lef.setPackagesToScan("birt.domain");
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        lef.setJpaVendorAdapter(vendorAdapter);

        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        jpaProperties.put("hibernate.hbm2ddl.auto", "update");
        jpaProperties.put("hibernate.show_sql", "false");
        lef.setJpaProperties(jpaProperties);
        return lef;
    }

    @Bean
    public SimpleDriverDataSource dataSource() throws SQLException {
        String currentDir = System.getProperty("user.dir");
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        Driver driver = new Driver();
        dataSource.setDriver(driver);
        dataSource.setUrl(Constants.DB_URL.toString());
        dataSource.setUsername(Constants.DB_USER.toString());
        dataSource.setPassword(Constants.DB_PASSWORD.toString());

        Connection connection = dataSource.getConnection();
        ScriptRunner scriptRunner = new ScriptRunner(connection);
        InputStreamReader reader = null;
        try {
            reader = new InputStreamReader(new FileInputStream(currentDir + Constants.DB_INIT_SCRIPT));
            scriptRunner.runScript(reader);
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        connection.close();
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(emf);

        return jpaTransactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslationPostProcessor(){
        return new PersistenceExceptionTranslationPostProcessor();
    }

}