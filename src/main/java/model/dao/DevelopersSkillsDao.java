package model.dao;

import model.PersistentEntity;

public class DevelopersSkillsDao extends PersistentEntity {
    int developerId;
    int skillId;

    public DevelopersSkillsDao(int developerId, int skillId) {
        this.developerId = developerId;
        this.skillId = skillId;
    }

    public DevelopersSkillsDao() {
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
