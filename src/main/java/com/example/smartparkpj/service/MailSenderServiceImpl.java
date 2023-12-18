package com.example.smartparkpj.service;

import com.example.smartparkpj.dto.InquiryDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Random;

@Log4j2
@Service
public class MailSenderServiceImpl implements MailSenderService{
    @Autowired
    private JavaMailSender mailSender; // 메일을 보내는 역할

    private final TemplateEngine templateEngine;

    private String confirmKey; // 인증키

    @Value("${myapp.custom.mail.sender.mailFrom}")
    private String mailFrom; // 보내는 메일

    @Value("${myapp.custom.mail.sender.mailFromName}")
    private String mailFromName; // 보내는 사람

    public MailSenderServiceImpl(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @Override
    public boolean sendMailByAddMember(String mailTo) throws Exception { // 회원 가입시 인증 메일 발송
        this.confirmKey = createConfirmkey();
        MimeMessage message = createMessageByAddMember(mailTo);
        try { // 예외처리
            mailSender.send(message);
            return true;
        } catch (MailException e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String getConfirmKey() {
        return this.confirmKey;
    }

    private MimeMessage createMessageByAddMember(String mailTo) throws Exception {
        // 회원 가입시 인증 메일 관련 내용 작성
        log.info("보내는 대상 : " + mailTo);
        log.info("인증 번호 : " + confirmKey);

        // 메일 템플릿에 전달할 데이터 설정
        Context context = new Context();
        context.setVariable("confirmKey", confirmKey);
        // Thymeleaf 템플릿 엔진을 사용하여 메일 본문 생성
        String messageText = templateEngine.process("mail/add_member", context);

        MimeMessage message = mailSender.createMimeMessage();
        message.addRecipients(Message.RecipientType.TO, mailTo); // 보내는 대상
        message.setSubject(mailFromName + "이메일 인증"); // 제목
        message.setText(messageText, "UTF-8", "html"); // 내용
        message.setFrom(new InternetAddress(mailFrom, mailFromName)); // 보내는 사람

        return message;
    }

    private static String createConfirmkey() {
        // 인증키 작성
        StringBuilder key = new StringBuilder();
        Random rnd = new Random();

        for (int i = 0; i < 8; i++){ // 인증코드 8자리
            int index = rnd.nextInt(3); // 0~2 까지 렌덤

            switch(index) {
                case 0:
                    key.append((char) ((int) (rnd.nextInt(26)) + 97));
                    // 0~2 (ex. 1 + 97 = 98 => (char)98 = 'b'
                    break;
                case 1:
                    key.append((char) ((int) (rnd.nextInt(26)) + 65));
                    // A~Z
                    break;
                case 2:
                    key.append((rnd.nextInt(10)));
                    // 0~9
                    break;
            }
        }
        return key.toString();
    }


    // 문의글 메일전송 관련된코드 - 오승훈
    @Override
    public boolean sendReplyEmail(String to, String subject, String text) {    // 문의글 답변시 전송

        log.info(to);
        log.info(subject);
        log.info(text);

        try {
            MimeMessage message = replyMessageByInquiry(to, subject, text);
            mailSender.send(message);
            System.out.println("이메일이 성공적으로 전송되었습니다.");
            return true;

        } catch (Exception e) {
            System.out.println("이메일 전송 중 오류가 발생했습니다: " + e.getMessage());
            return false;
        }
    }
    
    
    private MimeMessage replyMessageByInquiry(String mailTo, String subject, String text) throws Exception {
        // 회원 가입시 인증 메일 관련 내용 작성

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);


        helper.setTo(mailTo);
        helper.setSubject(subject);
        helper.setText(text, true); // true: HTML 형식으로 전송

        message.addRecipients(Message.RecipientType.TO, mailTo);    // 보내는 대상
        message.setSubject("Smart_Park 문의답변 완료");   // 제목

        String messageText ="";
        messageText += "<div style='margin:20px;'>";
        messageText += "<br>";
        messageText += "<p>문의 하신 글에 답변이 달렸습니다 </p>";
        messageText += "<br>";
        message.setText(messageText,"utf-8","html");    // 내용
        message.setFrom(new InternetAddress(mailFrom, mailFromName));   // 보내는사람

        return message;
    }
}
