package com.redbull.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.redbull.game.view.ChoosePlane;
import com.redbull.game.view.GameOver;
import com.redbull.game.view.GameView;
import com.badlogic.gdx.Game;
import com.redbull.game.view.Highscores;
import com.redbull.game.view.LoadingScreen;
import com.redbull.game.view.MainMenu;
import com.redbull.game.view.RulesScreen;
import com.redbull.game.view.SubmitScore;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class RedBullGame extends Game {
	SpriteBatch batch;
	Texture img;
	Texture back;
	Sprite backSprite;
	private AssetManager assetManager;
	 FreeTypeFontGenerator generator;
	 FreeTypeFontGenerator.FreeTypeFontParameter parameter;
	 ParticleEffect effect;
	private BitmapFont font;

    @Override
	public void create () {
		batch = new SpriteBatch();
		assetManager = new AssetManager();
		generator = new FreeTypeFontGenerator(Gdx.files.internal("The Outbox St.ttf"));
		parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 120;
		parameter.borderWidth = 5;
		parameter.borderColor = Color.BLACK;

		font=generator.generateFont(parameter);

		effect = new ParticleEffect();
		effect.load(Gdx.files.internal("smoke.p"),Gdx.files.internal(""));

		LoadingScreen();

		this.loadAssets();

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

	public ParticleEffect getEffect() {
		return effect;
	}

	public JSONArray getHighScores(){
		try {
			HttpClient client = new DefaultHttpClient();
			HttpGet request = new HttpGet("http://redbullairrace.000webhostapp.com/selectScores.php?limit=10");
			HttpResponse response = client.execute(request);

			BufferedReader rd = new BufferedReader
					(new InputStreamReader(
							response.getEntity().getContent()));

			String line = "";
			JSONArray convResponse = new JSONArray(rd.readLine());

			return convResponse;
		}catch (Exception e){return new JSONArray();}
	}

	public int submitHighScore(String user,Integer score) throws Exception{
			HttpClient client = new DefaultHttpClient();
			HttpGet request = new HttpGet("http://redbullairrace.000webhostapp.com/insertScore.php?username="+user+"&score="+score.toString());
			HttpResponse response = client.execute(request);
			return 0;

	}

	public void startGame(){
		score=0;
		setScreen(new GameView(this));
	}


	public void MainMenu(){
		setScreen(new MainMenu(this));
	}

	public void LoadingScreen(){
		setScreen(new LoadingScreen(this));
	}

	public void RulesScreen(){
		setScreen(new RulesScreen(this));
	}

	public void choosePlane(){
		setScreen(new ChoosePlane(this));
	}

	public void highscores(){
		setScreen(new Highscores(this));
	}

	public void submitScore(){
		setScreen(new SubmitScore(this));
	}

	public void gameOver(){
		setScreen(new GameOver(this));
	}

	public void scored(){this.score++;}

	public void loadAssets(){
		loadImgPylons();

		loadImgPlanes();

		loadImgs();

		loadSkins();

		loadMusic();

		this.getAssetManager().finishLoading();

	}

	private void loadMusic() {
		this.getAssetManager().load("smokeon.mp3", Music.class);
		this.getAssetManager().load("smokeonsonka.mp3", Music.class);
		this.getAssetManager().load("passing.mp3", Music.class);
	}

	private void loadSkins() {
		this.getAssetManager().load("skin/clean-crispy-ui.json", Skin.class);
		this.getAssetManager().load("skin2/clean-crispy-ui.json", Skin.class);
		this.getAssetManager().load("skin3/clean-crispy-ui.json", Skin.class);
	}

	private void loadImgs() {
		this.getAssetManager().load("backg.png", Texture.class);
		this.getAssetManager().load("backgmenu.png", Texture.class);
		this.getAssetManager().load("winners.png", Texture.class);

		this.getAssetManager().load("masterbutton.png", Texture.class);
		this.getAssetManager().load("challbutton.png", Texture.class);

		this.getAssetManager().load("rbarlogowht.png", Texture.class);

		this.getAssetManager().load("rulesbackg.png",Texture.class);
		this.getAssetManager().load("rules.png",Texture.class);
	}

	private void loadImgPlanes() {
		this.getAssetManager().load("master1.png", Texture.class);
		this.getAssetManager().load("master2.png", Texture.class);
		this.getAssetManager().load("master3.png", Texture.class);
		this.getAssetManager().load("master4.png", Texture.class);
		this.getAssetManager().load("master5.png", Texture.class);
		this.getAssetManager().load("master6.png", Texture.class);
		this.getAssetManager().load("master7.png", Texture.class);

		this.getAssetManager().load("chall1.png", Texture.class);
		this.getAssetManager().load("chall2.png", Texture.class);
		this.getAssetManager().load("chall3.png", Texture.class);
		this.getAssetManager().load("chall4.png", Texture.class);
		this.getAssetManager().load("chall5.png", Texture.class);
		this.getAssetManager().load("chall6.png", Texture.class);
		this.getAssetManager().load("chall7.png", Texture.class);
	}

	private void loadImgPylons() {
		this.getAssetManager().load("p1v.png", Texture.class);
		this.getAssetManager().load("p2v.png", Texture.class);
		this.getAssetManager().load("p3v.png", Texture.class);
		this.getAssetManager().load("p4v.png", Texture.class);
		this.getAssetManager().load("p5v.png", Texture.class);
		this.getAssetManager().load("p6v.png", Texture.class);
		this.getAssetManager().load("p1a.png", Texture.class);
		this.getAssetManager().load("p2a.png", Texture.class);
		this.getAssetManager().load("p3a.png", Texture.class);
		this.getAssetManager().load("p4a.png", Texture.class);
		this.getAssetManager().load("p5a.png", Texture.class);
		this.getAssetManager().load("p6a.png", Texture.class);
	}
}
