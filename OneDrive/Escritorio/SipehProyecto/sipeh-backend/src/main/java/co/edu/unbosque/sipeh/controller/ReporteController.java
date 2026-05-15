package co.edu.unbosque.sipeh.controller;

import co.edu.unbosque.sipeh.dto.DecanoDashboardDTO;
import co.edu.unbosque.sipeh.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/reportes")
public class ReporteController {

	@Autowired
	private ReporteService reporteService;

	@GetMapping("/decano/dashboard")
	public ResponseEntity<DecanoDashboardDTO> getDashboardDecano() {
		DecanoDashboardDTO stats = reporteService.obtenerEstadisticasDecano();
		return ResponseEntity.ok(stats);
	}
}