package br.com.hbsis.boletim;

import br.com.hbsis.aluno.Aluno;
import br.com.hbsis.aluno.AlunoService;
import br.com.hbsis.materia.Materia;
import br.com.hbsis.materia.MateriaService;
import br.com.hbsis.nota.Nota;
import br.com.hbsis.periodo.Periodo;
import br.com.hbsis.periodo.PeriodoService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

@Service
public class ReportService {

    private final IBoletimRepository iBoletimRepository;
    private final PeriodoService periodoService;
    private final AlunoService alunoService;
    private final MateriaService materiaService;

    @Autowired
    public ReportService(IBoletimRepository iBoletimRepository, PeriodoService periodoService, AlunoService alunoService, MateriaService materiaService) {
        this.iBoletimRepository = iBoletimRepository;
        this.periodoService = periodoService;
        this.alunoService = alunoService;
        this.materiaService = materiaService;
    }

    public List<BoletimModel> test(Long idPeriodo, Long idAluno){
        Aluno aluno = this.alunoService.findAlunoById(idAluno);
        Periodo periodo = this.periodoService.findPeriodoById(idPeriodo);

        Optional<Boletim> boletimOptional = this.iBoletimRepository.findByAlunoAndPeriodo(aluno, periodo);
        if (boletimOptional.isPresent()) {
            return this.populateModel(boletimOptional.get());
        }

        throw new IllegalArgumentException("Boletim não existe");


    }


    public String exportReport(String reportFormat, Long idPeriodo, Long idAluno) throws FileNotFoundException, JRException {
        String path = "C:\\Users\\rafael.silva\\Desktop";

        Aluno aluno = this.alunoService.findAlunoById(idAluno);
        Periodo periodo = this.periodoService.findPeriodoById(idPeriodo);

        Optional<Boletim> boletimOptional = this.iBoletimRepository.findByAlunoAndPeriodo(aluno, periodo);
        if (boletimOptional.isPresent()) {

            List<BoletimModel> boletins = this.populateModel(boletimOptional.get());

            File file = ResourceUtils.getFile("classpath:boletim.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(boletins);
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("createdBy", "HBSIS");
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
            if (reportFormat.equalsIgnoreCase("html")) {
                JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\boletim.html");
            }

            if (reportFormat.equalsIgnoreCase("pdf")) {
                JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\boletim.pdf");
            }

            return "report generated in path : " + path;
        }
        throw new IllegalArgumentException("Boletim não existe");
    }

    private List<BoletimModel> populateModel(Boletim boletim) {
        List<Materia> materias = this.materiaService.findAll();
        List<BoletimModel> boletins = new ArrayList<>();

        for(Materia materia : materias) {
            BoletimModel boletimModel = new BoletimModel();
            boletimModel.setAluno(boletim.getAluno().getNome());
            boletimModel.setPeriodo(boletim.getPeriodo().getNome());
            boletimModel.setMateria(materia.getNome());
            int contador = 0;
            for (Nota nota : boletim.getNotas()) {
                if(nota.getMateria().getNome().equals(materia.getNome())) {
                    if (contador == 0) {
                        boletimModel.setNota1(nota.getNota());
                    } else if (contador == 1) {
                        boletimModel.setNota2(nota.getNota());
                    } else if (contador == 2) {
                        boletimModel.setNota3(nota.getNota());
                    } else if (contador == 3) {
                        boletimModel.setNota4(nota.getNota());
                    }
                    contador++;
                }
            }
            boletins.add(boletimModel);
        }

        return boletins;
    }
}
