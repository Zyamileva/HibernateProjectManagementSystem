package service;

import model.dao.DevelopersDao;
import model.dto.DevelopersDto;
import repository.DevelopersRepository;
import service.converter.Converter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DeveloperServiceImpl implements DeveloperService {
    DevelopersRepository developersRepository;
    Converter<DevelopersDto, DevelopersDao> converter;

    public DeveloperServiceImpl(DevelopersRepository developersRepository, Converter<DevelopersDto, DevelopersDao> converter) {
        this.developersRepository = developersRepository;
        this.converter = converter;
    }

    @Override
    public DevelopersDto saveDeveloper(DevelopersDto developer) {
        return converter.from(developersRepository.save(converter.to(developer)));
    }

    @Override
    public Optional<DevelopersDto> findById(int id) {
        return developersRepository.findById(id).map(element -> converter.from(element));
    }

    @Override
    public List<DevelopersDto> findAll() {
        return developersRepository.findAll().stream().map((element) -> converter.from(element))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(DevelopersDto developers) {
        developersRepository.delete(converter.to(developers));
    }

    @Override
    public void deleteSkillsOfDeveloper(int idDeveloper) {
        developersRepository.deleteSkillsOfDeveloper(idDeveloper);
    }

    @Override
    public void deleteDevelopersOfIdsSkill(int idSkill) {
        developersRepository.deleteDeveloperOfIdsSkill(idSkill);
    }

    @Override
    public List<DevelopersDto> listOfSkillNameDevelopers(String skillName) {
        return developersRepository.listOfSkillNameDevelopers(skillName).stream().map((element) -> converter.from(element))
                .collect(Collectors.toList());
    }

    @Override
    public List<DevelopersDto> listOfSkillLevelDevelopers(String skillLevel) {
        return developersRepository.listOfSkillLevelDevelopers(skillLevel).stream().map((element) -> converter.from(element))
                .collect(Collectors.toList());
    }

    @Override
    public void saveSkills(int idDeveloper, int idNameLevel) {
        developersRepository.saveSkills(idDeveloper, idNameLevel);
    }

    @Override
    public void update(DevelopersDto developer) {
        developersRepository.update(converter.to(developer));
    }
}
