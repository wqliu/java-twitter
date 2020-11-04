package com.example.twitter.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.twitter.utility.*;

@Controller
@RequestMapping(path="/java-twitter")
public class MatrixController {
    
	public matrixUtility mu;
	
	public MatrixController() {
		mu = new matrixUtility(4);
	}
	
	@GetMapping(path="/matrix-multiplication")
	public @ResponseBody int[][] MatrixMultiplication() throws Exception{
		int[][] a = {{1,2},{2,1}};
		int[][] b = {{2,1},{2,1}};
		try {
		    int[][] c = mu.normalMatrixMutiplication(a, b);
		    return c;
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
 	}
}
