package com.alex.listings;

import com.alex.listings.db.ListingDAO;
import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;

/**
 *
 * Created by alex on 14/07/2016.
 */
public class HelloWorldApplication extends Application<HelloWorldConfig> {

    public static void main(String[] args) throws Exception {
        new HelloWorldApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<HelloWorldConfig> bootstrap) {
        // nothing to do yet
    }

    /**
     * Register all Resources and HealthChecks here. Right now this must be done manually for each Resource
     * @param config
     * @param env
     */
    @Override
    public void run(HelloWorldConfig config, Environment env) {
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(env, config.getDataSourceFactory(), "alex_db");
        final ListingDAO listingDAO = jdbi.onDemand(ListingDAO.class);


        env.jersey().register(new HelloWorldResource(listingDAO, config.getTemplate(), config.getDefaultName()));
        env.healthChecks().register("template", new TemplateHealthCheck(config.getTemplate()));
    }

}
