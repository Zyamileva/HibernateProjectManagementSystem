package model.dao;

import model.PersistentEntity;

import java.util.Objects;

public class CompaniesDao extends PersistentEntity {
    private String name;
    private int staff;

    public CompaniesDao() {
    }

    public CompaniesDao(String name, int staff) {
        this.name = name;
        this.staff = staff;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStaff() {
        return staff;
    }

    public void setStaff(int staff) {
        this.staff = staff;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompaniesDao companiesDao)) return false;
        if (!super.equals(o)) return false;
        return staff == companiesDao.staff && name.equals(companiesDao.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, staff);
    }

    @Override
    public String toString() {
        return "Companies{" +
                "name='" + name + '\'' +
                ", staff=" + staff +
                ", id=" + id +
                '}';
    }
}
