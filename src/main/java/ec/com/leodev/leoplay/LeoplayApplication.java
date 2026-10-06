package ec.com.leodev.leoplay;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
        info = @Info(
                title = "LEO-PLAY API Documentation",
                version = "1.0.0-RELEASE",
                description = "Documentación de la API con OpenAPI 3 - Leo-play",
                contact = @Contact(name = "Leo Medina", email = "tioleodeveloper@gmail.com", url = "https://github.com/leomedinadev"),
                license = @License(name = "Apache 2.0", url = "http://www.apache.org/licenses/LICENSE-2.0.html")
        )
)
@SpringBootApplication
public class LeoplayApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeoplayApplication.class, args);
	}

}
