package fr.umontpellier.polytech.ig.colocalternant.dao.chat;

import fr.umontpellier.polytech.ig.colocalternant.dao.DAOSQLiteFactory;

/**
 * Concrete implementation of the chat DAO for SQLite.
 */
public class ChatDAOSQLite extends ChatDAO {
    /**
     * Constructor of the chat DAO
     */
    private ChatDAOSQLite() {
        this.daoFactory = DAOSQLiteFactory.getInstance();
    }

    /**
     * Retrieves the unique instance of the chat DAO.
     * @return The chat DAO.
     */
    public static ChatDAOSQLite getInstance() {
        return ChatDAOSQLiteHolder.instance;
    }

    /**
     * Holder of the unique instance of the chat DAO
     */
    private static class ChatDAOSQLiteHolder {
        private final static ChatDAOSQLite instance = new ChatDAOSQLite();
    }
}
