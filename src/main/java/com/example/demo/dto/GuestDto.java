package com.example.demo.dto;

import com.example.demo.enumerated.EmailStatus;
import com.example.demo.enumerated.RSVPStatus;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import lombok.ToString;

@ToString
@JsonPropertyOrder({"id", "name", "email", "phone", "status"})
public class GuestDto {

    int id;

    @NotBlank(message = "guest name is required")
    private String name;

    @NotBlank(message = "guest email is required")
    private String email;

    @Digits(integer = 10, fraction = 0, message = "enter valid phone")
    private long phone;

    private RSVPStatus status = RSVPStatus.PENDING;

    private EmailStatus emailStatus = EmailStatus.PENDING;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public RSVPStatus getStatus() {
        return status;
    }

    public void setStatus(RSVPStatus status) {
        this.status = status;
    }

    public EmailStatus getEmailStatus() {
        return emailStatus;
    }

    public void setEmailStatus(EmailStatus emailStatus) {
        this.emailStatus = emailStatus;
    }
}
