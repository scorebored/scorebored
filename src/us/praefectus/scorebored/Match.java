package us.praefectus.scorebored;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import us.praefectus.scorebored.Team.Side;
import us.praefectus.scorebored.talker.*;
import us.praefectus.scorebored.util.Check;

public class Match {
    
    private SwingTalker talker;
    private Style style; 
    private boolean active = false;
    private GameLength gameLength;
    private MatchLength matchLength;
    private Team leftTeam = new Team(Team.Side.LEFT);
    private Team rightTeam = new Team(Team.Side.RIGHT);
    private Team.Side server;
    private List<MatchListener> listeners = new LinkedList<MatchListener>();
    private boolean subtitles = false;
    private String pastPointTracker;
    private int oferCounter;
    
    public Match(SwingTalker talker) {
        this.talker = talker;
        gameLength = GameLength.TWENTY_ONE;
        matchLength = MatchLength.ONE; 
        
        style = Style.LED; 
        leftTeam.setName("Home Team");
        leftTeam.setColor(TeamColor.LED_RED);
        rightTeam.setName("Away Team");
        rightTeam.setColor(TeamColor.LED_CYAN);
        oferCounter = 1;
        pastPointTracker = "";
    }
    
    public void reset() {
        leftTeam.setScore(0);
        rightTeam.setScore(0);
        leftTeam.setWins(0);
        rightTeam.setWins(0);
        server = null;
        setActive(false);
        oferCounter = 1;
        pastPointTracker = "";
    }
    
    public void switchSides() {
        Team temp = leftTeam;
        leftTeam = rightTeam;
        rightTeam = temp;
        leftTeam.switchSides();
        rightTeam.switchSides();
        if ( server == Side.LEFT ) { 
            server = Side.RIGHT;
        } else if ( server == Side.RIGHT ) { 
            server = Side.LEFT;
        }
    }
    
    public boolean isOvertime() {        
        return (leftTeam.getScore() >= gameLength.getPoints() ||
               rightTeam.getScore() >= gameLength.getPoints()) && 
               Math.abs(leftTeam.getScore() - rightTeam.getScore()) < 2;
    }
    
    public boolean isEndOfGame() {
        return (leftTeam.getScore() >= gameLength.getPoints() || 
                rightTeam.getScore() >= gameLength.getPoints()) &&
               !isOvertime();
    }
    
    public Team getWinner() {
        if ( !isEndOfGame() ) {
            return null;
            
        }
        return leftTeam.getScore() > rightTeam.getScore() 
                ? leftTeam : rightTeam;
    }

    public Team getLoser() {
        if ( !isEndOfGame() ) {
            return null;
            
        }
        return leftTeam.getScore() > rightTeam.getScore() 
                ? rightTeam : leftTeam;
    }
        
    public boolean isEndOfMatch() {
        if ( !isEndOfGame() ) {
            return false;
        }
        return leftTeam.getWins() == matchLength.getMinGames() ||
               rightTeam.getWins() == matchLength.getMinGames(); 
    }
    
    public boolean isServerChange() {
        int totalScore = leftTeam.getScore() + rightTeam.getScore();
        if ( totalScore == 0 ) { 
            return false;
        }
        switch ( gameLength ) {
            case ELEVEN:
                return totalScore % 2 == 0 || isOvertime();
            case TWENTY_ONE:
                return totalScore % 5 == 0 || isOvertime();
        }
        throw new IllegalStateException("Invalid game length: " + gameLength);
    }
    
    public void switchServers() {
        if ( server == Team.Side.LEFT ) {
            server = Team.Side.RIGHT;  
        } else if ( server == Team.Side.RIGHT ) {
            server = Team.Side.LEFT;
        }
    }
    
    public void advanceTeam(Team.Side side) {
        Commentary commentary = new Commentary();
        
        if ( isEndOfMatch() ) { 
            return;
        }
        if ( isEndOfGame() ) { 
            Team winner = getWinner();
            Team loser = getLoser();
            leftTeam.setScore(0);
            rightTeam.setScore(0);
            server = loser.getSide();
            switchSides();
            return;
        }

        Team team = getTeam(side);

        if ( server == null ) {
            server = side;
            commentary.add(team.getName() + " serves first.");
        } else {                
            team.setScore(team.getScore() + 1);
            commentary.add("Point " + team.getName());
            if ( isEndOfGame() ) {
                Team winner = getWinner();
                Team loser  = getLoser();

                winner.setWins(winner.getWins() + 1);
                
                if(leftTeam.getWins() == matchLength.getMinGames() ||
                   rightTeam.getWins() == matchLength.getMinGames()) {
                    commentary.add("Congratulations " + winner.getName() +
                                   ", You have Defeated " + loser.getName());  
                }
                else {
                     commentary.add("Switch sides, losers serve first.");
                }
                if(loser.getScore() <= 12) {
                    commentary.add("Sorry " + loser.getName() + 
                            ", Jacob is not impressed!");
                }              
            }
            else {
                //check for o-fer and add to commentary
                commentary = checkForOfer(team, commentary);

                if ( isServerChange() ) {
                    if(!isOvertime()) {
                        commentary.add("Change servers", "Change servers!");
                    }
                    switchServers();
                    String name = getTeam(server).getName();
                    oferCounter = 0;
                }
                //announce score
                if(!isOvertime()) {
                    AnnounceScore announceScore = new AnnounceScore(this);
                    commentary.add(announceScore.getScore());
                }
                else {
                    if(leftTeam.getScore() == rightTeam.getScore()) {
                        commentary.add("Deuce", "Deuce!");
                    }
                }

                //check for gamepoint or matchpoint
                commentary = checkForGamePoint(commentary);
            }
        }
        talker.say(commentary);
    }

