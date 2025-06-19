# JMEE
<div style="text-align: justify;">
Java Mathematical Expression Evaluator JMEE utiliza el algoritmo 
<a href="https://es.wikipedia.org/wiki/Algoritmo_shunting_yard">Shunting Yard</a>, creado por el famoso científico de la
computación <a href="https://es.wikipedia.org/wiki/Edsger_Dijkstra">Edsger Dijkstra</a>. El objetivo principal 
de este algoritmo es convertir una expresión matemática en notación infija la forma habitual en la que escribimos y 
comprendemos las operaciones matemáticas, como 3+3*4-10 a una notación
posfija o notación polaca inversa por ejemplo, 3 3 4 * + 10 -. Esta conversión facilita la evaluación de la expresión
para un computador.

Mi objetivo con este proyecto es aprender nuevos conceptos relacionados con la optimizasión y reutilización de código
Java, asi como también la aplicación de algoritmos avanzados para el análisis de expresiones matemáticas y métodos 
numéricos. Todo esto con el fin de reforzar y mejorar mis conocimientos sobre las materias de **Métodos numéricos, 
Calculo, Algoritmos computacionales y Lógica computacional** anteriormente cursadas en la universidad.


## Funcionamiento
Haciendo uso de un algoritmo propio denominado **Sorting Buffer** se analiza y tokeniza la expresión matemática 
permitiendo identificar cualquier número real y algunas funciones básicas (trigonométricas, logarítmicas, estadísticas, 
etc). El algoritmo toma como entrada un String con la operación matemática para luego ir iterando carácter por carácter
y determinar si se trata de un número o función apoyándose en un buffer temporal el cual ira ensamblando el número o 
función correspondiente.

**Descripción básica:**

La funcionalidad de este algoritmo radica en que al encontrarse con un número o letra este crea un buffer temporal en el
cual va armando el token correspondiente y guarda el resultado en una lista con todos los tokens individuales que 
componen la expresión.

Ejemplo: `"3+5.5*sin(30)` se convierte en `[3, +, 5.5, *, sin, (, 30 , )]`


Ilustración sencilla de como trabaja el algoritmo Sorting Buffer.
```java
    // Buffer para números.
    if (Character.isDigit(currentChar)) {
        StringBuilder buffer = new StringBuilder();
        
            // Agregar todos los digitos y posibles puntos decimales al buffer.
            while (i < length && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                buffer.append(expression.charAt(i));
                i++;
            }

            tokens.add(buffer.toString());
            i--; // Retrocede para no saltar el sigiente caracter.

        // Buffer para funciones.
    } else if (Character.isLetter(currentChar)) {
        StringBuilder buffer = new StringBuilder();

        while (i < length && (Character.isLetter(currentChar))) {
            buffer.append(currentChar);
            i++;
            currentChar = expression.charAt(i);
        }

        tokens.add(buffer.toString());
        i--;
    }
```
Luego se convierte la expresión matematica ya tokenizada a notación posfija para finalmente ser analizada y evaluada.

</div>