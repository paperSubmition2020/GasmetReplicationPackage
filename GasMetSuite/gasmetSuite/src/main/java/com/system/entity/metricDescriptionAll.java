package com.system.entity;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class metricDescriptionAll {

    private String metric;
    private String description;
    private String descCostSmell;
    private String valore;
    private List line;

    public metricDescriptionAll() {
    }

    public metricDescriptionAll(String metric, String description, String descCostSmell, String valore, List line) {
        this.metric = metric;
        this.description = description;
        this.descCostSmell = descCostSmell;
        this.valore = valore;
        this.line = line;
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

    public String getDescCostSmell() {
        return descCostSmell;
    }

    public void setDescCostSmell(String descCostSmell) {
        this.descCostSmell = descCostSmell;
    }

    public String getValore() {
        return valore;
    }

    public void setValore(String valore) {
        this.valore = valore;
    }

    public List getLine() {
        return line;
    }

    public void setLine(List line) {
        this.line = line;
    }

}
