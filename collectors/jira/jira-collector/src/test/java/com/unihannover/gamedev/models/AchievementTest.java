package com.unihannover.gamedev.models;

/**
 * Represents an achievement
 */
abstract public class AchievementTest {

    /*
    public void testToJSON() {

    }
    */

    @Test
    public void testSetAndGetId(){
        Achievement a = new Achievement();
        a.setId("111");
        assertEquals("111", a.getId());
    }

    @Test
    public void testSetAndGetCollectorId(){
        Achievement a = new Achievement();
        a.setCollectorId("111");
        assertEquals("111", a.getCollectorId());
    }

    @Test
    public void testSetAndGetName(){
        Achievement a = new Achievement();
        a.setName("Achievement 1");
        assertEquals("Achievement 1", a.getName());
    }

    @Test
    public void testSetAndGetDescription(){
        Achievement a = new Achievement();
        a.setDescription("Complete 1 Ticket");
        assertEquals("Complete 1 Ticket", a.getDescription());
    }

    @Test
    public void testSetAndGetValue(){
        Achievement a = new Achievement();
        a.setValue(5.0);
        assertEquals(5.0, a.getValue());
    }
}
