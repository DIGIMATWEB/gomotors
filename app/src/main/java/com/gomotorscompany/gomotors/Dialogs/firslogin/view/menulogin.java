package com.gomotorscompany.gomotors.Dialogs.firslogin.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.gomotorscompany.gomotors.R;
import com.gomotorscompany.gomotors.mainContent.view.mainContentViewImpl;
import com.gomotorscompany.gomotors.pedido.view.dialog;
import com.airbnb.lottie.LottieAnimationView;

public class menulogin  extends DialogFragment {

    public static final String TAG = menulogin.class.getSimpleName();
   // private Button iralmenu;
    private LottieAnimationView animationView;
    private dialog interfaced;
    private mainContentViewImpl mainActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_DeviceDefault_Light_NoActionBar);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mainlottielogin, container, false);
        getDialog().getWindow().setBackgroundDrawableResource(R.color.customTransparent) ;
        setCancelable(false);
        mainActivity= (mainContentViewImpl) this.getActivity();
        initDialog(view);
        //setFonts();
        return view;
    }
    private void initDialog(View view) {

        animationView = view.findViewById(R.id.splash);
        animationView.addAnimatorUpdateListener(
                (animation) -> {
                });
        animationView.playAnimation();

        if (animationView.isAnimating()) {

        } else {

        }
    }


}
