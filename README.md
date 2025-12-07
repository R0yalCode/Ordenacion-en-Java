# Algoritmos de Ordenación en Java

## Unidad 2 – Estructura de Datos

---

Link del taller:
## [Taller 5](https://github.com/R0yalCode/Ordenacion-en-Java/blob/develop/Ordenacion_en_Java_Grupo_W.pdf)
---

## 1. Introducción

Este proyecto implementa y compara tres algoritmos clásicos de ordenación:

* **Insertion Sort** (Ordenación por Inserción)
* **Selection Sort** (Ordenación por Selección)
* **Bubble Sort** (Ordenación Burbuja, optimizada con corte temprano)

Como parte del Taller 5 de la Unidad 2, se desarrolló una **aplicación de consola** con enfoque profesional que permite:

1.  Ordenar datos ingresados **manualmente**.
2.  Cargar valores desde un archivo **`.txt`**.
3.  Usar **datasets oficiales** del taller.
4.  Mostrar **trazas visuales tipo VisuAlgo** mediante barras ASCII, colores ANSI y animación.
5.  Seleccionar distintos algoritmos desde un **menú interactivo**.
6.  Registrar **historial de ejecuciones** (método, tiempo, swaps, movimientos).
7.  Mantener una **estructura por capas** (`domain`, `model`, `service`, `structure`, `exception`, `presentation`).

Este `README` documenta la arquitectura, el funcionamiento y las instrucciones necesarias para ejecutar, probar y comprender el proyecto.

---

##  2. Arquitectura del Proyecto

El proyecto sigue una **arquitectura por capas**, organizada para facilitar mantenibilidad, escalabilidad y claridad del código.
```
src/
└── ed/u2/sorting/
   ├── model/         
   │ ├── HistoryManager.java
   │ ├── SortMetrics.java
   │ └── SortResult.java
   ├── domain/           
   │ └── Dataset.java
   ├── service/         
   │ ├── SortingService.java
   │ ├── SortingFactory.java
   │ ├── SelectionSort.java
   │ ├── InsertionSort.java
   │ └── BubbleSort.java
   ├── structure/        
   │ ├── SortingUtils.java
   │ ├── FileUtils.java
   │ └── ConsoleVisualizer.java
   ├── presentation/     
   │ ├── SortingDemo.java
   │ └── SortingMenu.java
   └── exception/        
   └── SortingException.java
```

###  Descripción breve de cada capa

| Capa | Función |
| :--- | :--- |
| `domain` | Contiene el modelo conceptual de los datos a ordenar. |
| `model` | Métricas, tiempos, resultados, historial. |
| `service` | Implementa algoritmos de ordenación (Inserción, Selección, Burbuja). |
| `structure` | Visualización ASCII, colores, delay, swap, validación y lectura de archivos. |
| `presentation` | Menú, entrada de usuario, ejecución del sistema. |
| `exception` | Manejo adecuado de errores para evitar fallos en ejecución. |

---

##  3. Algoritmos Implementados

###  Insertion Sort

* **Estable**
* Ideal para datos **casi ordenados**
* Muy eficiente en arreglos pequeños
* Traza visual clara
* Ordena insertando `a[i]` en el subarreglo previo

###  Selection Sort

* **NO estable**
* Realiza muy pocos swaps (a lo sumo $n - 1$)
* Busca explícitamente el **mínimo** en $i..n-1$
* Útil cuando el intercambio es costoso

###  Bubble Sort (Optimizado)

* **Estable**
* Muy visual y didáctico
* Recorre elementos adyacentes y realiza `swap` si están mal ordenados
* Implementa **corte temprano** para mejorar rendimiento
* Excelente para ver animaciones paso a paso

---

##  4. Visualización Estilo VisuAlgo (ASCII + colores + delay)

El proyecto incluye:

* Barras horizontales estilo VisuAlgo
* Colores ANSI:
    * Comparaciones → **Amarillo**
    * Swaps → **Rojo**
    * Elemento fijo → **Cyan**
    * Arreglo final → **Verde**
* Delay configurable (**150 ms** por paso)

### Ejemplo:

> Comparando: 6 y 3
> [SWAP]
> 6: ******
> 3: ***
> Estado del arreglo:
> 3: ***
> 6: ******
> 8: ********

---

##  5. Requisitos del Sistema

###  Software necesario

