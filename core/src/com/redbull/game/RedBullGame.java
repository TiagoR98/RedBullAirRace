package com.redbull.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class RedBullGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Texture back;
	Sprite backSprite;


	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		back = new Texture("bakcg.png");
		backSprite = new Sprite(back);
	}

	int x = 0;
	int y = 0;

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();

		batch.draw(img, 0, 0);
		backSprite.setX(x++);
		backSprite.setY(0);
		backSprite.setScale(2,2);
		backSprite.draw(batch);
		batch.end();




	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
