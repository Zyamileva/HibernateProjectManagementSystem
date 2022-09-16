package model.dao;

import model.PersistentEntity;

import java.util.Objects;

public class SkillsDao extends PersistentEntity {
    private String name;
    private String level;

    public SkillsDao() {
    }

    public SkillsDao(String name, String level) {
        this.name = name;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SkillsDao skillsDao)) return false;
        if (!super.equals(o)) return false;
        return name.equals(skillsDao.name) && level.equals(skillsDao.level);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, level);
    }

    @Override
    public String toString() {
        return "Skills{" +
                "name='" + name + '\'' +
                ", level='" + level + '\'' +
                ", id=" + id +
                '}';
    }
}
