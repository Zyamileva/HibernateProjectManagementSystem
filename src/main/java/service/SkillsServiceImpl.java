package service;

import model.dao.SkillsDao;
import model.dto.SkillsDto;
import repository.SkillsRepository;
import service.converter.Converter;

import java.util.Optional;
import java.util.Set;
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
    public Optional<SkillsDto> findByName(String name) {
        return skillsRepository.findByName(name).map(element -> converterSkills.from(element));}

    @Override
    public Set<SkillsDto> findByNameSet(String name) {
        return skillsRepository.findByNameSet(name).stream()
                .map(element -> converterSkills.from(element)).collect(Collectors.toSet());}

    @Override
    public Optional<SkillsDto> findById(int id) {
        return skillsRepository.findById(id).map(element -> converterSkills.from(element));
    }

    @Override
    public Set<SkillsDto> findAll() {
        return skillsRepository.findAll().stream().map(element -> converterSkills.from(element))
                .collect(Collectors.toSet());
    }

    @Override
    public void delete(SkillsDto skills) {
        skillsRepository.delete(converterSkills.to(skills));
    }

    @Override
    public int findByNameLevel(String name, String level) {
        return skillsRepository.findByNameLevel(name, level);
    }

    @Override
    public void update(SkillsDto skills) {
        skillsRepository.update(converterSkills.to(skills));
    }
}
