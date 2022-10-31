package model.dao;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Table(name = "developers")
@Entity
public class DevelopersDao {

    private int id;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private int salary;

    private Set<ProjectsDao> projects = new HashSet<>();

    private Set<SkillsDao> skills = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "phone_number")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Column(name = "salary")
    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "developers_projects",
            joinColumns = {@JoinColumn(name = "developer_id")},
            inverseJoinColumns = {@JoinColumn(name = "project_id")})
    public Set<ProjectsDao> getProjects() {
        return projects;
    }

    public void setProjects(Set<ProjectsDao> projects) {
        this.projects = projects;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "developers_skills",
            joinColumns = {@JoinColumn(name = "developer_id")},
            inverseJoinColumns = {@JoinColumn(name = "skill_id")})
    public Set<SkillsDao> getSkills() {
        return skills;
    }

    public void setSkills(Set<SkillsDao> skills) {
        this.skills = skills;
    }

    public DevelopersDao(int id, String firstName, String lastName, String email, String phoneNumber, int salary) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
    }

    public DevelopersDao(String firstName, String lastName, String email, String phoneNumber, int salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
    }

    public DevelopersDao() {
    }

    public DevelopersDao(int id, String firstName, String lastName, String email, String phoneNumber,
                         int salary, Set<ProjectsDao> projects, Set<SkillsDao> skills) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.projects = projects;
        this.skills = skills;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        DevelopersDao that = (DevelopersDao) o;
//        return id == that.id && salary == that.salary && firstName.equals(that.firstName) && lastName.equals(that.lastName) && email.equals(that.email) && phoneNumber.equals(that.phoneNumber) && Objects.equals(projects, that.projects) && Objects.equals(skills, that.skills);
//    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(id, firstName, lastName, email, phoneNumber, salary, projects, skills);
//    }

    @Override
    public String toString() {
        return "DevelopersDao{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", salary=" + salary +
                ", projects=" + projects +
                ", skills=" + skills +
                '}';
    }
}