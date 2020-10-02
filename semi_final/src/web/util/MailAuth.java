package web.util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;


public class MailAuth extends Authenticator{
    
    PasswordAuthentication pa;
    
    public MailAuth() {
        String mail_id = "haemichuu";
        String mail_pw = "Goalgoa11!";
        
        pa = new PasswordAuthentication(mail_id, mail_pw);
    }
    
    public PasswordAuthentication getPasswordAuthentication() {
        return pa;
    }
}