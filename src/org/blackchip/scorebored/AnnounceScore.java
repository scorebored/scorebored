/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.blackchip.scorebored;

/**
 *
 * @author ken
 */
public class AnnounceScore {

    private Match match;
    private Team leftTeam;
    private Team rightTeam;
       
    private AnnounceScore() {
    }
    
    public AnnounceScore(Match match) { 
        this.match = match;
    }
    
    public String getScore() {

        leftTeam = match.getTeam(Team.Side.LEFT);
        rightTeam = match.getTeam(Team.Side.RIGHT);

        String returnString = "";
        int leftScore = leftTeam.getScore();
        int rightScore = rightTeam.getScore();
        if(leftScore == rightScore) {
            if(leftScore == 10) {
                returnString = "Ken, All.";
            }
            else {
                returnString = String.valueOf(leftScore) + ", All.";
            }
        }
        //left side is serving
        else if(match.getServer() == leftTeam.getSide())
        {
            if(leftScore == 10 || rightScore == 10) {
                if(leftScore == 10) {
                    returnString = "Ken to " + String.valueOf(rightScore);
                }
                else {
                    returnString = String.valueOf(leftScore) + " to Ken.";
                }
            }
             //check for 7-11
            else if(leftScore == 7 && rightScore == 11) {
                returnString = "Slurp eees.";
            }
             //check for 9-11
            else if(leftScore == 9 && rightScore == 11) {
                returnString = "Always Remember.";
            }
            else {
                returnString = String.valueOf(leftScore) +
                               " to " + String.valueOf(rightScore);
            }
        }
        //right side is serving
        else {
            if(leftScore == 10 || rightScore == 10) {
                if(leftScore == 10) {
                    returnString = String.valueOf(rightScore) + " to Ken.";
                }
                else {
                    returnString = "Ken to " + String.valueOf(leftScore);
                }
            }
            //check for 7-11
            else if(rightScore == 7 && leftScore == 11) {
                returnString = "Slurp eees.";
            }
            //check for 9-11
            else if(rightScore == 9 && leftScore == 11) {
                returnString = "Always Remember.";
            }
            else {
                returnString = String.valueOf(rightScore) +
                               " to " + String.valueOf(leftScore);
             }
        }
        return returnString;
    }
}
