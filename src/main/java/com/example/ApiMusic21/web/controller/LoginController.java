package com.example.ApiMusic21.web.controller;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {
    private final OkHttpClient httpClient = new OkHttpClient.Builder().followRedirects(false).build();;

    @Value("${auth0.audience}")
    private String audience;

    @Value("${auth0.clientId}")
    private String clientId;

    @Value("${auth0.clientSecret}")
    private String clientSecret;

    @GetMapping(value = "/{password}")
    public Map<String,String> getToken(@PathVariable String password){
        HashMap<String,String> result = new HashMap<>();

        if(password.equals("portugeeseNathan")){
            System.out.println("User authentificated");

            //token request
            String url = "https://dev-tkgfp3v2.us.auth0.com/oauth/token";
            String grantType = "client_credentials";

            // form parameters
            RequestBody formBody = new FormBody.Builder()
                    .add("client_id", "dvgKZgQWC33LowY7FW97osEj7N1Gddd1")
                    .add("client_secret", "Om1OLzTiPXt5KNT6FYQInGulb_xAeNV7mAxKRohqcm7-HSuENY5LPyH4fvfp0-7y")
                    .add("audience", "https://security-api.example.com")
                    .add("grant_type",grantType)
                    .build();

            Request request = new Request.Builder()
                    .url(url)
                    .addHeader("Content-Type","application/json")
                    .post(formBody)
                    .build();

            try {
                Response response = httpClient.newCall(request).execute();
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                // Get response body
                String reponse = response.body().string();

                String tokenTest = reponse.split(":")[1];
                String token = tokenTest.split(",")[0].replaceAll("\"","");
                result.put("token",token);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            result.put("token","frerot ça marche pas");
        }

        return result;
    }
}
