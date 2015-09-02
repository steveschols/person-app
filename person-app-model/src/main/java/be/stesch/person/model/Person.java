package be.stesch.person.model;

import be.stesch.person.common.observer.Observable;
import be.stesch.person.common.observer.Observer;
import be.stesch.person.model.listener.AuditEntityListener;
import be.stesch.person.model.listener.ObservableEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.persistence.EnumType.STRING;

/**
 * @author Steve Schols
 * @since 27/08/2015
 */
@Entity
@EntityListeners({AuditEntityListener.class, ObservableEntityListener.class})
public class Person implements Auditable, Observable, Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String firstName;

    private String lastName;

    @Enumerated(STRING)
    private MaritalStatus maritalStatus;

    private Date creationDate;

    private Date lastUpdateDate;

    @Transient
    private List<Observer> observers = new ArrayList<>();

    public Person() {
    }

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    public void unregisterObserver(Observer observer) {
        observers.remove(observer);
    }

    public List<Observer> getObservers() {
        return observers;
    }

    public void notifyObservers() {
        for (Observer observer : getObservers()) {
            observer.notifyObserver(this);
        }
    }

}
