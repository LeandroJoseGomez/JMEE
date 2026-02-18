# Descripcion
En este documento se explicara e ilustrara como usar los diferentes metodos y clases de la libreria JMEE para evaluar 
expresiones matematicas.

Por el momento se estara trabajando en mejorar la capacidad de la libreria, de reconocer distintas funciones matematicas
asi como tambien que pueda trabajar con distintos tipos de operadores y que su arquitectura sea lo más modular posible.

### Evaluacion tipica
Este es el caso mas basico para usar la libreria, para evaluar una expresion lo unico que se necesita es:
- Pasarle al Builder la expresion como un parametro de tipo String.
- Contruir la expresion con el metodo buildExpression().
- Evaluar la expresion.

```java
import builder.Builder;
import custom.CustomFunction;

public class MainClass {
    public static void main(String[] args) {
        // Se pasa la funcion tal cual
        Builder builder = new Builder("(3+2)*5");

        // Procesa la expresion (tokeniza y parsea)
        builder.builExpression(); // Se procesa.

        // Evaluar
        double result = builder.evaluate();

        System.out.println("Expresion a evaluar => " + builder.getExpression());
        System.out.println("Tokens => " + builder.getTokens());
        System.out.println("Tokens en notacion posfija => " + builder.getPosfixExpression());
        System.out.println("El resultado fue => " + result);
    }
}
```

### Evaluacion con variables / parametros
En este caso lo unico que cambia es que la exprecion contiene variables las cuales seran sustituidas por algun valor,
lo cual se puede realizar de la siguiente manera.

_**Nota**: el programa solo identifica variables aisladas como en el ejemplo, no del tipo (xy+2)_

```java
import builder.Builder;
import custom.CustomFunction;

public class MainClass {
    public static void main(String[] args) {
        
        Builder builder = new Builder("sin(X)+cos(Y)");

        // Se setean los valores de las variables.
        builder.setParameter("x", 10);
        builder.setParameter("y", 5);

        builder.builExpression();
        double result = builder.evaluate();

        // Se muestra.
        System.out.println("Expresion a evaluar => " +builder.getExpression());
        System.out.println("Tokens => " + builder.getTokens());
        System.out.println("Tokens en notacion posfija => " + builder.getPosfixExpression());
        System.out.println("El resultado fue => " +result);

    }
}
```

### Creacion de funciones customizadas
La libreria cuenta con dos formas de crear funciones personalizadas, una de ellas es crear la funcion en la misma clase 
donde se vaya a usar, excelente para integrar alguna que otra funcion basica que se requiera en el momento. La otra
forma es creando una clase aparte que implemente de la interfaz Function y sobrescribir sus metodos.

_**Nota**: en ambos casos se requiere que se registre la funcion antes de usarla._

**Usando la clase CustomFunction**
```java
import builder.Builder;
import custom.CustomFunction;

public class MainClass {
    public static void main(String[] args) {

        // Creación de funciones personalizadas.
        CustomFunction customFunction = new CustomFunction("sum",2) {
            @Override
            public double function(double... arguments) {
                return arguments[0] + arguments[1];
            }
        };
        customFunction.addFunction(); // se agrega al registro.

        CustomFunction customFunction2 = new CustomFunction("min",2) {
            @Override
            public double function(double... arguments) {
                return arguments[0] - arguments[1];
            }
        };
        customFunction2.addFunction();

        Builder builder = new Builder("sum(3,2) * min(10,5)");
        builder.builExpression();
        double result = builder.evaluate();

    }
}
```

**Usando la interfaz Function**
```java
import functions.Function;

public class Custom implements Function {
    @Override
    public String getName() {
        return "custom";
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
public class MainClass {
    public static void main(String[] args) {
        // Registro de clase independiente
        FunctionRegistry.register(new Custom());

        Builder builder = new Builder("custom(10)");
        builder.builExpression();
        double result = builder.evaluate();
    }
}
```













