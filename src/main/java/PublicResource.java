import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import java.util.List;

@Path("/api/public")
public class PublicResource {

    @Inject
    UserRepository userRepository;

    @GET
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAllUsers() {
        return userRepository.listAll();
    }

    @GET
    @Path("{userId}")
    @RolesAllowed({"user", "admin"})
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(@PathParam("userId") Long userId) {
        return userRepository.findById(userId);
    }

    @GET
    @Path("who-am-i")
    @RolesAllowed({"user", "admin", "owner"})
    @Produces(MediaType.APPLICATION_JSON)
    public User whoAmI(@Context SecurityContext securityContext) {

        return userRepository.find("username", securityContext.getUserPrincipal().getName()).firstResult();
//        String response = "";
//        if (securityContext.isUserInRole("user")) {
//            response = "You're just a user!";
//        } else if (securityContext.isUserInRole("owner")) {
//            response = "Wow, you own this thing.... Maybe we should give you more respect.";
//        } else if (securityContext.isUserInRole("admin")) {
//            response = "Wow, you're an admin!";
//        }
//
//        response += " Welcome, " + securityContext.getUserPrincipal().getName();
//        return response;
    }


    @POST
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public User createUser(User user) {

        userRepository.persist(user);
        return user;
    }

    @PUT
    @Path("{userId}")
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public User updateUser(@PathParam("userId") Long userId, User user) {

        return user;
    }

    @DELETE
    @Path("{userID}")
    @PermitAll
    @Produces(MediaType.TEXT_PLAIN)
    @Transactional
    public void deleteUser(@PathParam("userID") Long userId) {
        userRepository.deleteById(userId);
    }
}