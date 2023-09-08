package com.neutrinosys.peopledbweb.data;

import com.neutrinosys.peopledbweb.biz.model.Person;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Component
public class PersonDataLoader implements ApplicationRunner {
    private final PersonRepository personRepository;

    public PersonDataLoader(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Person> people = null;
        if (personRepository.count() == 0) {
            people = List.of(
                    new Person(null, "Jake", "Snake", LocalDate.of(1950, 1, 1), new BigDecimal(50000)),
                    new Person(null, "Sarah", "Smith", LocalDate.of(1960, 2, 1), new BigDecimal(60000)),
                    new Person(null, "Johny", "Jackson", LocalDate.of(1970, 3, 1), new BigDecimal(70000)),
                    new Person(null, "Bobby", "Kim", LocalDate.of(1980, 4, 1), new BigDecimal(80000))
            );
            personRepository.saveAll(people);
        }

    }
}
