package com.leitzke.apialunos.service;

import com.leitzke.apialunos.dto.AlunoRequest;
import com.leitzke.apialunos.exception.AlunoNaoEncontradoException;
import com.leitzke.apialunos.exception.EmailCadastradoException;
import com.leitzke.apialunos.model.Aluno;
import com.leitzke.apialunos.dto.AlunoResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlunoService {

    private final List<Aluno> alunos;
    private int id = 1;

    public AlunoService() {
        alunos = new ArrayList<Aluno>();
    }

    public List<AlunoResponse> listarAlunos() {
        List<Aluno> alunosModel = alunos;
        List<AlunoResponse> alunosResponse = new ArrayList<>();

        for (Aluno a : alunosModel){
            alunosResponse.add(new AlunoResponse(a.getId(), a.getNome(), a.getEmail(), a.getDataNascimento(), a.getMedia()));
        }

        return alunosResponse;
    }

    public AlunoResponse obterAlunoPorId(int id) {
        for (Aluno a : alunos) {
            if (a.getId() == id) {
                return new AlunoResponse(id, a.getNome(), a.getEmail(), a.getDataNascimento(), a.getMedia());
            }
        }
        throw new AlunoNaoEncontradoException("Aluno não encontrado");
    }

    public AlunoResponse cadastrarAluno(AlunoRequest request){

        for (Aluno a : alunos) {
            if (a.getEmail().equalsIgnoreCase(request.getEmail())){
                throw new EmailCadastradoException("Email já cadastrado");
            }
        }

        alunos.add(new Aluno(id, request.getNome(), request.getEmail(), request.getSenha(),
                request.getDataNascimento(), request.getMedia()));

        id++;
        Aluno alunoCadastrado = alunos.get(alunos.size()-1);
        return new AlunoResponse(alunoCadastrado.getId(), alunoCadastrado.getNome(), alunoCadastrado.getEmail(), alunoCadastrado.getDataNascimento(), alunoCadastrado.getMedia());
    }

    public AlunoResponse atualizarAluno(int id, AlunoRequest request){

        for (Aluno a : alunos) {
            if (request.getEmail().equalsIgnoreCase(a.getEmail()) && id != a.getId()) {
                throw new EmailCadastradoException("Email já cadastrado");
            }
        }

        for (Aluno a : alunos) {
            if (a.getId() == id) {
                a.setNome(request.getNome());
                a.setEmail(request.getEmail());
                a.setSenha(request.getSenha());
                a.setDataNascimento(request.getDataNascimento());
                a.setMedia(request.getMedia());

                return new AlunoResponse(id, a.getNome(), a.getEmail(), a.getDataNascimento(), a.getMedia());
            }
        }

        throw new AlunoNaoEncontradoException("Aluno não encontrado");
    }

    public void excluirAluno(int id){
        for (Aluno a : alunos) {
            if (a.getId() == id) {
                alunos.remove(a);
                return;
            }
        }
    }
}
