package service.converter;

import model.dao.CompaniesDao;
import model.dto.CompaniesDto;

public class CompaniesConverter implements Converter<CompaniesDto, CompaniesDao> {
    @Override
    public CompaniesDto from(CompaniesDao entity) {
            CompaniesDto companiesDto = new CompaniesDto();
            companiesDto.setId(entity.getId());
            companiesDto.setName(entity.getName());
            companiesDto.setStaff(entity.getStaff());
            return companiesDto;
    }

    @Override
    public CompaniesDao to(CompaniesDto entity) {
        CompaniesDao companiesDao = new CompaniesDao();
        companiesDao.setId(entity.getId());
        companiesDao.setName(entity.getName());
        companiesDao.setStaff(entity.getStaff());
        return companiesDao;
    }
}
