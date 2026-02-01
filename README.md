# JMEE

### Descripción
<div style="text-align: justify;">
Java Mathematical Expression Evaluator JMEE utiliza el algoritmo 
<a href="https://es.wikipedia.org/wiki/Algoritmo_shunting_yard">Shunting Yard</a>, creado por el famoso científico de la
computación <a href="https://es.wikipedia.org/wiki/Edsger_Dijkstra">Edsger Dijkstra</a>. El objetivo principal 
de este algoritmo es convertir una expresión matemática en notación infija la forma habitual en la que escribimos y 
comprendemos las operaciones matemáticas, como 3+3*4-10 a una notación
posfija o notación polaca inversa por ejemplo, 3 3 4 * + 10 -. Esta conversión facilita la evaluación de la expresión
para un computador.
&nbsp;

### Mi objetivo
Inicialmente la idea de este programa surgió cuando intentaba fortalecer mis habilidades como programador en Java y 
decidí crear una calculadora la cual evaluará operaciones matemáticas básicas con tan solo proporcionarle la expresión 
tal cual la entendemos nosotros los humanos así como lo hacen las calculadoras científicas. Todo esto por sí sola, eso 
quiere decir que no debía de auxiliarme de librerías que agilizarán el trabajo esto supondría un gran reto para mi pues 
debía de aprender como transformar la expresión en un formato más fácil de procesar y evaluar para luego empezar a 
codificar.

Mi objetivo con este proyecto es aprender nuevos conceptos relacionados con la optimizasión y reutilización de código
Java, asi como también la aplicación de algoritmos avanzados para el análisis de expresiones matemáticas y métodos
numéricos.
<br/>
<br/>

## Funcionamiento
Haciendo uso de un algoritmo propio denominado **Sorting Buffer** se analiza y tokeniza la expresión matemática
permitiendo identificar cualquier número real y algunas funciones básicas (trigonométricas, logarítmicas, estadísticas,
etc). El algoritmo toma como entrada un String con la operación matemática para luego ir iterando carácter por carácter
y determinar si se trata de un número o función, apoyándose en un buffer temporal el cual ira ensamblando el número o
función correspondiente.

### Versiones y sus objetivos
- **v1.0.0:** Poder evaluar expresiones matematicas basicas como por ejemplo "3+3*sin(90)", contar con la funcion de 
  soportar variables/parametros y que el usuario tenga la opcion de crear sus propias funciones personalizadas. Todo
  esto teniendo como entrada un String con la expresion matematica a evaluar.

```java
import builder.Builder;
import parser.CustomFunction;

public class MainClass {
    public static void main(String[] args) {

        // Creacion de funciones personalizadas.
        CustomFunction customFunction = new CustomFunction("sum",2) {
            @Override
            public double function(double... arguments) {
                return arguments[0] + arguments[1];
            }
        };
        customFunction.addFunction();

        CustomFunction customFunction2 = new CustomFunction("min",2) {
            @Override
            public double function(double... arguments) {
                return arguments[0] - arguments[1];
            }
        };
        customFunction2.addFunction();

        Builder builder = new Builder("sum(3,2) * min(x,y)"); // (3+2) * (10-5) => 25

        // Se setean los valores de las variables.
        builder.setParameter("x", 10);
        builder.setParameter("y", 5);

        builder.builExpression();// Se procesa.

        // Se evalua.
        double result = builder.evaluate();

        // Se muestra.
        System.out.println("Expresion a evaluar => " +builder.getExpression());
        System.out.println("Tokens => " + builder.getTokens());
        System.out.println("Tokens en notacion posfija => " + builder.getPosfixExpression());
        System.out.println("El resultado fue => " +result);

    }
}
```
</div>