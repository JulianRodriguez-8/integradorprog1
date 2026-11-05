# CityLink - Sistema de Gestión de Reparto Urbano (Entregable 1)

Sistema desarrollado en Java para la gestión integral de envíos urbanos de la empresa CityLink, aplicando Programación Orientada a Objetos (POO), jerarquías de clases abstractas, interfaces, polimorfismo y un manejo completo de excepciones con almacenamiento en arreglos estáticos.

---

## 👥 Integrantes del Grupo
* [Julián Rodríguez]



---

## 🚀 Instrucciones para Compilar y Ejecutar

El proyecto es una aplicación de consola en Java organizada en paquetes (`app`, `modelo`, `excepciones`). Ubícate en la carpeta raíz del proyecto (`integradorprog1`) y ejecuta el siguiente comando en la terminal de PowerShell:

```powershell
Remove-Item -Recurse -Force entregable1/out; javac -d entregable1/out (Get-ChildItem -Recurse entregable1/src -Filter *.java).FullName; java -cp entregable1/out app.Main
```

---

## 📊 Diagrama de Clases

![Diagrama de Clases de CityLink](integradorprog1\diagrama_prog1_entregable1.png)

---

## 🤖 Declaración de Uso de Inteligencia Artificial

* **Herramientas utilizadas:** 
  * **Claude:** Utilizado en las etapas iniciales para estructurar las bases de código, proponer diseños orientados a objetos, resolución de errores de compilación.
  * **Gemini:** la redacción de la documentación formal en Javadoc de las clases y la estructuración de la documentación del repositorio.
* **Propósito:** Agilizar el proceso de estructuración inicial, asegurar un estándar profesional en la documentación del código y optimizar la presentación técnica del proyecto.
* **Verificación de resultados:** prueba y error constante, ejecutaba el programa, ingresaba datos a mano por consola, probababa romperlo a propósito con casos inválidos (como meter pesos negativos, códigos duplicados o pasarnos de los límites) para ver si saltaban las excepciones y el menú aguantaba sin romperse.

---

## 💡 Extras y Decisiones Propias fuera de la Consignas

implementaciones por cuenta propia:
* **Manejo avanzado de excepciones y robustez:** En la interfaz de consola implementao validaciones extra con bloques `try-catch` y un bloque `finally` para asegurar que el sistema nunca se caiga ni se rompa ante entradas inválidas o errores de tipeo del usuario.
* **Flexibilidad en el ingreso de datos:** Adapta la lectura de números decimales para que el sistema acepte indistintamente puntos (`.`) o comas (`,`), evitando problemas comunes con la configuración regional del teclado. Tambien búsqueda por nombre sin distinguir mayúsculas (equalsIgnoreCase).
* **Automatización de compilación con PowerShell:** Dejamos la compilación y la ejecución en un solo comando de PowerShell.
* **Puebas Internas:** Un archivo PruebaModelo.java. 
*  **vehiculoAsignado en Pedido:** Pedido implementa Facturable, cuyo calcularImporte() no recibe parámetros, pero el costo depende del vehículo. La solución fue que Pedido guarde con que Vehiculo se entrega. Repartidor.asignarPedido se lo pasa con setVehiculoAsignado, y calcularImporte() devuelve 0 si todavía no tiene vehículo.
*  **Bicicleta rechaza frágiles sin usar instanceof:** esFragil() en Pedido (devuelve false, PedidoFragil lo redefine con true)
*  **Estados como String con constantes estáticas, no enum:** no conocía el uso de enum
*  **Tipos de los códigos:** el código de cliente es int; el de pedido y el legajo son String.
*   **Sobrecarga:** asignarPedido(Pedido) llama a asignarPedido(Pedido, Vehiculo). Tambien en Citylink tengo buscarCliente, uno recibe un número entero (int codigo) y el otro recibe un texto (String nombre).
*   **Sin contador de entregas:** el estado del pedido (ENTREGADO) dice si se entregó, y la comisión (10%) suma solo esos pedidos.
*   **IllegalArgumentException e IllegalStateException donde la consigna no asigna una propia:** setters de Cliente, Vehiculo y Repartidor, cliente o legajo repetido, y pedido o repartidor no encontrado.
*   **Control extra en cambiarEstadoPedido:** un pedido sin repartidor asignado no puede pasar a EN_CAMINO ni ENTREGADO, solo a CANCELADO.
*    **Pedidos sin asignar aportan 0 al importe total y el listado los muestra como “sin asignar”.**
*  **cargarDatosPrueba():** es un método aparte, no el constructor, para que new CityLink() en las pruebas no choque con códigos repetidos.
*  **eliminarCliente:** desplaza los elementos, pero no está en el menú porque la consigna no lo lista, y no controla si el cliente tiene pedidos. Se demuestra en PruebaModelo.
* **finally:** está en la opción 4, pase lo que pase, muestra el estado del pedido.
* **Ejemplos de los 3 tipos de errores:**
*  Compilación: calcularTiempoEntregra mal escrito; la llave que faltaba en Pedido; EstadoInvalidoException sin extends.
* Ejecución: ClassNotFoundException: app.Main (la clase se llamaba maindeprueba); UnsupportedClassVersionError por .class compilados en otra PC.
* Lógico: setCodigo guardaba el valor aunque fuera negativo; el costo urgente daba 1600 en vez de 3300; se permitía EN_CAMINO → CANCELADO.