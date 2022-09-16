package service;

import model.dto.DevelopersDto;
import model.dto.SkillsDto;

import java.util.List;
import java.util.Optional;

public interface SkillsService {

    SkillsDto saveSkill(SkillsDto skillsDto);

    Optional<SkillsDto> findById(int id);

    List<SkillsDto> findAll();

    boolean existByIds(int a, int j);

    void delete(DevelopersDto developers);
}