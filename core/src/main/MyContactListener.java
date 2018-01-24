/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

/**
 * @author Beshoy
 * @author Kiran
 * @author Maloof
 */
public class MyContactListener implements ContactListener {

    private boolean playerOnGround;

    /**
     * called when two fixtures start to collide
     *
     * @param c
     */
    @Override
    public void beginContact(Contact c) {

        Fixture floor = c.getFixtureA();
        Fixture player = c.getFixtureB();

        if (player.getUserData() != null && player.getUserData().equals("player")) {
            playerOnGround = true;
        }

        if (floor.getUserData() != null && floor.getUserData().equals("floor")) {
            playerOnGround = true;
        }

    }

    /**
     * called when two fixtures no longer collide
     *
     * @param c
     */
    @Override
    public void endContact(Contact c) {
        Fixture floor = c.getFixtureA();
        Fixture player = c.getFixtureB();

        if (player.getUserData() != null && player.getUserData().equals("player")) {
            playerOnGround = false;
        }

        if (floor.getUserData() != null && floor.getUserData().equals("floor")) {
            playerOnGround = false;
        }

    }

    public boolean isPlayerOnGround() {
        return playerOnGround;
    }

    @Override
    public void preSolve(Contact c, Manifold m) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void postSolve(Contact c, ContactImpulse ci) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
