package be.stesch.person.model.listener;

import be.stesch.person.model.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.database.annotations.Transactional;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.orm.jpa.JpaUnitils;
import org.unitils.orm.jpa.annotation.JpaEntityManagerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static be.stesch.person.model.MaritalStatus.SINGLE;
import static org.junit.Assert.assertNotNull;
import static org.unitils.database.util.TransactionMode.ROLLBACK;

/**
 * @author Steve Schols
 * @since 1/09/2015
 */
@RunWith(UnitilsJUnit4TestClassRunner.class)
@JpaEntityManagerFactory(persistenceUnit = "person-app-test", configFile = "/META-INF/persistence-test.xml")
@Transactional(ROLLBACK)
public class AuditEntityListenerTest {

    @PersistenceContext
    private EntityManager entityManager;

//    @Before
//    public void setUp() {
//        // This should NOT be necessary - and doesn't work anyway
//        JpaUnitils.injectEntityManagerInto(this);
//    }

    @Test
    public void testSetAuditDataOnPersist() throws Exception {
        Person person = new Person("Test", "Person", SINGLE);

        entityManager.persist(person);

        assertNotNull(person.getCreationDate());
    }

    @Test
    @DataSet("datasets/PersonDataSet.xml")
    // TODO: Move PersonDataSet.xml to person-app-test module
    // If I do that while using unitils 3.3, I get an "URI is not hierarchical" exception.
    // This should be fixed in unitils 3.4.2, but then other persistence-related issues occur.
    public void testSetAuditDataOnUpdate() throws Exception {
        Person person = entityManager.find(Person.class, 1L);
        person.setFirstName("John");
        person.setLastName("Doe");

        person = entityManager.merge(person);
        // The flush might or might not be necessary, depending on the used implementation as per JPA-specification;
        //
        // The PreUpdate and PostUpdate callbacks occur before and after the database update operations to
        // entity data respectively. These database operations may occur at the time the entity state is updated or
        // they may occur at the time state is flushed to the database (which may be at the end of the transaction).
        JpaUnitils.flushDatabaseUpdates();

        assertNotNull(person.getLastUpdateDate());
    }

}
