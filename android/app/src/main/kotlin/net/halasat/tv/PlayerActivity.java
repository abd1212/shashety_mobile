package net.halasat.tv;

import android.animation.LayoutTransition;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.Layout;
import android.util.Log;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
<<<<<<< HEAD
=======
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.mediarouter.app.MediaRouteButton;

>>>>>>> origin/master
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.text.CaptionStyleCompat;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.ui.DefaultTimeBar;
import com.google.android.exoplayer2.ui.PlaybackControlView;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.ui.SubtitleView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

public class PlayerActivity extends AppCompatActivity {
    private boolean isShowingTrackSelectionDialog;
    private boolean mExoPlayerFullscreen = false;
    private SimpleExoPlayer player;
    private PlayerView simpleExoPlayerView;
    private DefaultTrackSelector trackSelector;
    private Uri videoUri;
    private Toolbar mToolbar;
    private String mediaInfoTitle;
    private ImageView backArrow, bottomSheet;
    private LinearLayout bottomSheetLayout;
    private BottomSheetBehavior bottomSheetBehavior;
<<<<<<< HEAD
    private TextView changeQuailty, changeFontSize, textSize, exo_duration, exo_position, text_title;
    private ImageButton  forewordButton, backwardButton;
    private DefaultTimeBar timeBar;
    private LinearLayout layoutControle;
    private Animation fadeIn,fadeOut;
=======
    private TextView changeQuailty, changeFontSize, textSize,exo_duration,exo_position,text_title;
    private OrientationEventListener orientationEventListener;
    private ImageButton nextButton,prevButton,forewordButton, backwardButton;
    private DefaultTimeBar timeBar;
    private ConstraintLayout layoutControle;
    private FrameLayout playButton;
>>>>>>> origin/master

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_player);
<<<<<<< HEAD

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        //check the both sid of landscape
        setOrientationSensor();


=======
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setOrientationSensor();

>>>>>>> origin/master
        // Find view by id
        findView();
        // Fullscreen activity
        hideSystemUI();

        //define fade anim to appbar , control button and progress bar
        fadeIn = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
        fadeOut = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_out);

        // Setup custom toolbar
        setupActionBar();
<<<<<<< HEAD

        //made the appbar , control button and progress bar hid by click on exoplaer
        simpleExoPlayerView.setControllerShowTimeoutMs(0);
        simpleExoPlayerView.setControllerHideOnTouch(false);

        // Set title
=======
        simpleExoPlayerView.setControllerShowTimeoutMs(0);
        simpleExoPlayerView.setControllerHideOnTouch(false);
        // Set tit
>>>>>>> origin/master
        setMediaInfoTitle(getIntent().getStringExtra("title"));
        // Set videoUrl
        setVideoUri(getIntent().getStringExtra("videoUrl"));
        //set title to appbar
        text_title.setText(getIntent().getStringExtra("title"));

        // Initialize SimpleExoPlayer
        initializePlayer();
<<<<<<< HEAD
=======
        // Setup setUpMediaRouteButton for casting
        //setUpCasting();
        // Setup media info for casting
        //setUpMediaInfo();
        //initial full screen mode
        /*initFullscreenDialog();
        initFullscreenButton();
        if (mExoPlayerFullscreen) {
            mFullScreenIcon.setImageDrawable(ContextCompat.getDrawable(PlayerActivity.this, R.drawable.ic_fullscreen_shrink));
        }*/

>>>>>>> origin/master

        //set bottom sheet
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetLayout);
        bottomSheetBehavior.setPeekHeight(0);
        bottomSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(bottomSheetLayout.getVisibility() == View.VISIBLE) return;

                bottomSheetLayout.setVisibility(View.VISIBLE);
                //hide text
                textSize.setVisibility(View.GONE);
                changeFontSize.setText("Font Size");
                changeQuailty.setText("Change Quailty");

                Drawable fontDrawable = getResources().getDrawable(R.drawable.ic_font);
                Drawable quailtyDrawable = getResources().getDrawable(R.drawable.ic_high_quality);
                changeFontSize.setCompoundDrawablesWithIntrinsicBounds(fontDrawable, null, null, null);
                changeQuailty.setCompoundDrawablesWithIntrinsicBounds(quailtyDrawable, null, null, null);

                // set the peek height
                bottomSheetBehavior.setPeekHeight(150);
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HALF_EXPANDED);
<<<<<<< HEAD
                Animation animSlideDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slid_up);
