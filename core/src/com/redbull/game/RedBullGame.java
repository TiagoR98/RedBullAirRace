package com.redbull.game;

import com.badlogic.gdx.Net;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import com.redbull.game.view.GameView;
import com.badlogic.gdx.Game;
import com.redbull.game.view.MainMenu;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;


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

    public JSONArray getHighScores() throws SQLException{
		try{
			HttpClient client = new DefaultHttpClient();
			HttpGet request = new HttpGet("http://redbullairrace.000webhostapp.com/selectScores.php?limit=10");
			HttpResponse response = client.execute(request);

			// Get the response
			BufferedReader rd = new BufferedReader
					(new InputStreamReader(
							response.getEntity().getContent()));

			String line = "";
			JSONArray convResponse = new JSONArray(rd.readLine());


			//print - temporario
			for (int i=0; i < convResponse.length(); i++) {
				System.out.println(convResponse.getJSONObject(i).getString("username") + " - " + convResponse.getJSONObject(i).getString("score"));
			}

			return convResponse;
		}catch(Exception e){
			System.err.println(e.getCause());
			return null;
		}
	}

	public int submitHighScore(String user,Integer score){
		try{
			HttpClient client = new DefaultHttpClient();
			HttpGet request = new HttpGet("http://redbullairrace.000webhostapp.com/insertScore.php?username="+user+"&score="+score.toString());
			HttpResponse response = client.execute(request);
			return 0;

		}catch(Exception e){
			System.err.println(e.getCause());
			return -1;
		}
	}

	public void startGame() throws SQLException {

		/*
		//-------
		getHighScores();
		submitHighScore("Miguel",30);
		getHighScores();
		System.out.println("FUCK Jas");
		//-----
		*/

		score=0;
		setScreen(new GameView(this));
	}


	public void MainMenu(){
		setScreen(new MainMenu(this));
	}

	public void scored(){this.score++;}

	public void endGame(){this.pause();}
}
