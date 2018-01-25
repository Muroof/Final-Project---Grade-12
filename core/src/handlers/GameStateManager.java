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

    // variables required in order to create a GameStateManager, which is needed to effeciently run box2d
    protected MainClass game;
    // use a stack to keep all of the possible gamestates
    protected Stack<GameState> gameStates;
    public static final int PLAY = 90000;

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
     * renders the the play state at the top of the stack
     */
    public void render() {
        gameStates.peek().render();

    }

    /**
     *
     * @param state the state of the game
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
     * @param state an integer which can be used to set the current state of the
     * game
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
     * remove the topmost state from the stack
     */
    public void popState() {
        GameState g = gameStates.pop();
        g.dispose();
    }

}
