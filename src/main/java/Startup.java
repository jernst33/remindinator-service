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
        userRepository.persist(new User("admin", "admin@test.com", LocalDate.of(2002, 6, 11), "12345", "admin"));
        userRepository.persist(new User("user", "user@test.com",LocalDate.of(1999, 3, 5), "12345", "user"));
        userRepository.persist(new User("jacob", "jacob@test.com", LocalDate.of(1999, 3, 5), "12345", "owner"));
        userRepository.persist(new User("thane", "thane@test.com", LocalDate.of(1999, 3, 5), "12345", "owner"));
    }
}