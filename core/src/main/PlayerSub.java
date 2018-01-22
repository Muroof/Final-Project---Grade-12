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
 *
 * @author awadb3223
 */
public class PlayerSub extends CharacterSuper {

    public PlayerSub(float x, float y, float xWidth, float yHeight, World world, Body squareBody, BodyDef bodyDef, PolygonShape shape, FixtureDef fixtureDef) {
        super(x, y, xWidth, yHeight, world, squareBody, bodyDef, shape, fixtureDef);

    }

    public void hasBeenSeenByLight(EnemySub enemy) {

    }
    
    @Override
    public void handleMovement(){
        // if right is pressed
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            // apply force to centre parametrs (xforce, yforce,
            this.getBody().applyForceToCenter(20, 10, true);
            
            
        }
        // if left is pressed
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            // apply force to centre parametrs (xforce, yforce,
            this.getBody().applyForceToCenter(-20, 10, true);
          
            
        }
        // if up is pressed
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            // apply force to centre parametrs (xforce, yforce,
            this.getBody().applyForceToCenter(0, 75, true);
        }
    }

}
