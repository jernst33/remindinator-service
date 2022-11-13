import org.bson.types.ObjectId;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Path("/persons")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource {

    @Inject
    PersonRepository personRepository;

    @GET
    public List<Person> list() {
        return personRepository.listAll();
    }

    @GET
    @Path("/{id}")
    public Person get(String id) {
        return personRepository.findById(new ObjectId(id));
    }

    @POST
    public Response create(Person person) {
        personRepository.persist(person);
        return Response.created(URI.create("/persons/" + person.id)).build();
    }

    @POST
    @Path("/create/thane")
    public Person createForThane(Person person) {

        System.out.println(person.getBirth());

        Date normal = new Date("03/05/1999");

        Instant i = normal.toInstant();
        person.setBirth(i);
        System.out.println(person.getBirth());


        personRepository.persist(person);

        return person;
    }

    @PUT
    @Path("/{id}")
    public void update(String id, Person person) {
        personRepository.update(person);
    }

    @DELETE
    @Path("/{id}")
    public void delete(String id) {
        Person person = personRepository.findById(new ObjectId(id));
        if(person == null) {
            throw new NotFoundException();
        }
        personRepository.delete(person);
    }

    @GET
    @Path("/search/{name}")
    public Person search(String name) {
        return personRepository.findByName(name);
    }

    @GET
    @Path("/count")
    public Long count() {
        return personRepository.count();
    }
}