package model.dao;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Table(name = "companies")
@Entity
public class CompaniesDao {
    private int id;
    private String name;
    private int staff;

    private Set<ProjectsDao> projects = new HashSet<>();

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

    @Column(name = "staff")
    public int getStaff() {
        return staff;
    }

    public void setStaff(int staff) {
        this.staff = staff;
    }

    @OneToMany(mappedBy = "companies")
    public Set<ProjectsDao> getProjects() {
        return projects;
    }

    public void setProjects(Set<ProjectsDao> projects) {
        this.projects = projects;
    }


    public CompaniesDao() {
    }

    public CompaniesDao(String name, int staff) {
        this.name = name;
        this.staff = staff;
    }

    public CompaniesDao(int id, String name, int staff) {
        this.id = id;
        this.name = name;
        this.staff = staff;
    }

    public CompaniesDao(int id, String name, int staff, Set<ProjectsDao> projects) {
        this.id = id;
        this.name = name;
        this.staff = staff;
        this.projects = projects;
    }

    @Override
    public String toString() {
        return "CompaniesDao{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", staff=" + staff +
                ", projects=" + projects +
                '}';
    }
}
