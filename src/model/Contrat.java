package model;

import enums.TypeContrat;

import java.time.LocalDate;
import java.util.ArrayList;

public class Contrat {
    private TypeContrat typeContrat;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private Integer client_id;

    public Contrat(TypeContrat typeContrat, LocalDate dateDebut, LocalDate dateFin, Integer client_id) {
        this.typeContrat = typeContrat;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.client_id = client_id;
    }

    public Integer getClient_id() {
        return client_id;
    }

    public void setClient_id(Integer client_id) {
        this.client_id = client_id;
    }

    public TypeContrat getTypeContrat() {
        return typeContrat;
    }

    public void setTypeContrat(TypeContrat typeContrat) {
        this.typeContrat = typeContrat;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    @Override
    public String toString() {
        return "Contrat{" +
                ", typeContrat=" + typeContrat +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", clientId=" + client_id +
                '}';
    }
}
