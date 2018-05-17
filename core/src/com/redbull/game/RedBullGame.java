package com.redbull.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.redbull.game.PylonTypes.*;
import com.redbull.game.PylonTypes.PylonType;
import com.redbull.game.view.GameView;
import com.badlogic.gdx.Game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RedBullGame extends Game {
	SpriteBatch batch;
	Texture img;
	Texture back;
	Sprite backSprite;
	private AssetManager assetManager;







    @Override
	public void create () {
		batch = new SpriteBatch();
		assetManager = new AssetManager();



		startGame();


	}

	int x = 0;
	int y = 0;

	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}

	public AssetManager getAssetManager() {
		return assetManager;
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public void startGame(){
		setScreen(new GameView(this));
	}
}
