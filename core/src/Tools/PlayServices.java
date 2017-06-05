package Tools;

/**
 * An interface that shows what a
 * game should implements about
 * the google play services
 */
public interface PlayServices {
    void signIn();
    void signOut();
    void rateGame();
    void unlockAchievement(int achiev);
    void incrementAchievement(int achiev, int i);
    void submitScore(int highScore);
    void showAchievement();
    void showScore();
    boolean isSignedIn();
}