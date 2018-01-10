package com.mygdx.game;

import box2dLight.ConeLight;
import box2dLight.DirectionalLight;
import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MainClass extends ApplicationAdapter {

    SpriteBatch batch;
    private World world;
    private Stage stage;
    private Box2DDebugRenderer debugRenderer;

    private RayHandler rayHandler;

    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        world = new World(new Vector2(0, -3), true);
       
        batch = new SpriteBatch();

        Gdx.input.setInputProcessor(stage);
        float ratio = (float) (Gdx.graphics.getWidth()) / (float) (Gdx.graphics.getHeight());

        stage = new Stage(new ScreenViewport());
        stage.getCamera().position.set(0, 0, 10);
        stage.getCamera().lookAt(0, 0, 0);
        stage.getCamera().viewportWidth = 10;
        stage.getCamera().viewportHeight = 10 / ratio;
        debugRenderer = new Box2DDebugRenderer();

        rayHandler = new RayHandler(world);
        rayHandler.setAmbientLight(0.1f, 0.1f, 0.1f, 1f);
        rayHandler.setBlurNum(3);

        //POINT LIGHTS
        //PointLight pl2 = new PointLight(rayHandler, 128, Color.BLUE, 10, 5, 2);
        //PointLight pl = new PointLight(rayHandler, 128, Color.RED, 10, -5, 2);
        
        //DIRECTIONAL LIGHTS
        //DirectionalLight p3 = new DirectionalLight(rayHandler, 20, Color.YELLOW, 90);

        //CONE LIGHTS
        ConeLight p4 = new ConeLight(rayHandler, 5, Color.YELLOW, 5, -5, 0, 0, 45);
        //turns on light cone
        p4.setStaticLight(true);
        // softens the light so its less harsh
        p4.setSoft(false);
        
        ConeLight p5 = new ConeLight(rayHandler, 5, Color.BLUE, 5, 5, 0, 180, 45);
        p5.setStaticLight(true);
        p5.setSoft(false);

        //enables shadows
        rayHandler.setShadows(true);
        //turns on point light
//        pl.setStaticLight(false);
//        pl.setSoft(true);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        world.step(Gdx.graphics.getDeltaTime(), 6, 2);
        stage.draw();

        debugRenderer.render(world, stage.getCamera().combined);
        
        rayHandler.setCombinedMatrix(stage.getCamera().combined, 0, 0, 1, 1);
        rayHandler.updateAndRender();
    }

    @Override
    public void dispose() {
        batch.dispose();
        rayHandler.dispose();
    }
}
