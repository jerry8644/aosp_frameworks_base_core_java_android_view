package android.view;

/**
 * This service is responsibile for monitoring scrolling operations
 */
import java.util.Map;

interface IScrollMonitorService {
    boolean setFrameRate(float frameRate);
    boolean setModel(in Map speedToFR);
    void notifyScrollStopped();
}