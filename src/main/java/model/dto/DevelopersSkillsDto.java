package model.dto;

import model.PersistentEntity;

public class DevelopersSkillsDto extends PersistentEntity {
    int developerId;
    int skillId;

    public DevelopersSkillsDto(int developerId, int skillId) {
        this.developerId = developerId;
        this.skillId = skillId;
    }

    public DevelopersSkillsDto() {

    }

    public int getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(int developerId) {
        this.developerId = developerId;
    }

    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

}
