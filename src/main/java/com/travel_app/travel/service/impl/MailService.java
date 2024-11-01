package com.travel_app.travel.service.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class MailService {


    @Autowired
    private JavaMailSender mailSender;

    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendNewPasswordEmail(String email, String newPassword) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setTo(email);
            helper.setSubject("Travel App - Your Password Has Been Changed");

            String htmlContent = "<h1>Password Change Notification</h1>"
                    + "<p>Dear user,</p>"
                    + "<p>Your password has been successfully changed. Here is your new password:</p>"
                    + "<p><strong>" + newPassword + "</strong></p>"
                    + "<p>Please make sure to change your password after logging in for security reasons.</p>"
                    + "<p>Thank you,<br/>TravelApp</p>";

            helper.setText(htmlContent, true);

            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendCheckInNotificationEmail(String email, String firstName, String lastName, String accommodationName, Integer roomNumber, String checkInDate) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setTo(email);
            helper.setSubject("Check-In Notification");

            // Xây dựng nội dung email HTML
            String htmlContent = "<h1>Check-In Notification</h1>"
                    + "<p>Dear " + firstName + " " + lastName + ",</p>"
                    + "<p>This is a reminder that your check-in date is today:</p>"
                    + "<p><strong>Accommodation: " + accommodationName + "</strong></p>"
                    + "<p><strong>Room Number: " + roomNumber + "</strong></p>"
                    + "<p><strong>Check-In Date: " + checkInDate + "</strong></p>"
                    + "<p>We look forward to your stay with us.</p>"
                    + "<p>Thank you,<br/>TravelApp</p>";

            helper.setText(htmlContent, true);

            // Gửi email
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendCheckOutNotificationEmail(String email, String firstName, String lastName, String accommodationName, Integer roomNumber, String checkOutDate) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setTo(email);
            helper.setSubject("Check-Out Notification");

            // Xây dựng nội dung email HTML
            String htmlContent = "<h1>Check-Out Notification</h1>"
                    + "<p>Dear " + firstName + " " + lastName + ",</p>"
                    + "<p>This is a reminder that your check-out date is today:</p>"
                    + "<p><strong>Accommodation: " + accommodationName + "</strong></p>"
                    + "<p><strong>Room Number: " + roomNumber + "</strong></p>"
                    + "<p><strong>Check-Out Date: " + checkOutDate + "</strong></p>"
                    + "<p>We hope you had a pleasant stay.</p>"
                    + "<p>Thank you,<br/>TravelApp</p>";

            helper.setText(htmlContent, true);

            // Gửi email
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendBookingVehicleNotificationEmail(String email, String firstName, String lastName, Long seatNumber,
                                              String vehicleType, String brand, String startingLocation, String destination,
                                              Integer travelTime, BigDecimal price) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setTo(email);
            helper.setSubject("Check-Out Notification");

            String htmlContent = "<h1>Check-Out Notification</h1>"
                    + "<p>Dear " + firstName + " " + lastName + ",</p>"
                    + "<p>This is a notification about your vehicle booking:</p>"
                    + "<p><strong>Seat Number: " + seatNumber + "</strong></p>"
                    + "<p><strong>Vehicle Type: " + vehicleType + "</strong></p>"
                    + "<p><strong>Brand: " + brand + "</strong></p>"
                    + "<p><strong>Starting Location: " + startingLocation + "</strong></p>"
                    + "<p><strong>Destination: " + destination + "</strong></p>"
                    + "<p><strong>Travel Time: " + travelTime + "</strong></p>"
                    + "<p><strong>Price: " + price + "</strong></p>"
                    + "<p>We hope you had a pleasant journey with us.</p>"
                    + "<p>Thank you,<br/>TravelApp</p>";

            helper.setText(htmlContent, true);

            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}



