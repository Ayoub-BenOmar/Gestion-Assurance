package model;

import enums.TypeContrat;

import java.util.Date;
import java.util.ArrayList;

public class Contract {
    private String id;
    private TypeContrat typeContrat;
    private Date dateDebut;
    private Date dateFin;
    private ArrayList<String> sinistres = new ArrayList<>();

    public Contract(String id, TypeContrat typeContrat, Date dateDebut, Date dateFin, ArrayList<String> sinistres) {
        this.id = id;
        this.typeContrat = typeContrat;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.sinistres = sinistres;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TypeContrat getTypeContrat() {
        return typeContrat;
    }

    public void setTypeContrat(TypeContrat typeContrat) {
        this.typeContrat = typeContrat;
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

    public ArrayList<String> getSinistres() {
        return sinistres;
    }

    public void setSinistres(ArrayList<String> sinistres) {
        this.sinistres = sinistres;
    }
}
