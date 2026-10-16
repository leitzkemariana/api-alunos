package com.leitzke.apialunos.service;

import com.leitzke.apialunos.dto.AlunoRequest;
import com.leitzke.apialunos.dto.AlunoResponse;
import com.leitzke.apialunos.exception.AlunoNaoEncontradoException;
import com.leitzke.apialunos.exception.EmailCadastradoException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class AlunoServiceTest {

    private AlunoService service;

    @BeforeEach
    void setUp(){
        service = new AlunoService();
    }

    @Test
    void deveCadastrarAlunoQuandoDadosForemValidos(){
        //Arrange
        AlunoRequest request = new AlunoRequest("Luisa", "lu@gmail.com", "senha", LocalDate.of(2000,04,05), 8);

        //Act
        AlunoResponse response = service.cadastrarAluno(request);

        //Assert
        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getId()).isGreaterThan(0);
    }

    @Test
    void deveLancarExcecaoQuandoEmailDuplicado(){
        AlunoRequest request1 = new AlunoRequest("Luisa", "lu@gmail.com", "senha", LocalDate.of(2000,04,05), 8);
        AlunoRequest request2 = new AlunoRequest("Luana", "lu@gmail.com", "senha", LocalDate.of(2000,03,05), 8);

        service.cadastrarAluno(request1);

        Assertions.assertThatThrownBy(() -> service.cadastrarAluno(request2)).isInstanceOf(EmailCadastradoException.class).hasMessage("Email já cadastrado");
    }

    @Test
    void deveLancarExcecaoQuandoEmailDuplicadoAoAtualizar(){
        AlunoRequest request1 = new AlunoRequest("Luisa", "lu@gmail.com", "senha", LocalDate.of(2000,04,05), 8);
        AlunoRequest request2 = new AlunoRequest("Luana", "lu@gmail.com", "senha", LocalDate.of(2000,03,05), 8);

        service.cadastrarAluno(request1);

        Assertions.assertThatThrownBy(() -> service.atualizarAluno(2, request2)).isInstanceOf(EmailCadastradoException.class).hasMessage("Email já cadastrado");
    }

    @Test
    void deveLancarExcecaoQuandoAlunoNaoExisteAoAtualizar(){
        AlunoRequest request1 = new AlunoRequest("Luisa", "lu@gmail.com", "senha", LocalDate.of(2000,04,05), 8);

        Assertions.assertThatThrownBy(() -> service.atualizarAluno(10, request1)).isInstanceOf(AlunoNaoEncontradoException.class).hasMessage("Aluno não encontrado");
    }

    @Test
    void deveLancarExcecaoQuandoAlunoNaoExiste(){
        Assertions.assertThatThrownBy(() -> service.obterAlunoPorId(100)).isInstanceOf(AlunoNaoEncontradoException.class).hasMessage("Aluno não encontrado");
    }
}
