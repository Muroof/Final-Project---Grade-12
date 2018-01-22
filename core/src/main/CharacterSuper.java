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
import static handlers.box2dvairables.PPM;

/**
 *
 * @author awadb3223
 */
public abstract class CharacterSuper {

    //instance variables
    private World world;
    private Body squareBody;
    private BodyDef bodydDef;
    private PolygonShape shape;
    private FixtureDef fixtureDef;
    private float x;
    private float y;
    // wodth and heights of the enemies!
    private float xWidth;
    private float yHeight;
    //displacement of x and y
   
    public CharacterSuper(float x, float y,float xWidth, float yHeight, World world, Body squareBody, BodyDef bodyDef, PolygonShape shape, FixtureDef fixtureDef) {
        this.world = world;
        this.x = x/PPM;
        this.y = y/PPM;
        this.xWidth = xWidth/PPM;
                this.yHeight = yHeight/PPM;

        
        
        
        bodyDef = new BodyDef();
bodyDef.type = BodyDef.BodyType.DynamicBody;
        //set position of square
        bodyDef.position.set(this.x, this.y);
        
        squareBody = world.createBody(bodyDef);
//create the sqaure shape
        shape = new PolygonShape();
        fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.8f;
        fixtureDef.friction = 5f;
        shape.setAsBox(this.xWidth, this.yHeight);
        
        squareBody.createFixture(fixtureDef);
        this.squareBody = squareBody;
        this.bodydDef = bodyDef;
        this.shape = shape;
        this.fixtureDef = fixtureDef;
    }
    
    public Body getBody(){
        return this.squareBody;
    }
    
    
    public float getXPosition(){
        return this.x;
    }
    
    public void updateXPosition(float x){
        this.x = x;
    }
    
    public void updateYPosition(float y){
        this.y = y;
    }
    public float getYPosition(){
        return this.y;
    }
    public float getXWidth(){
        return this.xWidth;
    }
    public float getYHeight(){
        return this.yHeight;
    }
    public FixtureDef getFixtureDef(){
        return this.fixtureDef;
    }
    
    public abstract void handleMovement();

    
    
    
}
