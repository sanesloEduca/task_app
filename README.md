# .

![portada.jpg](Untitled%2066b4ef4ab64b473eae15a7d01af91813/portada.jpg)

# Mi primera aplicación

El proyecto consiste en desarrollar una aplicación móvil de gestión de tareas que permita a los usuarios crear una cuenta, iniciar sesión, listar tareas existentes y agregar nuevas tareas. La aplicación tiene como objetivo ayudar a los usuarios a organizar sus actividades diarias de manera eficiente.

## Objetivos

1. **Desarrollar una interfaz intuitiva:** Crear una interfaz de usuario fácil de usar y atractiva que permita a los usuarios navegar de manera eficiente por las diferentes funcionalidades de la aplicación.
2. **Implementar funciones de autenticación:** Permitir que los usuarios creen una cuenta con información segura y autenticación para garantizar la privacidad y la seguridad de sus datos.
3. **Gestión de tareas eficiente:** Desarrollar funcionalidades que permitan a los usuarios listar tareas existentes, agregar nuevas tareas y marcar tareas como completadas.
4. **Integrar notificaciones:** Implementar un sistema de notificaciones para recordar a los usuarios sobre las tareas pendientes, mejorando así la gestión del tiempo.
5. **Añadir imágenes:** Permitir a los usuarios personalizar sus tareas mediante la adición de imágenes relevantes, mejorando la experiencia visual y la identificación de tareas.

## Requisitos

**Elementos Obligatorios:**

- **View (TextView, EditText, Button, ...):** Implementar componentes de vista esenciales para la interacción del usuario, como TextView para mostrar información, EditText para la entrada de datos y Button para acciones.
- **Formato en las Vistas (color, tamaño, tipo, ...):** Personalizar el formato de las vistas para mejorar la estética de la aplicación, incluyendo colores atractivos, tamaños de texto adecuados y un estilo coherente.
- **Layouts Mínimo:** Desarrollar al menos tres layouts diferentes para organizar y presentar la información de manera efectiva en diferentes secciones de la aplicación.
- **Activities Mínimo:** Crear al menos dos actividades distintas para gestionar las funciones clave de la aplicación, como la actividad de inicio de sesión y la actividad principal de la lista de tareas.
- **Añadir Imágenes:** Integrar la capacidad de agregar imágenes relevantes a las tareas para mejorar la experiencia visual del usuario.
- **Notificaciones:** Implementar un sistema de notificaciones para recordar a los usuarios sobre tareas pendientes o eventos importantes.
- **BBDD (Base de Datos):** Utilizar una base de datos para almacenar y gestionar la información del usuario, como detalles de inicio de sesión y tareas creadas.
- **Multimedia (Audio y/o Video):** Integrar la capacidad de añadir archivos de audio y/o video a las tareas, si es relevante para la gestión de tareas.
- **Memoria:** Preparar una memoria detallada que aborde aspectos clave del proyecto.

## Diseño de la aplicación

![Añadir un poco de texto.png](Untitled%2066b4ef4ab64b473eae15a7d01af91813/Aadir_un_poco_de_texto.png)

- **Inicio de sesión:** Un `Layout` que se utiliza para iniciar la sesión y comprobar que el usuario este registrado.
- **Registro:** Si el usuario no esta registrado aparece  `Alert Dialog` que permite registrarse al usuario, almacenando los datos posteriormente en la BBDD, creada previamente.
- **Gestión de Tareas:** Muestra una lista de tareas, permitiendo filtrar por tipo de prioridad y si está realizada o no.
- **Nueva Tarea:** Si el usuario pulsa en el botón flotante aparece otro `Alert Dialog` para registrar la tarea nueva.

### Gama Cromática

![![Paleta_Colores.PNG](httpsprod-files-secure.s3.us-west-2.amazonaws.com182a567c-500f-4112-9f26-b41c809dd61aa6684268-7195-42bd-bfe3-742cb7aaa745Paleta_Colores.png).png](Untitled%2066b4ef4ab64b473eae15a7d01af91813/!Paleta_Colores.PNG(httpsprod-files-secure.s3.us-west-2.amazonaws.com182a567c-500f-4112-9f26-b41c809dd61aa6684268-7195-42bd-bfe3-742cb7aaa745Paleta_Colores.png).png)