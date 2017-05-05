package com.gruponutresa.prisma.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.validation.Validation;
import com.google.inject.Injector;
import com.gruponutresa.prisma.PrismaConfiguration;
import com.gruponutresa.prisma.bean.DatabaseConfiguration;
import io.dropwizard.configuration.ConfigurationFactory;
import io.dropwizard.jackson.Jackson;

public class ConexionHelper {

	private ConexionHelper() {
		// constructor
	}

	private static Injector injector = null;
	private static String configFilename;

	public static String getConfigFilename() {
		return configFilename;
	}

	public static void setConfigFilename(String configuration) {
		ConexionHelper.configFilename = configuration;
	}

	public static Properties createPropertiesFromConfiguration(PrismaConfiguration localConfiguration) {

		DatabaseConfiguration databaseConfiguration = localConfiguration.getDatabaseConfiguration();
		List<String> propertiesList = new ArrayList<>();
		propertiesList.add("hibernate.dialect");
		propertiesList.add("hibernate.show_sql");
		propertiesList.add("hibernate.hbm2ddl.auto");
		propertiesList.add("hibernate.dialect");
		propertiesList.add("hibernate.connection.driver_class");
		propertiesList.add("hibernate.connection.username");
		propertiesList.add("hibernate.connection.password");

		Properties properties = new Properties();
		properties.setProperty("javax.persistence.jdbc.url", databaseConfiguration.getUrl());

		for (String p : propertiesList) {
			String val = databaseConfiguration.getProperties().get(p);
			if (val != null) {
				properties.setProperty(p, val);
			}
		}

		return properties;
	}

	public static Injector getInjector() {
		if (injector == null) {
			throw new IllegalArgumentException("call StartHelper.init() first!");
		}
		return injector;
	}

	public static <T> T getInstance(Class<T> c) {
		return getInjector().getInstance(c);
	}

	public static PrismaConfiguration createConfiguration(String configFilename) {
		ConfigurationFactory<PrismaConfiguration> factory = new ConfigurationFactory<>(PrismaConfiguration.class,
				Validation.buildDefaultValidatorFactory().getValidator(), Jackson.newObjectMapper(), "");
		PrismaConfiguration configuration;
		try {
			configuration = factory.build(new File(configFilename));
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}

		return configuration;
	}

}
