package com.gruponutresa.prisma;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gruponutresa.prisma.bean.DatabaseConfiguration;
import io.dropwizard.Configuration;

public class PrismaConfiguration extends Configuration {

	@JsonProperty
	private DatabaseConfiguration databaseConfiguration;

	public DatabaseConfiguration getDatabaseConfiguration() {
		return databaseConfiguration;
	}

	public void setDatabaseConfiguration(DatabaseConfiguration databaseConfiguration) {
		this.databaseConfiguration = databaseConfiguration;
	}

}
