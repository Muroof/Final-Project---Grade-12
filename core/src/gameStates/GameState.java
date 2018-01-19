/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameStates;

import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import handlers.GameStateManager;
import main.MainClass;

/**
 * @author Beshoy
 * @author Kiran
 * @author Maloof
 */
public abstract class GameState {

    protected GameStateManager gsm;
    protected MainClass game;
    protected SpriteBatch sb;
    protected OrthographicCamera cam;
    protected OrthographicCamera hudCam;
    protected RayHandler rayHandler;

    protected GameState(GameStateManager gsm) {
        this.gsm = gsm;
        game = gsm.game();
        sb = game.getSpriteBatch();
        cam = game.getCamera();
        hudCam = game.getHUDCamera();
        rayHandler = game.getRayHandler();
        rayHandler.setShadows(true);
    }

    public abstract void handleInput();

    public abstract void update(float dt);

    public abstract void render();

    public abstract void dispose();

}
