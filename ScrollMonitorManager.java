package android.view; 

import java.util.Map;

import android.annotation.SdkConstant;
import android.annotation.SystemService;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.Build;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Log;
import android.view.View;

@SystemService(Context.SCROLL_MONITOR_SERVICE)
public class ScrollMonitorManager {
    private static final String TAG = "ScrollMonitorManager";
    private static ScrollMonitorManager mScrollMonitorManager;
    
    private final IScrollMonitorService mService;
    
    public ScrollMonitorManager(IScrollMonitorService service) {
        mService = service;
    }
    public static synchronized ScrollMonitorManager getScrollMonitorManager() {
        if(mScrollMonitorManager == null) {
            IBinder binder = ServiceManager.getService(Context.SCROLL_MONITOR_SERVICE);
            if(binder != null) {
                IScrollMonitorService service = IScrollMonitorService.Stub.asInterface(binder);
                mScrollMonitorManager = new ScrollMonitorManager(service);
            } else {
                Log.e(TAG, "Service binder is null");
            }
        }
        return mScrollMonitorManager;
    }

    public boolean setFrameRate(float frameRate) {
        try {
            return mService.setFrameRate(frameRate);
        } catch(RemoteException e){
            Log.e(TAG, e.toString());
            return false;
        }  
    }

    public boolean setModel(Map speedToFR) {
        try {
            return mService.setModel(speedToFR);
        } catch(RemoteException e){
            Log.e(TAG, e.toString());
            return false;
        }
    }

    public void notifyScrollStopped() {
        try {
            mService.notifyScrollStopped();
        } catch(RemoteException e){
            Log.e(TAG, e.toString());
        }
    }
}