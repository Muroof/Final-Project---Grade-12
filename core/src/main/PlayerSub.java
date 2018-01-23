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
         * Okay, the easiest way to solve our movement issue (which if you
         * forgot is that our player previously was skating on an ice rink, and
         * would exponentially move if a key was held down) is to somehow read
         * in when a key is released. If a key is released, then we can just set
         * the linear velocity to (0,0) making the player stop immediately. I
         * (Maloof) made it so that there is no acceleration to the player, he
         * moves uniformally.
         *
         */

        // starting here
        // this was an attempt to add a input processor
        MyInputProcessor inputProcessor = new MyInputProcessor();
        Gdx.input.setInputProcessor(inputProcessor);

        boolean leftMove;
        boolean rightMove;
        // ends here

        // Let's not keep changing the movement back to the arrow keys, as it is standard to use WASD to move in computer games, thanks - Maloof
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            // constantly moves at a set velocity once the key is pressed
            this.getBody().setLinearVelocity(2, 0);
        }
        // if 'A' key is pressed
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            // constantly moves at a set velocity once the key is pressed
            this.getBody().setLinearVelocity(-2, 0);

        }
        // if 'W' key is pressed
        if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
            // apply force to centre parametrs (xforce, yforce,
            this.getBody().applyForceToCenter(0, 20, true);
        }
    }

}
