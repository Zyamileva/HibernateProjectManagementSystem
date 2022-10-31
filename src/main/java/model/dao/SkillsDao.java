package model.dao;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Table(name = "skills")
@Entity
public class SkillsDao {

    private int id;

    private String name;

    private String level;

    private Set<DevelopersDao> developers=new HashSet<>();

    public SkillsDao() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "level")
    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }


    @ManyToMany(mappedBy = "skills")
    public Set<DevelopersDao> getDevelopers() {
        return developers;
    }

    public void setDevelopers(Set<DevelopersDao> developers) {
        this.developers = developers;
    }

    public SkillsDao(String name, String level) {
        this.name = name;
        this.level = level;
    }

    public SkillsDao(int id, String name, String level) {
        this.id = id;
        this.name = name;
        this.level = level;
    }

    public SkillsDao(int id, String name, String level, Set<DevelopersDao> developers) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.developers = developers;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        SkillsDao skillsDao = (SkillsDao) o;
//        return id == skillsDao.id && name.equals(skillsDao.name) && level.equals(skillsDao.level) && Objects.equals(developers, skillsDao.developers);
//    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(id, name, level, developers);
//    }

    @Override
    public String toString() {
        return "SkillsDao{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", level='" + level + '\'' +
                ", developers=" + developers +
                '}';
    }
}
