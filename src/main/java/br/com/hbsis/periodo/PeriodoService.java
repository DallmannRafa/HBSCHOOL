package br.com.hbsis.periodo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class PeriodoService {

    private final IPeriodoRepository iPeriodoRepository;

    @Autowired
    public PeriodoService(IPeriodoRepository iPeriodoRepository) {
        this.iPeriodoRepository = iPeriodoRepository;
    }

    public PeriodoDTO save(PeriodoDTO periodoDTO) {
        this.validate(periodoDTO);

        Periodo periodo = new Periodo();

        periodo.setNome(periodoDTO.getNome());
        periodo.setDataInicio(periodoDTO.getDataInicio());
        periodo.setDataFim(periodoDTO.getDataFim());

        this.iPeriodoRepository.save(periodo);

        return PeriodoDTO.of(periodo);
    }

    public PeriodoDTO update(PeriodoDTO periodoDTO, Long id) {
        Optional<Periodo> periodoOptional = this.iPeriodoRepository.findById(id);

        if (periodoOptional.isPresent()) {
            Periodo periodo = periodoOptional.get();

            this.validate(periodoDTO);

            periodo.setNome(periodoDTO.getNome());
            periodo.setDataInicio(periodoDTO.getDataInicio());
            periodo.setDataFim(periodoDTO.getDataFim());

            this.iPeriodoRepository.save(periodo);

            return PeriodoDTO.of(periodo);
        }

        throw new IllegalArgumentException(String.format("Periodo com id %s não existe", id));
    }

    public PeriodoDTO findById(Long id) {
        Optional<Periodo> periodoOptional = this.iPeriodoRepository.findById(id);

        if (periodoOptional.isPresent()) {
            return PeriodoDTO.of(periodoOptional.get());
        }

        throw new IllegalArgumentException(String.format("Periodo com id %s não existe", id));
    }

    public Periodo findPeriodoById(Long id) {
        Optional<Periodo> periodoOptional = this.iPeriodoRepository.findById(id);

        if (periodoOptional.isPresent()) {
            return periodoOptional.get();
        }

        throw new IllegalArgumentException(String.format("Periodo com id %s não existe", id));
    }

    public void delete(Long id) {
        this.iPeriodoRepository.deleteById(id);
    }

    private void validate(PeriodoDTO periodoDTO) {
        if (periodoDTO == null) {
            throw new IllegalArgumentException("Periodo não pode ser nulo!");
        }

        if (StringUtils.isEmpty(periodoDTO.getNome())) {
            throw new IllegalArgumentException("Nome do periodo não pode ser nulo");
        }

        if (StringUtils.isEmpty(periodoDTO.getDataInicio().toString())) {
            throw new IllegalArgumentException("A data de inicio não pode ser nula!");
        }

        if (StringUtils.isEmpty(periodoDTO.getDataFim().toString())) {
            throw new IllegalArgumentException("A data de fim não pode ser nula!");
        }

        if (periodoDTO.getDataInicio().isAfter(periodoDTO.getDataFim())) {
            throw new IllegalArgumentException("A ordem das datas não está correta!");
        }
    }
}
