package one.digitalinnovatoin.personapi.service;

import one.digitalinnovatoin.personapi.dto.request.PersonDTO;
import one.digitalinnovatoin.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovatoin.personapi.entity.Person;
import one.digitalinnovatoin.personapi.mapper.PersonMapper;
import one.digitalinnovatoin.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PersonService {

    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public MessageResponseDTO CreatePerson(PersonDTO personDTO){
        Person personToSave = personMapper.toModel(personDTO);

        Person savedPerson = personRepository.save(personToSave);
        return MessageResponseDTO
                .builder()
                .message("Created person with ID " + savedPerson.getId())
                .build();

    }
}
