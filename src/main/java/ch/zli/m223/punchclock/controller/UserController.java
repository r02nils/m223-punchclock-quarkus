package ch.zli.m223.punchclock.controller;

import java.util.List;

import javax.inject.Inject;
import javax.json.JsonException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.json.JSONException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import ch.zli.m223.punchclock.domain.Entry;
import ch.zli.m223.punchclock.domain.User;
import ch.zli.m223.punchclock.service.EntryService;
import ch.zli.m223.punchclock.service.UserService;
import io.quarkus.vertx.web.Header;

/**
 * EntryController
 * @author Nils Rothenbühler
 * @version 1.0
 * @date: 2022-07-15
 */
@Path("/users")
@Tag(name = "User", description = "Handling of users")
public class UserController {

    @Inject
    UserService userService;

   
    /**
     * Alle Users werden zurückgegeben
     * @return List<User>
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "List all Users", description = "")
    public List<User> list() {
        return userService.findAll();
    }

    /**
     * Der Benutzer wird registriert
     * @param user
     * @return
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Signup", description = "")
    public int signup(@RequestBody User user) {
       return userService.signupUser(user);
    }

    /**
     * Der Benutzer meldet sich mit seinen Anmeldedaten an
     * @param user
     * @return
     */
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Login", description = "")
    public Response login(@RequestBody User user) {
       return userService.loginUser(user);
    }

   /**
    * Es wird überprüft, ob der Token existiert
    * @param myToken
    * @return
    * @throws JsonException
    * @throws JSONException
    */
    @POST  
    @Path("/login/check")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Login", description = "")
    public int check(@RequestBody String myToken) throws JsonException, JSONException {
       return userService.checkIfLoggedIn(myToken);
    }
}
