package com.myproject

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Bundle
import com.facebook.react.ReactActivity
import com.facebook.react.ReactActivityDelegate
import com.facebook.react.defaults.DefaultNewArchitectureEntryPoint
import com.facebook.react.defaults.DefaultReactActivityDelegate

class MainActivity : ReactActivity() {
    val CUSTOM_EXTRA_NAME = "RELAUNCHED"

    override fun onCreate(savedInstanceState: Bundle?) {
        if (!intent.getBooleanExtra(CUSTOM_EXTRA_NAME, false)) {
            this.finish()
            intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(CUSTOM_EXTRA_NAME, true);
            startActivity(intent);
        }
        super.onCreate(null)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
    }

    /**
     * Returns the name of the main component registered from JavaScript. This is used to schedule
     * rendering of the component.
     */
    override fun getMainComponentName(): String {
        return "myProject"
    }

    /**
     * Returns the instance of the [ReactActivityDelegate]. Here we use a util class [ ] which allows you to easily enable Fabric and Concurrent React
     * (aka React 18) with two boolean flags.
     */
    override fun createReactActivityDelegate(): ReactActivityDelegate {
        return DefaultReactActivityDelegate(
            this,
            mainComponentName,  // If you opted-in for the New Architecture, we enable the Fabric Renderer.
            DefaultNewArchitectureEntryPoint.fabricEnabled
        )
    }
}
