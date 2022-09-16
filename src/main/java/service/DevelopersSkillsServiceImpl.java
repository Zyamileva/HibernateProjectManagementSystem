package service;

import model.dao.DevelopersDao;
import model.dao.DevelopersSkillsDao;
import model.dto.DevelopersDto;
import model.dto.DevelopersSkillsDto;
import repository.DevelopersSkillsRepository;
import service.converter.Converter;

import java.util.List;
import java.util.Optional;

public class DevelopersSkillsServiceImpl implements DevelopersSkillsService {
    DevelopersSkillsRepository developersSkillsRepository;
    Converter<DevelopersSkillsDto, DevelopersSkillsDao> converter;

    public DevelopersSkillsServiceImpl(DevelopersSkillsRepository developersSkillsRepository, Converter<DevelopersSkillsDto, DevelopersSkillsDao> converter) {
        this.developersSkillsRepository = developersSkillsRepository;
        this.converter = converter;
    }

    @Override
    public DevelopersSkillsDto saveDevelopersSkiils(DevelopersSkillsDto developersSkillsDto) {
        return converter.from(developersSkillsRepository.save(converter.to(developersSkillsDto)));
    }

    @Override
    public Optional<DevelopersSkillsDto> findById(int developerId, int skillId) {
        return Optional.empty();
    }

    @Override
    public List<DevelopersSkillsDto> findAll() {
        return null;
    }

    @Override
    public boolean existById(int developerId, int skillId) {
        return true;
    }

    @Override
    public void delete(DevelopersSkillsDto developersSkillsDto) {

    }
}
