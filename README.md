Sistema de Gestión de Fichas Bibliográficas

Proyecto Java con JDBC y MySQL

Descripción del Dominio

El dominio del proyecto consiste en un sistema simple para gestionar fichas bibliográficas.
Cada FichaBibliografica representa un registro con información básica de un libro o material escrito.
El sistema permite realizar operaciones CRUD (crear, leer, actualizar, eliminar) utilizando un diseño basado en DAO genérico (GenericDao) para desacoplar la lógica de acceso a datos de la lógica de negocio.

Requisitos
Software necesario

Java 25

MySQL Server (8.x recomendado)

Conector JDBC para MySQL (mysql-connector-j)

IDE recomendado: NetBeans 


Creación de la Base de Datos

El proyecto incluye un archivo SQL llamado Instrucciones_Base_de_Datos.sql.
Ejecute el archivo para crear la base de datos.
Ejecute Insertr_Biblioteca.sql para insertar los datos en la tabla.


Cómo Compilar y Ejecutar
1. Clonar o descargar el proyecto
2. Compilar y ejecutar

redenciales de prueba

En el archivo DatabaseConfig.java:

public static final String URL = "jdbc:mysql://localhost:3306/biblioteca";
public static final String USER = "root";
public static final String PASSWORD = "1234";


(Modificar según se necesite.)

Flujo básico de uso

Al ejecutar el programa, se abre el menú principal.

El usuario puede:

Crear una nueva ficha bibliográfica

Listar todas las fichas

Leer una ficha por ID

Actualizar una ficha

Eliminar una ficha

Todas las operaciones se ejecutan mediante el FichaBibliograficaDao, que extiende el GenericDao.

Enlace al video demostración

Video explicativo:
