package com.example.sistemapagamento.pix;

import java.io.IOException;
import java.io.InputStream;

import lombok.Getter;
import org.json.JSONObject;
import org.json.JSONTokener;

@Getter
public class Credentials {

    private String clientId;

    private String clientSecret;

    private String certificate;

    private boolean sandbox;

    private boolean debug;

    public Credentials() {

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream credentialsFile = classLoader.getResourceAsStream("credentials.json");
        JSONTokener tokener = new JSONTokener(credentialsFile);
        JSONObject credentials = new JSONObject(tokener);

        try {
            credentialsFile.close();
        } catch (IOException e) {
            System.out.println("Impossible to close file credentials.json");
        }

        this.certificate = credentials.getString("certificate");
        this.sandbox = credentials.getBoolean("sandbox");
        this.debug = credentials.getBoolean("debug");
    }

}