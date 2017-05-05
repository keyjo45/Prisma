package com.gruponutresa.prisma;

import java.util.EnumSet;
import java.util.Properties;
import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration.Dynamic;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import com.google.inject.persist.PersistFilter;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.gruponutresa.prisma.constants.Valores;
import com.gruponutresa.prisma.resources.ResponsableService;
import com.gruponutresa.prisma.resources.RestConfig;
import com.gruponutresa.prisma.util.ConexionHelper;
import com.hubspot.dropwizard.guice.GuiceBundle;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class PrismaApplication extends Application<PrismaConfiguration> {
	
	private GuiceBundle<PrismaConfiguration> guiceBundle;

	public static void main(final String[] args) throws Exception {
		 for (int i = 0; i < args.length; i++) {
	            if (args[i].endsWith(".yml")) {
	                ConexionHelper.setConfigFilename(args[i]);
	            }
	        }
		new PrismaApplication().run(args);
	}

	@Override
	public String getName() {
		return "MVNPrisma3020";
	}

	@Override
	public void initialize(final Bootstrap<PrismaConfiguration> bootstrap) {

		

		PrismaConfiguration configuration = ConexionHelper.createConfiguration(ConexionHelper.getConfigFilename());
		Properties jpaProperties = ConexionHelper.createPropertiesFromConfiguration(configuration);

		JpaPersistModule jpaPersistModule = new JpaPersistModule(Valores.JPA_UNIT);
		jpaPersistModule.properties(jpaProperties);

		guiceBundle = GuiceBundle.<PrismaConfiguration>newBuilder().addModule(new PrismaGuideModule())
				.addModule(jpaPersistModule).enableAutoConfig("com.gruponutresa")
				.setConfigClass(PrismaConfiguration.class).build();

		bootstrap.addBundle(guiceBundle);

	}

	@Override
	public void run(final PrismaConfiguration configuration, final Environment environment) {

		environment.servlets().addFilter("persistFilter", guiceBundle.getInjector().getInstance(PersistFilter.class))
				.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");

		Dynamic filter = environment.servlets().addFilter("CORS", CrossOriginFilter.class);
		filter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
		filter.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "GET,PUT,POST,DELETE,OPTIONS");
		filter.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
		filter.setInitParameter(CrossOriginFilter.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, "*");
		filter.setInitParameter(CrossOriginFilter.EXPOSED_HEADERS_PARAM,
				"Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin,Location");
		filter.setInitParameter(CrossOriginFilter.ALLOWED_HEADERS_PARAM,
				"Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin,Location");
		filter.setInitParameter(CrossOriginFilter.ALLOW_CREDENTIALS_PARAM, "true");
		
		environment.jersey().register(guiceBundle.getInjector().getInstance(RestConfig.class));
		
		environment.jersey().register(guiceBundle.getInjector().getInstance(ResponsableService.class));

	}

}
