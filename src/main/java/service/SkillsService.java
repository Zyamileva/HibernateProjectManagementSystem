package service;

import model.dto.CustomersDto;
import model.dto.DevelopersDto;
import model.dto.SkillsDto;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface SkillsService {

    SkillsDto saveSkill(SkillsDto skillsDto);

    Set<SkillsDto> findByName(String name);
    Optional<SkillsDto> findById(int id);

    Set<SkillsDto> findAll();

    void delete(SkillsDto skills);

    public int findByNameLevel(String name, String level);

    void update(SkillsDto skills);
}