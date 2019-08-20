package com.demo;

import com.demo.api.FileApiResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class APIDemoApplication extends Application<APIDemoConfiguration> {

    public static void main(final String[] args) throws Exception {
        new APIDemoApplication().run(args);
    }

    @Override
    public String getName() {
        return "APIDemo";
    }

    @Override
    public void initialize(final Bootstrap<APIDemoConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final APIDemoConfiguration configuration,
                    final Environment environment) {
        environment.jersey().register(new FileApiResource());
    }

}
