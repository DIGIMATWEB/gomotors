package com.gomotorscompany.gomotors.Login.view;

import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gomotorscompany.gomotors.Dialogs.RepartidorRegistro.keyRegister;
import com.gomotorscompany.gomotors.R;
import com.gomotorscompany.gomotors.Login.presenter.LoginPresenter;
import com.gomotorscompany.gomotors.Login.presenter.LoginPresenterImpl;
import com.gomotorscompany.gomotors.forgotpasword.view.restore_password;
import com.gomotorscompany.gomotors.mainContent.view.mainContentViewImpl;
import com.gomotorscompany.gomotors.register.view.registerViewImpl;
import com.gomotorscompany.gomotors.retrofit.GeneralConstantsV2;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.Credentials;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.auth.api.credentials.HintRequest;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class LoginViewImpl extends Fragment implements View.OnClickListener, LoginView {

    private GoogleSignInClient mSignInClient;
    private SignInButton signInButton;
    private static final int RC_SIGN_IN = 9001;
    private Button buttonLoguout, buttonrevocke;

    public static final String TAG = LoginViewImpl.class.getSimpleName();
    //private DialogTrackingLoader loader;
    private Context context;
    private CardView btnLogin;
    private TextView txvTitleLogin, txvForgetPassword, registrateaqui,repartidor;
    private EditText edtUserOrEmail, edtPassword;
    private TextInputLayout textInputLayoutUser;
    private TextInputLayout textInputLayoutPass;
    private ProgressDialog loader;
    private LoginPresenter presenter;
    private ImageView imagecompany;
    public static boolean showedLoginView = false;


    private static final int CREDENTIAL_PICKER_REQUEST = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_login_container, container, false);
        initLoginViewImpl(view);
        return view;
    }


    private void initLoginViewImpl(View view) {
        context = getContext();

        HintRequest hintRequest = new HintRequest.Builder()
                .setPhoneNumberIdentifierSupported(true)
                .build();
        phoneNumberR(hintRequest);

        GoogleSignInOptions options =new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestEmail()
                        .build();

        mSignInClient = GoogleSignIn.getClient(getActivity().getApplicationContext(), options);

        imagecompany=view.findViewById(R.id.img_login_icon);
        textInputLayoutUser = view.findViewById(R.id.til_user);
        textInputLayoutPass = view.findViewById(R.id.til_password);
        edtUserOrEmail = view.findViewById(R.id.edtUserOrEmail);
        edtPassword = view.findViewById(R.id.edtPassword);
        txvTitleLogin = view.findViewById(R.id.txt_login_title);
        txvForgetPassword = view.findViewById(R.id.txvForgetPassword);
        btnLogin = view.findViewById(R.id.btn_login);
        loader = new ProgressDialog(getActivity());
        registrateaqui=view.findViewById(R.id.Registrateaqui);
        registrateaqui.setVisibility(View.GONE);
        signInButton=view.findViewById(R.id.bottonGoogle);
        repartidor=view.findViewById(R.id.repartidor);
        repartidor.setOnClickListener(this);
        //signInButton.setColorScheme(SignInButton.COLOR_DARK);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });

        buttonLoguout=view.findViewById(R.id.tempLogout);
        buttonrevocke=view.findViewById(R.id.revocke);
        buttonLoguout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
        buttonrevocke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                revoke();
            }
        });

        registrateaqui.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        txvForgetPassword.setOnClickListener(this);

        presenter = new LoginPresenterImpl(this,getContext());
        presenter.setView(this);
        disabledTextInputAnimation();




