-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 23-07-2019 a las 19:57:17
-- Versión del servidor: 10.1.38-MariaDB
-- Versión de PHP: 7.3.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `green bit hotel`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clients`
--

CREATE TABLE `clients` (
  `Id` int(10) NOT NULL,
  `Name` text NOT NULL,
  `Email` text NOT NULL,
  `Phone` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleados`
--

CREATE TABLE `empleados` (
  `id` int(10) NOT NULL,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `telefono` int(10) NOT NULL,
  `tipoEmpleado` int(255) NOT NULL,
  `hora_Ingreso` time NOT NULL,
  `hora_Salida` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `empleados`
--

INSERT INTO `empleados` (`id`, `name`, `email`, `telefono`, `tipoEmpleado`, `hora_Ingreso`, `hora_Salida`) VALUES
(1234, 'Armando', 'armando.j1221@gamil.com', 123456789, 1, '13:00:00', '23:00:00'),
(1231212, 'Juana', 'juana@gmail.com', 12312, 1, '20:00:00', '03:00:00'),
(1355432, 'Felipe', 'Felipe@hotmail.com', 45234414, 2, '22:00:00', '00:00:00'),
(2342323, 'Dario', 'dario@gmail.com', 2543253, 2, '00:00:00', '02:00:00'),
(123456789, 'Juan', 'juan@gmail.com', 12357, 2, '14:00:00', '23:00:00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hotelsetting`
--

CREATE TABLE `hotelsetting` (
  `RoomPrice` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `hotelsetting`
--

INSERT INTO `hotelsetting` (`RoomPrice`) VALUES
(15000);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `registro`
--

CREATE TABLE `registro` (
  `Id` int(255) NOT NULL,
  `Name` text NOT NULL,
  `Email` text NOT NULL,
  `Phone` int(10) NOT NULL,
  `CheckIn` date NOT NULL,
  `CheckOut` date NOT NULL,
  `Room` int(100) NOT NULL,
  `RegisterNumber` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rooms`
--

CREATE TABLE `rooms` (
  `Ocupide` tinyint(1) NOT NULL,
  `RoomNumber` int(10) NOT NULL,
  `Cliente` int(10) NOT NULL,
  `CheckIn` date NOT NULL,
  `CheckOut` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `rooms`
--

INSERT INTO `rooms` (`Ocupide`, `RoomNumber`, `Cliente`, `CheckIn`, `CheckOut`) VALUES
(0, 1, 0, '2019-05-19', '2019-05-21'),
(0, 2, 0, '2019-05-28', '2019-05-31'),
(0, 3, 0, '2019-04-28', '2019-04-28'),
(0, 4, 0, '2019-04-28', '2019-04-28'),
(0, 5, 0, '2019-04-20', '2019-04-20'),
(0, 6, 0, '2019-04-21', '2019-04-21');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipoempleados`
--

CREATE TABLE `tipoempleados` (
  `id_TipoEmpleados` int(11) NOT NULL,
  `nombreTipoEmpleado` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tipoempleados`
--

INSERT INTO `tipoempleados` (`id_TipoEmpleados`, `nombreTipoEmpleado`) VALUES
(1, 'Gerente'),
(2, 'Aseador/a'),
(3, 'Cocinero'),
(4, 'Boton'),
(5, 'Supervisor de recepción'),
(6, 'Embajador del vestíbulo'),
(7, 'Supervisor de relaciones con el huésped'),
(8, 'Agent-Call Center'),
(9, 'Director asistente-Recursos Humanos'),
(10, 'Camarero'),
(11, 'Panadero pastelero');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `clients`
--
ALTER TABLE `clients`
  ADD PRIMARY KEY (`Id`);

--
-- Indices de la tabla `empleados`
--
ALTER TABLE `empleados`
  ADD PRIMARY KEY (`id`),
  ADD KEY `tipoEmpleado` (`tipoEmpleado`);

--
-- Indices de la tabla `registro`
--
ALTER TABLE `registro`
  ADD PRIMARY KEY (`RegisterNumber`);

--
-- Indices de la tabla `rooms`
--
ALTER TABLE `rooms`
  ADD PRIMARY KEY (`RoomNumber`);

--
-- Indices de la tabla `tipoempleados`
--
ALTER TABLE `tipoempleados`
  ADD PRIMARY KEY (`id_TipoEmpleados`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `registro`
--
ALTER TABLE `registro`
  MODIFY `RegisterNumber` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tipoempleados`
--
ALTER TABLE `tipoempleados`
  MODIFY `id_TipoEmpleados` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `empleados`
--
ALTER TABLE `empleados`
  ADD CONSTRAINT `empleados_ibfk_1` FOREIGN KEY (`tipoEmpleado`) REFERENCES `tipoempleados` (`id_TipoEmpleados`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
