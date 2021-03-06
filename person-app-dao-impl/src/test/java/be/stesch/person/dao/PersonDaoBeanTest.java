package be.stesch.person.dao;

import be.stesch.person.model.Person;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.database.annotations.Transactional;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.orm.jpa.JpaUnitils;
import org.unitils.orm.jpa.annotation.JpaEntityManagerFactory;

import java.time.LocalDateTime;
import java.time.ZoneId;

import static be.stesch.person.model.MaritalStatus.MARRIED;
import static be.stesch.person.model.MaritalStatus.SINGLE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.unitils.database.util.TransactionMode.ROLLBACK;

/**
 * @author Steve Schols
 * @since 28/08/2015
 */
@RunWith(UnitilsJUnit4TestClassRunner.class)
@JpaEntityManagerFactory(persistenceUnit = "person-app-test", configFile = "/META-INF/persistence-test.xml")
@Transactional(ROLLBACK)
public class PersonDaoBeanTest {

    private PersonDao personDao;

    @Before
    public void setUp() {
        personDao = new PersonDaoBean();
        JpaUnitils.injectEntityManagerInto(personDao);
    }

    @Test
    public void testPersistPerson() throws Exception {
        Person person = new Person("Test", "Person", SINGLE);
        personDao.persist(person);

        assertNotNull(person.getId());
    }

    @Test
    @DataSet("datasets/PersonDataSet.xml")
    public void testUpdatePerson() {
        Person person = personDao.find(1L);
        LocalDateTime expectedCreationDate = LocalDateTime.of(2015, 8, 27, 0, 0);
        LocalDateTime actualCreationDate = LocalDateTime.ofInstant(person.getCreationDate().toInstant(),
                ZoneId.systemDefault());
        assertEquals("Test", person.getFirstName());
        assertEquals("Person", person.getLastName());
        assertEquals(SINGLE, person.getMaritalStatus());
        assertEquals(expectedCreationDate, actualCreationDate);

        person.setFirstName("John");
        person.setLastName("Doe");
        person.setMaritalStatus(MARRIED);
        person = personDao.merge(person);

        assertEquals("John", person.getFirstName());
        assertEquals("Doe", person.getLastName());
        assertEquals(MARRIED, person.getMaritalStatus());
        assertEquals(expectedCreationDate, actualCreationDate);
    }

}
