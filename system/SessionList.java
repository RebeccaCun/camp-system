package system;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * An SessionList class that contains all of the sessions for the camp system.
 * @author Cyber Council
 */
public class SessionList {
    private ArrayList<Session> sessions;
    private static SessionList sessionList;

    /**
     * Initializes an instance of the SessionList class.
     */
    private SessionList() {
        sessions = DataReader.getAllSessions();
        if(sessions == null){
            sessions = new ArrayList<Session>();
        }
        sessionList = this;
    }

    /**
     * Returns the Sessions for the SessionList class.
     * @return The ArrayList of sessions in the SessionList.
     */
    public ArrayList<Session> getSessions(){
        return this.sessions;
    }

    /**
     * Creates an instance of the SessionList class.
     * @return The created SessionList instance.
     */
    public static SessionList getInstance(){
        if (sessionList == null) {
			sessionList = new SessionList();
		}
		return sessionList;    
    }

    /**
     * Searches for a specific Session and returns them if found.
     * @param startDate The start date of the Session being searched for.
     * @param endDate The end date of the Session being searched for.
     * @return The Session being searched for.
     */
    public Session getSession(LocalDate startDate, LocalDate endDate) {
        for(int i = 0; i < sessions.size(); i++) {
            if(sessions.get(i) == new Session(startDate, endDate)){
                return sessions.get(i);
            }
        }
        return null;
    }

    /**
     * Searches for a specific Session  and returns a boolean depending of if it are found.
     * @param startDate The start date of the Session being searched for.
     * @param endDate The end date of the Session being searched for.
     * @return The boolean representing the status of the Session.
     */
    public boolean hasSession(LocalDate startDate, LocalDate endDate){
        for (int i = 0; i < sessions.size(); i++) {
            if(sessions.get(i) == new Session(startDate, endDate)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a Session to the list and returns a boolean depending of if it was added.
     * @param session The session to be added
     * @return The boolean representing the status of the Session.
     */
    public void addSession(Session session) {
        sessions.add(session);
    }

    /**
     * Edits a Session in the list.
     * @param session The Session to be edited.
     * @param startDate The start date of the new Session.
     * @param endDate The end date of the new Session.
     */
    public void editSession(Session session, LocalDate newStartDate, LocalDate newEndDate){
        for (int i = 0; i < sessions.size(); i++) {
            if(sessions.get(i) == session) {
                sessions.set(i,new Session(newStartDate, newEndDate));
            }
        }
    }

    /**
     * Saves the SessionList.
     */
    public void saveSessions(){
        DataWriter.saveSessions();
    }
}
