/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

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
public abstract class EnvironmentSuper {

    // instance variables
    private World enviroWorld;
    private Body platformBody;
    private BodyDef platformBodyDef;
    private PolygonShape platformShape;
    private FixtureDef platformFixtureDef;
    private float platformX;
    private float platformY;

    // instace variables for the width and length of a platform/floor
    private float platformWidth;
    private float platformLength;

    //Environment Superclass constructor
    public EnvironmentSuper(float platformX, float platformY, float platformWidth, float platformLength, World enviroworld, Body platformBody, BodyDef platformBodyDef, PolygonShape platformShape, FixtureDef platformFixtureDef) {
        // instance variables initialized
        this.enviroWorld = enviroWorld;
        this.platformBody = platformBody;
        this.platformBodyDef = platformBodyDef;
        this.platformShape = platformShape;
        this.platformFixtureDef = platformFixtureDef;
        this.platformX = platformX;
        this.platformY = platformY;
        this.platformWidth = platformWidth;
        this.platformLength = platformLength;

        //**CREATE RECTANGULAR PLATFORM**
        platformBodyDef = new BodyDef();
        // sets this bodyDef to a STATIC body; immovable
        platformBodyDef.type = BodyDef.BodyType.StaticBody;
        // set position of the bodyDef
        platformBodyDef.position.set(this.platformX, this.platformY);

        platformBody = enviroworld.createBody(platformBodyDef);

        // create the sqaure polygon
        platformShape = new PolygonShape();
        // create a FixtureDef for the character
        platformFixtureDef = new FixtureDef();
        // make the characterFixtureDef's shape equal to the shape we created earlier: characterShape
        platformFixtureDef.shape = platformShape;

        // set the charaterShape as a box
        platformShape.setAsBox(this.platformWidth, this.platformLength);

        // pass in the characterFixtureDef as the fixture for our characterBody
        platformBody.createFixture(platformFixtureDef).setUserData("platform");

    }
}
