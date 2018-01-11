package com.mygdx.game;

import box2dLight.ConeLight;
import box2dLight.DirectionalLight;
import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MainClass extends ApplicationAdapter {

    OrthographicCamera camera;
    World world;
    float width, height;
    FPSLogger logger;
    Box2DDebugRenderer renderer;
    Body circleBody;
    
    
    
    @Override
    public void create() {
        
        width = Gdx.graphics.getWidth() / 5;
        height = Gdx.graphics.getHeight() / 5;
        
        camera = new OrthographicCamera (width, height);
        camera.position.set(width * 0.5f, height * 0.5f, 0);
        camera.update();
        
        world = new World(new Vector2(0,-9.8f), false);
        
        renderer = new Box2DDebugRenderer();
        
        logger = new FPSLogger();
        
        //DYNAMIC BODY
        BodyDef circleDef = new BodyDef();
        circleDef.type = BodyType.DynamicBody;
        circleDef.position.set(width / 2f, height / 2f);
        
        circleBody = world.createBody(circleDef);
        
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(5f);
        
        FixtureDef circleFixture = new FixtureDef();
        circleFixture.shape = circleShape;
        circleFixture.density = 0.04f;
        circleFixture.friction = 0.02f;
        circleFixture.restitution = 0.8f;
        
        circleBody.createFixture(circleFixture);
        
        //STATIC BODY A.K.A. THE GROUND
        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.position.set(0,3);
        
        Body groundBody = world.createBody(groundBodyDef);
        
        PolygonShape groundBox = new PolygonShape();
        groundBox.setAsBox(camera.viewportWidth * 2, 3.0f);
        
        groundBody.createFixture(groundBox, 0.0f);
        
    }

    @Override
    public void render() {
      Gdx.gl.glClearColor(0, 0, 0, 1);
      Gdx.gl.glClear(0);
      
      renderer.render(world, camera.combined);
      
      world.step(1/60f, 6, 2);
      
      logger.log();
    }

    @Override
    public void dispose() {
     world.dispose();
    }
}
