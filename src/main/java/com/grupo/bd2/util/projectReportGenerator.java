package com.grupo.bd2.util;

import com.grupo.bd2.model.Project;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class projectReportGenerator {

    public byte[] exportToPdf(List<Project> list) throws JRException, FileNotFoundException {
        JasperPrint report = getReport(list);
        return JasperExportManager.exportReportToPdf(report);
    }

    public byte[] exportToXls(List<Project> list) throws JRException, FileNotFoundException {
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        SimpleOutputStreamExporterOutput output = new SimpleOutputStreamExporterOutput(byteArray);
        JRXlsExporter exporter = new JRXlsExporter();
        exporter.setExporterInput(new SimpleExporterInput(getReport(list)));
        exporter.setExporterOutput(output);
        exporter.exportReport();
        output.close();
        return byteArray.toByteArray();
    }

    private JasperPrint getReport(List<Project> list) throws FileNotFoundException, JRException {
        Map<String, Object> params = new HashMap<>();

        // Prepare the JRBeanCollectionDataSource with your list of Project objects
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
        params.put("projectData", dataSource);

        // Load and compile the Jasper report template
        String reportFilePath = ResourceUtils.getFile("src/main/resources/projectReport.jrxml").getAbsolutePath();
        JasperReport jasperReport = JasperCompileManager.compileReport(reportFilePath);

        // Fill the report with data
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());

        // Return the filled JasperPrint object
        return jasperPrint;
    }

}