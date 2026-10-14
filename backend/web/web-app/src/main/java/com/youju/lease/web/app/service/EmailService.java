package com.youju.lease.web.app.service;

public interface EmailService {
    void sendLoginCode(String email, String code);
}
