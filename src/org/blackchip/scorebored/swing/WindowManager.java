/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.blackchip.scorebored.swing;

import org.blackchip.scorebored.DashboardFrame;
import org.blackchip.scorebored.ScoreboardFrame;
import org.blackchip.scorebored.TalkerFrame;
import org.blackchip.scorebored.JacobExcusesFrame;

/**
 *
 * @author mcgann
 */
public class WindowManager {
   
    private static final WindowManager instance = new WindowManager();
    
    private DashboardFrame dashboardFrame = null;
    private ScoreboardFrame scoreboardFrame = null;
    private TalkerFrame talkerFrame = null;
    private JacobExcusesFrame jacobExcusesFrame = null;
    
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
    
    public void register(JacobExcusesFrame jacobExcusesFrame) {
        if ( this.getJacobExcusesFrame() != null ) {
            throw new IllegalArgumentException("JacobExcusesFrame already " + 
                    "registered");
        }
        this.jacobExcusesFrame = jacobExcusesFrame;
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
    
    /**
     * @return the jacobExcuseFrame
     */
    public JacobExcusesFrame getJacobExcusesFrame() {
        return jacobExcusesFrame;
    }
}
