package br.com.associado.error;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ExceptionResponse {

  private String mensagem;
  private LocalDate data;

  public ExceptionResponse(LocalDate timestamp, String message, String details,String httpCodeMessage) {
    super();
    this.data = timestamp;
    this.mensagem = message;
  }
  
}