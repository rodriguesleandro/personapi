package one.digitalinnovatoin.personapi.repository;

import one.digitalinnovatoin.personapi.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
