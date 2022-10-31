package model.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Data
public class ProjectsDto {
    private int id;
    private String name;
    private LocalDateTime datePosted;
    private String task_difficulty;
    private int cost;
    private CompaniesDto companies;
    private CustomersDto customers;
    private Set<DevelopersDto> developers;

    public ProjectsDto(int id, String name, LocalDateTime datePosted, String task_difficulty, int cost,
                       CompaniesDto companies, CustomersDto customers) {
        this.id = id;
        this.name = name;
        this.datePosted = datePosted;
        this.task_difficulty = task_difficulty;
        this.cost = cost;
        this.companies = companies;
        this.customers = customers;
    }

    public ProjectsDto(String name, LocalDateTime datePosted, String task_difficulty, int cost, CompaniesDto companies,
                       CustomersDto customers) {
        this.name = name;
        this.datePosted = datePosted;
        this.task_difficulty = task_difficulty;
        this.cost = cost;
        this.companies = companies;
        this.customers = customers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectsDto that = (ProjectsDto) o;
        return id == that.id && cost == that.cost && name.equals(that.name) && datePosted.equals(that.datePosted) && task_difficulty.equals(that.task_difficulty) && companies.equals(that.companies) && customers.equals(that.customers) && Objects.equals(developers, that.developers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, datePosted, task_difficulty, cost, companies, customers, developers);
    }

    public ProjectsDto() {
    }
}
