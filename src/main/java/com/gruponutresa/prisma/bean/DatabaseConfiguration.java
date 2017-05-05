package com.gruponutresa.prisma.bean;

import java.util.Map;

import javax.validation.constraints.NotNull;

import com.google.common.collect.Maps;

public class DatabaseConfiguration {

	@NotNull
	private String driverClass;
	private String user;
	private String password;
	@NotNull
	private String url;
	@NotNull
	private Map<String, String> properties = Maps.newLinkedHashMap();

	public String getDriverClass() {
		return driverClass;
	}

	public void setDriverClass(String driverClass) {
		this.driverClass = driverClass;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Map<String, String> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}

}