=======
               /* LayoutTransition layoutTransition=new LayoutTransition();
                bottomSheetLayout.setLayoutTransition(layoutTransition);*/
                Animation animSlideDown = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slid_up);
>>>>>>> origin/master
                bottomSheetLayout.startAnimation(animSlideDown);

                // set hideable or not
                bottomSheetBehavior.setHideable(false);

                changeFontSize.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetBehavior.setPeekHeight(0);
                        bottomSheetLayout.setVisibility(View.GONE);
                        changeFontSize();
                    }
                });
                changeQuailty.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetLayout.setVisibility(View.GONE);
                        if (!isShowingTrackSelectionDialog
                                && TrackSelectionDialog.willHaveContent((DefaultTrackSelector) trackSelector)) {
                            isShowingTrackSelectionDialog = true;
                            TrackSelectionDialog trackSelectionDialog =
                                    TrackSelectionDialog.createForTrackSelector(
                                            (DefaultTrackSelector) trackSelector,
                                            /* onDismissListener= */ dismissedDialog -> isShowingTrackSelectionDialog = false);
                            trackSelectionDialog.show(getSupportFragmentManager(), /* tag= */ null);
<<<<<<< HEAD
=======


>>>>>>> origin/master
                        }
                    }
                });
            }
        });


<<<<<<< HEAD
        // arrow in app bare
=======


        // set orientation listener
>>>>>>> origin/master
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
<<<<<<< HEAD
            }
        });

        //when  the tv is operation
        boolean showTV = getIntent().getBooleanExtra("useTvPlayer", false);
        if (showTV) {

            //hid control button of exoplayer
            hideController();
        }
    }

=======
>>>>>>> origin/master

    private void changeFontSize() {
        bottomSheetLayout.setVisibility(View.VISIBLE);
        // set the peek height
        bottomSheetBehavior.setPeekHeight(150);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HALF_EXPANDED);
        Animation animSlideDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slid_up);
        bottomSheetLayout.startAnimation(animSlideDown);
        // set hideable or not
        bottomSheetBehavior.setHideable(false);
        textSize.setText("Large");
        textSize.setVisibility(View.VISIBLE);
        textSize.setTextSize(15);
        changeFontSize.setTextSize(15);
        changeQuailty.setTextSize(15);
        changeQuailty.setText("Small");
        changeQuailty.setCompoundDrawables(null, null, null, null);
        changeFontSize.setText("Medium");
        changeFontSize.setCompoundDrawables(null, null, null, null);
        changeQuailty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeSubtitleStyle(1);
                bottomSheetLayout.setVisibility(View.GONE);
            }
        });
        changeFontSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeSubtitleStyle(2);
                bottomSheetLayout.setVisibility(View.GONE);
            }
        });
        textSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeSubtitleStyle(3);
                bottomSheetLayout.setVisibility(View.GONE);
            }
        });
    }

<<<<<<< HEAD
    //to put player on both side of landscape
=======

    private void changeFontSize() {
        bottomSheetLayout.setVisibility(View.VISIBLE);
        // set the peek height
        bottomSheetBehavior.setPeekHeight(150);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HALF_EXPANDED);
        Animation animSlideDown = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slid_up);
        bottomSheetLayout.startAnimation(animSlideDown);
        // set hideable or not
        bottomSheetBehavior.setHideable(false);
        textSize.setText("Large");
        textSize.setVisibility(View.VISIBLE);
        textSize.setTextSize(15);
        changeFontSize.setTextSize(15);
        changeQuailty.setTextSize(15);
        changeQuailty.setText("Small");
        changeQuailty.setCompoundDrawables(null, null, null, null);
        changeFontSize.setText("Medium");
        changeFontSize.setCompoundDrawables(null, null, null, null);
        changeQuailty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeSubtitleStyle(1);
                bottomSheetLayout.setVisibility(View.GONE);
            }
        });
        changeFontSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeSubtitleStyle(2);
                bottomSheetLayout.setVisibility(View.GONE);
            }
        });
        textSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeSubtitleStyle(3);
                bottomSheetLayout.setVisibility(View.GONE);
            }
        });

    }

