package com.unihannover.gamedev.component.achievements;

public interface AchievementLogicInterface {
    /**
     * Returns user progress by email.
     * To be implemented by the specialized achievement class.
     *
     * @param userEmail User email
     * @return Progress as a float in [0, 100]
     */
    public float getProgress(String userEmail);
}
