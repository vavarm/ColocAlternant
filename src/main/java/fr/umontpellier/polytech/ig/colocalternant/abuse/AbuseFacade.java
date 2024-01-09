package fr.umontpellier.polytech.ig.colocalternant.abuse;

import fr.umontpellier.polytech.ig.colocalternant.dao.DAOFactory;
import fr.umontpellier.polytech.ig.colocalternant.dao.DAOSQLiteFactory;
import fr.umontpellier.polytech.ig.colocalternant.dao.abuse.AbuseDAO;
import fr.umontpellier.polytech.ig.colocalternant.user.User;
import fr.umontpellier.polytech.ig.colocalternant.user.UserFacade;

import java.util.*;

/**
 *
 */
public class AbuseFacade {

    private DAOFactory daoFactory;

    /**
     * Default constructor
     */
    private AbuseFacade() {
        this.daoFactory = DAOSQLiteFactory.getInstance();
    }


    /**
     * the user who is about to get reported
     */
    private User abuser;

    /**
     * The abuse that's currently handled
     */
    private Abuse currentAbuse;



    /**
     * @return
     */
    public static AbuseFacade getInstance(){return AbuseFacadeHolder.instance;}


    /**
     * @param message 
     * @param dest 
     * @return
     */
    public Abuse createAbuse(String message, User dest) {
        return daoFactory.getAbuseDAO().createAbuse(message, dest);
    }

    /**
     * @param abuse 
     * @param status 
     * @return
     */
    public Abuse updateAbuse(Abuse abuse, StatusEnum status) {
        return daoFactory.getAbuseDAO().updateAbuse(abuse, status);
    }

    /**
     * @param abuse 
     * @return
     */
    public Abuse deleteAbuse(Abuse abuse) {
        return daoFactory.getAbuseDAO().deleteAbuse(abuse);
    }

    /**
     * @return
     */
    public ArrayList<Abuse> getAllAbuses() {
        return daoFactory.getAbuseDAO().getAllAbuses();
    }


    public User getAbuser(){
        return this.abuser;
    }

    public void setAbuser(User abuser){
        this.abuser = abuser;
    }

    public Abuse getCurrentAbuse() {
        return currentAbuse;
    }

    public void setCurrentAbuse(Abuse currentAbuse) {
        this.currentAbuse = currentAbuse;
    }

    private static class AbuseFacadeHolder{
        private final static AbuseFacade instance = new AbuseFacade();
    }


}