package service;

import model.dao.DevelopersDao;
import model.dto.DevelopersDto;
import repository.DevelopersRepository;
import service.converter.Converter;

import java.util.Optional;
import java.util.Set;
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
    public Optional<DevelopersDto> findByName(String name) {
        return developersRepository.findByName(name).map(element -> converter.from(element));
    }

    @Override
    public Optional<DevelopersDto> findById(int id) {
        return developersRepository.findById(id).map(element -> converter.from(element));
    }

    @Override
    public Set<DevelopersDto> findAll() {
        return developersRepository.findAll().stream().map((element) -> converter.from(element))
                .collect(Collectors.toSet());
    }

    @Override
    public void delete(DevelopersDto developers) {
        developersRepository.delete(converter.to(developers));
    }

    @Override
    public void deleteDevelopersOfIdsSkill(int idSkill) {
        developersRepository.deleteDeveloperOfIdsSkill(idSkill);
    }

    @Override
    public void saveSkills(int idDeveloper, int idNameLevel) {
        developersRepository.saveSkills(idDeveloper, idNameLevel);
    }

    @Override
    public void update(DevelopersDto developer) {
        developersRepository.update(converter.to(developer));
    }

    public Set<Integer> findDevelopersOfProject(int idProject) {
        return developersRepository.findDevelopersOfProject(idProject);
    }

    @Override
    public Set<DevelopersDto> findByNameSet(String name) {
        return developersRepository.findByNameSet(name).stream()
                .map((element) -> converter.from(element))
                .collect(Collectors.toSet());
    }
}
