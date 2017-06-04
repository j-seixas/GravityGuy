package com.lpoo.gravityguy;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.games.Games;
import com.google.example.games.basegameutils.GameHelper;
import Tools.PlayServices;

import Game.GravityGuy;

public class AndroidLauncher extends AndroidApplication implements PlayServices {
	private GameHelper gameHelper;
	private final static int requestCode = 1;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		gameHelper = new GameHelper(this, GameHelper.CLIENT_GAMES);
		gameHelper.enableDebugLog(false);

		GameHelper.GameHelperListener gameHelperListener = new GameHelper.GameHelperListener()
		{
			@Override
			public void onSignInFailed(){ }

			@Override
			public void onSignInSucceeded(){ }
		};

		gameHelper.setup(gameHelperListener);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new GravityGuy(this), config);
	}

	@Override
	protected void onStart()
	{
		super.onStart();
		gameHelper.onStart(this);
	}

	@Override
	protected void onStop()
	{
		super.onStop();
		gameHelper.onStop();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		gameHelper.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void signIn()
	{
		try
		{
			runOnUiThread(new Runnable()
			{
				@Override
				public void run()
				{
					gameHelper.beginUserInitiatedSignIn();
				}
			});
		}
		catch (Exception e)
		{
			Gdx.app.log("MainActivity", "Log in failed: " + e.getMessage() + ".");
		}
	}

	@Override
	public void signOut()
	{
		try
		{
			runOnUiThread(new Runnable()
			{
				@Override
				public void run()
				{
					gameHelper.signOut();
				}
			});
		}
		catch (Exception e)
		{
			Gdx.app.log("MainActivity", "Log out failed: " + e.getMessage() + ".");
		}
	}

	@Override
	public void rateGame()
	{
		String str = "Your PlayStore Link";
		startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(str)));
	}

	public String getAchiev(int achiev){
		switch (achiev){
			case 0: return getString(R.string.achievement_silent_gamer);
			case 1: return getString(R.string.achievement_gravity_master);
			case 2: return getString(R.string.achievement_devil_player);
			case 3: return getString(R.string.achievement_oww);
			default: return null;
		}
	}

	public String getIncAchiev(int achiev){
		switch (achiev){
			case 0: return getString(R.string.achievement_rookie);
			case 1: return getString(R.string.achievement_expert);
			case 2: return getString(R.string.achievement_gravity_sensei);
			default: return null;
		}
	}

	@Override
	public void unlockAchievement(int achiev) {
		if(isSignedIn()) {
            String str = getAchiev(achiev);
            if (str != null)
                Games.Achievements.unlock(gameHelper.getApiClient(), str);
        }
	}

	@Override
	public void incrementAchievement(int achiev, int i) {
        if(isSignedIn()) {
            String str = getIncAchiev(achiev);
            if (str != null)
                Games.Achievements.increment(gameHelper.getApiClient(), str, i);
        }
	}

	@Override
	public void submitScore(int highScore) {
		if (isSignedIn()) {
			Games.Leaderboards.submitScore(gameHelper.getApiClient(),
					getString(R.string.leaderboard_time_highscore), highScore);
		}
	}

	@Override
	public void showAchievement() {
		if (isSignedIn())
			startActivityForResult(Games.Achievements.getAchievementsIntent(gameHelper.getApiClient()), requestCode);

		else
			signIn();

	}

	@Override
	public void showScore() {
		if (isSignedIn()) {
			startActivityForResult(Games.Leaderboards.getLeaderboardIntent(gameHelper.getApiClient(),
					getString(R.string.leaderboard_time_highscore)), requestCode);
		} else
			signIn();

	}

	@Override
	public boolean isSignedIn()
	{
		return gameHelper.isSignedIn();
	}

}
