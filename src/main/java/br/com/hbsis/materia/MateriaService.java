package br.com.hbsis.materia;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class MateriaService {

    private final IMateriaRepository iMateriaRepository;

    public MateriaService(IMateriaRepository iMateriaRepository) {
        this.iMateriaRepository = iMateriaRepository;
    }

    public MateriaDTO save(MateriaDTO materiaDTO) {

        this.validate(materiaDTO);

        Materia materia = new Materia();

        materia.setNome(materiaDTO.getNome());

        this.iMateriaRepository.save(materia);

        return MateriaDTO.of(materia);

    }

    public MateriaDTO update(MateriaDTO materiaDTO, Long id) {
        Optional<Materia> materiaOptional = this.iMateriaRepository.findById(id);

        if (materiaOptional.isPresent()) {
            Materia materia = materiaOptional.get();

            materia.setNome(materiaDTO.getNome());

            this.iMateriaRepository.save(materia);
        }

        throw new IllegalArgumentException(String.format("Materia com id %s não existe", id));
    }

    public List<Materia> findAll() {
        List<Materia> materias = this.iMateriaRepository.findAll();

        if (!materias.isEmpty()) {
            return materias;
        }

        throw new IllegalArgumentException("não existem matérias cadastradas!");
    }

    public MateriaDTO findById(Long id) {
        Optional<Materia> materiaOptional = this.iMateriaRepository.findById(id);

        if (materiaOptional.isPresent()) {
            return MateriaDTO.of(materiaOptional.get());
        }

        throw new IllegalArgumentException(String.format("Materia com id %s não existe", id));
    }

    public Materia findMateriaById(Long id) {
        Optional<Materia> materiaOptional = this.iMateriaRepository.findById(id);

        if (materiaOptional.isPresent()) {
            return materiaOptional.get();
        }

        throw new IllegalArgumentException(String.format("Materia com id %s não existe", id));
    }

    public void delete(Long id) {
        this.iMateriaRepository.deleteById(id);
    }

    private void validate(MateriaDTO materiaDTO) {
        if (materiaDTO == null) {
            throw new IllegalArgumentException("Materia não pode ser nula!");
        }

        if (StringUtils.isEmpty(materiaDTO.getNome())) {
            throw new IllegalArgumentException("Nome da Matéria não pode ser nula");
        }
    }
}
