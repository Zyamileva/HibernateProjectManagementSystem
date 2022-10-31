package model.dao;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Table(name = "projects")
@Entity
public class ProjectsDao {

    private int id;

    private String name;

    private LocalDateTime datePosted;

    private String task_difficulty;

    private int cost;

    private CompaniesDao companies;

    private CustomersDao customers;

    private Set<DevelopersDao> developers=new HashSet<>();

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

    @Column(name = "date_create")
    public LocalDateTime getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(LocalDateTime datePosted) {
        this.datePosted = datePosted;
    }

    @Column(name = "task_difficulty")
    public String getTask_difficulty() {
        return task_difficulty;
    }

    public void setTask_difficulty(String task_difficulty) {
        this.task_difficulty = task_difficulty;
    }

    @Column(name = "cost")
    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @ManyToOne
    @JoinColumn(name = "company_id")
    public CompaniesDao getCompanies() {
        return companies;
    }

    public void setCompanies(CompaniesDao companies) {
        this.companies = companies;
    }

    @ManyToOne
    @JoinColumn(name = "customer_id")
    public CustomersDao getCustomers() {
        return customers;
    }

    public void setCustomers(CustomersDao customers) {
        this.customers = customers;
    }

    @ManyToMany(mappedBy = "projects")
    public Set<DevelopersDao> getDevelopers() {
        return developers;
    }

    public void setDevelopers(Set<DevelopersDao> developers) {
        this.developers = developers;
    }

    public ProjectsDao(int id, String name, LocalDateTime datePosted, String task_difficulty, int cost,
                       CompaniesDao companies, CustomersDao customers) {
        this.id = id;
        this.name = name;
        this.datePosted = datePosted;
        this.task_difficulty = task_difficulty;
        this.cost = cost;
        this.companies = companies;
        this.customers = customers;
    }

    public ProjectsDao(int id, String name, LocalDateTime datePosted, String task_difficulty, int cost,
                       CompaniesDao companies, CustomersDao customers, Set<DevelopersDao> developers) {
        this.id = id;
        this.name = name;
        this.datePosted = datePosted;
        this.task_difficulty = task_difficulty;
        this.cost = cost;
        this.companies = companies;
        this.customers = customers;
        this.developers = developers;
    }

    public ProjectsDao(String name, LocalDateTime datePosted, String task_difficulty, int cost) {
        this.name = name;
        this.datePosted = datePosted;
        this.task_difficulty = task_difficulty;
        this.cost = cost;
    }

    public ProjectsDao(int id, String name, LocalDateTime datePosted, String task_difficulty, int cost) {
        this.id = id;
        this.name = name;
        this.datePosted = datePosted;
        this.task_difficulty = task_difficulty;
        this.cost = cost;
    }

    public ProjectsDao() {
    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        ProjectsDao that = (ProjectsDao) o;
//        return id == that.id && cost == that.cost && name.equals(that.name) && Objects.equals(datePosted, that.datePosted) && task_difficulty.equals(that.task_difficulty) && companies.equals(that.companies) && customers.equals(that.customers);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, name, datePosted, task_difficulty, cost, companies, customers);
//    }

    @Override
    public String toString() {
        return "ProjectsDao{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", datePosted=" + datePosted +
                ", task_difficulty='" + task_difficulty + '\'' +
                ", cost=" + cost +
                '}';
    }
}