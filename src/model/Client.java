package model;

public class Client extends Person{
    private String contrats;
    private String conseiller;

    public Client(String name, String prenom, String email, String contrats, String conseiller) {
        super(name, prenom, email);
        this.contrats = contrats;
        this.conseiller = conseiller;
    }

    public String getContrats() {
        return contrats;
    }

    public void setContrats(String contrats) {
        this.contrats = contrats;
    }

    public String getConseiller() {
        return conseiller;
    }

    public void setConseiller(String conseiller) {
        this.conseiller = conseiller;
    }
}
