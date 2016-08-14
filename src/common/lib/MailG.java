package common.lib;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailG {

	public String user;
	public String pass;

	public String from;
	public String fromName;

	public MailG(String user,String pass){
		this.user=user;
		this.pass=pass;
	}
	public void sendMail(String to,String subject,String body){
		Properties pt=new Properties();

		pt.put("mail.smtp.auth", "true");
		pt.put("mail.smtp.starttls.enable", "true");
		pt.put("mail.smtp.host", "smtp.gmail.com");
		pt.put("mail.smtp.port", "587");
		pt.put("mail.smtp.debug", "true");

		Session session = Session.getInstance(pt, new Authenticator(){
			@Override
			protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(user, pass);
            }

		});
		MimeMessage msg = new MimeMessage(session);

		try {

			msg.setRecipients(Message.RecipientType.TO, to);

			msg.setFrom(new InternetAddress(from,fromName));

			msg.setSubject(subject,"ISO-2022-JP");

			msg.setText(body,"ISO-2022-JP");

			msg.setSentDate(new Date());

			Transport.send(msg);
		} catch (AddressException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}
}
