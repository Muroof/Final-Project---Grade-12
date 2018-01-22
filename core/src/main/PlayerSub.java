/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * @author Beshoy
 * @author Kiran
 * @author Maloof
 */
public class PlayerSub extends CharacterSuper {

    /**
     * constructor for Player
     *
     * @param playerX
     * @param playerY
     * @param xWidth
     * @param yHeight
     * @param world
     * @param playerBody
     * @param playerBodyDef
     * @param playerShape
     * @param playerFixtureDef
     */
    public PlayerSub(float playerX, float playerY, float xWidth, float yHeight, World world, Body playerBody, BodyDef playerBodyDef, PolygonShape playerShape, FixtureDef playerFixtureDef) {
        // calls on super for all parameters required
        super(playerX, playerY, xWidth, yHeight, world, playerBody, playerBodyDef, playerShape, playerFixtureDef);

    }

    /**
     *
     * @param enemy
     */
    public void hasBeenSeenByLight(EnemySub enemy) {

    }

    @Override
    public void handleMovement() {
        // Let's not keep changing the movement back to the arrow keys, as it is standard to use WASD to move in computer games, thanks - Maloof

        // if 'D' key is pressed
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            // apply force to centre parametrs (xforce, yforce,
            this.getBody().applyForceToCenter(2, 0, true);

        }
        // if 'A' key is pressed
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            // apply force to centre parametrs (xforce, yforce,
            this.getBody().applyForceToCenter(-2, 0, true);

        }
        // if 'W' key is pressed
        if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
            // apply force to centre parametrs (xforce, yforce,
            this.getBody().applyForceToCenter(0, 20, true);
        }
    }

}
