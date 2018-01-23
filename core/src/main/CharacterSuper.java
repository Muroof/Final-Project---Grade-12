/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import static handlers.box2dvairables.PPM;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author Beshoy
 * @author Kiran
 * @author Maloof
 */
public abstract class CharacterSuper {

    // instance variables
    private World world;
    private Body characterBody;
    private BodyDef characterBodyDef;
    private PolygonShape characterShape;
    private FixtureDef characterFixtureDef;
    private float characterX;
    private float characterY;

    // instace variables for the width and height of a character
    private float xWidth;
    private float yHeight;

    /**
     * constructor for Character
     *
     * @param x
     * @param y
     * @param xWidth
     * @param yHeight
     * @param world
     * @param characterBody
     * @param characterBodyDef
     * @param characterShape
     * @param characterFixtureDef
     */
    public CharacterSuper(float x, float y, float xWidth, float yHeight, World world, Body characterBody, BodyDef characterBodyDef, PolygonShape characterShape, FixtureDef characterFixtureDef) {
        // instance variables are set to the varaibles found in the parameters required
        this.world = world;
        this.characterX = x / PPM;
        this.characterY = y / PPM;
        this.xWidth = xWidth / PPM;
        this.yHeight = yHeight / PPM;

        // creates a bodyDef for a character
        characterBodyDef = new BodyDef();
        // sets this bodyDef to a dynamic body, as forces will act on the player
        characterBodyDef.type = BodyDef.BodyType.DynamicBody;
        // set position of the bodyDef
        characterBodyDef.position.set(this.characterX, this.characterY);

        // create a body, and pass in the characterBodyDef
        characterBody = world.createBody(characterBodyDef);

        // create the sqaure polygon
        characterShape = new PolygonShape();
        // create a FixtureDef for the character
        characterFixtureDef = new FixtureDef();
        // make the characterFixtureDef's shape equal to the shape we created earlier: characterShape
        characterFixtureDef.shape = characterShape;
        // set the characterFixtureDef's density and friction
        characterFixtureDef.density = 0.8f;
        characterFixtureDef.friction = 0f;

        // set the charaterShape as a box
        characterShape.setAsBox(this.xWidth, this.yHeight);

        // pass in the characterFixtureDef as the fixture for our characterBody
        characterBody.createFixture(characterFixtureDef);

        // make instance variables equal the parameters
        this.characterBody = characterBody;
        this.characterBodyDef = characterBodyDef;
        this.characterShape = characterShape;
        this.characterFixtureDef = characterFixtureDef;
    }

    /**
     *
     * @return
     */
    public Body getBody() {
        return this.characterBody;
    }

    /**
     *
     * @return
     */
    public float getXPosition() {
        return this.characterX;
    }

    /**
     *
     * @param x
     */
    public void updateXPosition(float x) {
        this.characterX = x;
    }

    /**
     *
     * @param y
     */
    public void updateYPosition(float y) {
        this.characterY = y;
    }

    /**
     *
     * @return
     */
    public float getYPosition() {
        return this.characterY;
    }

    /**
     *
     * @return
     */
    public float getXWidth() {
        return this.xWidth;
    }

    /**
     *
     * @return
     */
    public float getYHeight() {
        return this.yHeight;
    }

    /**
     *
     * @return
     */
    public FixtureDef getFixtureDef() {
        return this.characterFixtureDef;
    }

    /**
     *
     */
    public abstract void handleMovement();

}
