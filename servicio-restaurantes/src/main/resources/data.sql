INSERT INTO Restaurante (nit_rest, nombre_rest, desc_rest, img_rest, categoria_rest, status_rest)
VALUES (1, 'Pio Pio', 'Pollo frito', 'vacio', 'asadero', 'ACTIVATED');

INSERT INTO Restaurante (nit_rest, nombre_rest, desc_rest, img_rest, categoria_rest, status_rest)
VALUES (2, 'Sason casero', 'sin descripcion', 'vacio', 'sin categoria', 'ACTIVATED');


INSERT INTO Empleado (id_empleado, nombre_empleado, correo_empleado, password_empleado, telefono_empleado, direccion_empleado, img_empleado, status_empleado, id_rol, nombre_rol, nit_rest)
VALUES (1, 'Ana', 'ana@unicauca.edu.co', '123', '123456789', 'vacio', '', 'ACTIVATED', 2, 'JEFE DE COCINA', '1');

INSERT INTO Empleado (id_empleado, nombre_empleado, correo_empleado, password_empleado, telefono_empleado, direccion_empleado, img_empleado, status_empleado, id_rol, nombre_rol, nit_rest)
VALUES (2, 'Luisa', 'luisa@unicauca.edu.co', '123', '123456789', 'vacio', '', 'ACTIVATED', 3, 'ADMINISTRADOR', '1');

INSERT INTO Empleado (id_empleado, nombre_empleado, correo_empleado, password_empleado, telefono_empleado, direccion_empleado, img_empleado, status_empleado, id_rol, nombre_rol, nit_rest)
VALUES (3, 'Josefina', 'josefina@unicauca.edu.co', '123', '123456789', 'vacio', '', 'ACTIVATED', 5, 'MENSAJERO', '1');

INSERT INTO Empleado (id_empleado, nombre_empleado, correo_empleado, password_empleado, telefono_empleado, direccion_empleado, img_empleado, status_empleado, id_rol, nombre_rol, nit_rest)
VALUES (4, 'Nacho Gonzales', 'nacho@unicauca.edu.co', '123', '123456789', 'vacio', '', 'ACTIVATED', 2, 'JEFE DE COCINA', '2');

INSERT INTO Empleado (id_empleado, nombre_empleado, correo_empleado, password_empleado, telefono_empleado, direccion_empleado, img_empleado, status_empleado, id_rol, nombre_rol, nit_rest)
VALUES (5, 'Juan Corellano', 'juan@unicauca.edu.co', '123', '123456789', 'vacio', '', 'ACTIVATED', 3, 'ADMINISTRADOR', '2');

INSERT INTO Empleado (id_empleado, nombre_empleado, correo_empleado, password_empleado, telefono_empleado, direccion_empleado, img_empleado, status_empleado, id_rol, nombre_rol, nit_rest)
VALUES (6, 'Javi Torres', 'javi@unicauca.edu.co', '123', '123456789', 'vacio', '', 'ACTIVATED', 5, 'MENSAJERO', '2');