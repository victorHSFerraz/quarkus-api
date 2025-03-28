package io.github.victorhsferraz;

import javax.ws.rs.core.Application;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;

@OpenAPIDefinition(info = @Info(title = "Quarkus Social", version = "1.0.0", contact = @Contact(name = "Victor Ferraz", url = "https://github.com/victorhsferraz"), license = @License(name = "Apache 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0.html")))
public class QuarkusSocialApplication extends Application {

}
