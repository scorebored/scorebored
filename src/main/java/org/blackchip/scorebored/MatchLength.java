package org.blackchip.scorebored;

public enum MatchLength {
    
    ONE("Single", 1, 1),
    THREE("2 out of 3", 2, 3),
    FIVE("3 out of 5", 3, 5),
    SEVEN("4 out of 7", 4, 7);
    
    private String name;
    private int minGames;
    private int maxGames;
    
    MatchLength(String name, int minGames, int maxGames) {
        this.name = name;
        this.minGames = minGames;
        this.maxGames = maxGames;
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getMinGames() {
        return this.minGames;
    }
    
    public int getMaxGames() {
        return this.maxGames;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
    
}
