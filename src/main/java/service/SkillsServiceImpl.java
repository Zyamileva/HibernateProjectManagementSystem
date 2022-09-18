package service;

import model.dto.DevelopersDto;
import model.dto.SkillsDto;
import repository.SkillsRepository;

import java.util.List;
import java.util.Optional;

public class SkillsServiceImpl implements SkillsService {
    SkillsRepository skillsRepository;

    public SkillsServiceImpl(SkillsRepository skillsRepository) {
        this.skillsRepository = skillsRepository;
    }

    @Override
    public SkillsDto saveSkill(SkillsDto skillsDto) {
        return null;
    }

    @Override
    public Optional<SkillsDto> findById(int id) {
        return Optional.empty();
    }

    @Override
    public List<SkillsDto> findAll() {
        return null;
    }

    @Override
    public boolean existByIds(int a, int j) {
        return false;
    }

    @Override
    public void delete(DevelopersDto developers) {
    }

    public int findByNameLevel(String name, String level) {
        return skillsRepository.findByNameLevel(name, level);
    }
}
