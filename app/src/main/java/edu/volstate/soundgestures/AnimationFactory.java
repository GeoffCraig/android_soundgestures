package edu.volstate.soundgestures;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Animatable;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class AnimationFactory implements Animation.AnimationListener {
    Context context;
    Activity activity;

    public AnimationFactory(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }

    @Override
    public void onAnimationStart(Animation animation) {
        Log.i("onAnimationStart", "true");
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        Log.i("onAnimationEnd", "true");
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
        Log.i("onAnimationRepeat", "true");
    }

    public void fadeInOut(int imageView, int duration)
    {
        Animation animFadeInOut = AnimationUtils.loadAnimation(context, R.anim.fade_in_out);
        animFadeInOut.setDuration(duration);
        animFadeInOut.setAnimationListener(this);
        ImageView imgView;
        imgView = activity.findViewById(imageView);
        imgView.startAnimation(animFadeInOut);
    }

    public void bounce(int imageView, int duration) {
        Animation animBounce = AnimationUtils.loadAnimation(context, R.anim.bounce);
        animBounce.setDuration(duration);
        ImageView imgView = activity.findViewById(imageView);
        imgView.startAnimation(animBounce);
    }
}
