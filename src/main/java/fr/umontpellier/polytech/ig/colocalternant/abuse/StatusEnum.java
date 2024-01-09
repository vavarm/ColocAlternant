package fr.umontpellier.polytech.ig.colocalternant.abuse;

public enum StatusEnum {
    PENDING("Pending"),
    RESOLVED("Resolved"),
    REJECTED("Rejected");

    String status;

    StatusEnum(String status) {
        this.status = status;

    }
}
