package one.digitalinnovatoin.personapi.service;

import one.digitalinnovatoin.personapi.dto.request.PersonDTO;
import one.digitalinnovatoin.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovatoin.personapi.entity.Person;
import one.digitalinnovatoin.personapi.exception.PersonNotFoundException;
import one.digitalinnovatoin.personapi.mapper.PersonMapper;
import one.digitalinnovatoin.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


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
        return CreateMessageResponse("Created person with ID ", savedPerson.getId());

    }

    public List<PersonDTO> listAll() {
        List<Person> allPeople = personRepository.findAll();
        return allPeople.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
        Person person = VerifyIfExists(id);

        return personMapper.toDTO(person);
    }

    public void deleteId(Long id) throws PersonNotFoundException {
        VerifyIfExists(id);
        personRepository.deleteById(id);
    }

    public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException{
        VerifyIfExists(id);
        Person personToUpdate = personMapper.toModel(personDTO);

        Person updatedPerson = personRepository.save(personToUpdate);
        return CreateMessageResponse("Updated person with ID ", updatedPerson.getId());
    }

    private MessageResponseDTO CreateMessageResponse(String message, Long id) {
        return MessageResponseDTO
                .builder()
                .message(message + id)
                .build();
    }

    private Person VerifyIfExists(Long id) throws PersonNotFoundException {
        return personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
    }
}
