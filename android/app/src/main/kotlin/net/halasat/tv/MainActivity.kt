package net.halasat.tv

import android.content.Intent
import android.os.Bundle

import io.flutter.app.FlutterActivity

import io.flutter.plugins.GeneratedPluginRegistrant
import io.flutter.plugin.common.MethodChannel


class MainActivity : FlutterActivity() {

    private val kChannelId = "player-channel"



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        GeneratedPluginRegistrant.registerWith(this)


        MethodChannel(flutterView, kChannelId).setMethodCallHandler {
            call, _ ->
            val args = call.arguments as HashMap<String, String>

            args.size;

            if (call.method == "launchMoviePlayer") {
                // Get the arguments

                val videoUrl = args["movieUrl"] as String
                val title = args["title"] as String
                val subtitles = args["subtitlesUrl"] as String

                val intent = Intent(this, PlayerActivity::class.java)

                // Bundle the video information

                val bundle = Bundle()
                bundle.putString("videoUrl", videoUrl)
                bundle.putBoolean("useTvPlayer", false)
                bundle.putString("title", title)
                bundle.putString("subtitle",subtitles)
                //bundle.putInt("size",args.size);

                // Pass the bundle
                intent.putExtras(bundle)

                // Start the player activity
                startActivity(intent)
            } else if (call.method == "launchChannelPlayer") {

                val videoUrl = args["channelUrl"] as String
                val title = args["title"] as String

                val intent = Intent(this, PlayerActivity::class.java)

                // Bundle the video information
                val bundle = Bundle()
                bundle.putString("videoUrl", videoUrl)
                bundle.putBoolean("useTvPlayer", true)
                bundle.putString("title", title)
                intent.putExtras(bundle)
                startActivity(intent)
            }
        }
    }
}
