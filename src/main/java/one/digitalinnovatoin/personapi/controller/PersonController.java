package one.digitalinnovatoin.personapi.controller;

import one.digitalinnovatoin.personapi.dto.request.PersonDTO;
import one.digitalinnovatoin.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovatoin.personapi.exception.PersonNotFoundException;
import one.digitalinnovatoin.personapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/people")
public class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService){
        this.personService = personService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPerson(@RequestBody @Valid PersonDTO personDTO){
        return personService.CreatePerson(personDTO);
    }

    @GetMapping
    public List<PersonDTO> listAll() {
        return personService.listAll();
    }

    @GetMapping("/{id}")
    public PersonDTO listById(@PathVariable Long id) throws PersonNotFoundException {
        return personService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws PersonNotFoundException{
        personService.deleteId(id);
    }

    @PutMapping("/{id}")
    public MessageResponseDTO updateById(@PathVariable Long id, @RequestBody @Valid PersonDTO personDTO) throws PersonNotFoundException{
        return personService.updateById(id, personDTO);
    }
}
