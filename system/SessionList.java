package system;

import java.util.ArrayList;

public class SessionList {
    private ArrayList<Session> sessions;
    private static SessionList sessionList;

    private SessionList(){}

    public ArrayList<Session> getSessions(){
        return this.sessions;
    }

    public SessionList getInstance(){
        return new SessionList();
    }

    public boolean hasSession(String startDate, String endDate, String ageGroup){
        return true;
    }

    public boolean addSession(String startDate, String endDate, String ageGroup){
        return true;
    }

    public void editSession(Session session, String newStartDate, String newEndDate, String newAgeGroup){

    }

    public void deleteSession(Session session){}

    public void saveSessions(){}


}
