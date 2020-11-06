INSERT INTO tbl_facturas(fac_id, fac_descripcion, fac_idcliente, fac_fecha, fac_estado) VALUES ( 2 , ' factura menu del dia ' , 1 , NOW(), ' CREADO ' );
INSERT INTO tbl_facturas(fac_id, fac_descripcion, fac_idcliente, fac_fecha, fac_estado) VALUES ( 3 , ' factura menu del dia ' , 2 , NOW(), ' CREADO ' );


INSERT INTO tbl_itemfacturas(item_id, item_cantidad, item_precio, item_producto_id) VALUES ( 1, 1, 178.89, 1 );
INSERT INTO tbl_itemfacturas(item_id, item_cantidad, item_precio, item_producto_id) VALUES ( 2, 2, 12.5, 2 );
INSERT INTO tbl_itemfacturas(item_id, item_cantidad, item_precio, item_producto_id) VALUES ( 3, 1, 40.06, 3 );