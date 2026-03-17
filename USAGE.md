<div style="text-align: justify;">
    
# Descripción
En este documento se explica e ilustrará cómo usar los diferentes métodos y clases de la librería JMEE para evaluar 
expresiones matemáticas. Por el momento se está trabajando en mejorar la capacidad de la librería de reconocer distintas funciones matemáticas, 
así como también que pueda trabajar con distintos tipos de operadores y que su arquitectura sea lo más modular posible.

### Evaluación típica
Este es el caso más básico y práctico para evaluar una expresión; lo único que se necesita es:

- Crear un objeto Builder.
- Pasarle al builder la expresión.
- Construir la expresión.
- Evaluar la expresión.

```java
import builder.Builder;
import custom.CustomFunction;

import java.math.BigDecimal;

public class MainClass {
    public static void main(String[] args) {
        // Se pasa la funcion tal cual
        Builder builder = new Builder("(3+2)*5");

        // Procesa la expresion (tokeniza y parsea)
        builder.builExpression(); // Se procesa.

        // Evaluar
        BigDecimal result = builder.evaluate();

        System.out.println("Expresión a evaluar => " + builder.getExpression());
        System.out.println("Tokens => " + builder.getTokens());
        System.out.println("Tokens en notación posfija => " + builder.getPosfixExpression());
        System.out.println("El resultado es => " + result);
    }
}
```

### Evaluación con variables / parámetros
En este caso lo único que cambia es que la expresión contiene variables, las cuales serán sustituidas por algún valor, 
lo cual se puede realizar de la siguiente manera.

_**Nota**: el programa solo identifica variables aisladas como en el ejemplo. No multiplicación implícita tipo (xy+2)._

```java
import builder.Builder;
import custom.CustomFunction;

import java.math.BigDecimal;

public class MainClass {
    public static void main(String[] args) {

        Builder builder = new Builder("sin(X)+cos(Y)");

        // Se setean los valores de las variables.
        builder.setParameter("x", 10);
        builder.setParameter("y", 5);

        builder.builExpression();
        BigDecimal result = builder.evaluate();

        // Se muestra.
        System.out.println("Expresión a evaluar => " + builder.getExpression());
        System.out.println("Tokens => " + builder.getTokens());
        System.out.println("Tokens en notación posfija => " + builder.getPosfixExpression());
        System.out.println("El resultado es => " + result);

    }
}
```

## Diferentes cosas de hacer lo mismo
La librería cuenta con varias formas de implementar funciones y operadores matemáticos personalizados; todas estas 
llevan al mismo resultado, pero dejan un espacio a la libertad del usuario de elegir la implementación que mejor convenga
según el caso.

_**Nota**: en todos los casos se requiere un objeto de tipo MathContext y que se registre la función u operador antes 
de usarlo._

### Creación de funciones customizadas usando la clase CustomFunction
Esta implementación permite crear una función en la misma clase donde se vaya a usar; excelente para integrar alguna que
otra función básica que se requiera en el momento.

```java
import builder.Builder;
import custom.CustomFunction;

import java.math.BigDecimal;
import java.math.MathContext;

public class MainClass {
    public static void main(String[] args) {

        // Creación de funciones personalizadas.
        CustomFunction functionX = new CustomFunction("funcX", 2) {
            @Override
            public BigDecimal function(MathContext mathContext, BigDecimal... args) {
                return args[0].add(args[1], mathContext);
            }
        };
        functionX.addFunction(); // se agrega al registro.

        CustomFunction functionY = new CustomFunction("funcY", 2) {
            @Override
            public BigDecimal function(MathContext mathContext, BigDecimal... args) {
                return args[0].substract(args[1], mathContext);
            }
        };
        functionY.addFunction();

        // se especifica la cantidad de decimales con el parametro MathContext.
        Builder builder = new Builder("funcX(3.7,2) * funcY(10,5.03)", new MathContext(10));
        builder.builExpression();
        BigDecimal result = builder.evaluate();

    }
}
```

### Creación de funciones customizadas implementando la interfaz Function
En este caso se debe de crear una clase aparte que implemente de la interfaz Function y sobrescriba sus métodos, ideal 
para implementaciones mas complejas que requieran de una lógica más amplia.

```java
import functions.Function;

public class Random implements Function {
    @Override
    public String getName() {
        return "random";
    }

    @Override
    public int getArgsCount() {
        return 1;
    }

    @Override
    public int getPrecedence() {
        return 3;
    }

    @Override
    public double execute(double... args) {
        return Math.random() * args[0];
    }
}
```

```java
import java.math.BigDecimal;

public class MainClass {
    public static void main(String[] args) {
        // Registro de clase independiente
        FunctionRegistry.register(new Random());

        Builder builder = new Builder("random(10)");
        builder.builExpression();
        BigDecimal result = builder.evaluate();
    }
}
```

### Creación de funciones customizadas usando directamente la interfaz Function
Este caso es parecido al primero en el que se utiliza la clase CustomFunction, pero se diferencia en que esta 
implementación utiliza directamente la interfaz Function dentro de la clase sin necesidad de implementarla.

```java
import builder.Builder;
import functions.Function;

import java.math.BigDecimal;
import java.math.MathContext;

public class MainClass {
    public static void main(String[] args) {

        function = new Function() {
            @Override
            public String getName() {
                return "log2";
            }

            @Override
            public int getArgsCount() {
                return 1;
            }

            @Override
            public int getPrecedence() {
                return 3;
            }

            @Override
            public BigDecimal execute(MathContext mathContext, BigDecimal... args) {
                return BigDecimalMath.log2(args[0], mathContext);
            }
        };
        FunctionRegistry.register(function);// Se registra la funcion

        // se especifica la cantidad de decimales con el parametro MathContext.
        Builder builder = new Builder("log2(10)", new MathContext(5));
        builder.builExpression();
        BigDecimal result = builder.evaluate();

    }
}
```
### Creación de operadores customizados implementando la interfaz Operator
Haciendo uso de la interfaz Operator se pueden crear diferentes tipos de operadores personalizados permitiendo la 
creación de nuevos tipos de evaluaciones.

```java
package operators.common;

import operators.Operator;

import java.math.BigDecimal;
import java.math.MathContext;

public class Multiplication implements Operator {
    @Override
    public String getSymbol() {
        return "*";
    }

    @Override
    public int getPrecedence() {
        return 1;
    }

    @Override
    public int getArgsCount() {
        return 2;
    }

    @Override
    public boolean isLeftAssociative() {
        return true;
    }

    @Override
    public BigDecimal execute(MathContext mathContext, BigDecimal... args) {
        return args[0].multiply(args[1], mathContext);
    }
}
```
</div>












