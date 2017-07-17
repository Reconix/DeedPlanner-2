package pl.wurmonline.deedplanner;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.media.opengl.*;
import javax.media.opengl.awt.GLJPanel;
import pl.wurmonline.deedplanner.data.Map;
import pl.wurmonline.deedplanner.graphics.*;
import pl.wurmonline.deedplanner.util.jogl.GLInit;

public class MapPanel extends GLJPanel implements ComponentListener {

    private Map map;
    private Map destroyingMap;
    
    private final MainLoop loop;
    
    private final FPPCamera fppCamera;
    private final UpCamera upCamera;
    
    public MapPanel() {
        this(GLInit.getCapabilities());
    }
    
    public MapPanel(GLCapabilities capabilities) {
        super(capabilities);
        addComponentListener(this);
        
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
        destroyingMap = map;
        loop.syncAndExecute(() -> {
            this.map = map;
        });
    }
    
    public Map getAndClearDestroyingMap() {
        Map map = destroyingMap;
        destroyingMap = null;
        return map;
    }

    public void componentResized(ComponentEvent e) {
        Globals.glWindowWidth = getWidth();
        Globals.glWindowHeight = getHeight();
        Globals.glAspectRatio = (float)getWidth()/(float)getHeight();
    }

    public void componentMoved(ComponentEvent e) {}

    public void componentShown(ComponentEvent e) {}

    public void componentHidden(ComponentEvent e) {}
    
}
