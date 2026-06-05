package eu.baran.pokedex.permissions

import android.Manifest.permission.ACCESS_LOCAL_NETWORK
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat

@SuppressLint("InlinedApi")
@Composable
actual fun rememberLocalNetworkAccessGate(): LocalNetworkAccessGate {
    val context = LocalContext.current
    val activity = context.findActivity()
    var status by remember(context) { mutableStateOf(currentAccessStatus(context)) }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        status = if (granted){
            LocalNetworkAccessStatus.Ready
        } else {
            LocalNetworkAccessStatus.Denied
        }
    }

    return remember(status, activity, launcher) {
        LocalNetworkAccessGate(
            status = status,
            requestAccess = {
                val latestStatus = currentAccessStatus(context)
                status = latestStatus
                if (latestStatus == LocalNetworkAccessStatus.Blocked && activity != null) {
                    launcher.launch(ACCESS_LOCAL_NETWORK)
                }
            }
        )
    }
}

private fun currentAccessStatus(context: Context): LocalNetworkAccessStatus {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.CINNAMON_BUN) {
        return LocalNetworkAccessStatus.Ready
    }
    val isGranted = ContextCompat.checkSelfPermission(
        context,
        ACCESS_LOCAL_NETWORK
    ) == PackageManager.PERMISSION_GRANTED
    return if (isGranted){
        LocalNetworkAccessStatus.Ready
    } else {
        LocalNetworkAccessStatus.Blocked
    }
}

private tailrec fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}
