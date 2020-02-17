package br.com.hbsis.aluno;

public class AlunoDTO {

    private long id;
    private String nome;

    public AlunoDTO(long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public static AlunoDTO of(Aluno aluno) {
        return new AlunoDTO(
                aluno.getId(),
                aluno.getNome()
        );
    }

    public long getId() {
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
        return "AlunoDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
