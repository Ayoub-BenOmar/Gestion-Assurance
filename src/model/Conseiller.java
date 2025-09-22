package model;

public class Conseiller extends Person{
    private String clients;

    public Conseiller(String name, String prenom, String email, String clients) {
        super(name, prenom, email);
        this.clients = clients;
    }

    public String getClients() {
        return clients;
    }

    public void setClients(String clients) {
        this.clients = clients;
    }
}
