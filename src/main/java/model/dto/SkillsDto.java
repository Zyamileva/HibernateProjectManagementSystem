package model.dto;

import lombok.Data;

import java.util.Objects;

@Data
public class SkillsDto {
    private int id;
    private String name;
    private String level;

    public SkillsDto() {
    }

    public SkillsDto(String name, String level) {
        this.name = name;
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SkillsDto skillsDto = (SkillsDto) o;
        return id == skillsDto.id && name.equals(skillsDto.name) && level.equals(skillsDto.level);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, level);
    }
}