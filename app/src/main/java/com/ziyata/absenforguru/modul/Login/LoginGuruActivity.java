package com.ziyata.absenforguru.modul.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.ziyata.absenforguru.MainActivity;
import com.ziyata.absenforguru.MainApp;
import com.ziyata.absenforguru.R;
import com.ziyata.absenforguru.api.parameters.DoRequestLogin;
import com.ziyata.absenforguru.api.parameters.DoResponseLogin;
import com.ziyata.absenforguru.api.rest.ServiceGenerator;
import com.ziyata.absenforguru.api.rest.UserService;
import com.ziyata.absenforguru.db.Siswa;
import com.ziyata.absenforguru.modul.register.RegisterGuruActivity;
import com.ziyata.absenforguru.utils.DialogActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginGuruActivity extends DialogActivity implements Validator.ValidationListener{

    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.signIn_password)
    TextInputEditText signInPassword;
    @BindView(R.id.loginBtn)
    Button loginBtn;
    @BindView(R.id.register)
    TextView register;
    Validator validator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_guru);
        initialComponents();
    }

    private void initialComponents() {
        ButterKnife.bind(this);
        validator = new Validator(this);
        validator.setValidationListener(this);
//       login button
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validator.validate();
            }
        });
//      register
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginGuruActivity.this, RegisterGuruActivity.class);
                startActivity(i);
            }
        });

    }


    @Override
    public void onValidationSucceeded() {
        onSignIn();
    }

    private void onSignIn() {
        showProgressDialog("Please Wait !");
        DoRequestLogin request = new DoRequestLogin();
        request.setUsername(username.getText().toString());
        request.setPassword(signInPassword.getText().toString());
        final Realm realm = Realm.getDefaultInstance();
        UserService service = ServiceGenerator.createService(UserService.class , request.getUsername() , request.getPassword());
        service.login(request).enqueue(new Callback<DoResponseLogin>() {
            @Override
            public void onResponse(Call<DoResponseLogin> call, Response<DoResponseLogin> response) {
                hideProgressDialog();
                if (response.isSuccessful()){
                    if (response.body().getResponseStatus().equalsIgnoreCase("200")){
                        Siswa siswa = response.body().getData();
                        saveUser(siswa);
                    }else {
                        Toast.makeText(LoginGuruActivity.this, "Username atau Password salah", Toast.LENGTH_SHORT).show();

                    }
                }
            }

            @Override
            public void onFailure(Call<DoResponseLogin> call, Throwable t) {
                hideProgressDialog();
                t.printStackTrace();
                Toast.makeText(LoginGuruActivity.this, "System error: " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void saveUser(Siswa siswa) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.delete(Siswa.class);
        realm.copyToRealm(siswa);
        MainApp.getInstance(LoginGuruActivity.this).setSiswa(siswa);
        Intent i = new Intent(LoginGuruActivity.this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors){
            View view = error.getView();
            String massage = error.getCollatedErrorMessage(this);
            if (view instanceof EditText){
                ((EditText)view).setError(massage);
            }else {
                Toast.makeText(this, massage, Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    public void onBackPressed() {
        finish();
    }

}
