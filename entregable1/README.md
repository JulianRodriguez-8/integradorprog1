# CityLink - Sistema de Gestión de Reparto Urbano (Entregable 1)

Sistema desarrollado en Java para la gestión integral de envíos urbanos de la empresa CityLink, aplicando Programación Orientada a Objetos (POO), jerarquías de clases abstractas, interfaces, polimorfismo y un manejo completo de excepciones con almacenamiento en arreglos estáticos[cite: 1, 3, 4].

---

## 👥 Integrantes del Grupo
* [Julián Rodríguez]
* [Matías Figueroa]


---

## 🚀 Instrucciones para Compilar y Ejecutar

El proyecto es una aplicación de consola en Java[cite: 1] organizada en paquetes (`app`, `modelo`, `excepciones`)[cite: 4]. Ubícate en la carpeta raíz del proyecto (`integradorprog1`) y ejecuta los siguientes comandos en la terminal de PowerShell:

```powershell
Remove-Item -Recurse -Force entregable1/out; javac -d entregable1/out (Get-ChildItem -Recurse entregable1/src -Filter *.java).FullName; java -cp entregable1/out app.Main

---

## 📊 Diagrama de Clases

![Diagrama de Clases de CityLink](integradorprog1\diagrama_prog1_entregable1.png)

---

## 🤖 Declaración de Uso de Inteligencia Artificial

* **Herramientas utilizadas:** 
  * **Claude:** Utilizado en las etapas iniciales para estructurar las bases de código, proponer diseños orientados a objetos, resolución de errores de compilación.
  * **Gemini:** la redacción de la documentación formal en Javadoc de las clases y la estructuración de la documentación del repositorio.
* **Propósito:** Agilizar el proceso de estructuración inicial, asegurar un estándar profesional en la documentación del código y optimizar la presentación técnica del proyecto.
* **Verificación de resultados:** prueba y error constante, ejecutábamos el programa, ingresábamos datos a mano por consola, probabábamos romperlo a propósito con casos inválidos (como meter pesos negativos, códigos duplicados o pasarnos de los límites) para ver si saltaban las excepciones y el menú aguantaba sin romperse.

---

## 💡 Extras y Decisiones Propias fuera de la Consignas

Además de cumplir con todos los requisitos obligatorios del entregable, sumamos algunas mejoras e implementaciones por cuenta propia:
* **Manejo avanzado de excepciones y robustez:** En la interfaz de consola implementamos validaciones extra con bloques `try-catch` y un bloque `finally` para asegurar que el sistema nunca se caiga ni se rompa ante entradas maliciosas o errores de tipeo del usuario.
* **Flexibilidad en el ingreso de datos:** Adaptamos la lectura de números decimales para que el sistema acepte indistintamente puntos (`.`) o comas (`,`), evitando problemas comunes con la configuración regional del teclado.
* **Organización modular:** Estructuramos el código en paquetes bien definidos (`app`, `modelo`, `excepciones`) y automatizamos toda la compilación y ejecución mediante un script limpio en PowerShell.