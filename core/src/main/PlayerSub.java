/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

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

    public PlayerSub(float x, float y, World world, Body squareBody) {
        super(x, y, world, squareBody);

    }

    @Override
    public void createBody() {
        
    }

    @Override
    public void light() {
        
    }
    
    
}