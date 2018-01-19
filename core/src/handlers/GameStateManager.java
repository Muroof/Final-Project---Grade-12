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

    protected MainClass game;
    protected Stack<GameState> gameStates;
    public static final int PLAY = 912837;

    public GameStateManager(MainClass game) {
        this.game = game;
        gameStates = new Stack<GameState>();
        pushState(PLAY);
    }

    public MainClass game() {
        return game;
    }

    public void update(float dt) {
        gameStates.peek().update(dt);
    }

    public void render() {
        gameStates.peek().render();

    }

    private GameState getState(int state) {
        if (state == PLAY) {
            return new Play(this);
        }
        return null;

    }

    public void setState(int state) {
        popState();
        pushState(state);
    }

    public void pushState(int state) {
        gameStates.push(getState(state));
    }

    public void popState() {
        GameState g = gameStates.pop();
        g.dispose();
    }

}
