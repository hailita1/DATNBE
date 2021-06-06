package com.example.demo.service;

public class SignalRConnectionInfo {
    public SignalRConnectionInfo() {
    }

    public SignalRConnectionInfo(String url, String accessToken) {
        this.url = url;
        this.accessToken = accessToken;
    }

    public String url;
    public String accessToken;
}
