package fr.umontpellier.polytech.ig.colocalternant.abuse;

/**
 * The StatusEnum class represents the possible statuses of an abuse report.
 * It is an enumeration with values PENDING, RESOLVED, and REJECTED, each associated with a corresponding status message.
 */
public enum StatusEnum {
    /**
     * Indicates that the abuse report is pending review or action.
     */
    PENDING("Pending"),

    /**
     * Indicates that the abuse report has been resolved.
     */
    RESOLVED("Resolved"),

    /**
     * Indicates that the abuse report has been rejected.
     */
    REJECTED("Rejected");

    /**
     * The status message associated with each enum value.
     */
    String status;

    /**
     * Constructor for StatusEnum.
     *
     * @param status The status message associated with the enum value.
     */
    StatusEnum(String status) {
        this.status = status;
    }
}
