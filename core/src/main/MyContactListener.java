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

    private int numberOfPlayerFloorCollisions;

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
            numberOfPlayerFloorCollisions++;
            System.out.println("player hit floor");
        }

        if (floor.getUserData() != null && floor.getUserData().equals("floor")) {
            numberOfPlayerFloorCollisions++;
            System.out.println("floor?");

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
            numberOfPlayerFloorCollisions--;
            System.out.println("player left floor");
        }

        if (floor.getUserData() != null && floor.getUserData().equals("floor")) {
            numberOfPlayerFloorCollisions--;
        }

    }

    public boolean isPlayerOnGround() {
        return numberOfPlayerFloorCollisions > 0;
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
