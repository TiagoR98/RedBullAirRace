package com.redbull.game.view.Input;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.redbull.game.RedBullGame;

public class InputHandler extends InputListener {
    private RedBullGame game;

    public InputHandler(RedBullGame game){
        super();
        this.game=game;
    }

    public RedBullGame getGame() {
        return game;
    }
}
