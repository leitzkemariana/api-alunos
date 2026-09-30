package com.leitzke.apialunos.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class AlunoRequest {

    @NotBlank(message = "Nome não pode ser vazio")
    @Size(max = 50, message = "Nome tem ter menos que 50 caracteres")
    private String nome;

    @NotBlank(message = "Email não pode ser vazio")
    @Email(message = "Email com formato inválido")
    @Size(max = 50, min = 5, message = "Email deve ter entre 5 e 50 caracteres")
    private String email;

    @NotBlank(message = "Senha não pode ser vazia")
    @Size(max = 50, message = "A senha deve ter menos de 50 caracteres")
    private String senha;

    @NotNull(message = "Data de nascimento não pode ser vazia")
    @PastOrPresent(message = "Data de nascimento não pode ser futura")
    private LocalDate dataNascimento;

    //@Min(value = 0)
    @PositiveOrZero(message = "Média deve ser maior ou igual a 0")
    @Max(value = 10, message = "Média não pode ser acima de 10")
    private double media;

    public AlunoRequest(String nome, String email, String senha, LocalDate dataNascimento, double media) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.dataNascimento = dataNascimento;
        this.media = media;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public double getMedia() {
        return media;
    }

    public void setMedia(double media) {
        this.media = media;
    }
}
