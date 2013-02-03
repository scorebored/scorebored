/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package us.praefectus.scorebored.swing;

import us.praefectus.scorebored.DashboardFrame;
import us.praefectus.scorebored.ScoreboardFrame;
import us.praefectus.scorebored.TalkerFrame;

/**
 *
 * @author mcgann
 */
public class WindowManager {
   
    private static final WindowManager instance = new WindowManager();
    
    private DashboardFrame dashboardFrame = null;
    private ScoreboardFrame scoreboardFrame = null;
    private TalkerFrame talkerFrame = null;
    
    private WindowManager() {
    }
    
    public static WindowManager getInstance() {
        return instance;
    }
    
    public void register(DashboardFrame dashboardFrame) {
        if ( this.getDashboardFrame() != null ) {
            throw new IllegalArgumentException("DashboardFrame already " + 
                    "registered");
        }
        this.dashboardFrame = dashboardFrame;
    }

    public void register(ScoreboardFrame scoreboardFrame) {
        if ( this.getScoreboardFrame() != null ) {
            throw new IllegalArgumentException("ScoreboardFrame already " + 
                    "registered");
        }
        this.scoreboardFrame = scoreboardFrame;
    }   
    
    public void register(TalkerFrame talkerFrame) {
        if ( this.getTalkerFrame() != null ) {
            throw new IllegalArgumentException("TalkerFrame already " + 
                    "registered");
        }
        this.talkerFrame = talkerFrame;
    }   

    /**
     * @return the dashboardFrame
     */
    public DashboardFrame getDashboardFrame() {
        return dashboardFrame;
    }

    /**
     * @return the scoreboardFrame
     */
    public ScoreboardFrame getScoreboardFrame() {
        return scoreboardFrame;
    }

    /**
     * @return the talkerFrame
     */
    public TalkerFrame getTalkerFrame() {
        return talkerFrame;
    }
    
    
    
}
