import io.quarkus.mongodb.panache.PanacheMongoRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PersonRepository implements PanacheMongoRepository<Person> {

    // put your custom logic here as instance methods

    public Person findByName(String name){
        return find("name", name).firstResult();
    }

//    public List<Person> findAlive(){
//        return list("status", Status.Alive);
//    }

    public void deleteLoics(){
        delete("name", "Loïc");
    }
}