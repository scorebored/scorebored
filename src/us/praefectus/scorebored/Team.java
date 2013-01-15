package us.praefectus.scorebored;

import us.praefectus.scorebored.swing.Swing;
import us.praefectus.scorebored.talker.TalkException;
import us.praefectus.scorebored.util.Check;
import us.praefectus.scorebored.Match;

public class Team {

    public enum Side { 
        LEFT,
        RIGHT;
    }

    private Side side;
    private String name;
    private TeamColor color;
    private int score;
    private int wins;
    
    private Team() {
    }
    
    public Team(Side side) {
        this.side = Check.notNull(side);
    }
    
    public Side getSide() {
        return side;
    }
    
    public void switchSides() {
        switch ( getSide() ) { 
            case LEFT:
                side = Side.RIGHT;
                break;
            case RIGHT:
                side = Side.LEFT;
                break;
            default:
                throw new IllegalStateException("Invalid side: " + getSide());
        }
    }
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the color
     */
    public TeamColor getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(TeamColor color) {
        this.color = color;
    }

    /**
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * @return the wins
     */
    public int getWins() {
        return wins;
    }

    /**
     * @param wins the wins to set
     */
    public void setWins(int wins) {
        this.wins = wins;
    }
    
}

