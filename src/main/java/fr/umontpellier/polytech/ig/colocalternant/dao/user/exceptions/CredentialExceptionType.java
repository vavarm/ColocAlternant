package fr.umontpellier.polytech.ig.colocalternant.dao.user.exceptions;

/**
 * Enumeration of the types of the credential exceptions.
 */
public enum CredentialExceptionType {
    /*
     * The email is invalid.
     */
    INVALID_EMAIL,
    /*
     * The password is invalid.
     */
    INVALID_PASSWORD,
    /*
     * The email is already used.
     */
    EMAIL_ALREADY_USED,
}