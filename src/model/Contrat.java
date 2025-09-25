package model;

import enums.TypeContrat;
import java.util.Date;
import java.util.ArrayList;

public class Contrat {
    private TypeContrat typeContrat;
    private Date dateDebut;
    private Date dateFin;

    public Contrat(TypeContrat typeContrat, Date dateDebut, Date dateFin) {
        this.typeContrat = typeContrat;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
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
}
