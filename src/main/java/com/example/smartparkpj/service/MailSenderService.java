package com.example.smartparkpj.service;

public interface MailSenderService {
    boolean sendMailByAddMember(String mailTo) throws Exception;
    String getConfirmKey();


    // 문의글에 대한 답변이 달리면 날아가는 메일
    boolean sendReplyEmail(String to, String subject, String text) throws Exception;

}
