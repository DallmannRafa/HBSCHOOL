package br.com.hbsis.boletim;

import br.com.hbsis.aluno.AlunoService;
import br.com.hbsis.materia.MateriaService;
import br.com.hbsis.nota.Nota;
import br.com.hbsis.nota.NotaDTO;
import br.com.hbsis.periodo.PeriodoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BoletimService {

    private final IBoletimRepository iBoletimRepository;
    private final AlunoService alunoService;
    private final PeriodoService periodoService;
    private final MateriaService materiaService;

    public BoletimService(IBoletimRepository iBoletimRepository, AlunoService alunoService, PeriodoService periodoService, MateriaService materiaService) {
        this.iBoletimRepository = iBoletimRepository;
        this.alunoService = alunoService;
        this.periodoService = periodoService;
        this.materiaService = materiaService;
    }

    public BoletimDTO save(BoletimDTO boletimDTO) {
        this.validate(boletimDTO);

        Boletim boletim = new Boletim();

        boletim.setAluno(this.alunoService.findAlunoById(boletimDTO.getAluno().getId()));
        boletim.setPeriodo(this.periodoService.findPeriodoById(boletimDTO.getPeriodo().getId()));
        boletim.setNotas(this.populateNotas(boletimDTO.getNotas(), boletim));

        this.iBoletimRepository.save(boletim);

        return BoletimDTO.of(boletim);
    }

    public BoletimDTO update(BoletimDTO boletimDTO, Long id) {
        Optional<Boletim> boletimOptional = this.iBoletimRepository.findById(id);

        if (boletimOptional.isPresent()) {
            Boletim boletim = boletimOptional.get();

            boletim.setAluno(this.alunoService.findAlunoById(boletimDTO.getAluno().getId()));
            boletim.setPeriodo(this.periodoService.findPeriodoById(boletimDTO.getPeriodo().getId()));
            boletim.updatenotas(this.populateNotas(boletimDTO.getNotas(), boletim));


            this.iBoletimRepository.save(boletim);

            return BoletimDTO.of(boletim);
        }

        throw new IllegalArgumentException(String.format("Não existe boletim com o id %s", id));
    }


    public Boletim findBoletimById(Long id) {
        Optional<Boletim> boletimOptional = this.iBoletimRepository.findById(id);

        if (boletimOptional.isPresent()) {
            return boletimOptional.get();
        }

        throw new IllegalArgumentException(String.format("Não existe Boletim com o id %s", id));
    }

    public BoletimDTO findById(Long id) {
        Optional<Boletim> boletimOptional = this.iBoletimRepository.findById(id);

        if (boletimOptional.isPresent()) {
            return BoletimDTO.of(boletimOptional.get());
        }

        throw new IllegalArgumentException(String.format("Não existe Boletim com o id %s", id));
    }

    public void delete(Long id) {
        this.iBoletimRepository.deleteById(id);
    }

    private void validate(BoletimDTO boletimDTO) {
        if (boletimDTO == null) {
            throw new IllegalArgumentException("Boletim não pode ser nulo!");
        }
        if (boletimDTO.getAluno() == null) {
            throw new IllegalArgumentException("Aluno não pode ser nulo!");
        }
        if (boletimDTO.getPeriodo() == null) {
            throw new IllegalArgumentException("Periodo não pode ser nulo!");
        }

    }

    private List<Nota> populateNotas(List<NotaDTO> notas, Boletim boletim) {
        List<Nota> returnNotas = new ArrayList<>();

        for (NotaDTO notaDTO : notas) {
            Nota nota = new Nota();

            nota.setBoletim(boletim);
            nota.setNota(notaDTO.getNota());
            nota.setMateria(this.materiaService.findMateriaById(notaDTO.getMateria().getId()));

            returnNotas.add(nota);
        }

        return returnNotas;
    }
}
