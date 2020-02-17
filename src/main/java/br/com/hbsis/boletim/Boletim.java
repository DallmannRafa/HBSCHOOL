package br.com.hbsis.boletim;

import br.com.hbsis.aluno.Aluno;
import br.com.hbsis.nota.Nota;
import br.com.hbsis.periodo.Periodo;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "boletim")
public class Boletim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "aluno", referencedColumnName = "id")
    private Aluno aluno;
    @ManyToOne
    @JoinColumn(name = "periodo", referencedColumnName = "id")
    private Periodo periodo;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "boletim", orphanRemoval = true)
    private List<Nota> notas;

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

    public List<Nota> getNotas() {
        return notas;
    }

    public void setNotas(List<Nota> notas) {
        this.notas = notas;
    }

    public void updatenotas(List<Nota> notas) {
        this.notas.clear();
        this.notas.addAll(notas);
    }

    @Override
    public String toString() {
        return "Boletim{" +
                "id=" + id +
                ", aluno=" + aluno +
                ", periodo=" + periodo +
                ", nota=" + notas +
                '}';
    }
}
