package be.stesch.person.service;

import be.stesch.person.dao.PersonDao;
import be.stesch.person.model.Person;

import javax.ejb.Stateless;

/**
 * @author Steve Schols
 * @since 3/09/2015
 */
@Stateless
public class PersonServiceBean implements PersonService {

    private PersonDao personDao;

    @Override
    public void createPerson(Person person) {
        personDao.persist(person);
    }

    @Override
    public Person findPerson(Long id) {
        return personDao.find(id);
    }

    @Override
    public Person updatePerson(Person person) {
        return personDao.merge(person);
    }

}
