package service.converter;

import model.dao.SkillsDao;
import model.dto.SkillsDto;

public class SkillsConverter implements Converter<SkillsDto, SkillsDao> {
    @Override
    public SkillsDto from(SkillsDao entity) {
        SkillsDto skillsDto = new SkillsDto();
        skillsDto.setId(entity.getId());
        skillsDto.setName(entity.getName());
        skillsDto.setLevel(entity.getLevel());
        return skillsDto;
    }

    @Override
    public SkillsDao to(SkillsDto entity) {
        SkillsDao skillsDao = new SkillsDao();
        skillsDao.setId(entity.getId());
        skillsDao.setName(entity.getName());
        skillsDao.setLevel(entity.getLevel());
        return skillsDao;
    }
}
