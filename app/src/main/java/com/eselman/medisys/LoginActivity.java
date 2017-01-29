package com.eselman.medisys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.eselman.medisys.entities.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Evangelina Selman on 26/01/2017.
 */
public class LoginActivity extends AppCompatActivity {

    private static final String USERS_ASSET_FILE = "users.json";

    private EditText usernameInput;
    private EditText passwordInput;
    private Button loginBtn;
    private List<User> authorizedUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getAuthorizedUsers();

        usernameInput = (EditText) findViewById(R.id.username);
        passwordInput = (EditText) findViewById(R.id.password);

        loginBtn = (Button) findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameInput.getText().toString();
                String password = passwordInput.getText().toString();

                if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
                    User currentUser = new User();
                    currentUser.setUsername(username);
                    currentUser.setPassword(password);

                    for (User user : authorizedUsers){
                        if (user.getUsername().equals(currentUser.getUsername()) && user.getPassword().equals(currentUser.getPassword())) {
                            Intent patientsLandingIntent = new Intent(LoginActivity.this, PatientsLandingActivity.class);
                            startActivity(patientsLandingIntent);
                            break;
                        }
                    }
                }
            }
        });
    }

    /**
     * Loads users from json file and parse them to authorized users list.
     */
    private void getAuthorizedUsers(){
        try {
            InputStream usersAssetIS = getApplicationContext().getAssets().open(USERS_ASSET_FILE);
            int size = usersAssetIS.available();
            byte[] buffer = new byte[size];
            usersAssetIS.read(buffer);
            usersAssetIS.close();
            String bufferString = new String(buffer);
            //convert string to JSONArray
            JSONArray usersJsonArray = new JSONArray(bufferString);

            authorizedUsers = new ArrayList<>();
            for (int i=0; i< usersJsonArray.length(); i++) {
                // get each user from json array.
                JSONObject userJsonObject = usersJsonArray.getJSONObject(i);
                User user = new User();
                user.setUsername(userJsonObject.getString("username"));
                user.setPassword(userJsonObject.getString("password"));
                authorizedUsers.add(user);
            }

        } catch (IOException e) {

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
