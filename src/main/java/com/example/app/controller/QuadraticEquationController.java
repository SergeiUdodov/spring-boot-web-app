package com.example.app.controller;

import java.util.HashMap;
import java.util.Map;

import com.example.app.service.QuadraticEquationSolverService;
import com.example.app.wsdl.DiscriminantException_Exception;
import com.example.app.wsdl.QuadraticSolution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/calc")
public class QuadraticEquationController {

    private final QuadraticEquationSolverService soapService;

    @Autowired
    public QuadraticEquationController(QuadraticEquationSolverService soapService) {
        this.soapService = soapService;
    }

    @GetMapping
    public ResponseEntity<?> solveEquation(@RequestParam double a,
                                           @RequestParam double b,
                                           @RequestParam double c) {
        try {
            QuadraticSolution solution = soapService.solveEquation(a, b, c);
            return ResponseEntity.ok(solution);
        } catch (DiscriminantException_Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error message", e.getMessage());
            errorResponse.put("formula", e.getFaultInfo().getFormula());
            errorResponse.put("discriminant", e.getFaultInfo().getDiscriminant());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
}
