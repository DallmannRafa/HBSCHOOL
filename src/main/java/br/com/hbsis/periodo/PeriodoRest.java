package br.com.hbsis.periodo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/periodos")
public class PeriodoRest {

    private final PeriodoService periodoService;

    @Autowired
    public PeriodoRest(PeriodoService periodoService) {
        this.periodoService = periodoService;
    }

    @PostMapping
    public PeriodoDTO save(@RequestBody PeriodoDTO periodoDTO) {
        return this.periodoService.save(periodoDTO);
    }

    @PutMapping("/{id}")
    public PeriodoDTO update(@RequestBody PeriodoDTO periodoDTO, @PathVariable("id") Long id) {
        return this.periodoService.update(periodoDTO, id);
    }

    @GetMapping("/{id}")
    public PeriodoDTO findById(@PathVariable("id") Long id) {
        return this.periodoService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        this.periodoService.delete(id);
    }
}
