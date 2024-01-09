package fr.umontpellier.polytech.ig.colocalternant.notification;

import fr.umontpellier.polytech.ig.colocalternant.accommodationAlert.AccommodationAlert;
import fr.umontpellier.polytech.ig.colocalternant.accommodationAlert.AccommodationAlertFacade;
import fr.umontpellier.polytech.ig.colocalternant.accomodation.Accommodation;
import fr.umontpellier.polytech.ig.colocalternant.accomodation.AccommodationFacade;
import fr.umontpellier.polytech.ig.colocalternant.dao.DAOFactory;
import fr.umontpellier.polytech.ig.colocalternant.dao.DAOSQLiteFactory;
import fr.umontpellier.polytech.ig.colocalternant.dao.notification.NotificationDAO;
import fr.umontpellier.polytech.ig.colocalternant.notification.observer.NotificationObservable;
import fr.umontpellier.polytech.ig.colocalternant.notification.observer.NotificationObserver;
import fr.umontpellier.polytech.ig.colocalternant.user.UserFacade;

import java.util.ArrayList;

public class NotificationFacade {

    private DAOFactory daoFactory;
    private NotificationDAO notificationDAO;

    private UserFacade uf = UserFacade.getInstance();
    private AccommodationFacade acc = AccommodationFacade.getInstance();
    private NotificationObservable no = acc.getNotificationObservable();

    private NotificationObserver notificationObserver = new NotificationObserver();

    public NotificationFacade() {
        this.daoFactory = DAOSQLiteFactory.getInstance();
        this.notificationDAO = daoFactory.getNotificationDAO();
        no.addObserver(notificationObserver);
    }

    /**
     * Delete a notification from the database.
     * @param id The ID of the notification to be deleted.
     */
    public void deleteNotif(int id) {
        notificationDAO.delete(id);
    }

    public void insertNotif(int userID, int newItemID) {
        notificationDAO.insertNotif(userID, newItemID);
    }


    public ArrayList<Notification> getOwnNotifs() {
        ArrayList<AccommodationAlert> alerts = AccommodationAlertFacade.getInstance().getOwnAccommodationAlert(uf.getCurrentUser().getId());
        ArrayList<Notification> notifs = notificationDAO.getAllNotifs();

        ArrayList<Notification> myNotifs = new ArrayList<>();

        for (Notification notif: notifs) {
            for (AccommodationAlert alert: alerts) {
                String location = acc.getAccommodation(notif.getNewItemID()).getLocation();
                float price = acc.getAccommodation(notif.getNewItemID()).getPrice();

                if (location.equals(alert.getLocation()) && (price >= alert.getMinPrice()) && (price <= alert.getMaxPrice())) {
                    myNotifs.add(notif);
                }
            }
        }
        return myNotifs;
    }


    public static NotificationFacade getInstance() {
        return NotificationFacade.NotificationFacadeHolder.instance;
    }
    private static class NotificationFacadeHolder {
        private final static NotificationFacade instance = new NotificationFacade();
    }
}
