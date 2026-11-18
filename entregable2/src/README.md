# CityLink - Sistema de Gestión de Reparto Urbano (Entregable 2)

Sistema desarrollado en Java para la gestión integral de envíos urbanos de la empresa CityLink, aplicando Programación Orientada a Objetos (POO), jerarquías de clases abstractas, interfaces, polimorfismo, manejo completo de excepciones, estructuras de datos dinámicas (`TreeMap`, `ArrayList`, `ArrayDeque`) y algoritmos propios de ordenamiento, búsqueda e intercalación.

---

## 👥 Integrantes del Grupo
- Julián Rodríguez

---

## 🚀 Instrucciones para Compilar y Ejecutar

El proyecto es una aplicación de consola en Java organizada en cuatro paquetes (`app`, `modelo`, `excepciones`, `algoritmos`). Ubícate en la carpeta raíz del proyecto (`integradorprog1`) y ejecuta el siguiente comando en la terminal de PowerShell:

```powershell
Remove-Item -Recurse -Force entregable2/out -ErrorAction SilentlyContinue; javac -encoding UTF-8 -d entregable2/out (Get-ChildItem -Recurse entregable2/src -Filter *.java).FullName; java -cp entregable2/out app.Main
```

---

## 🤖 Declaración de Uso de Inteligencia Artificial

- **Gemini:** Utilizado para la migración hacia colecciones dinámicas, el diseño de algoritmos sobre listas, la redacción de la documentación formal en Javadoc de todas las clases y la estructuración del repositorio.
- **Propósito:** Agilizar el proceso de estructuración inicial, asegurar un estándar profesional en la documentación del código y optimizar la presentación técnica del proyecto.
- **Verificación de resultados:** prueba y error constante, ejecutaba el programa, ingresaba datos a mano por consola, probababa romperlo a propósito con casos inválidos (como meter pesos negativos, códigos duplicados o pasarnos de los límites) para ver si saltaban las excepciones y el menú aguantaba sin romperse.

---

## 💡 Extras y Decisiones Propias fuera de la Consignas

- **colecciones dinámicas:** reemplacé los arreglos estáticos y la excepción AlmacenamientoLlenoException del Entregable 1 por estructuras dinámicas de java.util (TreeMap para clientes ordenados automáticamente por código, ArrayList para listas generales y ArrayDeque para colas y pilas), eliminando topes artificiales de almacenamiento.
- **peek() antes de poll():** En atenderSiguientePedido(), primero se consulta el frente de colaUrgentes (o colaNormales) con peek() y se intenta asignar el pedido al repartidor. Recién si el vehículo soporta el peso y tipo de carga se extrae definitivamente con poll(), evitando perder el pedido si ocurre una CapacidadExcedidaException.
- **Historial y forzarEstado():** creé la clase Operacion para guardar un registro de cada acción realizada (como un comprobante con el estado que tenía el pedido antes del cambio) dentro de una pila (Deque<Operacion>). Como el método normal cambiarEstado() solo permite avanzar (por ejemplo, de PENDIENTE a EN_CAMINO) y prohíbe volver atrás, agregué el método forzarEstado() en Pedido para usarlo únicamente al deshacer una operación y poder devolver el pedido a su estado anterior.
- **Ordenamiento y búsqueda sin alterar la lista original:** En el menú, tanto los algoritmos de ordenamiento (Burbuja e Inserción) como la Búsqueda Binaria operan sobre una copia (new ArrayList<>(gestor.getPedidos())), preservando el orden original de carga en CityLink.
- **Cálculo de comisiones sin contador:** El 10% de comisión del repartidor se calcula dinámicamente recorriendo sus pedidos asignados en estado ENTREGADO.