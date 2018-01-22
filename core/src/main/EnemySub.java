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
public class EnemySub extends CharacterSuper{

    public EnemySub(float x, float y, float xWidth, float yHeight, World world, Body squareBody, BodyDef bodyDef, PolygonShape shape, FixtureDef fixtureDef) {
        
        super(x, y, xWidth, yHeight, world, squareBody, bodyDef, shape, fixtureDef);
    }
 
    
    
    
    
    
    @Override
    public void handleMovement(){
        if(Gdx.input.isKeyPressed(Input.Keys.Q)){
            this.getBody().applyForceToCenter(-20,0,true);
        }
        
        while(this.getXPosition()>10&&(this.getYPosition()==60)){
            this.getBody().applyForceToCenter(2000, 0,true);
        }
    }
}
