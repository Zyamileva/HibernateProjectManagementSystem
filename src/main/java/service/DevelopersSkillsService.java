package service;

import model.dto.DevelopersSkillsDto;

import java.util.List;
import java.util.Optional;

public interface DevelopersSkillsService {

    DevelopersSkillsDto saveDevelopersSkiils(DevelopersSkillsDto developersSkillsDto);

    Optional<DevelopersSkillsDto> findById(int developerId, int skillId);

    List<DevelopersSkillsDto> findAll();

    boolean existById(int developerId, int skillId);

    void delete(DevelopersSkillsDto developersSkillsDto);
}
