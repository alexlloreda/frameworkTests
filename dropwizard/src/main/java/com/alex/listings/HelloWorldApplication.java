package com.alex.listings;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

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
        env.jersey().register(new HelloWorldResource(config.getTemplate(), config.getDefaultName()));
        env.healthChecks().register("template", new TemplateHealthCheck(config.getTemplate()));
    }

}