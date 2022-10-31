package model.dto;

import lombok.Data;

import java.util.Objects;
import java.util.Set;

@Data
public class CustomersDto {
    private int id;
    private String name;
    private String contactPerson;
    private String phoneNumber;
    private Set<ProjectsDto> projects;

    public CustomersDto() {
    }

    public CustomersDto(int id, String name, String contactPerson, String phoneNumber, Set<ProjectsDto> projects) {
        this.id = id;
        this.name = name;
        this.contactPerson = contactPerson;
        this.phoneNumber = phoneNumber;
        this.projects = projects;
    }

    public CustomersDto(String name, String contactPerson, String phoneNumber) {
        this.name = name;
        this.contactPerson = contactPerson;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomersDto that = (CustomersDto) o;
        return id == that.id && name.equals(that.name) && contactPerson.equals(that.contactPerson) && phoneNumber.equals(that.phoneNumber) && Objects.equals(projects, that.projects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, contactPerson, phoneNumber, projects);
    }
}