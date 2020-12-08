package com.system.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class metricDescription {

    @Id
    private String metric;
    private String description;
    private String costSmell;

    public metricDescription() {
    }

    public String getCostSmell() {
        return costSmell;
    }

    public void setCostSmell(String costSmell) {
        this.costSmell = costSmell;
    }

    public metricDescription(String metric, String costSmell, String description) {
        this.metric = metric;
        this.costSmell = costSmell;
        this.description = description;
    }

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "metricDescription{" + "metric=" + metric + ", description=" + description + ", costSmell=" + costSmell + '}';
    }

}
