package service.converter;

import model.dao.DevelopersSkillsDao;
import model.dto.DevelopersSkillsDto;

public class DevelopersSkillsConverter implements Converter<DevelopersSkillsDto, DevelopersSkillsDao> {
    @Override
    public DevelopersSkillsDto from(DevelopersSkillsDao entity) {
        DevelopersSkillsDto developersSkillsDto = new DevelopersSkillsDto();
        developersSkillsDto.setDeveloperId(entity.getDeveloperId());
        developersSkillsDto.setSkillId(entity.getSkillId());
        return developersSkillsDto;
    }

    @Override
    public DevelopersSkillsDao to(DevelopersSkillsDto entity) {
        DevelopersSkillsDao developersSkillsDao = new DevelopersSkillsDao();
        developersSkillsDao.setDeveloperId(entity.getDeveloperId());
        developersSkillsDao.setSkillId(entity.getSkillId());
        return developersSkillsDao;
    }
}
