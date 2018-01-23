/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handlers;

import gameStates.GameState;
import gameStates.Play;
import java.util.Stack;
import main.MainClass;

/**
 * @author Beshoy
 * @author Kiran
 * @author Maloof
 */
public class GameStateManager {

    // variables required in order to create a GameStateManager
    protected MainClass game;
    protected Stack<GameState> gameStates;
    public static final int PLAY = 912837;

    /**
     * constructor for GameStateManager
     *
     * @param game
     */
    public GameStateManager(MainClass game) {
        this.game = game;
        gameStates = new Stack<GameState>();
        pushState(PLAY);
    }

    /**
     *
     * @return game
     */
    public MainClass game() {
        return game;
    }

    /**
     *
     * @param dt
     */
    public void update(float dt) {
        gameStates.peek().update(dt);
    }

    /**
     *
     */
    public void render() {
        gameStates.peek().render();

    }

    /**
     *
     * @param state
     * @return null
     */
    private GameState getState(int state) {
        if (state == PLAY) {
            return new Play(this);
        }
        return null;

    }

    /**
     *
     * @param state
     */
    public void setState(int state) {
        popState();
        pushState(state);
    }

    /**
     *
     * @param state
     */
    public void pushState(int state) {
        gameStates.push(getState(state));
    }

    /**
     *
     */
    public void popState() {
        GameState g = gameStates.pop();
        g.dispose();
    }

}
