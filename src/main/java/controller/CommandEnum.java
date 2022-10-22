package controller;

public enum CommandEnum {

    COMPANY_TABLE_QUERIES("Company table queries"),
    CUSTOMER_TABLE_QUERIES("Customer table queries"),
    DEVELOPER_TABLE_QUERIES("Developer table queries"),
    PROJECT_TABLE_QUERIES("Project table queries"),
    SKILL_TABLE_QUERIES("Skill table queries"),
    ADD_COMPANY("Add company"),
    ADD_CUSTOMER("Add customer"),
    ADD_DEVELOPER("Add developer"),
    ADD_PROJECT("Add project"),
    ADD_SKILL("Add skill"),
    DELETE_COMPANY("Delete company"),
    DELETE_CUSTOMER("Delete customer"),
    DELETE_DEVELOPER("Delete developer"),
    DELETE_PROJECT("Delete project"),
    DELETE_SKILL("Delete skill"),

    SELECT_COMPANY("Select company"),
    SELECT_CUSTOMER("Select customer"),
    SELECT_DEVELOPER("Select developer"),
    SELECT_PROJECT("Select project"),
    SELECT_SKILL("Select skill"),
    UPDATE_COMPANY("Update company"),
    UPDATE_CUSTOMER("Update customer"),
    UPDATE_DEVELOPER("Update developer"),
    UPDATE_PROJECT("Update project"),
    UPDATE_SKILL("Update skill"),

    FIND_ALL_COMPANY("Find all company"),
    FIND_ALL_CUSTOMER("Find all customer"),
    FIND_ALL_DEVELOPER("Find all developer"),
    FIND_ALL_PROJECT("Find all project"),
    FIND_ALL_SKILL("Find all skill"),
    LIST_DEVELOPERS_OF_PROJECTS("List developers of projects"),
    LIST_DEVELOPERS_OF_SKILL_NAME("List developers of skill Name"),
    LIST_DEVELOPERS_OF_SKILL_LEVEL("List developers of skill Level"),
    LIST_OF_PROJECTS("List of projects"),
    SALARY_OF_PROJECTS("Salary of projects"),
    ADD_SKILL_DEVELOPER("Add developer`s skill"),
    DELETE_SKILL_DEVELOPER("Delete developer`s skill"),
    ADD_DEVELOPER_OF_PROJECT("Add developer of Project"),
    DELETE_DEVELOPER_OF_PROJECT("Delete developer of Project"),
    MAIN_COMMANDS("Show main commands"),
    EXIT("Exit");

    private final String value;

    CommandEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}