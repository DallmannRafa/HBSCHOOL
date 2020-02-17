package br.com.hbsis.nota;

import br.com.hbsis.materia.Materia;

public class NotaDTO {

    private Long id;
    private Double nota;
    private Materia materia;
    private Long boletim;

    public NotaDTO(Long id, Double nota, Materia materia, Long boletim) {
        this.id = id;
        this.nota = nota;
        this.materia = materia;
        this.boletim = boletim;
    }

    public static NotaDTO of(Nota nota) {
        return new NotaDTO(
                nota.getId(),
                nota.getNota(),
                nota.getMateria(),
                nota.getBoletim().getId()
        );
    }

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

    public Long getBoletim() {
        return boletim;
    }

    public void setBoletim(Long boletim) {
        this.boletim = boletim;
    }

    @Override
    public String toString() {
        return "NotaDTO{" +
                "id=" + id +
                ", nota=" + nota +
                ", materia=" + materia +
                ", boletim=" + boletim +
                '}';
    }
}
