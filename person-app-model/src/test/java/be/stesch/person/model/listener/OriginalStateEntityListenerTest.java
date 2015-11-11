package be.stesch.person.model.listener;

import be.stesch.person.model.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.database.annotations.Transactional;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.orm.jpa.annotation.JpaEntityManagerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static be.stesch.person.model.MaritalStatus.MARRIED;
import static be.stesch.person.model.MaritalStatus.SINGLE;
import static org.junit.Assert.assertEquals;
import static org.unitils.database.util.TransactionMode.ROLLBACK;

/**
 * @author Steve Schols
 * @since 3/09/2015
 */
@RunWith(UnitilsJUnit4TestClassRunner.class)
@JpaEntityManagerFactory(persistenceUnit = "person-app-test", configFile = "/META-INF/persistence-test.xml")
@Transactional(ROLLBACK)
public class OriginalStateEntityListenerTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    @DataSet("/datasets/PersonDataSet.xml")
    public void testPersonOriginalMaritalStatus() throws Exception {
        Person person = entityManager.find(Person.class, 1L);
        assertEquals(SINGLE, person.getMaritalStatus());
        assertEquals(SINGLE, person.getOriginalMaritalStatus());

        person.setMaritalStatus(MARRIED);
        assertEquals(MARRIED, person.getMaritalStatus());
        assertEquals(SINGLE, person.getOriginalMaritalStatus());
    }

}