>>>>>>> origin/master
    private void setOrientationSensor() {
        OrientationEventListener orientationEventListener = new OrientationEventListener(PlayerActivity.this) {
            @Override
            public void onOrientationChanged(int orientation) {
<<<<<<< HEAD
=======
                if (orientation ==90 && orientation== 270) {
                    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
                    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
                    hideSystemUI();
                }
                else
                {
                    PlayerActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
                    hideSystemUI();
                }
                int epsilon = 10;
                int leftLandscape = 90;
                int rightLandscape = 270;
                if (epsilonCheck(orientation, leftLandscape, epsilon) ||
                        epsilonCheck(orientation, rightLandscape, epsilon)) {
                    PlayerActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
                }
            }
>>>>>>> origin/master

                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
            }
        };
        orientationEventListener.enable();

    }

    private void initializePlayer() {
        DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter(); //test

        TrackSelection.Factory videoTrackSelectionFactory = new
                AdaptiveTrackSelection.Factory(bandwidthMeter);

        trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);
        LoadControl loadControl = new DefaultLoadControl();

        // Set the subtitles
        trackSelector.setParameters(
                trackSelector
                        .buildUponParameters()
                        .setPreferredTextLanguage("ar")
        );

        //  Create the player
       /* player = ExoPlayerFactory.
                newSimpleInstance(this, trackSelector, loadControl);*/

        player = ExoPlayerFactory.
                newSimpleInstance(this, trackSelector, loadControl);

<<<<<<< HEAD
=======


>>>>>>> origin/master
        // Produces DataSource instances through which media data is loaded.
        DataSource.Factory dataSourceFactory = new
                DefaultDataSourceFactory(this,
                Util.getUserAgent(this,
                        "exoplayer2example"), bandwidthMeter);


        MediaSource videoSource = new
                HlsMediaSource.Factory(dataSourceFactory).
                createMediaSource(videoUri);

        // Prepare video with sub title
        player.prepare(videoSource);

        // Set the player to view
        simpleExoPlayerView.setPlayer(player);

        // Auto play
        player.setPlayWhenReady(true);

        simpleExoPlayerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
<<<<<<< HEAD
                if (mToolbar.getVisibility() == View.VISIBLE ) {
                    mToolbar.startAnimation(fadeOut);
                    mToolbar.setVisibility(View.INVISIBLE);
                    layoutControle.startAnimation(fadeOut);
                    layoutControle.setVisibility(View.INVISIBLE);
                } else if (mToolbar.getVisibility() == View.GONE || mToolbar.getVisibility() == View.INVISIBLE) {
                    mToolbar.setVisibility(View.VISIBLE);
                    mToolbar.startAnimation(fadeIn);
                    layoutControle.setVisibility(View.VISIBLE);
                    layoutControle.startAnimation(fadeIn);
                }
                if (bottomSheetLayout.getVisibility() == View.VISIBLE) {
                    Animation animSlideDown = AnimationUtils.loadAnimation(PlayerActivity.this, R.anim.slid_down);
=======


                if (mToolbar.getVisibility() == View.VISIBLE) {
                    mToolbar.setVisibility(View.INVISIBLE);
                    layoutControle.setVisibility(View.INVISIBLE);
                } else if (mToolbar.getVisibility() == View.GONE || mToolbar.getVisibility() == View.INVISIBLE) {
                    mToolbar.setVisibility(View.VISIBLE);
                    layoutControle.setVisibility(View.VISIBLE);
                    //delayApp_bar();
                }
                if (bottomSheetLayout.getVisibility() == View.VISIBLE) {
                    Animation animSlideDown = AnimationUtils.loadAnimation(PlayerActivity.this,R.anim.slid_down);
>>>>>>> origin/master
                    bottomSheetLayout.startAnimation(animSlideDown);
                    bottomSheetLayout.setVisibility(View.GONE);
                }
                hideSystemUI();
            }
        });

        changeSubtitleStyle(1);
        player.addListener(new PlayerEventListener());
    }


    public void delayApp_bar(){
        final Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mToolbar.setVisibility(View.INVISIBLE);
                layoutControle.setVisibility(View.INVISIBLE);
            }
        }, 5000);

    }





    @Override
    protected void onPause() {
        super.onPause();
        //If Exo is ready, passing false you will pause the player
        player.setPlayWhenReady(false);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

    }

