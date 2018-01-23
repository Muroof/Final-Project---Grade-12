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
         * The problem with setting a linear force is that it can't be
         * counteracted without setting another linear force. This is causing
         * problems especially with jumping. What we really need is some kind of
         * force that is uniform. Lamont apparently said that we can fix this
         * with his jump code in his example. I'll take a look
         *
         */
        // starting here
        // this was an attempt to add a input processor
        MyInputProcessor inputProcessor = new MyInputProcessor();
        Gdx.input.setInputProcessor(inputProcessor);

        boolean leftMove;
        boolean rightMove;
        // ends here

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            this.getBody().applyLinearImpulse(0.2f, 0, this.getBody().getWorldCenter().x, this.getBody().getWorldCenter().y, true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            this.getBody().applyLinearImpulse(-0.2f, 0, this.getBody().getWorldCenter().x, this.getBody().getWorldCenter().y, true);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
            this.getBody().applyLinearImpulse(0, 0.2f, this.getBody().getWorldCenter().x, this.getBody().getWorldCenter().y, true);
        } else if (!Gdx.input.isKeyPressed(Input.Keys.D) && !Gdx.input.isKeyPressed(Input.Keys.A) && !Gdx.input.isKeyPressed(Input.Keys.W)) {
            this.getBody().setLinearVelocity(0, -9.8f);
        }




//        // Let's not keep changing the movement back to the arrow keys, as it is standard to use WASD to move in computer games, thanks - Maloof
//        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
//            // constantly moves at a set velocity once the key is pressed
//            this.getBody().setLinearVelocity(2, 0);
//        } else if (!Gdx.input.isKeyPressed(Input.Keys.D)) {
//            this.getBody().set(new Vector2(-2, 0), true);
//        } else {
//            System.out.println("Hello");
//        }
//        // if 'A' key is pressed
//        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
//            // constantly moves at a set velocity once the key is pressed
//            this.getBody().setLinearVelocity(-2, 0);
//
//        }
//        // if 'W' key is pressed
//        if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
//            // apply force to centre parametrs (xforce, yforce,
//            this.getBody().applyForceToCenter(0, 20, true);
//
//        }
    }
}
