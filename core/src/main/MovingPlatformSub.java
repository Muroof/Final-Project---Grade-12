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
public class MovingPlatformSub extends EnvironmentSuper{

    public MovingPlatformSub(float platformX, float platformY, float platformWidth, float platformLength, World enviroworld, Body platformBody, BodyDef platformBodyDef, PolygonShape platformShape, FixtureDef platformFixtureDef) {
        super(platformX, platformY, platformWidth, platformLength, enviroworld, platformBody, platformBodyDef, platformShape, platformFixtureDef);
    }

}
