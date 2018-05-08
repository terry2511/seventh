/*
 * see license.txt 
 */
package seventh.client.gfx;

import seventh.client.ClientTeam;
import seventh.client.network.ClientConnection;
import seventh.game.entities.Entity.Type;
import seventh.math.Rectangle;
import seventh.math.Vector2f;
import seventh.network.messages.PlayerSwitchWeaponClassMessage;
import seventh.shared.EventDispatcher;
import seventh.ui.Button;
import seventh.ui.Label;
import seventh.ui.Label.TextAlignment;
import seventh.ui.Widget;
import seventh.ui.events.ButtonEvent;
import seventh.ui.events.OnButtonClickedListener;

/**
 * A dialog box to pick the weapon class.
 * 
 * @author Tony
 *
 */
public class WeaponClassDialog extends Widget {

    private ClientTeam team;

    private Label title;
    private Theme theme;
    
    private static final int NUMBER_OF_WEAPON_CLASSES = 7;
    
    private Button[] weaponClasses;
    private Label[] weaponClassDescriptions;
    private Button cancel;
    
    private ClientConnection connection;
    private InGameOptionsDialog owner;
    /**
     */
    public WeaponClassDialog(InGameOptionsDialog owner, ClientConnection network, Theme theme) {
        super(new EventDispatcher());
        
        this.owner = owner;
        this.connection = network;
        
        this.team = ClientTeam.ALLIES;
        this.theme = theme;

        this.weaponClasses = new Button[NUMBER_OF_WEAPON_CLASSES];
        this.weaponClassDescriptions = new Label[NUMBER_OF_WEAPON_CLASSES];
        
        createUI();
    }
    
    /* (non-Javadoc)
     * @see seventh.ui.Widget#setBounds(seventh.math.Rectangle)
     */
    @Override
    public void setBounds(Rectangle bounds) {    
        super.setBounds(bounds);
        
        createUI();
    }
    
    /**
     * @return the team
     */
    public ClientTeam getTeam() {
        return team;
    }
    
    /**
     * @return the weaponClasses
     */
    public Button[] getWeaponClasses() {
        return weaponClasses;
    }
    
    /**
     * @return the weaponClassDescriptions
     */
    public Label[] getWeaponClassDescriptions() {
        return weaponClassDescriptions;
    }
    
    private void createUI() {
        destroyChildren();
        
        Rectangle bounds = getBounds();
        
        setupTitleLabel(bounds);
                    
        refreshButtons();

        setupCancelButton(bounds);
                
        addWidget(cancel);
        addWidget(title);
    }

    /**
     * @param bounds
     */
    private void setupTitleLabel(final Rectangle bounds) {
        this.title = new Label("Select a Weapon");
        this.title.setTheme(theme);
        //this.title.setForegroundColor(0xffffffff);
        this.title.setBounds(new Rectangle(bounds));
        this.title.getBounds().height = 15;
        this.title.getBounds().y += 20;
        this.title.setFont(theme.getSecondaryFontName());
        this.title.setHorizontalTextAlignment(TextAlignment.CENTER);
        this.title.setTextSize(22);
    }
    
    /**
     * @param bounds
     */
    private void setupCancelButton(final Rectangle bounds) {
        this.cancel = new Button();        
        this.cancel.setText("Cancel");
        this.cancel.setBounds(new Rectangle(0,0,100,40));
        this.cancel.getBounds().centerAround(bounds.x + 205, bounds.y + bounds.height - 10);
        this.cancel.setEnableGradiant(false);
        this.cancel.setTheme(theme);
        this.cancel.getTextLabel().setFont(theme.getSecondaryFontName());
        this.cancel.getTextLabel().setForegroundColor(theme.getForegroundColor());
        this.cancel.setTextSize(22);
        this.cancel.setHoverTextSize(26);
        this.cancel.addOnButtonClickedListener(new OnButtonClickedListener() {
            
            @Override
            public void onButtonClicked(ButtonEvent event) {
                owner.close();
            }
        });
    }
        
    private Vector2f refreshButtons() {
        
        initWeaponClasses();
        
        return setupWeaponClasses();
    }

    private void initWeaponClasses() {
        for(int i = 0; i < weaponClasses.length; i++) {
            if( this.weaponClasses[i] != null ) {
                removeWidget(weaponClasses[i]);
                this.weaponClasses[i].destroy();
                this.weaponClasses[i] = null;
                
                removeWidget(this.weaponClassDescriptions[i]);
                this.weaponClassDescriptions[i].destroy();
                this.weaponClassDescriptions[i] = null;
            }
        }
    }

    private Vector2f setupWeaponClasses() {
        Rectangle bounds = getBounds();
        
        Vector2f pos = new Vector2f();
        pos.x = bounds.x + 120;
        pos.y = bounds.y + 50;
        
        int yInc = 50;
        
        switch(team) {            
            case AXIS:
                for (int weaponClassIndex = 0; weaponClassIndex < NUMBER_OF_WEAPON_CLASSES; weaponClassIndex++) {
                    setupAXISWeaponClass(weaponClassIndex, pos);
                    if (weaponClassIndex < NUMBER_OF_WEAPON_CLASSES - 1) {
                        pos.y += yInc;
                    }
                }
                break;        
            case ALLIES:
            default:
                for (int weaponClassIndex = 0; weaponClassIndex < NUMBER_OF_WEAPON_CLASSES; weaponClassIndex++) {
                    setupALLIESWeaponClass(weaponClassIndex, pos);
                    if (weaponClassIndex < NUMBER_OF_WEAPON_CLASSES - 1) {
                        pos.y += yInc;
                    }
                }
                break;   
        }
                
        return pos;
    }
    