    public Commentary checkForOfer(Team team, Commentary commentary) {
        if(pastPointTracker.equals(team.getName())) {
            oferCounter ++;
            if (gameLength == GameLength.TWENTY_ONE) {
                if (oferCounter == 5) {
                    commentary.add("O-fer!", "Oh fer!");
                }
            }
            if (gameLength == GameLength.ELEVEN) {
                if(oferCounter == 2) {
                    commentary.add("O-fer!", "Oh fer!");
                }
            }
        }
        else {
            pastPointTracker = team.getName();
            oferCounter = 1;
        }
        return commentary;

    }

    public Commentary checkForGamePoint(Commentary commentary) {    
        if(leftTeam.getScore() >= 20 && leftTeam.getScore() - rightTeam.getScore() >= 1) {
            if(isOvertime()) {
                commentary.add("Advantage " + leftTeam.getName());
            }
            else if(leftTeam.getWins() == matchLength.getMinGames() - 1) {
                commentary.add("Match Point " + leftTeam.getName());
            }
            else {
                commentary.add("Game Point " + leftTeam.getName());
            }
        }
        if(rightTeam.getScore() >= 20 && rightTeam.getScore() - leftTeam.getScore() >= 1) {
            if(isOvertime()) {
                commentary.add("Advantage " + rightTeam.getName());
            }
            else if(rightTeam.getWins() == matchLength.getMinGames() - 1) {
                commentary.add("Match Point " + rightTeam.getName());
            }
            else {
                commentary.add("Game Point " + rightTeam.getName());
            }
        }
        
        return commentary;
    }
    
    public void addListener(MatchListener listener) { 
        listeners.add(Check.notNull(listener));
    }
    
    public boolean removeListener(MatchListener listener) { 
        return listeners.remove(listener);
    }
    
    /**
     * @return the active
     */
    public boolean isActive() {
        return active;
    }

    public void introductionCommentary() {
        talker.say("Todays matchup: " + getTeam(Team.Side.LEFT).getName() +
                        " versus "     + getTeam(Team.Side.RIGHT).getName(), 
                    "Volley for serve");
    }
    
    /**
     * @param active the active to set
     */
    public void setActive(boolean active) {
        this.active = active;
        if ( active ) {
            for ( MatchListener listener: listeners ) { 
                listener.matchStarted();
            }
        } else { 
            for ( MatchListener listener: listeners ) { 
                listener.matchEnded();
            }
        }
    }

    /**
     * @return the gameLength
     */
    public GameLength getGameLength() {
        return gameLength;
    }

    /**
     * @param gameLength the gameLength to set
     */
    public void setGameLength(GameLength gameLength) {
        this.gameLength = gameLength;
    }

    /**
     * @return the matchLength
     */
    public MatchLength getMatchLength() {
        return matchLength;
    }

    /**
     * @param matchLength the matchLength to set
     */
    public void setMatchLength(MatchLength matchLength) {
        this.matchLength = matchLength;
    }

    public Team getTeam(Team.Side side) { 
        switch ( side ) {
            case LEFT:
                return leftTeam;
            case RIGHT:
                return rightTeam;
        }
        throw new IllegalStateException("Unknown side: " + side);
    }

    /**
     * @return the service
     */
    public Team.Side getServer() {
        return server;
    }

    /**
     * @param server the service to set
     */
    public void setServer(Team.Side server) {
        this.server = server;
    }

    /**
     * @return the style
     */
    public Style getStyle() {
        return style;
    }

    /**
     * @param style the style to set
     */
    public void setStyle(Style style) {
        this.style = style;
    }
    
    public boolean isSubtitled() {
        return subtitles;
    }
    
    public void setSubtitled(boolean subtitles) { 
        this.subtitles = subtitles;
    }
    
    public SwingTalker getTalker() {
        return talker;
    }
}
