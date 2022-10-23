package service;

import model.dto.SkillsDto;

import java.util.Optional;
import java.util.Set;

public interface SkillsService {

    SkillsDto saveSkill(SkillsDto skillsDto);

    Optional<SkillsDto> findByName(String name);
    Set<SkillsDto> findByNameSet(String name);
    Optional<SkillsDto> findById(int id);

    Set<SkillsDto> findAll();

    void delete(SkillsDto skills);

    public int findByNameLevel(String name, String level);

    void update(SkillsDto skills);
}