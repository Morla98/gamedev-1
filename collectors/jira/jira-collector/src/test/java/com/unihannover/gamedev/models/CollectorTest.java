package com.unihannover.gamedev.models;

import java.sql.Timestamp;

public class CollectorTest {


    @Test
    public void testSetAndGetId(){
        Collector test = new Collector();
        test.setId("111");
        assertEquals("111", test.getId());
    }

    @Test
    public void testSetAndGetName(){
        Collector test = new Collector();
        test.setName("Collector 1");
        assertEquals("Collector 1", test.getName());
    }

    /*
    public Timestamp getLastSeen() {

    }

    public void setLastSeen(Timestamp lastSeen) {

    }


    public String getToken() {

    }

    public void setToken(String token) {

    }
    */

}
