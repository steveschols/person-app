package be.stesch.person.common;

import org.junit.Test;

import static be.stesch.person.common.PersonAppProperties.MARITAL_STATUS_CHANGE_OBSERVER_CLASS;
import static org.junit.Assert.assertNotNull;

/**
 * @author Steve Schols
 * @since 1/09/2015
 */
public class PersonAppPropertiesTest {

    @Test
    public void testPersonAppProperties() throws Exception {
        PersonAppProperties personAppProperties = PersonAppProperties.getInstance();

        assertNotNull(personAppProperties.getProperty(MARITAL_STATUS_CHANGE_OBSERVER_CLASS));
    }

}