* **Java JDK 21 o superior**
    * (Recomendado: JDK 25 OpenJDK)
* Cualquier IDE Java:
    * IntelliJ IDEA
    * NetBeans
    * Eclipse
    * Visual Studio Code con extensión Java
* Git (opcional pero recomendado)

###  Sistema operativo

Funciona en:

* Windows
* Linux
* macOS

---

##  6. Cómo clonar y ejecutar el proyecto

###  1. Clonar el repositorio
```git clone https://github.com/R0yalCode/Ordenacion-en-Java.git ```

###  2. Navegar al proyecto
``` cd REPO  ```

###  3. Compilar el proyecto
``` javac -d bin src/ed/u2/sorting/**/*.java ```

###  4. Ejecutar el programa
``` java -cp bin ed.u2.sorting.presentation.SortingMenu ```

---

##  7. Instrucciones de Uso

Al ejecutar el proyecto, aparece un menú:

===== SISTEMA DE ORDENACIÓN =====
1. Insertion Sort
2. Selection Sort
3. Bubble Sort
---------------------
4. Ingresar números manualmente
5. Cargar números desde archivo .txt
6. Usar datasets del Taller
---------------------
7. Ver historial de ordenamientos
0. Salir


###  Puedes:

*  Elegir método de ordenación
*  Escribir los números separados por espacios
*  Cargar números desde un archivo `.txt`
*  Activar/desactivar trazas visuales
*  Ver historial de ejecuciones
*  Guardar el historial en un archivo

---

##  8. Formato del Archivo .txt

Acepta:

### Opción A (en una sola línea)
8 3 6 3 9

### Opción B (una línea por número)
8
3
6
3
9

---

##  9. Datasets del Taller (A–E)

El sistema incluye los datasets oficiales:

| Nombre | Valores |
| :--- | :--- |
| **A** | 8 3 6 3 9 |
| **B** | 5 4 3 2 1 |
| **C** | 1 2 3 4 5 |
| **D** | 2 2 2 2 |
| **E** | 9 1 8 2 |

---

##  10. Métricas Registradas

Cada ejecución registra:

* Algoritmo usado
* Tiempo total en **milisegundos**
* **Comparaciones**
* **Swaps**
* **Movimientos**
* Arreglo original
* Arreglo final
* Fecha y hora

### Ejemplo:
Método: BubbleSort
Original: [8, 3, 6, 3, 9]
Resultado: [3, 3, 6, 8, 9]
Swaps: 3 | Movimientos: 4 | Tiempo: 5 ms

---

##  11. Excepciones manejadas

El sistema detecta y evita errores comunes:

* Archivo no encontrado
* Archivo vacío
* Formato inválido (caracteres no numéricos)
* Arreglos muy grandes (> 10 000)
* Datos manuales inválidos

Se manejan mediante clases personalizadas en `exception/`.

---

##  12. Capturas de Ejecución 

- Menu principal
<img width="361" height="245" alt="image" src="https://github.com/user-attachments/assets/9aa000ff-0379-4540-8517-96efd4def46a" />


- Datos ingresados manualmente
<img width="564" height="600" alt="image" src="https://github.com/user-attachments/assets/a127a605-0463-4f8e-8977-a2f3adf902b7" />


- Datos cargados desde un .txt
<img width="1066" height="342" alt="image" src="https://github.com/user-attachments/assets/84ec2c0c-0ea2-481c-8a73-52ead4c99006" />


- Historial de ordenamiento
<img width="1459" height="411" alt="image" src="https://github.com/user-attachments/assets/3c245590-f919-4aeb-b0cf-eae81b355b97" />


- Exportar historial
<img width="498" height="286" alt="image" src="https://github.com/user-attachments/assets/9bdba58a-d951-469e-94b1-16d61c1bd140" />
<img width="1733" height="261" alt="image" src="https://github.com/user-attachments/assets/515306ff-dfd8-443b-ada9-43d50655d964" />

---

##  13. Conclusiones

Este proyecto permite **comprender profundamente los algoritmos de ordenación** mediante:

* Implementación estructurada
* Visualización interactiva
* Comparación clara de comportamientos
* Manejo profesional de excepciones y capas
* Métricas completas
* Integración con archivos externos

## Autores: 
### [Royel Jima](https://github.com/R0yalCode)
### [Darwin Jimbo](https://github.com/Darwin-J5)

