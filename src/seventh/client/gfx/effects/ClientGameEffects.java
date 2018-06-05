/*
 * see license.txt 
 */
package seventh.client.gfx.effects;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

import seventh.client.ClientGame;
import seventh.client.ClientTeam;
import seventh.client.gfx.Camera;
import seventh.client.gfx.Canvas;
import seventh.client.gfx.Colors;
import seventh.client.gfx.FrameBufferRenderable;
import seventh.client.gfx.ImageBasedLightSystem;
import seventh.client.gfx.LightSystem;
import seventh.client.gfx.effects.particle_system.Emitter;
import seventh.client.gfx.effects.particle_system.Emitters;
import seventh.game.net.NetCtfGameTypeInfo;
import seventh.math.Rectangle;
import seventh.math.Vector2f;
import seventh.shared.SeventhConstants;
import seventh.shared.TimeStep;

/**
 * @author Tony
 *
 */
public class ClientGameEffects {

    
    private final Effects backgroundEffects, foregroundEffects;
    private final LightSystem lightSystem;
    private final ExplosionEffect explosions;
    private final HurtEffect hurtEffect;

    private final List<FrameBufferRenderable> frameBufferRenderables;
    private final Sprite frameBufferSprite;
    
    private final TankTrackMarks[] trackMarks;
    
    private final Emitter[] playerBloodEmitters;
    private final BulletCasingEffect[] bulletCasings;
    
    private NetCtfGameTypeInfo ctfInfo;
    
    
    /**
     */
    public ClientGameEffects(Random random) {
        
        this.frameBufferRenderables = new ArrayList<>();
        this.hurtEffect = new HurtEffect();
        
        this.backgroundEffects = new Effects();
        this.foregroundEffects = new Effects();

        this.playerBloodEmitters = new Emitter[SeventhConstants.MAX_PLAYERS*3];
        for(int i = 0; i < this.playerBloodEmitters.length; i++) {
            this.playerBloodEmitters[i] = Emitters.newBloodEmitter(new Vector2f(), 5, 20_000, 30)                                                   
                                                  .kill();             
        }
        
        
        this.lightSystem = new ImageBasedLightSystem();        
        this.frameBufferRenderables.add(lightSystem);
        
        this.explosions = new ExplosionEffect(15, 800, 0.6f);
        this.frameBufferSprite = new Sprite();
        
        this.trackMarks = new TankTrackMarks[SeventhConstants.MAX_ENTITIES];
        
        this.bulletCasings = new BulletCasingEffect[256];
        for(int i = 0; i < this.bulletCasings.length; i++) {
            this.bulletCasings[i] = new BulletCasingEffect(random);
        }
    }
    
    public void addCtfInformation(NetCtfGameTypeInfo info) {
        this.ctfInfo = info;
    }
    
    public void clearCtfInformation() {
        this.ctfInfo = null;
    }
    
    public void spawnBulletCasing(Vector2f pos, float orientation) {
        for(int i = 0; i < this.bulletCasings.length; i++) {
            if(this.bulletCasings[i].isFree()) {
                this.bulletCasings[i].respawn(pos, orientation);
                this.backgroundEffects.addEffect(this.bulletCasings[i]);
                break;
            }
        }
    }
    
    public void spawnBloodSplatter(Vector2f pos) {
        for(int i = 0; i < this.playerBloodEmitters.length; i++) {
            Emitter emitter = this.playerBloodEmitters[i];
            if(!emitter.isAlive()) {            
                this.backgroundEffects.addEffect(emitter.reset().setPos(pos));
                break;
            }
        }
    }

    /**
     * @return the explosions
     */
    public ExplosionEffect getExplosions() {
        return explosions;
    }
    
    /**
     * @return the lightSystem
     */
    public LightSystem getLightSystem() {
        return lightSystem;
    }
    
    /**
     * @return the hurtEffect
     */
    public HurtEffect getHurtEffect() {
        return hurtEffect;
    }
    
    /**
     * Removes all lights
     */
    public void removeAllLights() {
        lightSystem.removeAllLights();
    }
    
    /**
     * Clear all the effects
     */
    public void clearEffects() {        
        explosions.deactiveAll();
        backgroundEffects.clearEffects();
        foregroundEffects.clearEffects();
        
        for(int i =0; i < trackMarks.length; i++) {
            if(trackMarks[i] != null) {
                trackMarks[i].clear();
                trackMarks[i] = null;
            }
        }
        
        for(int i = 0; i < this.playerBloodEmitters.length; i++) {         
            this.playerBloodEmitters[i].kill();
        }
        
        clearCtfInformation();
    }
    
    
    /**
     * Destroys these effects, releasing any resources
     */
    public void destroy() {
        clearEffects();
        removeAllLights();
        
        lightSystem.destroy();
        frameBufferRenderables.clear();
        
        for(int i = 0; i < this.playerBloodEmitters.length; i++) {         
            this.playerBloodEmitters[i].destroy();
            this.playerBloodEmitters[i] = null;
        }
    }
    
