package br.com.hbsis.nota;

import br.com.hbsis.boletim.BoletimService;
import br.com.hbsis.materia.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class NotaService {

    private final INotaRepository iNotaRepository;
    private final MateriaService materiaService;
    private final BoletimService boletimService;

    @Autowired
    public NotaService(INotaRepository iNotaRepository, MateriaService materiaService, BoletimService boletimService) {
        this.iNotaRepository = iNotaRepository;
        this.materiaService = materiaService;
        this.boletimService = boletimService;
    }

    public NotaDTO save(NotaDTO notaDTO) {

        this.validate(notaDTO);

        Nota nota = new Nota();

        nota.setNota(notaDTO.getNota());
        nota.setMateria(this.materiaService.findMateriaById(notaDTO.getMateria().getId()));
        nota.setBoletim(this.boletimService.findBoletimById(notaDTO.getBoletim()));

        this.iNotaRepository.save(nota);

        return NotaDTO.of(nota);

    }

    public NotaDTO update(NotaDTO notaDTO, Long id) {
        Optional<Nota> notaOptional = this.iNotaRepository.findById(id);

        if (notaOptional.isPresent()) {
            this.validate(notaDTO);

            Nota nota = notaOptional.get();

            nota.setMateria(this.materiaService.findMateriaById(notaDTO.getMateria().getId()));
            nota.setBoletim(this.boletimService.findBoletimById(notaDTO.getBoletim()));
            nota.setNota(notaDTO.getNota());

            this.iNotaRepository.save(nota);

            return NotaDTO.of(nota);
        }

        throw new IllegalArgumentException(String.format("Não existe Nota com o id %s", id));
    }

    public NotaDTO findById(Long id) {
        Optional<Nota> notaOptional = this.iNotaRepository.findById(id);

        if (notaOptional.isPresent()) {
            return NotaDTO.of(notaOptional.get());
        }

        throw new IllegalArgumentException(String.format("Não existe Nota com o id %s", id));
    }

    public void delete(Long id) {
        this.iNotaRepository.deleteById(id);
    }

    private void validate(NotaDTO notaDTO) {
        if (notaDTO == null) {
            throw new IllegalArgumentException("Nota não pode ser nula!");
        }

        if (StringUtils.isEmpty(notaDTO.getNota())) {
            throw new IllegalArgumentException("Valor da Nota não pode ser nula!");
        }

        if (notaDTO.getBoletim() == null) {
            throw new IllegalArgumentException("Boletim não pode ser nulo!");
        }

        if (notaDTO.getMateria() == null) {
            throw new IllegalArgumentException("Materia não pode ser nula!");
        }
    }
}
