package be.stesch.person.web.servlet;

import be.stesch.person.model.Person;
import be.stesch.person.service.PersonService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static be.stesch.person.model.MaritalStatus.MARRIED;
import static be.stesch.person.model.MaritalStatus.SINGLE;

/**
 * @author Steve Schols
 * @since 3/09/2015
 */
@WebServlet
public class PersonAppServlet extends HttpServlet {

    @EJB
    private PersonService personService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Person person = new Person("Test", "Person", SINGLE);
        personService.createPerson(person);

        Person personToUpdate = personService.findPerson(person.getId());
        person.setFirstName("John");
        person.setLastName("Doe");
        person.setMaritalStatus(MARRIED);
        personService.updatePerson(personToUpdate);

        resp.getWriter().print("Check the logs for the marital status change notification!");
    }

}
