package fr.umontpellier.polytech.ig.colocalternant.controller.profile;

import fr.umontpellier.polytech.ig.colocalternant.profile.EnumRole;

public class CombinedModel {
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private String description;
    private EnumRole role;

    public CombinedModel(int id, String firstName, String lastName, int age, String email, String description, EnumRole role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.description = description;
        this.role = role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRole(EnumRole role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getDescription() {
        return description;
    }

    public EnumRole getRole() {
        return role;
    }
}
