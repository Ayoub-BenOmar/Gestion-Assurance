package model;

import enums.TypeSinistre;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Sinistre {
    private String id;
    private TypeSinistre typeSinistre;
    private Date dateTime;
    private double cout;
    private String description;
    private Integer contratId;

    public Sinistre(TypeSinistre typeSinistre, Date dateTime, double cout, String description, Integer contratId) {
        this.typeSinistre = typeSinistre;
        this.dateTime = dateTime;
        this.cout = cout;
        this.description = description;
        this.contratId = contratId;
    }

    public Sinistre(String id, TypeSinistre typeSinistre, Date dateTime, double cout, String description, Integer contratId) {
        this.id = id;
        this.typeSinistre = typeSinistre;
        this.dateTime = dateTime;
        this.cout = cout;
        this.description = description;
        this.contratId = contratId;
    }

    public Integer getContratId() {
        return contratId;
    }

    public void setContratId(Integer contratId) {
        this.contratId = contratId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TypeSinistre getTypeSinistre() {
        return typeSinistre;
    }

    public void setTypeSinistre(TypeSinistre typeSinistre) {
        this.typeSinistre = typeSinistre;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public double getCout() {
        return cout;
    }

    public void setCout(double cout) {
        this.cout = cout;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
