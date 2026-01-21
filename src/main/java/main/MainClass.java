package main;

import evaluator.Evaluator;
import parser.CustomFunction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class MainClass {
    public static void main(String[] args) {
        Evaluator evaluator = new Evaluator("3*x+2*y"); // Se escribe la expresion.

        // Se setean los valores de las variables.
        evaluator.setParameter("x", 2);
        evaluator.setParameter("y", 3);
        evaluator.parseExpression();// Se procesa.

        // Se evalua.
        double result = evaluator.evaluateExpression();
        System.out.println("El resultado fue ==> " +result);

    }
}
