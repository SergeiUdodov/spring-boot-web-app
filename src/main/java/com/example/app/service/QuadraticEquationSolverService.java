package com.example.app.service;

import com.example.app.wsdl.DiscriminantException_Exception;
import com.example.app.wsdl.QuadraticEquationSolver;
import com.example.app.wsdl.QuadraticSolution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuadraticEquationSolverService {

    private final QuadraticEquationSolver solver;

    @Autowired
    public QuadraticEquationSolverService(QuadraticEquationSolver solver) {
        this.solver = solver;
    }

    public QuadraticSolution solveEquation(double a, double b, double c) throws DiscriminantException_Exception {
        return solver.solveEquation(a, b, c);
    }
}