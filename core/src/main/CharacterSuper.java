/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
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

    private MyContactListener cl;

    /**
     * constructor for Character
     *
     * @param x the x coordinate of the character
     * @param y the y coordinate of the character
     * @param xWidth the width of the character
     * @param yHeight the height of the character
     * @param world the world character is being rendered in
     * @param characterBody the body of the character itself
     * @param characterBodyDef the body definition of the character
     * @param characterShape the shape if the character
     * @param characterFixtureDef the fixture definition of the character
     *
     */
    public CharacterSuper(float x, float y, float xWidth, float yHeight, World world, Body characterBody, BodyDef characterBodyDef, PolygonShape characterShape, FixtureDef characterFixtureDef) {
        // instance variables are set to the varaibles found in the parameters required
        this.world = world;
        this.characterX = x / PPM;
        this.characterY = y / PPM;
        this.xWidth = xWidth / PPM;
        this.yHeight = yHeight / PPM;
        // intiialize contact listener
        cl = new MyContactListener();
        // set the world to have the contact listener
        world.setContactListener(cl);

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
        characterBody.createFixture(characterFixtureDef).setUserData("player");

        // make instance variables equal the parameters
        this.characterBody = characterBody;
        this.characterBodyDef = characterBodyDef;
        this.characterShape = characterShape;
        this.characterFixtureDef = characterFixtureDef;

    }

    /**
     *
     * @return the body of the character
     */
    public Body getBody() {
        return this.characterBody;
    }

    /**
     *
     * @return the x position of the character
     */
    public float getXPosition() {
        return this.characterX;
    }

    /**
     *
     * @param x updated x position of the character
     */
    public void updateXPosition(float x) {
        this.characterX = x;
    }

    /**
     *
     * @param y updated y position of character
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
     * @return the width
     */
    public float getXWidth() {
        return this.xWidth;
    }

    /**
     *
     * @return the height of the character
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
     * @return
     */
    public MyContactListener getMyContactListener() {
        return cl;
    }

    /**
     *
     */
    public abstract void handleMovement();

}