//        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
//
//
//        String urlImage = preferences.getString(GeneralConstantsV2.URL_SPLASH_IMAGE, null);
//
//        if(urlImage  !=null) {
//            if (!urlImage.equals("")) {
//
//                Glide.with(context).load(urlImage).into(imagecompany);
//            }
//        }

    }

    private void phoneNumberR(HintRequest hintRequest) {

        PendingIntent intent = Credentials.getClient(getActivity().getApplicationContext()).getHintPickerIntent(hintRequest);
        try
        {
            startIntentSenderForResult(intent.getIntentSender(), CREDENTIAL_PICKER_REQUEST, null, 0, 0, 0,new Bundle());
        }
        catch (IntentSender.SendIntentException e)
        {
            e.printStackTrace();
        }
    }

    private void signIn() {
        // Launches the sign in flow, the result is returned in onActivityResult
        Intent intent = mSignInClient.getSignInIntent();
        startActivityForResult(intent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
                if (requestCode == RC_SIGN_IN) {
                    Task<GoogleSignInAccount> task =
                            GoogleSignIn.getSignedInAccountFromIntent(data);
                    if (task.isSuccessful()) {
                       
                        // Sign in succeeded, proceed with account
                        GoogleSignInAccount acct = task.getResult();
                        Log.e("loginGoogle","data"+"\n"+acct.getDisplayName()+"\n"+acct.getId()+"\n"+acct.getEmail()+"\n"+acct.getIdToken()+"\n"+acct.getPhotoUrl()+"\n"
                                +acct.getFamilyName()+"\n"+acct.getServerAuthCode()+"\n"+acct.getGivenName()+"\n"+acct.getAccount());
                        Toast.makeText(getContext(), ""+acct.getDisplayName()+"\n"+acct.getId(), Toast.LENGTH_SHORT).show();

                    } else {
                        // Sign in failed, handle failure and update UI
                        // ...
                        Toast.makeText(getContext(), "hubo un error", Toast.LENGTH_SHORT).show();
                    }
                }else if (requestCode == CREDENTIAL_PICKER_REQUEST)
                    {
                        // Obtain the phone number from the result
                        Credential credentials = data.getParcelableExtra(Credential.EXTRA_KEY);
                       // EditText.setText(credentials.getId().substring(3));
                        if(credentials!=null)
                        Log.e("loginGoogle","telefono "+credentials.getId().substring(3));


                    }
                    else if (requestCode == CREDENTIAL_PICKER_REQUEST && resultCode == CredentialsApi.ACTIVITY_RESULT_NO_HINTS_AVAILABLE)
                    {
                        // *** No phone numbers available ***
                        Toast.makeText(context, "No phone numbers found", Toast.LENGTH_LONG).show();
                    }
    }
        private  void logout()
        {
            mSignInClient.signOut().addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(getContext(), "logout "+task.isComplete(), Toast.LENGTH_SHORT).show();
                }
            });

        }
        private void revoke()
        {
            mSignInClient.revokeAccess().addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(getContext(), "revoke "+task.isSuccessful(), Toast.LENGTH_SHORT).show();
                }
            });

        }



    private void showFragmentRestorePassword() {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.addToBackStack(restore_password.TAG);
        transaction.replace(R.id.login_containerF, new restore_password()).commit();
    }

    private void showRegisterViewimpl()
    {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.addToBackStack(registerViewImpl.TAG);
        transaction.replace(R.id.login_containerF, new registerViewImpl()).commit();
    }
    private void disabledTextInputAnimation(){
        textInputLayoutUser.setHintEnabled(false);
        textInputLayoutPass.setHintEnabled(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:

                /**AQUI VA EL PRESENTER PARA EL RETROFI*/
                ConnectivityManager cm =(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                boolean isConnected = activeNetwork != null &&
                        activeNetwork.isConnectedOrConnecting();
                if(isConnected) {
                    presenter.loginRequest(String.valueOf(edtUserOrEmail.getText()), String.valueOf(edtPassword.getText()));
                }else
                {
                    Toast.makeText(context, "No estas conectado a internet ", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.txvForgetPassword:
                showFragmentRestorePassword();
                break;
            case R.id.Registrateaqui:
                showRegisterViewimpl();
                 break;
            case R.id.repartidor:
                keyRegister externalGPSDialog = new keyRegister();
                externalGPSDialog.show(getActivity().getSupportFragmentManager(), keyRegister.TAG);
                break;
        }
    }

    @Override
    public void showLoader() {
        loader.setMessage("Accediendo");
        loader.setCancelable(false);
        loader.show();
    }

    @Override
    public void hideLoader() {
        loader.dismiss();
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void succesLogin() {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token     = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
                 presenter.checkStatus(token);

    }

    @Override
    public void succesLoginf() {
        Intent intent = new Intent(getActivity(), mainContentViewImpl.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_CLEAR_TOP);//
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


//    @Override
//    public void showLoader() {
////        loader = new DialogTrackingLoader();
////        loader.show(getActivity().getSupportFragmentManager(), DialogTrackingLoader.TAG);
//    }
//
//    @Override
//    public void hideLoader() {
////        if (loader != null)
////            loader.dismiss();
//    }
//
//    @Override
//    public void showMessage(String message) {
//        AlertDialog.Builder logoutDialog = new AlertDialog.Builder(getContext(), R.style.AlertDialogCustom);
//        logoutDialog.setMessage(message);
//        logoutDialog.setCancelable(false);
//        logoutDialog.setPositiveButton(R.string.logout_dialog_btn_ok, null);
//        logoutDialog.show();
//    }
//
//    @Override
//    public void successLogin() {
//        Bundle bndl = new Bundle();
//        bndl.putString("nav", "MAIN");
//        Intent intent = new Intent(getContext(), MapsActivity.class);// menuViewImpl.class);//MainMenuContainerActivity
//        intent.putExtras(bndl);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);//
//        startActivity(intent);
//    }
//
//    @Override
//    public void showFragmentChangePasswordV2(Bundle bundle) {
//        edtPassword.setText(null);
//        edtUserOrEmail.setText(null);
//        FragmentManager manager = getActivity().getSupportFragmentManager();
//        FragmentTransaction transaction = manager.beginTransaction();
////        ChangePasswordViewImpl changePasswordView = new ChangePasswordViewImpl();
////        changePasswordView.setArguments(bundle);
////        transaction.replace(R.id.login_container, changePasswordView).commit();
//    }
//
//    @Override
//    public void setUserData(String user, String password) {
//        // edtUserOrEmail.setText(user);
//        // edtPassword.setText(password);
//    }
}
