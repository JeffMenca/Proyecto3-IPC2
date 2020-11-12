-- -----------------------------------------------------
-- Codigo de DB el billeton 
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Borrar y volver a crear Schema elbileton
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `ELBILLETON` ;

-- -----------------------------------------------------
-- Crear Schema elbilleton
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ELBILLETON`;
USE `ELBILLETON` ;

-- -----------------------------------------------------
-- Tabla Gerente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `GERENTE` (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(200) NOT NULL,
  `turno` VARCHAR(45) NOT NULL,
  `DPI` INT NOT NULL,
  `direccion` VARCHAR(200) NOT NULL,
  `sexo` VARCHAR(45) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`codigo`));
  INSERT INTO GERENTE VALUES(1,'Banca virtual','Toda hora',101,'','','123');


-- -----------------------------------------------------
-- Tabla Cajero`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CAJERO` (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(200) NOT NULL,
  `turno` VARCHAR(45) NOT NULL,
  `DPI` INT NOT NULL,
  `direccion` VARCHAR(200) NOT NULL,
  `sexo` VARCHAR(45) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`codigo`));
  INSERT INTO CAJERO VALUES(1,'Banca virtual','Toda hora',101,'','','123');
INSERT INTO CAJERO VALUES(101,'Banca virtual','Toda hora',101,'','','8cX7%%tedj4!yJm4');

-- -----------------------------------------------------
-- Tabla Cliente
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CLIENTE` (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(200) NOT NULL,
  `fecha_nacimiento` DATE NOT NULL,
  `DPI` INT NOT NULL,
  `direccion` VARCHAR(200) NOT NULL,
  `sexo` VARCHAR(45) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  `DPI_copia` MEDIUMBLOB NOT NULL,
  PRIMARY KEY (`codigo`));
  INSERT INTO CLIENTE VALUES('1','PEDROS','2020-01-12','12','12A','MUJER','123','0');


-- -----------------------------------------------------
-- Tabla cuenta
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CUENTA` (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `fecha_creacion` DATE NOT NULL,
  `monto` DOUBLE NOT NULL,
  `cliente_codigo` INT NOT NULL,
  PRIMARY KEY (`codigo`),
  FOREIGN KEY (`cliente_codigo`) 
  REFERENCES `CLIENTE` (`codigo`));


-- -----------------------------------------------------
-- Tabla Solicitud_Asociacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SOLICITUD_ASOCIACION` (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `fecha` DATE NOT NULL,
  `estado` VARCHAR(45) NOT NULL,
  `cuenta_envia_codigo` INT NOT NULL,
  `cuenta2_recibe_codigo1` INT NOT NULL,
  PRIMARY KEY (`codigo`),
    FOREIGN KEY (`cuenta_envia_codigo`)
    REFERENCES `CUENTA` (`codigo`),
    FOREIGN KEY (`cuenta2_recibe_codigo1`)
    REFERENCES `CUENTA` (`codigo`));


-- -----------------------------------------------------
-- Tabla Asociacion_Cuentas
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ASOCIACION_CUENTAS` (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `fecha` DATE NOT NULL,
  `solicitud_asociacion_codigo` INT NOT NULL,
  PRIMARY KEY (`codigo`),
    FOREIGN KEY (`solicitud_asociacion_codigo`)
    REFERENCES `SOLICITUD_ASOCIACION` (`codigo`));


-- -----------------------------------------------------
-- Tabla Transaccion
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TRANSACCION` (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `fecha` DATE NOT NULL,
  `hora` DOUBLE NOT NULL,
  `tipo` VARCHAR(45) NOT NULL,
  `monto` DOUBLE NOT NULL,
  `cuenta_codigo` INT NOT NULL,
  `cajero_codigo` INT NOT NULL,
  PRIMARY KEY (`codigo`),
    FOREIGN KEY (`cuenta_codigo`)
    REFERENCES `CUENTA` (`codigo`),
    FOREIGN KEY (`cajero_codigo`)
    REFERENCES `CAJERO` (`codigo`));


-- -----------------------------------------------------
-- Tabla Historial_Gerente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `HISTORIAL_GERENTE` (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(200) NOT NULL,
  `turno` VARCHAR(45) NOT NULL,
  `DPI` INT NOT NULL,
  `direccion` VARCHAR(200) NOT NULL,
  `sexo` VARCHAR(45) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  `gerente_codigo` INT NOT NULL,
  PRIMARY KEY (`codigo`),
    FOREIGN KEY (`gerente_codigo`)
    REFERENCES `GERENTE` (`codigo`));


-- -----------------------------------------------------
-- Tabla Historial_Cajero`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `HISTORIAL_CAJERO` (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(200) NOT NULL,
  `turno` VARCHAR(45) NOT NULL,
  `DPI` INT NOT NULL,
  `direccion` VARCHAR(200) NOT NULL,
  `sexo` VARCHAR(45) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  `cajero_codigo` INT NOT NULL,
  PRIMARY KEY (`codigo`),
    FOREIGN KEY (`cajero_codigo`)
    REFERENCES `CAJERO` (`codigo`));


-- -----------------------------------------------------
-- Tabla Historial_Cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `HISTORIAL_CLIENTE` (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(200) NOT NULL,
  `fecha_nacimiento` DATE NOT NULL,
  `DPI` INT NOT NULL,
  `direccion` VARCHAR(200) NOT NULL,
  `sexo` VARCHAR(45) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  `DPI_copia` MEDIUMBLOB NOT NULL,
  `cliente_codigo` INT NOT NULL,
  PRIMARY KEY (`codigo`),
    FOREIGN KEY (`cliente_codigo`)
    REFERENCES `CLIENTE` (`codigo`));
    
    
    


