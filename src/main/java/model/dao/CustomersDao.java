package model.dao;

import model.PersistentEntity;

import java.util.Objects;

public class CustomersDao extends PersistentEntity {
    private String name;
    private String contactPerson;
    private String phoneNumber;

    public CustomersDao() {
    }

    public CustomersDao(String name, String contactPerson, String phoneNumber) {
        this.name = name;
        this.contactPerson = contactPerson;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomersDao customersDao)) return false;
        if (!super.equals(o)) return false;
        return name.equals(customersDao.name) && contactPerson.equals(customersDao.contactPerson) && phoneNumber.equals(customersDao.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, contactPerson, phoneNumber);
    }

    @Override
    public String toString() {
        return "CustomersDao{" +
                "name='" + name + '\'' +
                ", contactPerson='" + contactPerson + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", id=" + id +
                '}';
    }
}
