package com.system.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CostSmell implements Serializable{

    @Id
    private String id;
    private String CostSmell;
    private String Description;

    public CostSmell() {
    }

    public CostSmell(String id, String CostSmell, String Description) {
        this.id = id;
        this.CostSmell = CostSmell;
        this.Description = Description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCostSmell() {
        return CostSmell;
    }

    public void setCostSmell(String CostSmell) {
        this.CostSmell = CostSmell;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

}
