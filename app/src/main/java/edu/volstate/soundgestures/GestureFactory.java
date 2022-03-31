package edu.volstate.soundgestures;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.provider.Contacts;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintSet;

public final class GestureFactory extends GestureDetector.SimpleOnGestureListener {
    Context context;
    Activity activity;
    ImageView swiper;
    AnimationFactory animaniacs;
    MediaPlayerFactory mediaPlayerFactory;
    @Override
    public boolean onDown(MotionEvent event) {
        return true;
    }
    public GestureFactory(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
        animaniacs = new AnimationFactory(context, activity);
    }

    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {
        //courtesy of https://www.tutorialspoint.com/how-to-handle-right-to-left-and-left-to-right-swipe-gestures-on-android
        final int SWIPE_THRESHOLD = 100;
        final int SWIPE_VELOCITY_THRESHOLD = 100;
        try {
            float diffY = event2.getY() - event1.getY();
            float diffX = event2.getX() - event1.getX();
            if (Math.abs(diffX) > Math.abs(diffY)) {
                if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX > 0) {
                        onSwipeRight();
                    } else {
                        onSwipeLeft();
                    }
                }
            }
            else {
                if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffY > 0) {
                        onSwipeDown();
                    } else {
                        onSwipeUp();
                    }
                }
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        Log.d("Gesture:", "onFling: " + event1.toString() + event2.toString());
        return false;
    }

    private void onSwipeRight()
    {
        UIDrawer(R.drawable.ic_baseline_arrow_right_alt_24);
        MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory(activity);
        mediaPlayerFactory.playMedia(R.raw.dog_squeaky_toy_sound_effect);
        animaniacs.flash(R.id.imgv_swipe, 2000);
        Toast.makeText(context, "You Swiped Right", Toast.LENGTH_SHORT).show();
    }

    private void onSwipeLeft()
    {
        UIDrawer(R.drawable.ic_baseline_keyboard_backspace_24);
        MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory(activity);
        mediaPlayerFactory.playMedia(R.raw.small_plate_glass_pieces_being_dropped_on_concrete_floor);
        Toast.makeText(context, "You Swiped Left", Toast.LENGTH_SHORT).show();
    }

    private void onSwipeDown()
    {
        UIDrawer(R.drawable.ic_baseline_arrow_downward_24);
        animaniacs.bounce(R.id.imgv_swipe, 2000);
        MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory(activity);
        mediaPlayerFactory.playMedia(R.raw.zapsplat_leisure_board_game_yahtzee_dice_x1_put_in_shaker);
        Toast.makeText(context, "You Swiped Down", Toast.LENGTH_SHORT).show();
    }

    private void onSwipeUp()
    {
        UIDrawer(R.drawable.ic_baseline_arrow_upward_24);
        animaniacs.fadeInOut(R.id.imgv_swipe, 2000);
        MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory(activity);
        mediaPlayerFactory.playMedia(R.raw.sad_trombone);
        Toast.makeText(context, "You Swiped Up", Toast.LENGTH_SHORT).show();
    }

    private void UIDrawer(int image) {
        swiper = activity.findViewById(R.id.imgv_swipe);
        swiper.setImageResource(image);
        swiper.setVisibility(View.VISIBLE);

    }
}
