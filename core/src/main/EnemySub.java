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
public class EnemySub extends CharacterSuper {
// FOR SOME REASON, MOST LIKEY DUE TO BOX2D ONLY RECONGNIZNG TWO FIXTURES AT ONCE FOR MOVEMENT, THE ENEMIES COULD NOT BE MADE TO MOVE NORMALLY, SO WE LEFT THIS CLASS IN HERE JUST IN CASE, OTHER THAN HANDLE INPUT IT IS FUNCTIONA:

    /**
     * constructor for enemy
     *
     * @param enemyX
     * @param enemyY
     * @param xWidth
     * @param yHeight
     * @param world
     * @param enemyBody
     * @param enemyBodyDef
     * @param enemyShape
     * @param enemyFixtureDef
     */
    public EnemySub(float enemyX, float enemyY, float xWidth, float yHeight, World world, Body enemyBody, BodyDef enemyBodyDef, PolygonShape enemyShape, FixtureDef enemyFixtureDef) {
        // calls on super to retrieve paramaters required
        super(enemyX, enemyY, xWidth, yHeight, world, enemyBody, enemyBodyDef, enemyShape, enemyFixtureDef);
    }

    @Override
    public void handleMovement() {
        // this was here for testing reasons I'm assuming - Maloof

        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            this.getBody().applyForceToCenter(-20, 0, true);
        }

        while (this.getXPosition() > 10 && (this.getYPosition() == 60)) {
            this.getBody().applyForceToCenter(2000, 0, true);
        }
    }
}
