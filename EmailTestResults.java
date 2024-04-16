package org.example;

import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import org.testng.annotations.*;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

public class EmailTestResults extends TestListenerAdapter {

    public static void main(String[] args) {
        // Create TestNG instance
        TestNG testng = new TestNG();

        // Load testng.xml file
        List<XmlSuite> suites = new ArrayList<>();
        XmlSuite suite = new XmlSuite();
        suite.setSuiteFiles(Arrays.asList("testng.xml")); // Replace with your testng.xml file path
        suites.add(suite);
        testng.setXmlSuites(suites);

        // Run TestNG tests
        testng.run();

        // Get TestNG results file path
        String resultFilePath = "test-output/index.html"; // Replace with your TestNG results file path

        // Email TestNG results
        sendEmailWithAttachment("collineasley@gmail.com", "Okstatefan1", "creasley5167@eagle.fgcu.edu", "TestNG Results", "Please find attached TestNG results.", resultFilePath);
    }



    public static void sendEmailWithAttachment(String from, String password, String to, String subject, String body, String attachmentPath) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.example.com"); // Replace with your SMTP server
        props.put("mail.smtp.port", "587"); // Replace with your SMTP port

        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from, password);
                    }
                });

        try {
            // Create a MimeMessage object
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);

            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();

            // Now set the actual message
            messageBodyPart.setText(body);

            // Create a multipart message
            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            // Part two is attachment
            messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(attachmentPath);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(attachmentPath);
            multipart.addBodyPart(messageBodyPart);

            // Set the multipart as the message's content
            message.setContent(multipart);

            // Send message
            Transport.send(message);

            System.out.println("Email sent successfully.");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}


