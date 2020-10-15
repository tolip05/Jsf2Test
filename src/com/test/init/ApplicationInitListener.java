package com.test.init;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.cc.db.DBConfig;
import com.cc.db.connection.HikariConnectionFactory;
import com.cc.db.connection.HikariCustomConfig;
import com.cc.logging.impl.LogFactorySystemOut;

@WebListener
public class ApplicationInitListener implements ServletContextListener{
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		 DBConfig.initDBConfig(LogFactorySystemOut.getInstance(),
                 new HikariConnectionFactory(new HikariCustomConfig("org.postgresql.Driver",
                         "jdbc:postgresql://xx.xxx.xx.xxx:xxxx/test",
                         "xxxxxxxxx",
                         "xxxxx",
                         10,
                         "SELECT 1")));

	}
}
