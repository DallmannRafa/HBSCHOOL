package br.com.hbsis.materia;

public class MateriaDTO {

    private Long id;
    private String nome;

    public MateriaDTO(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public static MateriaDTO of(Materia materia) {
        return new MateriaDTO(
                materia.getId(),
                materia.getNome()
        );
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "MateriaDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
