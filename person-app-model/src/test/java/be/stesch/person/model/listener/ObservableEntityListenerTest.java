package be.stesch.person.model.listener;

import be.stesch.person.common.observer.Observer;
import be.stesch.person.model.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.database.annotations.Transactional;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.orm.jpa.annotation.JpaEntityManagerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.Assert.assertEquals;
import static org.unitils.database.util.TransactionMode.ROLLBACK;

/**
 * @author Steve Schols
 * @since 1/09/2015
 */
@RunWith(UnitilsJUnit4TestClassRunner.class)
@JpaEntityManagerFactory(persistenceUnit = "person-app-test", configFile = "/META-INF/persistence-test.xml")
@Transactional(ROLLBACK)
public class ObservableEntityListenerTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    @DataSet("datasets/PersonDataSet.xml")
    public void testAddObservers() throws Exception {
        Person person = entityManager.find(Person.class, 1L);

        assertEquals(1, person.getObservers().size());
        Observer observer = person.getObservers().get(0);
        Class maritalStatusChangeObserverClass = ObservableEntityListener.getMaritalStatusChangeObserverClass();
        assertEquals(maritalStatusChangeObserverClass, observer.getClass());
    }

}
