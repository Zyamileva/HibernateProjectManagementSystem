package model.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
public class DevelopersDto {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private int salary;
    private Set<ProjectsDto> projects=new HashSet<>();
    private Set<SkillsDto> skills=new HashSet<>();

    public DevelopersDto() {
    }

    public DevelopersDto(String firstName, String lastName, String email, String phoneNumber, int salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DevelopersDto that = (DevelopersDto) o;
        return id == that.id && salary == that.salary && firstName.equals(that.firstName) && lastName.equals(that.lastName) && email.equals(that.email) && phoneNumber.equals(that.phoneNumber) && Objects.equals(projects, that.projects) && Objects.equals(skills, that.skills);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, phoneNumber, salary, projects, skills);
    }
}