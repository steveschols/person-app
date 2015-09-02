package be.stesch.person.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Steve Schols
 * @since 1/09/2015
 */
public class PersonAppProperties {

    public static final String MARITAL_STATUS_CHANGE_OBSERVER_CLASS = "marital.status.change.observer.class";

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonAppProperties.class);

    private static final PersonAppProperties INSTANCE = new PersonAppProperties();

    private Properties personAppProperties;

    private PersonAppProperties() {
        try {
            InputStream propertiesInputStream = this.getClass().getResourceAsStream("/person-app.properties");

            personAppProperties = new Properties();
            personAppProperties.load(propertiesInputStream);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static PersonAppProperties getInstance() {
        return INSTANCE;
    }

    public String getProperty(String key) {
        return personAppProperties.getProperty(key);
    }

}
