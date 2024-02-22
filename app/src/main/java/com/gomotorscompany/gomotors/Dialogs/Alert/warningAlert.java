package com.gomotorscompany.gomotors.Dialogs.Alert;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;

import com.airbnb.lottie.LottieAnimationView;
import com.gomotorscompany.gomotors.Dialogs.distanciaLejana.view.maxDistance;
import com.gomotorscompany.gomotors.R;

public class warningAlert extends DialogFragment implements View.OnClickListener {
    public static final String TAG = warningAlert.class.getSimpleName();
//    private LottieAnimationView animationView;
//    private ConstraintLayout backgrouncontrainsnear;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_DeviceDefault_Light_NoActionBar);
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_aler_warning, container, false);
        getDialog().getWindow().setBackgroundDrawableResource(R.color.customTransparent) ;
        setCancelable(false);
        //  mainActivity= (mainContentViewImpl) this.getActivity();
        initDialog(view);

        return view;
    }

    private void initDialog(View view) {

    }
    private void closeDialog() {
        this.dismiss();
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case    R.id.backgrouncontrainsnear:
                closeDialog();
                break;
        }

    }
}