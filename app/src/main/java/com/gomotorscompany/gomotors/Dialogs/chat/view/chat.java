package com.gomotorscompany.gomotors.Dialogs.chat.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.airbnb.lottie.LottieAnimationView;
import com.gomotorscompany.gomotors.Dialogs.asignando.view.asignando;
import com.gomotorscompany.gomotors.R;
import com.gomotorscompany.gomotors.mainContent.view.mainContentViewImpl;
import com.gomotorscompany.gomotors.pedido.view.dialog;

public class chat extends DialogFragment implements View.OnClickListener {
    public static final String TAG = chat.class.getSimpleName();
    private Button iralmenu;
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
        View view = inflater.inflate(R.layout.chat_layout, container, false);
        getDialog().getWindow().setBackgroundDrawableResource(R.color.customTransparent);
        setCancelable(false);
        initDialog(view);
        //setFonts();
        return view;
    }

    private void initDialog(View view) {

    }

    public void closeDialog() {
        this.dismiss();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

        }
    }
}