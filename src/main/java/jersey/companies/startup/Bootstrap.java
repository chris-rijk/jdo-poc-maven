package jersey.companies.startup;

import java.io.IOException;
import ta.microservices.common.service.lifecycle.StartHttpServer;
import ta.microservices.common.service.lifecycle.StartServiceInstance;
import ta.microservices.common.service.lifecycle.SystemConfiguration;

public class Bootstrap {

    public static void main(String[] args) throws IOException {
        AppConfig app = new AppConfig();
        SystemConfiguration config = new SystemConfiguration();
        StartServiceInstance.Start(app, config);
        app.Setup();
        StartHttpServer.Start(config, app);
    }
}
