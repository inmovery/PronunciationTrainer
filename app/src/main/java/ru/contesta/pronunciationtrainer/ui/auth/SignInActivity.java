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
import ru.contesta.pronunciationtrainer.api.responses.AuthorizationResponse;
import ru.contesta.pronunciationtrainer.api.service.NetworkService;
import ru.contesta.pronunciationtrainer.ui.AudioTest;
import ru.contesta.pronunciationtrainer.ui.MainActivity;

public class SignInActivity extends AppCompatActivity {

    private Button loginCommand;
    private Button registerCommand;

    private Button audioTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        audioTest = findViewById(R.id.audio_test);

        loginCommand = findViewById(R.id.sign_in);
        registerCommand = findViewById(R.id.sign_up);

        loginCommand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        registerCommand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // getSecret();
            }
        });

        audioTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignInActivity.this, AudioTest.class));
            }
        });
    }

    private static String token;

    private void login() {
        AuthorizationRequest authorizationRequest = new AuthorizationRequest("rp.inmovery@gmail.com", "Pablo22890*");
        NetworkService.getInstance()
                .getRequestsApi()
                .auth(authorizationRequest)
                .enqueue(new Callback<AuthorizationResponse>() {
                    @Override
                    public void onResponse(Call<AuthorizationResponse> call, Response<AuthorizationResponse> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(SignInActivity.this, response.body().getAccessToken(), Toast.LENGTH_SHORT).show();
                            AuthorizationResponse authorizationResponse = response.body();

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    startActivity(new Intent(SignInActivity.this, MainActivity.class).putExtra("token", authorizationResponse.getAccessToken()));
                                }
                            }, 700);

                        } else {
                            Toast.makeText(SignInActivity.this, "login not correct :(", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<AuthorizationResponse> call, Throwable t) {
                        Toast.makeText(SignInActivity.this, "error :(", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    /*
    private void getSecret(){
        Call<ResponseBody> call = networkRequests.getSecret(token);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        Toast.makeText(SignInActivity.this, response.body().string(), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(SignInActivity.this, "token is not correct :(", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(SignInActivity.this, "error :(", Toast.LENGTH_SHORT).show();
            }
        });
    }
    */
}