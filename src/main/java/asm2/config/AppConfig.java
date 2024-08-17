package asm2.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;
import java.util.logging.Logger;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = "asm2")
@PropertySource("classpath:persistence-mysql.properties")
public class AppConfig {
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setContentType("text/html;charset=UTF-8"); // Đảm bảo sử dụng UTF-8
        return viewResolver;
    }

    @Autowired
    private Environment env;

    private Logger logger = Logger.getLogger(getClass().getName());
    @Bean
    public DataSource securityDataSource(){
        //create connection pool
        ComboPooledDataSource securityDataSources = new ComboPooledDataSource();

        //set jdbc driver class
        try {
            securityDataSources.setDriverClass(env.getProperty("jdbc.driver"));
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }
        // log the connection propsp
        logger.info(">>>> jdbc.url=" + env.getProperty("jdbc.url"));
        logger.info(">>>> jdbc.user=" + env.getProperty("jdbc.user"));


        securityDataSources.setJdbcUrl(env.getProperty("jdbc.url"));
        securityDataSources.setUser(env.getProperty("jdbc.user"));
        securityDataSources.setPassword(env.getProperty("jdbc.password"));

        securityDataSources.setInitialPoolSize(Integer.parseInt(env.getProperty("connection.pool.initialPoolSize"))  );
        securityDataSources.setMinPoolSize(Integer.parseInt(env.getProperty("connection.pool.minPoolSize"))  );
        securityDataSources.setMaxPoolSize(Integer.parseInt(env.getProperty("connection.pool.maxPoolSize"))  );
        securityDataSources.setMaxIdleTime(Integer.parseInt(env.getProperty("connection.pool.maxIdleTime"))  );



        return securityDataSources;

    }


    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(securityDataSource());
        sessionFactory.setPackagesToScan(env.getProperty("hibernate.packagesToScan"));
        sessionFactory.setHibernateProperties(getHibernateProperties());
        return sessionFactory;
    }
    private Properties getHibernateProperties() {
        Properties props = new Properties();
        props.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
        props.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        return props;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);
        return txManager;
    }




    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver commonsMultipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("UTF-8");
        // Đặt giới hạn kích thước tối đa cho file (ở đây là 10MB)
        resolver.setMaxUploadSize(10 * 1024 * 1024); // 10MB
        return resolver;
    }
}
