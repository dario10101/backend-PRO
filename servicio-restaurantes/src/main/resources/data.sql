INSERT INTO Restaurante (nit_rest, nombre_rest, desc_rest, img_rest, categoria_rest, status_rest)
VALUES (1, 'Pio Pio', 'Restaurantes PioPio con su receta única y deliciosa de 45 años es la mejor opción para comer pollo en Popayan', 'https://i.imgur.com/DBHjwR5.png', 'asadero', 'ACTIVATED');

INSERT INTO Restaurante (nit_rest, nombre_rest, desc_rest, img_rest, categoria_rest, status_rest)
VALUES (2, 'Sazon casero', 'Excelente atencion y rapidez para nuestros clientes, lugar muy calido y acogedor para disfrutar en pareja, amigos y familia', 'https://i.imgur.com/55YwfMG.png', 'sin categoria', 'ACTIVATED');

INSERT INTO Restaurante (nit_rest, nombre_rest, desc_rest, img_rest, categoria_rest, status_rest)
VALUES (3, 'El Frijol Verder', 'Dale un buen gusto a tu paladar', 'https://i.imgur.com/IeqadL1.jpg', 'asadero', 'ACTIVATED');

INSERT INTO Restaurante (nit_rest, nombre_rest, desc_rest, img_rest, categoria_rest, status_rest)
VALUES (4, 'El Quijote', 'Manejamos servicio a domicilio, platos a la carta (tipicos, parrilla, cocina internacional), almuerzos ejecutivos y eventos. Experiencia y tradicion', 'https://i.imgur.com/DYJ2TXD.jpg', 'asadero', 'ACTIVATED');

INSERT INTO Restaurante (nit_rest, nombre_rest, desc_rest, img_rest, categoria_rest, status_rest)
VALUES (5, 'Carantanta', 'Con la tradicion que nos caracteriza y porque sabemos lo importante que es satisfacer todas sus expectativas, le invitamos a deleitar de nuestros exquisitos platos con la comodidad que usted se merece', 'https://i.imgur.com/q6bvr7f.jpg', 'asadero', 'ACTIVATED');

INSERT INTO Restaurante (nit_rest, nombre_rest, desc_rest, img_rest, categoria_rest, status_rest)
VALUES (6, 'Jengibre', 'Venga y disfrute de un ambiente familiar, con la mas exquisita gastronomia, nacional e internacional', 'https://i.imgur.com/MYJU6oN.jpg', 'asadero', 'ACTIVATED');

INSERT INTO Restaurante (nit_rest, nombre_rest, desc_rest, img_rest, categoria_rest, status_rest)
VALUES (7, 'Comida Sana', 'Restaurante Vegetariano con un delicioso, variado y saludables almuerzos. Somos un estilo de vida, un familia y un buen comer', 'https://i.imgur.com/zYv2SSh.png', 'asadero', 'ACTIVATED');

INSERT INTO Restaurante (nit_rest, nombre_rest, desc_rest, img_rest, categoria_rest, status_rest)
VALUES (8, 'La Sorpresa', 'Restaurante con menu variado al precio mas bajo de la ciudad', 'https://i.imgur.com/kC1Gjoq.jpg', 'asadero', 'ACTIVATED');

INSERT INTO Restaurante (nit_rest, nombre_rest, desc_rest, img_rest, categoria_rest, status_rest)
VALUES (9, 'Loncheria Caldas', 'Disfruta carne de Res, Cerdo, Pollo, Costilla en Salsa BBQ y mucho mas', 'https://i.imgur.com/AH0h00l.jpg', 'asadero', 'ACTIVATED');

INSERT INTO Restaurante (nit_rest, nombre_rest, desc_rest, img_rest, categoria_rest, status_rest)
VALUES (10, 'Mestizo', 'En Mestizo encontrarás la mejor parrilla, carnes premium, cortes americanos, hamburguesas, brochetas, sándwich, cazuelas, en un ambiente tranquilo y muy acogedor', 'https://i.imgur.com/pCZ87p3.jpg', 'asadero', 'ACTIVATED');


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