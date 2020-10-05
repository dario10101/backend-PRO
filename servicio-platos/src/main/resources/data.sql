INSERT INTO Restaurante (id_rest, nombre_rest, desc_rest, img_rest, categoria_rest, status_rest)
VALUES (1, 'Pio Pio', 'Pollo frito', 'vacio', 'asadero', 'ACTIVATED');

INSERT INTO Restaurante (id_rest, nombre_rest, desc_rest, img_rest, categoria_rest, status_rest)
VALUES (2, 'Sason casero', 'sin descripcion', 'vacio', 'sin categoria', 'ACTIVATED');

INSERT INTO Plato (id_plato, nombre_plato, desc_plato, precio_plato, img_plato, categoria_plato, status_plato, cantidad_plato, ingredientes_plato, id_rest)
VALUES (1, 'pio', 'presa de pollo, arroz y consome', 10000.0, 'vacio', 'plato del dia','ACTIVATED', 10.0, 'ingredientes plato 1', 1);

INSERT INTO Plato (id_plato, nombre_plato, desc_plato, precio_plato, img_plato, categoria_plato, status_plato, cantidad_plato, ingredientes_plato, id_rest)
VALUES (2, 'carne asada', 'carne, ensalada,...', 20000.0, 'vacio', 'plato especial','ACTIVATED', 11.0, 'ingredientes plato 2', 2);

INSERT INTO Plato (id_plato, nombre_plato, desc_plato, precio_plato, img_plato, categoria_plato, status_plato, cantidad_plato, ingredientes_plato, id_rest)
VALUES (3, 'Ajiaco', 'sopa con papa y pollo', 15000.0, 'vacio', 'plato especial','ACTIVATED', 12.0, 'ingredientes plato 3', 2);

INSERT INTO Plato (id_plato, nombre_plato, desc_plato, precio_plato, img_plato, categoria_plato, status_plato, cantidad_plato, ingredientes_plato, id_rest)
VALUES (4, 'pescado', 'pescado frito', 13000.0, 'vacio', 'plato especial','DELETED', 4.0, 'ingredientes plato 4', 1);