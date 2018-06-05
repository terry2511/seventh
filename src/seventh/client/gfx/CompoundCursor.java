/*
 * see license.txt 
 */
package seventh.client.gfx;

import seventh.math.Rectangle;
import seventh.math.Vector2f;
import seventh.shared.TimeStep;

/**
 * @author Tony
 *
 */
public class CompoundCursor extends Cursor {

    private Cursor a,b;
    private Cursor active;
    
    /**
     * @param bounds
     */
    public CompoundCursor(Cursor a, Cursor b) {
        super(a.getBounds());

        this.a = a;
        this.b = b;
        
        this.active = a;
    }
    
    /**
     * @return the active
     */
    public Cursor getActive() {
        return active;
    }
    
    public CompoundCursor activateA() {
        this.active = a;
        return this;
    }
    
    public CompoundCursor activateB() {
        this.active = b;
        return this;
    }
    
    @Override
    public boolean isClampEnabled() {    
        return this.active.isClampEnabled();
    }
    
    @Override
    public void setClampEnabled(boolean isClampEnabled) {    
        this.a.setClampEnabled(isClampEnabled);
        this.b.setClampEnabled(isClampEnabled);
    }
    
    @Override
    public boolean isInverted() {    
        return this.active.isInverted();
    }
    
    @Override
    public void setInverted(boolean isInverted) {
        this.a.setInverted(isInverted);
        this.b.setInverted(isInverted);
    }
    
    @Override
    public float getAccuracy() {
        return this.active.getAccuracy();
    }
    
    @Override
    public Rectangle getBounds() {
        return this.active.getBounds();
    }
    
    @Override
    public int getColor() {    
        return this.active.getColor();
    }
    
    @Override
    public Vector2f getCursorPos() {    
        return this.active.getCursorPos();
    }
        
    @Override
    public Vector2f getPreviousCursorPos() {    
        return this.active.getPreviousCursorPos();
    }
    
    @Override
    public float getMouseSensitivity() {    
        return this.active.getMouseSensitivity();
    }
    
    @Override
    public int getX() {    
        return this.active.getX();
    }
    
    @Override
    public int getY() {     
        return this.active.getY();
    }
    
    @Override
    public boolean isVisible() {     
        return this.active.isVisible();
    }
    

    @Override
    public void centerMouse() {
        this.a.centerMouse();
        this.b.centerMouse();
    }
    
    
    @Override
    public void moveByDelta(float dx, float dy) {    
        this.a.moveByDelta(dx, dy);
        this.b.moveByDelta(dx, dy);
    }
    
    @Override
    public void moveTo(int x, int y) {
        this.a.moveTo(x, y);
        this.b.moveTo(x, y);
    }
        
    @Override
    public void snapTo(int x, int y) {    
        this.a.snapTo(x, y);
        this.b.snapTo(x, y);
    }
    
    @Override
    public void snapTo(Vector2f screenPos) {    
        this.a.snapTo(screenPos);
        this.b.snapTo(screenPos);
    }
    
    @Override
    public void setAccuracy(float accuracy) {
        this.active.setAccuracy(accuracy);
    }
    
    @Override
    public void setColor(int color) {
        this.active.setColor(color);
    }
    
    @Override
    public void setMouseSensitivity(float mouseSensitivity) {
        this.a.setMouseSensitivity(mouseSensitivity);
        this.b.setMouseSensitivity(mouseSensitivity);
    }
    
    @Override
    public void touchAccuracy() {
        this.active.touchAccuracy();
    }
    
    @Override
    public void setVisible(boolean isVisible) {
        this.a.setVisible(isVisible);
        this.b.setVisible(isVisible);
    }
    
    @Override
    public void update(TimeStep timeStep) {
        this.active.update(timeStep);
    }

    @Override
    public void render(Canvas canvas) {
        this.active.render(canvas);
    }

    @Override
    protected void doRender(Canvas canvas) {       
    }
}

