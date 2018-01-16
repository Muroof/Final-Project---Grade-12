/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 *
 * @author awadb3223
 */
public class CharacterSuper {

    //instance variables
    private World world;
    private Body squareBody;
    private float x;
    private float y;
    //displacement of x and y
    private float dx;
    private float dy;

    public CharacterSuper(float x, float y) {
        this.world = world;
        this.x = x;
        this.y = y;

        this.dx = dx;
        this.dy = dy;
    }

    public void create() {
        //DYNAMIC SQUARE BODY
        BodyDef squareBodyDef = new BodyDef();
        squareBodyDef.type = BodyDef.BodyType.DynamicBody;

        //set position of square
        squareBodyDef.position.set(40, 50);

        //create square body and add it to world
        squareBody = world.createBody(squareBodyDef);

        //create the sqaure shape
        PolygonShape squareBox = new PolygonShape();
        squareBox.setAsBox(20, 20);

        //create a fixture from the shape and add it to the body
        FixtureDef squareFixture = new FixtureDef();

        squareBody.createFixture(squareBox, 2.0f);
    }
}
