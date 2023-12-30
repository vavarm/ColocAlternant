package fr.umontpellier.polytech.ig.colocalternant.dao.user.exceptions;

/**
 * Exception thrown when the credentials are invalid.
 */
public class CredentialException extends Exception {

    /**
     * The type of the exception
     */
    private CredentialExceptionType type;

    /**
     * Constructor of the exception
     * @param type The type of the exception
     */
    public CredentialException(CredentialExceptionType type) {
        super();
        this.type = type;
    }

    /**
     * Retrieves the type of the exception
     * @return The type of the exception
     */
    public CredentialExceptionType getType() {
        return this.type;
    }

    /**
     * Retrieves a message describing the type of the exception
     * @return A message describing the type of the exception
     */
    @Override
    public String toString() {
        return switch (this.type) {
            case INVALID_EMAIL -> "Invalid email";
            case INVALID_PASSWORD -> "Invalid password";
            case EMAIL_ALREADY_USED -> "Email already used";
        };
    }
}