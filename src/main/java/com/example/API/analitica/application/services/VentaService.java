package com.example.API.analitica.application.services;

import com.example.API.analitica.domain.models.Venta;
import com.example.API.analitica.domain.repositories.VentaRepository;
import com.example.API.analitica.domain.repositories.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class VentaService {
    @Autowired
    private VentaRepository ventaRepository;
    @Autowired
    private EmpleadoRepository empleadoRepository;

    public List<Venta> obtenerTodas() { return ventaRepository.findAll(); }

    public Map<String, Object> obtenerDatosDashboard() {
        List<Venta> ventas = ventaRepository.findAll();
        Map<String, Object> data = new HashMap<>();

        data.put("totalVentas", ventas.size());

        double ingresos = ventas.stream()
                .mapToDouble(v -> v.getTotal() != null ? v.getTotal() : 0.0)
                .sum();

        data.put("ingresosTotales", ingresos);
        data.put("totalEmpleados", empleadoRepository.count());

        return data;
    }
}