<<<<<<< HEAD
=======



    private void setUpCasting() {


        CastButtonFactory.setUpMediaRouteButton(getApplicationContext(), mediaRouteButton);

        castContext = CastContext.getSharedInstance(this);
        if (castContext.getCastState() != CastState.NO_DEVICES_AVAILABLE)
            mediaRouteButton.setVisibility(View.VISIBLE);

        castContext.addCastStateListener(new CastStateListener() {
            @Override
            public void onCastStateChanged(int state) {
                if (state == CastState.NO_DEVICES_AVAILABLE)
                    mediaRouteButton.setVisibility(View.GONE);
                else {
                    if (mediaRouteButton.getVisibility() == View.GONE)
                        mediaRouteButton.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void setUpMediaInfo() {
        MediaMetadata movieMetadata = new MediaMetadata(MediaMetadata.MEDIA_TYPE_MOVIE);
        movieMetadata.putString(MediaMetadata.KEY_TITLE, getMediaInfoTitle());
        MediaInfo mediaInfo = new MediaInfo.Builder(videoUri + "")

                .setStreamType(MediaInfo.STREAM_TYPE_BUFFERED)
                .setContentType("video/m3u8")
                .setMetadata(movieMetadata)
                .build();


        final MediaQueueItem[] mediaItems = {new MediaQueueItem.Builder(mediaInfo).build()};

        CastPlayer castPlayer = new CastPlayer(castContext);
        castPlayer.setSessionAvailabilityListener(new CastPlayer.SessionAvailabilityListener() {
            @Override
            public void onCastSessionAvailable() {

                castPlayer.loadItems(mediaItems, 0, 0, Player.REPEAT_MODE_ALL);
            }

            @Override
            public void onCastSessionUnavailable() {
            }
        });
    }

>>>>>>> origin/master
    private void hideSystemUI() {
        // Set the IMMERSIVE flag.
        // Set the content to appear under the system bars so that the content
        // doesn't resize when the system bars hide and show.
        View decorView = getWindow().getDecorView();

        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {

        super.onConfigurationChanged(newConfig);

        // Checking the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            //First Hide other objects (listview or recyclerview), better hide them using Gone.
            FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) simpleExoPlayerView.getLayoutParams();
            params.width = ViewGroup.LayoutParams.MATCH_PARENT;
            params.height = ViewGroup.LayoutParams.MATCH_PARENT;
            simpleExoPlayerView.setLayoutParams(params);
            mToolbar.setVisibility(View.GONE);
            mToolbar.startAnimation(fadeOut);
            hideSystemUI();
<<<<<<< HEAD

            // Show status bar
=======
            // Hide status bar
            //getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
// Show status bar
>>>>>>> origin/master
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            //unhide your objects here.
            FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) simpleExoPlayerView.getLayoutParams();
            params.width = ViewGroup.LayoutParams.MATCH_PARENT;
            params.height = ViewGroup.LayoutParams.MATCH_PARENT;
            simpleExoPlayerView.setLayoutParams(params);
            mToolbar.setVisibility(View.VISIBLE);
            mToolbar.startAnimation(fadeIn);
            hideSystemUI();
        }
    }

    public String getMediaInfoTitle() {
        return mediaInfoTitle;
    }

    public void setMediaInfoTitle(String mediaInfoTitle) {
        this.mediaInfoTitle = mediaInfoTitle;
    }

    public void setVideoUri(String videoUri) {
        this.videoUri = Uri.parse(videoUri);
    }

    private void setupActionBar() {
        mToolbar.setVisibility(View.VISIBLE);
        mToolbar.startAnimation(fadeIn);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
<<<<<<< HEAD
=======

>>>>>>> origin/master

    }

    private void findView() {
        mToolbar = findViewById(R.id.app_bar);
        simpleExoPlayerView = findViewById(R.id.exoplayer);
        text_title = mToolbar.findViewById(R.id.text_title);
        PlaybackControlView controlView = simpleExoPlayerView.findViewById(R.id.exo_controller);
<<<<<<< HEAD
=======
        //mFullScreenIcon = controlView.findViewById(R.id.exo_fullscreen_icon);
>>>>>>> origin/master
        backArrow = mToolbar.findViewById(R.id.backarrow);
        bottomSheet = mToolbar.findViewById(R.id.bottom_sheet);
        bottomSheetLayout = findViewById(R.id.bottom_sheet_layout);
        changeFontSize = findViewById(R.id.change_font_size);
        changeQuailty = findViewById(R.id.change_quality);
        textSize = findViewById(R.id.text_size);
<<<<<<< HEAD
        layoutControle = controlView.findViewById(R.id.controlLayout);
        forewordButton = controlView.findViewById(R.id.exo_ffwd);
        backwardButton = controlView.findViewById(R.id.exo_rew);
        timeBar = controlView.findViewById(R.id.exo_progress);
        exo_duration = controlView.findViewById(R.id.exo_duration);
        exo_position = controlView.findViewById(R.id.exo_position);
    }
=======
        layoutControle=controlView.findViewById(R.id.controlLayout);
        //playButton=findViewById(R.id.playButton);
        forewordButton=controlView.findViewById(R.id.exo_ffwd);
        backwardButton=controlView.findViewById(R.id.exo_rew);
        //nextButton=controlView.findViewById(R.id.exo_next);
        //prevButton=controlView.findViewById(R.id.exo_prev);
        timeBar=controlView.findViewById(R.id.exo_progress);
        exo_duration=controlView.findViewById(R.id.exo_duration);
        exo_position=controlView.findViewById(R.id.exo_position);
        mediaRouteButton =  mToolbar.findViewById(R.id.media_route_button);
        //titleText=mToolbar.findViewById(R.id.title_text);
//        ViewGroup viewGroup= (ViewGroup) layoutControle.getParent();
//        View myButtonPlay= getLayoutInflater().inflate(R.layout.button_play, viewGroup, false);
//        viewGroup.removeView(layoutControle);
//        viewGroup.addView(myButtonPlay);
    }

    //initial full screen mode
    /*private void initFullscreenDialog() {

        if (mExoPlayerFullscreen)
            closeFullscreenDialog();


    }*/
>>>>>>> origin/master

    @Override
    protected void onResume() {
        super.onResume();
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
<<<<<<< HEAD
=======
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


    }



    /*private void openFullscreenDialog() {
        mFullScreenIcon.setImageDrawable(ContextCompat.getDrawable(PlayerActivity.this, R.drawable.ic_fullscreen_shrink));
        mExoPlayerFullscreen = true;
        hideSystemUI();
        simpleExoPlayerView.hideController();


>>>>>>> origin/master
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


    }

<<<<<<< HEAD
=======
    private void initFullscreenButton() {


        mFullScreenIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mExoPlayerFullscreen)
                    openFullscreenDialog();
                else
                    closeFullscreenDialog();
            }
        });
    }*/

>>>>>>> origin/master
    private void changeSubtitleStyle(int fontSize) {
        int defaultSubtitleColor = Color.argb(255, 218, 218, 218);
        int outlineColor = Color.argb(255, 43, 43, 43);
        CaptionStyleCompat style =
                new CaptionStyleCompat(defaultSubtitleColor,
                        Color.TRANSPARENT, Color.TRANSPARENT,
                        CaptionStyleCompat.EDGE_TYPE_OUTLINE,
                        outlineColor, null);
        simpleExoPlayerView.getSubtitleView().setStyle(style);

        simpleExoPlayerView.getSubtitleView().setFractionalTextSize(SubtitleView.DEFAULT_TEXT_SIZE_FRACTION * fontSize);
    }

    // Handle keeping the screen on while playing video
    private class PlayerEventListener implements Player.EventListener {
        @Override
        public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
            if (playbackState == Player.STATE_IDLE || playbackState == Player.STATE_ENDED || !playWhenReady) {
                simpleExoPlayerView.setKeepScreenOn(false);
            } else {
                simpleExoPlayerView.setKeepScreenOn(true);
            }


        }
    }

    // Hide controller button for TV use
<<<<<<< HEAD
    public void hideController() {
=======
    public void hideController(){
        // layoutControle.setVisibility(View.GONE);
        // playButton.setVisibility(View.VISIBLE);
>>>>>>> origin/master
        exo_position.setVisibility(View.INVISIBLE);
        forewordButton.setImageDrawable(null);
        forewordButton.setBackground(null);
        forewordButton.setVisibility(View.GONE);
        backwardButton.setImageDrawable(null);
        backwardButton.setBackground(null);
        backwardButton.setVisibility(View.GONE);
<<<<<<< HEAD
=======
        //nextButton.setImageDrawable(null);
        //prevButton.setImageDrawable(null);
>>>>>>> origin/master
        exo_duration.setVisibility(View.GONE);
        timeBar.setEnabled(false);
        timeBar.setVisibility(View.GONE);
        timeBar.setAdMarkerColor(Color.BLACK);
        timeBar.setBufferedColor(Color.BLACK);
        timeBar.setPlayedColor(Color.BLACK);
        timeBar.setPlayedAdMarkerColor(Color.BLACK);
        timeBar.setScrubberColor(Color.BLACK);
        timeBar.setUnplayedColor(Color.BLACK);

    }
}