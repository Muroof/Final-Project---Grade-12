/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.badlogic.gdx.Gdx;
import static com.badlogic.gdx.Gdx.input;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
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
        /**
         * Update from Maloof:
         *
         */
        // Let's not keep changing the movement back to the arrow keys, as it is standard to use WASD to move in computer games, thanks - Maloof
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            this.getBody().setLinearVelocity(2, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            this.getBody().setLinearVelocity(-2, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {

            float impulse = this.getBody().getMass() * 3;
            this.getBody().applyLinearImpulse(0, impulse, this.getBody().getWorldCenter().x, this.getBody().getWorldCenter().y, true);

        } else if (!Gdx.input.isKeyPressed(Input.Keys.D) && !Gdx.input.isKeyPressed(Input.Keys.A) && !Gdx.input.isKeyPressed(Input.Keys.W)) {
            this.getBody().setLinearVelocity(0, -9.8f);
        }
    }
}
