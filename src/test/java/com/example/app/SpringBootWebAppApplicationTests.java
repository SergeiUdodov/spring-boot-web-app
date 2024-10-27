package com.example.app;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

import com.example.app.controller.QuadraticEquationController;
import com.example.app.service.QuadraticEquationSolverService;
import com.example.app.wsdl.DiscriminantException;
import com.example.app.wsdl.DiscriminantException_Exception;
import com.example.app.wsdl.QuadraticSolution;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class SpringBootWebAppApplicationTests {

	@Mock
	private QuadraticEquationSolverService solverService;

	@InjectMocks
	private QuadraticEquationController controller;

	public void QuadraticEquationControllerTest() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testSolveEquationSuccess() throws DiscriminantException_Exception {
		QuadraticSolution solution = new QuadraticSolution();
		solution.setX1(2.0);
		solution.setX2(-1.0);
		when(solverService.solveEquation(1, -1, -2)).thenReturn(solution);

		ResponseEntity<?> response = controller.solveEquation(1, -1, -2);

		assertEquals(OK, response.getStatusCode());
		assertEquals(solution, response.getBody());
	}

	@Test
	void testSolveEquationDiscriminantException() throws DiscriminantException_Exception {
		DiscriminantException_Exception exception = new DiscriminantException_Exception("Discriminant is negative", new DiscriminantException());
		when(solverService.solveEquation(1, 2, 5)).thenThrow(exception);

		ResponseEntity<?> response = controller.solveEquation(1, 2, 5);
		assertEquals(BAD_REQUEST, response.getStatusCode());
	}
}
