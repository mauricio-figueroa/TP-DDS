package internalService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import domain.LineaReporte;
import domain.ReportePorTerminal;

public class ReportService {

    private static ReportService instance;
    private static List<ReportePorTerminal> reportes;


    public static ReportService getInstance() {
        if (instance == null) {
            reportes = new ArrayList<ReportePorTerminal>();
            return new ReportService();
        }
        return instance;
    }

    public List<ReportePorTerminal> getReportes() {
        return reportes;
    }

    public void setReportes(List<ReportePorTerminal> reportes) {
        this.reportes = reportes;
    }

    public void addReporte(String nombreTerminal, String palabraBuscada, Integer cantPoisBusqueda) {
        int i = 0;
        for (ReportePorTerminal currentReport : reportes) {
            if (currentReport.getNombreTerminal().equalsIgnoreCase(nombreTerminal)) {
                currentReport.agregarReporteAterminal(cantPoisBusqueda, palabraBuscada);
                i = 1;
            }
        }
        if (i == 0) {
            ReportePorTerminal reporte = new ReportePorTerminal(nombreTerminal);
            reporte.agregarReporteAterminal(cantPoisBusqueda, palabraBuscada);
            reportes.add(reporte);
        }
    }

    public Map<String, Integer> getReportesTotalesPorFecha() {
        Map<String, Integer> mapaResultadosTotales = new HashMap<String, Integer>();
        int suma = 0;
        List<ReportePorTerminal> reporsss = reportes;
        for (ReportePorTerminal reportePorTerminal : reportes) {
            for (LineaReporte lineaReporte : reportePorTerminal.getBusquedas()) {
                Date fecha = lineaReporte.getFechaBusqueda();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
                String date = sdf.format(fecha);
                int cantPoisResult = lineaReporte.getCantPoisBusqueda();
                suma = suma + cantPoisResult;
                mapaResultadosTotales.put(date, suma);
            }

        }
        return mapaResultadosTotales;
    }


    public void resetReports() {
        reportes = new ArrayList<ReportePorTerminal>();
    }


    public Map<String, Integer> getParcialesPorTerminal(String nombreTerminal) {
        int suma = 0;
        Map<String, Integer> mapaResultadoParcialPorTerminal = new HashMap<String, Integer>();

        ReportePorTerminal reporte = this.buscarReporteTerminal(nombreTerminal);
        if (reporte != null) {
            for (LineaReporte lineaReporte : reporte.getBusquedas()) {
                Date fecha = lineaReporte.getFechaBusqueda();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
                String date = sdf.format(fecha);
                int cantPoisResult = lineaReporte.getCantPoisBusqueda();
                suma = suma + cantPoisResult;
                mapaResultadoParcialPorTerminal.put(date, suma);

            }
        }
        return mapaResultadoParcialPorTerminal;
    }

    private ReportePorTerminal buscarReporteTerminal(String nombreTerminal) {
        ReportePorTerminal reporte = null;
        for (ReportePorTerminal reportePorTerminal : reportes) {
            if (reportePorTerminal.getNombreTerminal().equalsIgnoreCase(nombreTerminal)) {
                reporte = reportePorTerminal;
            }
        }
        return reporte;

    }

    public Map<String, Integer> getReportesTotalesTodasLasTerminales() {
        int suma;


        Map<String, Integer> mapaResultadoTotalesTodasLasTerminales = new HashMap<String, Integer>();
        for (ReportePorTerminal reportePorTerminal : reportes) {
            Map<String, Integer> resultadosPorTerminal = this
                    .getParcialesPorTerminal(reportePorTerminal.getNombreTerminal());
            suma = 0;

            List<String> keys = new ArrayList<String>();


            for (final Iterator<java.util.Map.Entry<String, Integer>> it = resultadosPorTerminal.entrySet()
                    .iterator(); it.hasNext(); ) {
                final java.util.Map.Entry<String, Integer> entry = it.next();
                final String numero = entry.getKey();
                keys.add(numero);

            }
            for (String key : keys) {
                suma = suma + resultadosPorTerminal.get(key);
            }
            mapaResultadoTotalesTodasLasTerminales.put(reportePorTerminal.getNombreTerminal(), suma);

        }
        return mapaResultadoTotalesTodasLasTerminales;
    }
}
