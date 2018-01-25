/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameStates;

import main.MainClass;
import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import handlers.GameStateManager;

/**
 * @author Beshoy
 * @author Kiran
 * @author Maloof
 */
public abstract class GameState {

    // variables required for box2d
    private GameStateManager gsm;
    private MainClass game;
    private SpriteBatch sb;
    protected OrthographicCamera cam;

    /**
     * constructor for GameState
     *
     * @param gsm
     */
    public GameState(GameStateManager gsm) {
        // defined all variables required for a GameState
        this.gsm = gsm;
        game = gsm.game();
        sb = game.getSpriteBatch();
        cam = game.getCamera();
    }

    /**
     *
     * @param dt
     */
    public abstract void update(float dt);

    /**
     *
     */
    public abstract void render();

    /**
     *
     */
    public abstract void dispose();

}
