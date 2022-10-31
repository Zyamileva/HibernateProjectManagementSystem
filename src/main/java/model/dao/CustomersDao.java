package model.dao;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Table(name = "customers")
@Entity
public class CustomersDao {

    private int id;

    private String name;

    private String contactPerson;

    private String phoneNumber;

    private Set<ProjectsDao> projects=new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "contact_person")
    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    @Column(name = "phone")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @OneToMany(mappedBy = "customers")
    public Set<ProjectsDao> getProjects() {
        return projects;
    }

    public void setProjects(Set<ProjectsDao> projects) {
        this.projects = projects;
    }

    public CustomersDao(int id, String name, String contactPerson, String phoneNumber, Set<ProjectsDao> projects) {
        this.id = id;
        this.name = name;
        this.contactPerson = contactPerson;
        this.phoneNumber = phoneNumber;
        this.projects = projects;
    }

    public CustomersDao() {
    }

    public CustomersDao(int id, String name, String contactPerson, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.contactPerson = contactPerson;
        this.phoneNumber = phoneNumber;
    }

    public CustomersDao(String name, String contactPerson, String phoneNumber) {
        this.name = name;
        this.contactPerson = contactPerson;
        this.phoneNumber = phoneNumber;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        CustomersDao that = (CustomersDao) o;
//        return id == that.id && name.equals(that.name) && contactPerson.equals(that.contactPerson) && phoneNumber.equals(that.phoneNumber) && Objects.equals(projects, that.projects);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, name, contactPerson, phoneNumber);
//    }

    @Override
    public String toString() {
        return "CustomersDao{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", contactPerson='" + contactPerson + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", projects=" + projects +
                '}';
    }
}
