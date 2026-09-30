package com.leitzke.apialunos.exception;

public class AlunoNaoEncontradoException extends RuntimeException {

    public AlunoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
