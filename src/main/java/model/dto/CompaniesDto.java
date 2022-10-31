package model.dto;

import lombok.Data;

import java.util.Objects;
import java.util.Set;

@Data
public class CompaniesDto {
    private int id;
    private String name;
    private int staff;
    private Set<ProjectsDto> projects;

    public CompaniesDto() {
    }

    public CompaniesDto(String name, int staff) {
        this.name = name;
        this.staff = staff;
    }

    public CompaniesDto(int id, String name, int staff, Set<ProjectsDto> projects) {
        this.id = id;
        this.name = name;
        this.staff = staff;
        this.projects = projects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompaniesDto that = (CompaniesDto) o;
        return id == that.id && staff == that.staff && name.equals(that.name) && Objects.equals(projects, that.projects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, staff, projects);
    }
}