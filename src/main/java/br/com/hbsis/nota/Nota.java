package br.com.hbsis.nota;

import br.com.hbsis.boletim.Boletim;
import br.com.hbsis.materia.Materia;

import javax.persistence.*;

@Entity
@Table(name = "nota")
public class Nota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nota")
    private Double nota;

    @ManyToOne
    @JoinColumn(name = "materia", referencedColumnName = "id")
    private Materia materia;
    @ManyToOne
    @JoinColumn(name = "boletim", referencedColumnName = "id")
    private Boletim boletim;

    public Long getId() {
        return id;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public Boletim getBoletim() {
        return boletim;
    }

    public void setBoletim(Boletim boletim) {
        this.boletim = boletim;
    }

    @Override
    public String toString() {
        return "Nota{" +
                "id=" + id +
                ", nota=" + nota +
                ", materia=" + materia +
                ", boletim=" + boletim +
                '}';
    }
}
