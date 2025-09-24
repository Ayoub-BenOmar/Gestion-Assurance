package model;

public abstract class Person {
    private Integer role;
    private String name;
    private String prenom;
    private String email;

    public Person(Integer role, String name, String prenom, String email) {
        this.role = role;
        this.name = name;
        this.prenom = prenom;
        this.email = email;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
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
