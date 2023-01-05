import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.LinkedList;
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