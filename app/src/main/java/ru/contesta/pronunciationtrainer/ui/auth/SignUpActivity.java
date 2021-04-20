package ru.contesta.pronunciationtrainer.ui.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.contesta.pronunciationtrainer.R;
import ru.contesta.pronunciationtrainer.api.models.AuthorizationRequest;
import ru.contesta.pronunciationtrainer.api.models.RegistrationRequest;
import ru.contesta.pronunciationtrainer.api.responses.AuthorizationResponse;
import ru.contesta.pronunciationtrainer.api.responses.RegistrationResponse;
import ru.contesta.pronunciationtrainer.api.service.NetworkService;
import ru.contesta.pronunciationtrainer.ui.MainActivity;

public class SignUpActivity extends AppCompatActivity {

    Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme_NoActionBar);
        setContentView(R.layout.activity_sign_up);

        signUp = findViewById(R.id.sign_up);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // register();
                Intent intentToGameActivity = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(intentToGameActivity);
            }
        });

    }

    private static String token;

    private void register() {
        RegistrationRequest registrationRequest = new RegistrationRequest("rp.inmovery@gmail.com", "Pablo22890*");
        NetworkService.getInstance()
                .getRequestsApi()
                .register(token, registrationRequest)
                .enqueue(new Callback<RegistrationResponse>() {
                    @Override
                    public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(SignUpActivity.this, response.body().getProfile().getLogin(), Toast.LENGTH_SHORT).show();
                            RegistrationResponse registrationResponse = response.body();

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                                }
                            }, 700);

                        } else {
                            Toast.makeText(SignUpActivity.this, "something went wrong :(", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<RegistrationResponse> call, Throwable t) {
                        Toast.makeText(SignUpActivity.this, "something went wrong :(", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}