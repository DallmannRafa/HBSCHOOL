package br.com.hbsis.boletim;

import java.math.BigDecimal;

public class BoletimModel {

    private String aluno;
    private String periodo;
    private String materia;
    private String nota1;
    private String nota2;
    private String nota3;
    private String nota4;
    private String media;


    public BoletimModel() {
        this.nota1 = "--";
        this.nota2 = "--";
        this.nota3 = "--";
        this.nota4 = "--";

    }

    public void setMedia(String nota1, String nota2, String nota3, String nota4) {
        int contador = 0;
        Double calculo = 0.0;

        if(!(nota1.equals("--"))) {
            contador++;
            calculo = calculo + Double.parseDouble(nota1);
        }
        if(!(nota2.equals("--"))) {
            contador++;
            calculo = calculo + Double.parseDouble(nota2);
        }
        if(!(nota3.equals("--"))) {
            contador++;
            calculo = calculo + Double.parseDouble(nota3);
        }
        if(!(nota4.equals("--"))) {
            contador++;
            calculo = calculo + Double.parseDouble(nota4);
        }

        BigDecimal bigDecimal = new BigDecimal(calculo / contador).setScale(2, BigDecimal.ROUND_HALF_UP);

        this.media = bigDecimal.toString();

    }

    public String getAluno() {
        return aluno;
    }

    public void setAluno(String aluno) {
        this.aluno = aluno;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getNota1() {
        return nota1;
    }

    public void setNota1(Double nota1) {
        this.nota1 = nota1.toString();
        this.setMedia(this.nota1, this.nota2, this.nota3, this.nota4);
    }

    public String getNota2() {
        return nota2;
    }

    public void setNota2(Double nota2) {

        this.nota2 = nota2.toString();
        this.setMedia(this.nota1, this.nota2, this.nota3, this.nota4);
    }

    public String getNota3() {
        return nota3;
    }

    public void setNota3(Double nota3) {

        this.nota3 = nota3.toString();
        this.setMedia(this.nota1, this.nota2, this.nota3, this.nota4);
    }

    public String getNota4() {
        return nota4;
    }

    public void setNota4(Double nota4) {

        this.nota4 = nota4.toString();
        this.setMedia(this.nota1, this.nota2, this.nota3, this.nota4);
    }

    public String getMedia() {
        return media;
    }

    @Override
    public String toString() {
        return "BoletimModel{" +
                "aluno='" + aluno + '\'' +
                ", periodo='" + periodo + '\'' +
                ", Materia='" + materia + '\'' +
                ", nota1=" + nota1 +
                ", nota2=" + nota2 +
                ", nota3=" + nota3 +
                ", nota4=" + nota4 +
                ", media=" + media +
                '}';
    }
}
