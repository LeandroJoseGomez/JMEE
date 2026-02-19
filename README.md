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

Inicialmente la idea de este programa surgió cuando intentaba fortalecer mis habilidades como programador en Java y
decidí crear una calculadora la cual evaluara operaciones matemáticas básicas con tan solo proporcionarle la expresión
tal cual la entendemos nosotros los humanos, así como lo hacen las calculadoras científicas. Todo esto por sí sola, eso
quiere decir que no debía de auxiliarme de librerías que agilizaran el trabajo; esto supondría un gran reto para mí, pues
debía de aprender cómo transformar la expresión en un formato más fácil de procesar y evaluar para luego empezar a
codificar.

Mi objetivo con este proyecto es aprender nuevos conceptos relacionados con la optimización y reutilización de código
Java, así como también la aplicación de algoritmos avanzados para el análisis de expresiones matemáticas y métodos
numéricos.

Agradeceria de antemano cualquier sugerencia u observacion sobre el programa, me ayudaria bastante a seguir mejorandolo.

### Funcionamiento

Haciendo uso de un algoritmo propio denominado Sorting Buffer, se analiza y tokeniza la expresión matemática
permitiendo identificar cualquier número real y algunas funciones básicas (trigonométricas, logarítmicas, estadísticas),
y determinar si se trata de un número o función, apoyándose en un buffer temporal el cual irá ensamblando el número o
función correspondiente.

Se recomienda ver el archivo
<a href="https://github.com/LeandroJoseGomez/JMEE/blob/main/USAGE.md">USAGE.md</a> para mayor comprensión y ejemplos de uso.

### Versiones y sus objetivos

- **v1.0.0:** Poder evaluar expresiones matemáticas básicas como, por ejemplo, "3+3*sin(90)", contar con la función de
  soportar variables/parámetros y que el usuario tenga la opción de crear sus propias funciones personalizadas. Todo
  esto teniendo como entrada un String con la expresión matemática a evaluar.

- **v1.2.0:** Con esta nueva actualización lo que se busca es que la librería esté asentada sobre una arquitectura modular
  y fácil de entender implementando el Principio de Responsabilidad Única de SOLID. También se ha pensado en su extensión, cómo
  los desarrolladores pueden modificarla o crear sus propias funciones personalizadas.

</div>
