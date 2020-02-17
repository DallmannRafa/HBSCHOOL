package br.com.hbsis.boletim;

import br.com.hbsis.aluno.Aluno;
import br.com.hbsis.nota.Nota;
import br.com.hbsis.nota.NotaDTO;
import br.com.hbsis.periodo.Periodo;

import java.util.ArrayList;
import java.util.List;

public class BoletimDTO {

    private Long id;
    private Aluno aluno;
    private Periodo periodo;
    private List<NotaDTO> notas;

    public BoletimDTO(Long id, Aluno aluno, Periodo periodo, List<NotaDTO> notas) {
        this.id = id;
        this.aluno = aluno;
        this.periodo = periodo;
        this.notas = notas;
    }

    public static BoletimDTO of(Boletim boletim) {
        List<NotaDTO> notas = new ArrayList<>();
        for (Nota nota : boletim.getNotas()) {
            notas.add(NotaDTO.of(nota));
        }

        return new BoletimDTO(
                boletim.getId(),
                boletim.getAluno(),
                boletim.getPeriodo(),
                notas
        );
    }

    public Long getId() {
        return id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    public List<NotaDTO> getNotas() {
        return notas;
    }

    public void setNotas(List<NotaDTO> notas) {
        this.notas = notas;
    }

    @Override
    public String toString() {
        return "BoletimDTO{" +
                "id=" + id +
                ", aluno=" + aluno +
                ", periodo=" + periodo +
                ", nota=" + notas +
                '}';
    }
}