    private static final Type[] AXIS_WEAPON_TYPES = {
            // AXIS only
            Type.MP40,
            Type.MP44,
            Type.KAR98,
            
            // AXIS, ALLIES share
            Type.RISKER,
            Type.SHOTGUN,
            Type.ROCKET_LAUNCHER,
            Type.FLAME_THROWER
    };
    
    private static final Type[] ALLIES_WEAPON_TYPES = {
            // ALLIES only
            Type.THOMPSON,
            Type.M1_GARAND,
            Type.SPRINGFIELD,
            
            // AXIS, ALLIES share
            Type.RISKER,
            Type.SHOTGUN,
            Type.ROCKET_LAUNCHER,
            Type.FLAME_THROWER
    };
    
    private void setupAXISWeaponClass(int weaponClassIndex, Vector2f pos) {
        this.weaponClasses[weaponClassIndex] = setupButton(pos, AXIS_WEAPON_TYPES[weaponClassIndex]);
        this.weaponClassDescriptions[weaponClassIndex] = setupLabel(pos, AXIS_WEAPON_TYPES[weaponClassIndex]);
    }
    
    private void setupALLIESWeaponClass(int weaponClassIndex, Vector2f pos) {
        this.weaponClasses[weaponClassIndex] = setupButton(pos, ALLIES_WEAPON_TYPES[weaponClassIndex]);
        this.weaponClassDescriptions[weaponClassIndex] = setupLabel(pos, ALLIES_WEAPON_TYPES[weaponClassIndex]);
    }
    
    private String getClassDescription(Type type) {
        String message = "";
        switch(type) {
            case THOMPSON:
                message = new ThompsonDescription().getDescription();
                break;
            case M1_GARAND:                
                message = new M1GarandDescription().getDescription();
                break;
            case SPRINGFIELD:                
                message = new SpringfieldDescription().getDescription();
                break;
            case MP40:
                message = new Mp40Description().getDescription();
                break;
            case MP44:                
                message = new Mp44Description().getDescription();
                break;
            case KAR98:                
                message = new Kar98Description().getDescription();
                break;
            case RISKER:                
                message = new RiskerDescription().getDescription();
                break;
            case SHOTGUN:                
                message = new ShotgunDescription().getDescription();
                break;
            case ROCKET_LAUNCHER:                
                message = new RocketLauncherDescription().getDescription();
                break;
            case FLAME_THROWER:                
                message = new FlameThrowerDescription().getDescription();
                break;                
            default:;
        }
        
        return message;
    }
    
    
    private Button setupButton(Vector2f pos, final Type type) {
        final Button btn = new Button();
        btn.setBounds(new Rectangle((int)pos.x, (int)pos.y, 320, 40));
        btn.setBorder(false);
//        btn.setText(getClassDescription(type));
//        btn.setTextSize(12);
//        btn.setHoverTextSize(12);
//        btn.getTextLabel().setForegroundColor(this.theme.getForegroundColor());
//        btn.getTextLabel().setHorizontalTextAlignment(TextAlignment.LEFT);
//        btn.getTextLabel().setVerticalTextAlignment(TextAlignment.BOTTOM);
                
        btn.addOnButtonClickedListener(new OnButtonClickedListener() {
            
            @Override
            public void onButtonClicked(ButtonEvent event) {
                if(connection.isConnected()) {
                    PlayerSwitchWeaponClassMessage msg = new PlayerSwitchWeaponClassMessage();
                    msg.weaponType = type;
                    connection.getClientProtocol().sendPlayerSwitchWeaponClassMessage(msg);
                }
                
                owner.close();
            }
        });
        
        addWidget(btn);
        
        return btn;
    }
    
    private Label setupLabel(Vector2f pos, final Type type) {
        Label lbl = new Label(this.getClassDescription(type));
        lbl.setBounds(new Rectangle((int)pos.x + 80, (int)pos.y, 220, 60));
        lbl.setTextSize(12);
        lbl.setForegroundColor(this.theme.getForegroundColor()); //0xff363e0f
        lbl.setShadow(false);
        lbl.setFont("Consola");
        lbl.setHorizontalTextAlignment(TextAlignment.LEFT);
        lbl.setVerticalTextAlignment(TextAlignment.TOP);
        lbl.hide();
        
        addWidget(lbl);
        
        return lbl;
    }
    
    /**
     * @return the cancel button
     */
    public Button getCancelBtn() {
        return cancel;
    }
    
    /**
     * @return the title
     */
    public Label getTitle() {
        return title;
    }

    /**
     * @param team the team to set
     */
    public void setTeam(ClientTeam team) {
        this.team = team;
        refreshButtons();
        
        if(this.isDisabled()) {
            owner.close();
        }
    }
    
    @Override
    public void show() {     
        super.show();
        
        for(Label lbl : weaponClassDescriptions) {
            lbl.hide();
        }
    }
}
