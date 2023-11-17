package com.example.smartparkpj.service;

public interface MailSenderService {
    boolean sendMailByAddMember(String mailTo) throws Exception;
    String getConfirmKey();
}
