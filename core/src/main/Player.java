/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

/**
 * @author Beshoy
 * @author Kiran  
 * @author Maloof
 */
public class Player extends Character {


    public Player(float x, float y) {
        super(x, y);

    }
    
    @Override
    public void create(){
        BodyDef playerBdef = new BodyDef();
        playerBdef.position.set(x, y);
        playerBdef.type = BodyType.DynamicBody;
        Body playerBody = world.createBody(playerBdef);
        
        PolygonShape sqaure = new PolygonShape();
        sqaure.setAsBox(50, 5);
        
        FixtureDef playerFdef = new FixtureDef();
        playerFdef.shape = sqaure;
        playerBody.createFixture(playerFdef);
        
        
        
    }

}
