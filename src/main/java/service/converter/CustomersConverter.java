package service.converter;

import model.dao.CustomersDao;
import model.dto.CustomersDto;

public class CustomersConverter implements Converter<CustomersDto, CustomersDao> {
        @Override
        public CustomersDto from(CustomersDao entity) {
            CustomersDto customersDto = new CustomersDto();
            customersDto.setId(entity.getId());
            customersDto.setName(entity.getName());
            customersDto.setContactPerson(entity.getContactPerson());
            customersDto.setPhoneNumber(entity.getPhoneNumber());
            return customersDto;
        }

        @Override
        public CustomersDao to(CustomersDto entity) {
            CustomersDao customersDao = new CustomersDao();
            customersDao.setId(entity.getId());
            customersDao.setName(entity.getName());
            customersDao.setContactPerson(entity.getContactPerson());
            customersDao.setPhoneNumber(entity.getPhoneNumber());
            return customersDao;
        }
    }
