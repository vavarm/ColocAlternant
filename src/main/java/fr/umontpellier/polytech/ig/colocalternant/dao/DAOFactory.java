package fr.umontpellier.polytech.ig.colocalternant.dao;

import fr.umontpellier.polytech.ig.colocalternant.dao.user.UserDAO;

public abstract class DAOFactory {
    public abstract UserDAO getUserDAO();
}