package br.com.hbsis.materia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/materias")
public class MateriaRest {

    private final MateriaService materiaService;

    @Autowired
    public MateriaRest(MateriaService materiaService) {
        this.materiaService = materiaService;
    }

    @PostMapping
    public MateriaDTO save(@RequestBody MateriaDTO materiaDTO) {
        return this.materiaService.save(materiaDTO);
    }

    @PutMapping("/{id}")
    public MateriaDTO update(@RequestBody MateriaDTO materiaDTO, @PathVariable("id") Long id) {
        return this.materiaService.update(materiaDTO, id);
    }

    @GetMapping("/{id}")
    public MateriaDTO findById(@PathVariable("id") Long id) {
        return this.materiaService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        this.materiaService.delete(id);
    }
}
