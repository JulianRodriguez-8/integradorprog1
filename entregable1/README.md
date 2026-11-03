# CityLink - Sistema de Gestión de Reparto Urbano (Entregable 1)

Sistema desarrollado en Java para la gestión integral de envíos urbanos de la empresa CityLink, aplicando Programación Orientada a Objetos (POO), jerarquías de clases abstractas, interfaces, polimorfismo y un manejo completo de excepciones con almacenamiento en arreglos estáticos[cite: 1, 3, 4].

---

## 👥 Integrantes del Grupo
* [Julián Rodríguez]
* [Nombre y Apellido del Integrante 2]


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
  * **Claude:** Utilizado en las etapas iniciales para estructurar las bases de código, proponer diseños orientados a objetos y asistir en la depuración y resolución de errores de compilación.
  * **Gemini:** Utilizado como soporte para la revisión de buenas prácticas, la redacción de la documentación formal en Javadoc de las clases y la estructuración de la documentación del repositorio.
* **Propósito:** Agilizar el proceso de estructuración inicial, asegurar un estándar profesional en la documentación del código y optimizar la presentación técnica del proyecto.
* **Verificación de resultados:** Todo el código fuente resultante, la implementación de la lógica de negocio, las estructuras de datos, las validaciones y los comandos automatizados de ejecución en PowerShell fueron analizados, probados, depurados y validados línea por línea por el equipo de desarrollo, garantizando el dominio total de la solución para su presentación y defensa oral.