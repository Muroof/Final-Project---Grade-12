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
public class PlatformAndFloorSub extends EnvironmentSuper {

    /**
     *
     * @param platformX the x coordinate of the platform
     * @param platformY the y coordinate of the platform
     * @param platformWidth the width of the plstform
     * @param platformLength the length of the platform
     * @param enviroworld the world
     * @param platformBody the body of the platform
     * @param platformBodyDef the body defintition of the platform
     * @param platformShape the shape of the platform
     * @param platformFixtureDef the fixture definition of the platform
     */
    public PlatformAndFloorSub(float platformX, float platformY, float platformWidth, float platformLength, World enviroworld, Body platformBody, BodyDef platformBodyDef, PolygonShape platformShape, FixtureDef platformFixtureDef) {
        // call up the super class
        super(platformX, platformY, platformWidth, platformLength, enviroworld, platformBody, platformBodyDef, platformShape, platformFixtureDef);
    }

}
