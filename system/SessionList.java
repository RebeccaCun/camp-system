package system;

import java.time.LocalDate;
import java.util.ArrayList;

public class SessionList {
    private ArrayList<Session> sessions;
    private static SessionList sessionList;

    private SessionList(){}

    public ArrayList<Session> getSessions(){
        return this.sessions;
    }

    public static SessionList getInstance(){
        if (sessionList == null) {
			sessionList = new sessionList();
		}
		return sessionList;    
    }

    public boolean hasSession(LocalDate startDate, LocalDate endDate){
        Session session = new Session(startDate, endDate);
        for (int i = 0; i < sessions.size(); i++) {
            if(sessions.get(i) == session) {
                return true;
            }
        }
        return false;
    }

    public boolean addSession(LocalDate startDate, LocalDate endDate){
        //need more info on method
        return true;
    }

    public void editSession(Session session, String newStartDate, String newEndDate, String newAgeGroup){
        for (int i = 0; i < sessions.size(); i++) {
            if(sessions.get(i) = session;
                sessions.get(i) == new Session(newStartDate, newEndDate, newAgeGroup);
        }

    }

    public void deleteSession(Session session){
        sessions.remove(session);
    }

    public void saveSessions(){
        //need more info on method
    }
}
