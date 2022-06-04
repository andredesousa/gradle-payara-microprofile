package app;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
@RequestScoped
public class AppResource {

    @Inject
    private transient AppService appService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return this.appService.hello();
    }
}
