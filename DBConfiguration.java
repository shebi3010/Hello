package com.js.config;

import java.util.Properties;

import javax.management.Notification;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.h2.engine.User;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.js.model.BlogComment;
import com.js.model.BlogPost;
import com.js.model.Friend;
import com.js.model.Job;
import com.js.model.ProfilePicture;

@Configuration
@EnableTransactionManagement
public class DBConfiguration {
	 public DBConfiguration()
	{
		 System.out.println("DBConfiguration class is instantiated"); 
	  }
	 
		@Bean(name="sessionFactory") 
		public SessionFactory sessionFactory() {
			System.out.println("Entering sessionFactory creation method");
			LocalSessionFactoryBuilder lsf=
					new LocalSessionFactoryBuilder(getDataSource());
			Properties hibernateProperties=new Properties();
			hibernateProperties.setProperty(
					"hibernate.dialect", "org.hibernate.dialect.H2Dialect");
			hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
			hibernateProperties.setProperty("hibernate.show_sql", "true");
			lsf.addProperties(hibernateProperties);
			Class classes[]=new Class[]{User.class,Job.class,BlogPost.class,Notification.class,BlogComment.class,Friend.class,ProfilePicture.class};//class objects of all entities
		    return lsf.addAnnotatedClasses(classes).buildSessionFactory();
		}

		@Bean(name="dataSource")
		public DataSource getDataSource() {
			System.out.println("Entering DataSource Bean creation method ");
			BasicDataSource dataSource = new BasicDataSource();
		    dataSource.setDriverClassName("org.h2.Driver");
		    dataSource.setUrl("jdbc:h2:tcp://localhost/~/media");
		    dataSource.setUsername("root");
		    dataSource.setPassword("root");
		    System.out.println("DataSource bean " +dataSource);
		    return dataSource;
		}
		
		@Bean
		public HibernateTransactionManager hibTransManagement(){
			return new HibernateTransactionManager(sessionFactory());
		}
	}
	


