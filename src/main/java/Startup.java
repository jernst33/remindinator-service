import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;

import io.quarkus.runtime.StartupEvent;

import java.time.LocalDate;
import java.util.Date;


@Singleton
public class Startup {

    @Inject
    UserRepository userRepository;

    @Transactional
    public void loadUsers(@Observes StartupEvent evt) {
        // reset and load all test users
        userRepository.deleteAll();
        userRepository.persist(new User("Jacob", "admin@test.com", LocalDate.of(1999, 3, 5), "12345", "admin"));
        userRepository.persist(new User("Thane", "admin@test.com",LocalDate.of(1999, 3, 5), "12345", "admin"));
        userRepository.persist(new User("Mike", "user@test.com", LocalDate.of(2002, 2, 6), "12345", "user"));
        userRepository.persist(new User("Jerry", "user@test.com", LocalDate.of(1996, 1, 18), "12345", "user"));
        userRepository.persist(new User("Bart", "user@test.com", LocalDate.of(2009, 5, 6), "12345", "user"));
    }
}