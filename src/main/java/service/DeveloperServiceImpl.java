package service;

import model.dao.DevelopersDao;
import model.dto.DevelopersDto;
import repository.DevelopersRepository;
import repository.Repository;
import service.converter.Converter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DeveloperServiceImpl implements DeveloperService {
    Repository<DevelopersDao> developersRepository;
    Converter<DevelopersDto, DevelopersDao> converter;

    public DeveloperServiceImpl(Repository<DevelopersDao> developersRepository, Converter<DevelopersDto, DevelopersDao> converter) {
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
        return developersRepository.findAll().stream().map((dao) -> converter.from(dao)).collect(Collectors.toList());
    }

    @Override
    public void delete(DevelopersDto developers) {
        developersRepository.delete(converter.to(developers));

    }
}
