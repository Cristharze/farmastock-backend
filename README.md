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
* `com.farmastock.sale.domain.VentaRepository`: Interfaz que define el contrato de persistencia sin acoplarse a bases de datos.
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