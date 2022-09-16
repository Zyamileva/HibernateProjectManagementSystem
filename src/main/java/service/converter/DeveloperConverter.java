package service.converter;

import model.dao.DevelopersDao;
import model.dto.DevelopersDto;

public class DeveloperConverter implements Converter<DevelopersDto, DevelopersDao> {
    @Override
    public DevelopersDto from(DevelopersDao entity) {
        DevelopersDto developersDto = new DevelopersDto();
        developersDto.setId(entity.getId());
        developersDto.setFirstName(entity.getFirstName());
        developersDto.setLastName(entity.getLastName());
        developersDto.setEmail(entity.getEmail());
        developersDto.setPhoneNumber(entity.getPhoneNumber());
        developersDto.setSalary(entity.getSalary());
        return developersDto;
    }

    @Override
    public DevelopersDao to(DevelopersDto entity) {
        DevelopersDao developersDao = new DevelopersDao();
        developersDao.setId((entity.getId()));
        developersDao.setFirstName(entity.getFirstName());
        developersDao.setLastName(entity.getLastName());
        developersDao.setEmail(entity.getEmail());
        developersDao.setPhoneNumber(entity.getPhoneNumber());
        developersDao.setSalary(entity.getSalary());
        return developersDao;
    }


}
