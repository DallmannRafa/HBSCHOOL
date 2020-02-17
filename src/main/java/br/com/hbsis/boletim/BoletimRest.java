package br.com.hbsis.boletim;

import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/boletins")
public class BoletimRest {

    private final BoletimService boletimService;
    private final ReportService reportService;

    @Autowired
    public BoletimRest(BoletimService boletimService, ReportService reportService) {
        this.boletimService = boletimService;
        this.reportService = reportService;
    }

    @PostMapping
    public BoletimDTO save(@RequestBody BoletimDTO boletimDTO) {
        return this.boletimService.save(boletimDTO);
    }

    @PutMapping("/{id}")
    public BoletimDTO update(@RequestBody BoletimDTO boletimDTO, @PathVariable("id") Long id) {
        return this.boletimService.update(boletimDTO, id);
    }

    @GetMapping("/{id}")
    public BoletimDTO findById(@PathVariable("id") Long id) {
        return this.boletimService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        this.boletimService.delete(id);
    }

    @GetMapping("{idPeriodo}/{idAluno}")
    public List<BoletimModel> test(@PathVariable("idPeriodo") Long idPeriodo, @PathVariable("idAluno") Long idAluno) {
        return this.reportService.test(idPeriodo, idAluno);
    }

    @GetMapping("/report/{format}/{idPeriodo}/{idAluno}")
    public String generateReport(@PathVariable("format") String format,@PathVariable("idPeriodo") Long idPeriodo, @PathVariable("idAluno") Long idAluno) throws FileNotFoundException, JRException {
        return reportService.exportReport(format, idPeriodo, idAluno);
    }
}
