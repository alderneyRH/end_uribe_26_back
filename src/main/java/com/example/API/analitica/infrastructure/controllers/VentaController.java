package com.example.API.analitica.infrastructure.controllers;

import com.example.API.analitica.application.services.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/analitica")
@CrossOrigin(origins = "*")
public class VentaController {

    @Autowired
    private VentaService service;

    @GetMapping("/dashboard")
    public Map<String, Object> getDashboard() {
        return service.obtenerDatosDashboard();
    }
}