package one.digitalinnovatoin.personapi.controller;

import one.digitalinnovatoin.personapi.dto.request.PersonDTO;
import one.digitalinnovatoin.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovatoin.personapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public MessageResponseDTO CreatePerson(@RequestBody @Valid PersonDTO personDTO){
        return personService.CreatePerson(personDTO);
    }
}
