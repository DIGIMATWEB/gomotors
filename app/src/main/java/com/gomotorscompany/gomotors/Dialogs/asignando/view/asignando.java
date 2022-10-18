package com.gomotorscompany.gomotors.Dialogs.asignando.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.gomotorscompany.gomotors.R;
import com.gomotorscompany.gomotors.mainContent.view.mainContentViewImpl;
import com.gomotorscompany.gomotors.pedido.view.dialog;
import com.airbnb.lottie.LottieAnimationView;

public class asignando extends DialogFragment implements View.OnClickListener {
    public static final String TAG = asignando.class.getSimpleName();
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
        View view = inflater.inflate(R.layout.asignando_pedido, container, false);
        getDialog().getWindow().setBackgroundDrawableResource(R.color.customTransparent) ;
        setCancelable(false);
        mainActivity= (mainContentViewImpl) this.getActivity();
        initDialog(view);
        //setFonts();
        return view;
    }

    private void initDialog(View view) {

        iralmenu=view.findViewById(R.id.iralmenupedido);
        iralmenu.setOnClickListener(this);
       // iralmenu.setVisibility(View.GONE);
        animationView = view.findViewById(R.id.splash);


        animationView.addAnimatorUpdateListener(
                (animation) -> {


                });
        animationView.playAnimation();

        if (animationView.isAnimating()) {

        } else {

        }
    }
    public void closeDialog() {
        this.dismiss();

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.externalgpsconstraint:
//                closeDialog();
//                break;
            case    R.id.iralmenupedido:
                //Toast.makeText(getContext(), "ir al menu", Toast.LENGTH_SHORT).show();
                animationView.cancelAnimation();
                closeDialog();
               // mainActivity.goTomiscompras();
                break;
        }
    }

}