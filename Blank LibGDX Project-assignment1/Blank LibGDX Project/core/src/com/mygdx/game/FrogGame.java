package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Screen.PlayScreen;

public class FrogGame extends Game {
	public SpriteBatch batch;


	public static final int V_WITDH = 800;
	public static final int V_HEIGHT = 480;
	public static final float PPM = 100;


	public static AssetManager musicManager;


	@Override
	public void create() {
		batch = new SpriteBatch();
		musicManager = new AssetManager();
		musicManager.load("audio/music/Netherplace.ogg", Music.class);
		musicManager.load("audio/sound/Randomize20.wav", Sound.class);
		musicManager.finishLoading();
		setScreen(new PlayScreen(this));

	}

	@Override
	public void render() {

		super.render();

	}
}

