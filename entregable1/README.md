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