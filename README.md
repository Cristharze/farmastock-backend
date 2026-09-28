# Capítulo 01 Java esencial

## Entidades elegidas
* **Tabla padre:** `venta` (`Venta`)
* **Tabla dependiente:** `detalle_venta` (`DetalleVenta`)
* **Relación:** 1:N (Una venta contiene uno o más detalles de venta)
* **FK:** `venta_id` / `idVenta` (conecta la tabla `detalle_venta` con `venta`)

## Clases Java
* `com.farmastock.sale.domain.Venta`: Representa la entidad padre con sus atributos, total calculado y lista de detalles.
* `com.farmastock.saledetail.domain.DetalleVenta`: Representa los ítems vendidos asociados a la venta.
* `com.farmastock.sale.domain.EstadoVenta`: Enum con los estados válidos de la transacción (`CONFIRMADA`, `ANULADA`).
* `com.farmastock.sale.port.VentaRepository`: Interfaz que define el contrato de persistencia sin acoplarse a bases de datos.
* `com.farmastock.Main`: Clase ejecutable que demuestra el flujo del dominio sin frameworks.

## Regla implementada
* **Validación de precios y cantidades:** En `DetalleVenta` no se permiten cantidades de productos $\le 0$ ni precios unitarios $\le 0$.
* **Control de estado:** En `Venta` no se permite agregar detalles si la venta se encuentra en estado `ANULADA`.
* **Cálculo automático:** El total de la venta se recalcula de forma consistente al agregar un nuevo `DetalleVenta`.

## Decisiones
* **¿Por qué usamos enum?**
  Utilizamos `EstadoVenta` para restringir los valores posibles del estado a un conjunto cerrado (`CONFIRMADA`, `ANULADA`), reflejando la restricción `CHECK` de la base de datos física y evitando inconsistencias por el uso de cadenas de texto libres (`String`).

* **¿Por qué la colección es privada?**
  La colección `List<DetalleVenta>` se mantiene privada dentro de `Venta` y se expone mediante `Collections.unmodifiableList(...)` para proteger el encapsulamiento. Esto evita que código externo modifique la lista directamente (ejemplo: hacer `.clear()` o `.add()` por fuera), obligando a que cualquier cambio pase por el método de negocio `agregarDetalle()`.

* **¿Qué NO implementamos todavía?**
  No implementamos Spring Boot, anotaciones JPA (`@Entity`, `@Table`), `JpaRepository` ni conexión directa a la base de datos PostgreSQL desde Java. Todo el modelo está construido utilizando únicamente Java 21 puro.

# Capítulo 02: Java 21 - Contratos, Colecciones y Errores Controlados

## Identificación de Entidades
* **Entidad padre:** `Venta` (`idVenta` - PK)
* **Entidad dependiente:** `DetalleVenta` (`idVenta` - FK)
* **Relación:** 1:N porque una venta puede contener múltiples productos/detalles, mientras que cada detalle pertenece exclusivamente a una venta.

## Colección Elegida
* **Uso de `Map<Integer, Venta>`:** Se eligió un `LinkedHashMap` en `VentaRepositoryEnMemoria` para almacenar las ventas en memoria. Permite búsquedas directas por ID con complejidad $O(1)$ y preserva el orden de inserción.
* **Uso de `List<DetalleVenta>`:** Se usó para agrupar los ítems dentro de una `Venta`, ya que requiere mantener el orden de adición de productos.

## Uso de Optional
* `Optional<Venta> buscarPorId(Integer id)`: Retorna un contenedor que de forma explícita indica que el registro puede existir o no, evitando el uso de `null` y previniendo errores de tipo `NullPointerException`.

## Excepciones Propias de Negocio
* `VentaNoEncontradaException`: Se dispara cuando se consulta un ID de venta inexistente en el repositorio.
* `NroComprobanteDuplicadoException`: Se dispara al intentar registrar una venta cuyo número de comprobante ya existe en el sistema (`UNIQUE`).

## Manejo de Estados con Enum
* `EstadoVenta`: Enum con valores `CONFIRMADA` y `ANULADA` que refleja la restricción `CHECK` de la base de datos y limita las transacciones a estados válidos.

## Uso de Record
* `RegistrarDetalleVentaCommand`: Utilizado como un DTO/Comando inmutable para transportar los datos requeridos para registrar un detalle de venta sin exponer lógica interna.

# Capítulo 03: Introducción a Spring Boot

## Endpoints Creados
* `GET /api/health`: Estado de salud de la aplicación e información del backend.
* `GET /api/ventas/demo`: Endpoint demo que retorna una respuesta simulada de la entidad padre (`Venta`).

## Conceptos Aplicados
* **Inversión de Control (IoC):** Spring Boot administra los componentes de la aplicación (`@Service`, `@RestController`).
* **Inyección de Dependencias (DI):** Los controladores reciben los servicios a través del constructor sin usar el operador `new`.
* **Estructura Modular:** Organización por dominios de negocio (`sale`, `saledetail`, `shared`) preparando la arquitectura del sistema.