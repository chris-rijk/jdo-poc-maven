package ta.microservices.common.service.lifecycle;

import java.io.IOException;
import java.net.URI;
import jdotest.dto.enums.DiagnosticType;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;

public class StartHttpServer {
    public static void Start(SystemConfiguration sysConfig, JerseyConfig config) throws IOException {
        URI uri = sysConfig.getBindURI();
        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(uri, config);
        config.AuditDiagnostics(DiagnosticType.Startup, "Jersey started at "+uri);

        //gracefully exit Grizzly services when app is shut down
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                config.AuditDiagnostics(DiagnosticType.Shutdown, "Stopping Jersey......");
                server.shutdownNow();
                config.AuditDiagnostics(DiagnosticType.Shutdown, "Jersey stopped");
            }
        }));
        server.start();
    }
}
