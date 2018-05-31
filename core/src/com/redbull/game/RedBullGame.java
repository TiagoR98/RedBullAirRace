package com.redbull.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.redbull.game.PylonTypes.*;
import com.redbull.game.PylonTypes.PylonType;
import com.redbull.game.view.GameView;
import com.badlogic.gdx.Game;
import com.redbull.game.view.MainMenu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RedBullGame extends Game {
	SpriteBatch batch;
	Texture img;
	Texture back;
	Sprite backSprite;
	private AssetManager assetManager;
	private BitmapFont font;

    @Override
	public void create () {
		batch = new SpriteBatch();
		assetManager = new AssetManager();

		font = new BitmapFont();
		font.setColor(Color.RED);

		MainMenu();


	}

	int x = 0;
	int y = 0;
	int score = 0;

	
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

	public BitmapFont getFont() {
		return font;
	}

    public int getScore() {
        return score;
    }

    public void startGame(){
		score=0;
		setScreen(new GameView(this));
	}


	public void MainMenu(){
		setScreen(new MainMenu(this));
	}

	public void scored(){this.score++;}

	public void endGame(){this.pause();}
}
