## Descripción

<div style="text-align: justify;">
Java Mathematical Expression Evaluator JMEE utiliza el algoritmo
<a href="https://es.wikipedia.org/wiki/Algoritmo_shunting_yard">Shunting Yard</a>, creado por el famoso científico de la
computación <a href="https://es.wikipedia.org/wiki/Edsger_Dijkstra">Edsger Dijkstra</a>. El objetivo principal
de este algoritmo es convertir una expresión matemática en notación infija, la forma habitual en la que escribimos y
comprendemos las operaciones matemáticas, como 3+3*4-10, a una notación
posfija o notación polaca inversa; por ejemplo, 3 3 4 * + 10 -. Esta conversión facilita la evaluación de la expresión
para un computador.
&nbsp;
  
### Mi objetivo

Mi objetivo con este proyecto es aprender nuevos conceptos relacionados con la optimización y reutilización de código
Java, así como también la aplicación de algoritmos avanzados para el análisis de expresiones matemáticas y métodos
numéricos.

Agradecería de antemano cualquier sugerencia u observación sobre el programa, me ayudaría bastante a seguir mejorándolo.

### Funcionamiento

Haciendo uso de un algoritmo propio denominado Sorting Buffer, se analiza y tokeniza la expresión matemática
permitiendo identificar cualquier número real y algunas funciones básicas (trigonométricas, logarítmicas, estadísticas),
y determinar si se trata de un número o función, apoyándose en un buffer temporal el cual irá ensamblando el número o
función correspondiente.

Se recomienda ver el archivo
<a href="https://github.com/LeandroJoseGomez/JMEE/blob/main/USAGE.md">USAGE.md</a> para mayor comprensión y ejemplos de uso.

### Dependencia Maven
Se recomienda usar JDK 17 o superior.

```xml
<dependency>
  <groupId>io.github.leandrojosegomez</groupId>
  <artifactId>JMEE</artifactId>
  <version>1.3.0</version>
</dependency>
```

### Versiones y sus objetivos

- **v1.3.0:** Corrección de clases, parámetros y métodos estáticos que impedían que las expresiones fueran independientes unas de la otras. Además de publicarse la librería en Maven Central.

- **v1.2.9:** Se reconfiguró la librería para usar el tipo de dato BigDecimal, esto para llevar un mayor control y validez de los cálculos que requieren de extrema precisión.

- **v1.2.0:** Con esta nueva actualización lo que se busca es que la librería esté asentada sobre una arquitectura modular
  y fácil de entender implementando el Principio de Responsabilidad Única de SOLID. También se ha pensado en su extensión, cómo
  los desarrolladores pueden modificarla o crear sus propias funciones personalizadas.

- **v1.0.0:** Poder evaluar expresiones matemáticas básicas como, por ejemplo, "3+3*sin(90)", contar con la función de
  soportar variables/parámetros y que el usuario tenga la opción de crear sus propias funciones personalizadas. Todo
  esto teniendo como entrada un String con la expresión matemática a evaluar.





</div>
