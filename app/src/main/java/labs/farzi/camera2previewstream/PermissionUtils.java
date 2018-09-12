package labs.farzi.camera2previewstream;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;

/**
 * Created by abhinav.srivastava on 4/2/18.
 */

public class PermissionUtils {
    public static final int REQUEST_CODE_PERMISSION = 2;
    static PermissionUtils permissionUtils;

    private static String[] PERMISSIONS_REQ = {
            Manifest.permission.CAMERA};
    public static PermissionUtils getInstance(){
        if(permissionUtils == null){
            permissionUtils = new PermissionUtils();
        }
        return permissionUtils;
    }

    public boolean verifyPermissions(Activity activity) {
        int camera_permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.CAMERA);
        if (camera_permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_REQ,
                    REQUEST_CODE_PERMISSION
            );
            return false;
        } else {
            return true;
        }
    }

    public void initialize(Activity activity) {
        // For API 23+ you need to request the read/write permissions even if they are already in your manifest.
        int currentapiVersion = Build.VERSION.SDK_INT;

        if (currentapiVersion >= Build.VERSION_CODES.M) {
            verifyPermissions(activity);
        }
    }
}
