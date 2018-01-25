/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
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
     * @param cl
     * @param playerX x position of the player
     * @param playerY y position of the player
     * @param xWidth width of the player
     * @param yHeight height of the player
     * @param world the world for objects to be rendered in
     * @param playerBody the body of the player
     * @param playerBodyDef the body definition of the player
     * @param playerShape the shape of the player
     * @param playerFixtureDef the fixture defintion of the player
     */
    public PlayerSub(float playerX, float playerY, float xWidth, float yHeight, World world, Body playerBody, BodyDef playerBodyDef, PolygonShape playerShape, FixtureDef playerFixtureDef) {
        // calls on super for all parameters required
        super(playerX, playerY, xWidth, yHeight, world, playerBody, playerBodyDef, playerShape, playerFixtureDef);

    }

    
/**
 * Handles the user controlled movement of the player
 */
    @Override
    public void handleMovement() {
        // The player's velocity is set to 0, if they are idile (not buttons are pressed, they are on the ground
        if (!Gdx.input.isKeyPressed(Input.Keys.A) && !Gdx.input.isKeyPressed(Input.Keys.W) && !Gdx.input.isKeyPressed(Input.Keys.D) && super.getMyContactListener().isPlayerOnGround()) {
            this.getBody().setLinearVelocity(0, this.getBody().getLinearVelocity().y);
        }
        // if the 'a' key is pressed, set the linear velocity
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            this.getBody().setLinearVelocity(-3, this.getBody().getLinearVelocity().y);
        }
        // if the 'd' key is pressed, set the linear velocity
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            this.getBody().setLinearVelocity(3, this.getBody().getLinearVelocity().y);
        }
        // if the 'w' key is pressed, and the player is on the ground, apply a linear impylse upwards to the center of the body
        if (Gdx.input.isKeyPressed(Input.Keys.W) && super.getMyContactListener().isPlayerOnGround()) {
            float impulse = this.getBody().getMass() * 5;
            this.getBody().applyLinearImpulse(0, impulse, this.getBody().getWorldCenter().x, this.getBody().getWorldCenter().y, true);
        }
    }
}
