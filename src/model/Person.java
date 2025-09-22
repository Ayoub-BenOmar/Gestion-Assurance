package model;

public class Person {
    private String name;
    private String prenom;
    private String email;

    public Person(String name, String prenom, String email) {
        this.name = name;
        this.prenom = prenom;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
