package br.com.hbsis.boletim;

import br.com.hbsis.aluno.Aluno;
import br.com.hbsis.periodo.Periodo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IBoletimRepository extends JpaRepository<Boletim, Long> {

    Optional<Boletim> findByAlunoAndPeriodo(Aluno aluno, Periodo periodo);
}
