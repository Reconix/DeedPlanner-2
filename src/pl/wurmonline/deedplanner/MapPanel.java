package pl.wurmonline.deedplanner;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLJPanel;
import pl.wurmonline.deedplanner.data.Map;
import pl.wurmonline.deedplanner.graphics.FPPCamera;
import pl.wurmonline.deedplanner.graphics.UpCamera;
import pl.wurmonline.deedplanner.util.jogl.GLInit;

public class MapPanel extends GLJPanel {

    private Map map;
    
    private final MainLoop loop;
    
    private final FPPCamera fppCamera;
    private final UpCamera upCamera;
    
    public MapPanel() {
        this(GLInit.prepareGLCapabilities());
    }
    
    public MapPanel(GLCapabilities capabilities) {
        super(capabilities);
        
        map = new Map(50, 50);
        
        fppCamera = new FPPCamera(this);
        upCamera = new UpCamera(this);
        
        loop = new MainLoop(this);
    }
    
    public MainLoop getLoop() {
        return loop;
    }
    
    public FPPCamera getFPPCamera() {
        return fppCamera;
    }
    
    public UpCamera getUpCamera() {
        return upCamera;
    }
    
    public Map getMap() {
        return map;
    }
    
    public void setMap(Map map) {
        loop.syncAndExecute(() -> {
            for (int i=0; i<map.getWidth(); i++) {
                for (int i2=0; i2<map.getHeight(); i2++) {
                    map.getTile(i, i2).getGround().destroy();
                }
            }
            this.map = map;
        });
    }
    
}