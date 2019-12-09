package com.unihannover.gamedev.restcontroller;

import com.unihannover.gamedev.models.Achievement;

import java.util.List;

public class AchievementController {

    /*
    Diese Liste soll aus Listen bestehen, die Achievements je nach Typen beinhalten,
    so dass man bei der Verarbeitung des Hooks bei einem einzigen Stichwort z.B. Merge
    direkt weiß, in welcher Liste man nachgucken kann, so dass man auch direkt alle Achievements hat,
    die man updaten kann.
     */
    private List<List<Achievement>> sortedachievements;

    public void setSortedachievements(List<List<Achievement>> sortedachievements)
    {
        this.sortedachievements = sortedachievements;
    }

    public static void parseHook(String data)
    {
        //Hier muss die Hook zu einer JSON geparsed werden.
        //Die GSON Library scheint mir da geeignet.
        //combinatoricalLogic(parsedJSON, dierichtigeListe);
    }

    private static void combinatoricalLogic(List<Achievement> achievements)
    {
        //Versuche den definierten Key vom Achievement in der geparsten JSON zu finden.
        //Wenn der existiert update es dementsprechend für den entsprechenden Nutzer,
        //der dank der E-Mail, die im Hook enthalten ist leicht auffindbar ist.
    }

    private static void sendUpdate(String email, Achievement a)
    {
        //Empfange das entsprechende Achievement mitsamt Fortschritt von der Hauptanwendung.
        //Berechne mithilfe der repititions, wie oft das Ereignis bereits stattfand und
        //berechne daraus wieder den neuen Fortschritt.
        //Sende das Update als HTTP Request an die Hauptanwendung.
    }
}
