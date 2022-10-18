package com.gomotorscompany.gomotors.forgotpasword.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.gomotorscompany.gomotors.Login.view.LoginViewImpl;
import com.gomotorscompany.gomotors.R;

public class restore_password extends Fragment implements View.OnClickListener {
    public static final String TAG = restore_password.class.getSimpleName();
    private ImageView iconoatras;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fracment_restorepass, container, false);
        initLoginViewImpl(view);
        return view;
    }

    private void initLoginViewImpl(View view) {
        iconoatras=view.findViewById(R.id.regresara_restorepass);
        iconoatras.setOnClickListener(this);
    }
    public void initLoginContainerView() {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.addToBackStack(LoginViewImpl.TAG);
        transaction.replace(R.id.login_containerF, new LoginViewImpl()).commit();


    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.regresara_restorepass:
                initLoginContainerView();
                break;
        }
    }

}
