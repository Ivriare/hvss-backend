package dao.factory;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DatabaseConfig {

    // ==============
    // PRIVATE FIELDS
    // ==============

    @Autowired
    private Environment _env;

    @Autowired
    private DataSource _dataSource;

    @Autowired
    private LocalContainerEntityManagerFactoryBean _entityManagerFactory;

    // ==============
    // PUBLIC METHODS
    // ==============

    /**
     * Method dataSource
     *
     * DataSource definition for database connection.
     */
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(_env.getProperty("spring.datasource.driver-class-name"));
        dataSource.setUrl(_env.getProperty("spring.datasource.url"));
        dataSource.setUsername(_env.getProperty("spring.datasource.username"));
        dataSource.setPassword(_env.getProperty("spring.datasource.password"));
        return dataSource;
    }

    /**
     * Method transactionManager
     *
     * Declare the transaction manager.
     */
    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager =
                new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                _entityManagerFactory.getObject());
        return transactionManager;
    }

    /**
     * Method persistenceExceptionTranslationPostProcessor
     *
     * PersistenceExceptionTranslationPostProcessor is a bean post processor
     * which adds an advisor to any bean that's annotated with \@Repository so
     * that any platform-specific exceptions are caught and then rethrown as one
     * Spring's unchecked data access exceptions (i.e. a subclass of
     * DataAccessException).
     */
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

} // class DatabaseConfig