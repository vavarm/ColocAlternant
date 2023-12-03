package fr.umontpellier.polytech.ig.colocalternant.dao.user.exceptions;

public class CredentialException extends Exception {

    private CredentialExceptionType type;

    public CredentialException(CredentialExceptionType type) {
        super();
        this.type = type;
    }

    public CredentialExceptionType getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return switch (this.type) {
            case INVALID_EMAIL -> "Invalid email";
            case INVALID_PASSWORD -> "Invalid password";
        };
    }
}