    /**
     * Return a counselor
     * @param userObj The JSONObject for the counselor
     * @param counselor a Counselor instance
     * @return a new Counselor class object
     */
    private static User getUser(JSONObject userObj, Counselor counselor) {
        
        counselor = new Counselor(
            UUID.fromString((String) userObj.get(USER_ID)),
            (String) userObj.get(FIRST_NAME),
            (String) userObj.get(LAST_NAME),
            (String) userObj.get(USERNAME));
            
        counselor.setPassword( (String) userObj.get(PASSWORD) );
        counselor.addEmail( (String) userObj.get(EMAIL) );
        counselor.addPhoneNumber( (String) userObj.get(PHONE_NUMBER) );
        counselor.addPreferredContact( (String) userObj.get(PREFFERED_CONTACT) );
        counselor.addBirthday( LocalDate.parse((String) userObj.get(BIRTHDAY)) );
        counselor.addAddress( (String) userObj.get(ADDRESS) );
       
        // get the type
        String type = (String) userObj.get(TYPE);
        Type newType = Type.valueOf(type);
        counselor.setType(newType);

        // initialize the campers ArrayList first...
        getCampers();

        // Now add campers to cabins, because campers have been initialized...
        addCampersToCabins();
        
        // add Campers to the Counselor
        counselor.addCampers(getSomeCampers(userObj));