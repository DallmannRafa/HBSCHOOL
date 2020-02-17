package br.com.hbsis.aluno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alunos")
public class AlunoRest {

    private final AlunoService alunoService;

    @Autowired
    public AlunoRest(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PostMapping
    public AlunoDTO save(@RequestBody AlunoDTO alunoDTO) {
        return this.alunoService.save(alunoDTO);
    }

    @PutMapping("/{id}")
    public AlunoDTO update(@RequestBody AlunoDTO alunoDTO, @PathVariable("id") Long id) {
        return this.alunoService.update(alunoDTO, id);
    }

    @GetMapping("/{id}")
    public AlunoDTO findById(@PathVariable("id") Long id) {
        return this.alunoService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        this.alunoService.delete(id);
    }
}
