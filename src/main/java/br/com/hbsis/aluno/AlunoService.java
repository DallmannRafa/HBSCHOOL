package br.com.hbsis.aluno;


import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class AlunoService {

    private final IAlunoRepository iAlunoRepository;

    public AlunoService(IAlunoRepository iAlunoRepository) {
        this.iAlunoRepository = iAlunoRepository;
    }

    public AlunoDTO save(AlunoDTO alunoDTO) {

        this.validate(alunoDTO);

        Aluno aluno = new Aluno();

        aluno.setNome(alunoDTO.getNome());

        this.iAlunoRepository.save(aluno);

        return AlunoDTO.of(aluno);

    }

    public AlunoDTO update(AlunoDTO alunoDTO, Long id) {
        Optional<Aluno> alunoOptional = this.iAlunoRepository.findById(id);

        if (alunoOptional.isPresent()) {
            Aluno aluno = alunoOptional.get();

            this.validate(alunoDTO);

            aluno.setNome(alunoDTO.getNome());

            this.iAlunoRepository.save(aluno);
            return AlunoDTO.of(aluno);
        }

        throw new IllegalArgumentException(String.format("Aluno com id %s não existe", id));

    }

    public AlunoDTO findById(Long id) {
        Optional<Aluno> alunoOptional = this.iAlunoRepository.findById(id);

        if (alunoOptional.isPresent()) {
            return AlunoDTO.of(alunoOptional.get());
        }

        throw new IllegalArgumentException(String.format("Aluno com id %s não existe", id));
    }

    public Aluno findAlunoById(Long id) {
        Optional<Aluno> alunoOptional = this.iAlunoRepository.findById(id);

        if (alunoOptional.isPresent()) {
            return alunoOptional.get();
        }

        throw new IllegalArgumentException(String.format("Aluno com id %s não existe", id));
    }

    public void delete(Long id) {
        this.iAlunoRepository.deleteById(id);
    }


    public void validate(AlunoDTO alunoDTO) {

        if (alunoDTO == null) {
            throw new IllegalArgumentException("Aluno não pode ser nulo!");
        }

        if (StringUtils.isEmpty(alunoDTO.getNome())) {
            throw new IllegalArgumentException("Nome do aluno não pode ser nulo!");
        }
    }

}
