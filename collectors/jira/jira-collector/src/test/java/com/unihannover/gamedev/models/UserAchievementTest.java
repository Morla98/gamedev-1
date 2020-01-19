package com.unihannover.gamedev.models;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Represents an achievement achieved by a user
 */
public class UserAchievementTest {

    /*

    public String toJSON() {

    }
    */

    @Test
    public void testSetAndGetAchievementId(){
        Configuration test = new Configuration();
        test.setAchievementId("123");
        assertEquals("123", test.getAchievementId());
    }

    @Test
    public void testSetAndGetCollectorId(){
        Configuration test = new Configuration();
        test.setCollectorId("123");
        assertEquals("123", test.getCollectorId());
    }

    @Test
    public void testSetAndGetEmail(){
        Configuration test = new Configuration();
        test.setEmail("test@user.com");
        assertEquals("test@user.com", test.getEmail());
    }

    @Test
    public void testSetAndGetProgress(){
        Configuration test = new Configuration();
        test.setProgress(0.5);
        assertEquals(0.5, test.getProgress());
    }

    /*
    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
    */
}
