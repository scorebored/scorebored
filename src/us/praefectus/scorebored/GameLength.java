package us.praefectus.scorebored;

public enum GameLength {
    
    TWENTY_ONE(21),
    ELEVEN(11);
    
    private int points;
    
    GameLength(int points) {
       this.points = points;
    }
    
    public int getPoints() {
        return points;
    }
    
    @Override
    public String toString() {
        return String.valueOf(points);
    }
}