    /**
     * Adds an explosion
     * 
     * @param index
     * @param pos
     */
    public void addExplosion(ClientGame game, int index, Vector2f pos) {
        //explosions.activate(index, pos);
        this.foregroundEffects.addEffect(new Explosion(game, pos));
    }

    /**
     * Adds a background effect
     * 
     * @param effect
     */
    public void addBackgroundEffect(Effect effect) {
        this.backgroundEffects.addEffect(effect);
    }
    
    public void addLightSource(Vector2f pos) {
        this.lightSystem.newConeLight(pos);
    }
    
    /**
     * Adds a foreground effect
     * 
     * @param effect
     */
    public void addForegroundEffect(Effect effect) {
        this.foregroundEffects.addEffect(effect);
    }

    public void allocateTrackMark(int id) {
        this.trackMarks[id] = new TankTrackMarks(256);
    }
    
    public void addTankTrackMark(int id, Vector2f pos, float orientation) {
        if(this.trackMarks[id]==null) {
            this.trackMarks[id] = new TankTrackMarks(256*2);
        }
        
        this.trackMarks[id].add(pos, orientation);
    }
    
    /**
     * Updates the special effects, etc.
     * 
     * @param timeStep
     */
    public void update(TimeStep timeStep) {
        lightSystem.update(timeStep);
        
        backgroundEffects.update(timeStep);
        foregroundEffects.update(timeStep);
        explosions.update(timeStep);
                
        hurtEffect.update(timeStep);
        
        int size = frameBufferRenderables.size();
        for(int i = 0; i < size; i++) {
            FrameBufferRenderable r = this.frameBufferRenderables.get(i);
            r.update(timeStep);
        }
        
        for(int i =0; i < trackMarks.length; i++) {
            if(trackMarks[i] != null) {
                trackMarks[i].update(timeStep);
            }
        }
    }
    
    
    /**
     * Renders to the frame buffer
     * @param canvas
     */
    public void preRenderFrameBuffer(Canvas canvas, Camera camera, float alpha) {
        int size = this.frameBufferRenderables.size();
        if(size>0) {
            canvas.setDefaultTransforms();
            canvas.setShader(null);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
            canvas.begin();

            for(int i = 0; i < size; i++) {
                FrameBufferRenderable r = this.frameBufferRenderables.get(i);
                r.frameBufferRender(canvas, camera, alpha);
            }
            
            canvas.end();
        }
    }
    
    public void postRenderFrameBuffer(Canvas canvas, Camera camera, float alpha) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        canvas.setDefaultTransforms();
        canvas.setShader(null);
        
        for(int i = 0; i < this.frameBufferRenderables.size(); ) {
            FrameBufferRenderable r = this.frameBufferRenderables.get(i);
            if(r.isExpired()) {
                this.frameBufferRenderables.remove(i);
            }
            else {
                r.render(canvas, camera, alpha);
                i++;
            }
        }
    }
    
    
    public void renderFrameBuffer(Canvas canvas, Camera camera, float alpha) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        canvas.setDefaultTransforms();

        frameBufferSprite.setRegion(canvas.getFrameBuffer());
        
        canvas.begin();
        {
            canvas.getFrameBuffer().bind();
            {
                ShaderProgram shader = ExplosionEffectShader.getInstance().getShader();
                
                canvas.setShader(shader);
                canvas.drawImage(frameBufferSprite, 0, 0, 0x0);
            }
        }
        canvas.end();
    }
    
        
    public void renderBackground(Canvas canvas, Camera camera, float alpha) {
        backgroundEffects.render(canvas, camera, alpha);
        
        for(int i =0; i < trackMarks.length; i++) {
            if(trackMarks[i] != null) {
                trackMarks[i].render(canvas, camera, alpha);
            }
        }
        
        if(this.ctfInfo != null) {            
            renderCtfBase(canvas, camera, alpha, this.ctfInfo.axisHomeBase, Colors.setAlpha(ClientTeam.AXIS.getColor(), 50));
            renderCtfBase(canvas, camera, alpha, this.ctfInfo.alliedHomeBase, Colors.setAlpha(ClientTeam.ALLIES.getColor(), 50));
        }
    }
    
    private void renderCtfBase(Canvas canvas, Camera camera, float alpha, Rectangle base, Integer color) {
        Vector2f cam = camera.getRenderPosition(alpha);
        canvas.fillRect(base.x - cam.x, base.y - cam.y, base.width, base.height, color);
        canvas.drawRect(base.x - cam.x, base.y - cam.y, base.width, base.height, 0x1a000000);
    }
    
    public void renderForeground(Canvas canvas, Camera camera, float alpha) {
        foregroundEffects.render(canvas, camera, alpha);
    }
    
    public void renderLightSystem(Canvas canvas, Camera camera, float alpha) {
        lightSystem.render(canvas, camera, alpha);
    }        
    
    public void renderHurtEffect(Canvas canvas, Camera camera, float alpha) {
        this.hurtEffect.render(canvas, camera, alpha);
    }
}
