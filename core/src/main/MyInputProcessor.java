/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

/**
 * @author Beshoy
 * @author Kiran
 * @author Maloof
 */
// this class was created in attempts to create an input processor
public class MyInputProcessor implements InputProcessor {

    @Override
    public boolean keyDown(int keycode) {
        return false;

    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;

    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
        return false;

    }

    @Override
    public boolean touchUp(int x, int y, int pointer, int button) {
        return false;

    }

    @Override
    public boolean touchDragged(int x, int y, int pointer) {
        return false;

    }

    @Override
    public boolean mouseMoved(int x, int y) {
        return false;

    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

}
