package us.praefectus.scorebored;

import java.util.List;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import us.praefectus.scorebored.Team.Side;
import us.praefectus.scorebored.util.Check;

public class PointHistory {

    public enum Type {
        
        INCREMENT (1),
        DECREMENT (-1);
   
        private int adjustment;
        
        Type(int adjustment) {
            this.adjustment = adjustment;
        }
        
        public int getAdjustment() {
            return adjustment;
        }
    };
    
    private static final Logger log = Logger.getLogger(PointHistory.class);

    private Team.Side side;
    private Type type;
    
    static {
        //log.setLevel(Level.TRACE);
    }
    
    private PointHistory() {
    }
    
    public PointHistory(Team.Side side, Type type) {
        this.side = Check.notNull(side);
        this.type = Check.notNull(type);
    }
    
    public Team.Side getSide() {
        return this.side;
    }

    public Type getType() {
        return this.type;
    }
    
    @Override
    public String toString() {
        return type + " " + side;
    }
    
    public static int getRunCount(List<PointHistory> histories) {
        Side teamToCheck = null;
        int runCount = 0;
        int otherTeamCorrections = 0;
        
        log.trace("Getting run count");
        for ( PointHistory history: histories ) {
            if ( teamToCheck == null ) {
                teamToCheck = history.getSide();
                log.trace("Checking for team: " + teamToCheck);
            }
            int adjustment = history.getType().getAdjustment();
            if ( history.getSide() == teamToCheck ) {
                runCount += adjustment;
            } else {
                if ( adjustment < 0 ) {
                    otherTeamCorrections++;
                } else {
                    if ( otherTeamCorrections == 0 ){
                        return runCount;
                    }
                    otherTeamCorrections--;
                }
            }
        }
        return runCount;
    }
}
