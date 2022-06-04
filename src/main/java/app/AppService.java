package app;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class AppService {

    @Inject
    @ConfigProperty(name = "message")
    private transient String message;

    public String hello() {
        return message;
    }
}
