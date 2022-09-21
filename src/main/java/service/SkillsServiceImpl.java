package service;

import model.dao.ProjectsDao;
import model.dao.SkillsDao;
import model.dto.DevelopersDto;
import model.dto.ProjectsDto;
import model.dto.SkillsDto;
import repository.SkillsRepository;
import service.converter.Converter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SkillsServiceImpl implements SkillsService {
    SkillsRepository skillsRepository;
    Converter<SkillsDto, SkillsDao> converterSkills;

    public SkillsServiceImpl(SkillsRepository skillsRepository, Converter<SkillsDto, SkillsDao> converterSkills) {
        this.skillsRepository = skillsRepository;
        this.converterSkills = converterSkills;
    }

    @Override
    public SkillsDto saveSkill(SkillsDto skillsDto) {
        return converterSkills.from(skillsRepository.save(converterSkills.to(skillsDto)));
    }

    @Override
    public Optional<SkillsDto> findById(int id) {
        return skillsRepository.findById(id).map(element -> converterSkills.from(element));
    }

    @Override
    public List<SkillsDto> findAll() {
        return skillsRepository.findAll().stream().map(element -> converterSkills.from(element))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(SkillsDto skills) {
        skillsRepository.delete(converterSkills.to(skills));
    }

    public int findByNameLevel(String name, String level) {
        return skillsRepository.findByNameLevel(name, level);
    }
}
