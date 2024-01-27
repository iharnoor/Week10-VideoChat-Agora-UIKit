package com.example.agora_test2

import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import io.agora.agorauikit_android.AgoraConnectionData
import io.agora.agorauikit_android.AgoraSettings
import io.agora.agorauikit_android.AgoraVideoViewer
import io.agora.rtc2.Constants


class MainActivity : AppCompatActivity() {

    // Object of AgoraVideoVIewer class
    private var agView: AgoraVideoViewer? = null

    // Fill the App ID of your project generated on Agora Console.
    private val appId = "475b5074826c4601899932d99fe766d1"

    // Fill the channel name.
    private val channelName = "harry"

    // Fill the temp token generated on Agora Console.
    private val token =
        "007eJxTYHD7we/2a3/0kTsqCx60+yyO65I0/mCWwnoyQsfO3fmrwTQFBhNz0yRTA3MTCyOzZBMzA0MLS0tLY6MUS8u0VHMzsxTDl/e2pDYEMjJc9ZVgYIRCEJ+VISOxqKiSgQEAKfIe4w=="

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeAndJoinChannel2()
    }

    private fun initializeAndJoinChannel2() {
        // Create AgoraVideoViewer instance
        agView = try {
            AgoraVideoViewer(
                this,
                AgoraConnectionData(appId, token),
                AgoraVideoViewer.Style.FLOATING,
                AgoraSettings(),
                null
            )
        } catch (e: Exception) {
            Log.e(
                "AgoraVideoViewer",
                "Could not initialize AgoraVideoViewer. Check that your app Id is valid."
            )
            Log.e("Exception", e.toString())
            return
        }

        // Add the AgoraVideoViewer to the Activity layout
        addContentView(
            agView, FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
            )
        )

        // Check permission and join a channel
//        if (DevicePermissionsKt.requestPermissions(AgoraVideoViewer, this)) {
        joinChannel()
//        } else {
        val joinButton = Button(this)
        joinButton.text = "Allow camera and microphone access, then click here"
        joinButton.setOnClickListener {

            (joinButton.parent as ViewGroup).removeView(joinButton)
            joinChannel()
        }
        addContentView(
            joinButton,
            FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, 200)
        )
    }


    fun joinChannel() {
        agView!!.join(channelName, token, Constants.CLIENT_ROLE_BROADCASTER, 0)
    }

}
