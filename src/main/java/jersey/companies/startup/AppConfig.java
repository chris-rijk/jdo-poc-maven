package jersey.companies.startup;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.models.Info;
import jersey.companies.resources.CompanyService;
import jersey.companies.resources.ICompanyService;
import ta.microservices.common.service.lifecycle.JerseyConfig;

public class AppConfig extends JerseyConfig {
    
    public void Setup() {
        this.RegisterDefault();

        ConfigureSwagger();
        RegisterMyServices();
    }   

    private void ConfigureSwagger() {
        String resources = "jersey.companies.resources";
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost("localhost");
        beanConfig.setBasePath("/");
        beanConfig.setResourcePackage(resources);
        beanConfig.setPrettyPrint(true);
        beanConfig.setTitle("Companies API");
        beanConfig.setDescription("Companies API");
        Info info = beanConfig.getInfo();
        if (info == null) {
            info = new Info();
            beanConfig.setInfo(info);
        }
        info.setVersion("1.0");
        beanConfig.setScan(true);
    }

    private void RegisterMyServices() {
        register(CompanyService.class, ICompanyService.class);
    }
}
