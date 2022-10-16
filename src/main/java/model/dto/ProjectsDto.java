package model.dto;

import model.PersistentEntity;

import java.time.LocalDateTime;
import java.util.Objects;

public class ProjectsDto extends PersistentEntity {
    private String name;
    private LocalDateTime datePosted;
    private String task_difficulty;
    private int customerId;
    private int companyId;
    private int cost;

    public ProjectsDto(String name, String task_difficulty, int customerId,
                       int companyId, int cost) {
        this.name = name;
        this.datePosted = datePosted;
        this.task_difficulty = task_difficulty;
        this.customerId = customerId;
        this.companyId = companyId;
        this.cost = cost;
    }

    public ProjectsDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(LocalDateTime datePosted) {
        this.datePosted = datePosted;
    }

    public String getTask_difficulty() {
        return task_difficulty;
    }

    public void setTask_difficulty(String task_difficulty) {
        this.task_difficulty = task_difficulty;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ProjectsDto that = (ProjectsDto) o;
        return customerId == that.customerId && companyId == that.companyId && cost == that.cost && name.equals(that.name) && datePosted.equals(that.datePosted) && task_difficulty.equals(that.task_difficulty);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, datePosted, task_difficulty, customerId, companyId, cost);
    }

    @Override
    public String toString() {
        return "Projects {" +
                "name='" + name + '\'' +
                ", datePosted=" + datePosted +
                ", task_difficulty='" + task_difficulty + '\'' +
                ", customerId=" + customerId +
                ", companyId=" + companyId +
                ", cost=" + cost +
                ", id=" + id +
                '}';
    }
}
