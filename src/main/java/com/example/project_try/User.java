package com.example.project_try;

public class User {
    private long highScore;
    private Game currentGame;
    private long coinCount;

    User(){
        highScore = 0;
        currentGame = new Game();
        coinCount = 0;
    }

    public long getCoinCount() {
        return coinCount;
    }

    public Game getCurrentGame() {
        return currentGame;
    }

    public long getHighScore() {
        return highScore;
    }

    public void setCoinCount(long coinCount) {
        this.coinCount = coinCount;
    }

    public void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
    }

    public void setHighScore(long highScore) {
        this.highScore = highScore;
    }
}
