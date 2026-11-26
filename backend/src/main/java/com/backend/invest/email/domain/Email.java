package com.backend.invest.email.domain;

public class Email {
  private EmailType type;
  private String code;
  private String to;
  private String name;

  public Email(EmailType type, String code, String to, String name){
    this.type = type;
    this.code = code;
    this.to = to;
    this.name = name;
  }

  public EmailType getType() {
    return this.type;
  }

  public String getCode() {
    return this.code;
  }

  public String getTo() {
    return this.to;
  }

  public String getName() {
    return this.name;
  }
}
