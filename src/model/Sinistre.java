package model;

import enums.TypeSinistre;
import java.util.Date;

public class Sinistre {
    private String id;
    private TypeSinistre typeSinistre;
    private Date dateDebut;
    private Date dateFin;
    private int montant;
    private String description;

    public Sinistre(String id, TypeSinistre typeSinistre, Date dateDebut, Date dateFin, int montant, String description) {
        this.id = id;
        this.typeSinistre = typeSinistre;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.montant = montant;
        this.description = description;
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

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
