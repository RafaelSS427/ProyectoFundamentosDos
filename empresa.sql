-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 04-12-2019 a las 01:12:06
-- Versión del servidor: 10.1.39-MariaDB
-- Versión de PHP: 7.3.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `empresa`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `administradores`
--

CREATE TABLE `administradores` (
  `User` varchar(50) NOT NULL,
  `Pass` varchar(90) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `administradores`
--

INSERT INTO `administradores` (`User`, `Pass`) VALUES
('Heistin', 'pedo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleados`
--

CREATE TABLE `empleados` (
  `cod_emp` varchar(6) NOT NULL,
  `nombre_emp` varchar(20) NOT NULL,
  `apellidoUno_emp` varchar(20) NOT NULL,
  `apellidoDos_emp` varchar(20) NOT NULL,
  `cedula_emp` varchar(15) NOT NULL,
  `telefono_emp` varchar(8) NOT NULL,
  `correo_emp` varchar(30) NOT NULL,
  `rol_emp` int(11) NOT NULL,
  `estado_emp` int(11) NOT NULL,
  `salario_emp` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `empleados`
--

INSERT INTO `empleados` (`cod_emp`, `nombre_emp`, `apellidoUno_emp`, `apellidoDos_emp`, `cedula_emp`, `telefono_emp`, `correo_emp`, `rol_emp`, `estado_emp`, `salario_emp`) VALUES
('123456', 'Rafael', 'Sequeira', 'Sandoval', '702800601', '85169415', 'Rafaelss427@gmail.com', 3, 2, 0),
('20HBJB', 'Pedro', 'Jimenez', 'Iglecias', '701200602', '87654321', 'Ejemplo2@gmail.com', 3, 2, 0),
('5GLHR2', 'David', 'Camacho', 'Hienas', '707800509', '87654321', 'David1234@gmail.com', 3, 2, 0),
('5YC0ND', 'Pancho', 'Perez', 'Cerdas', '604700204', '76852312', 'panchito4k@gmail.com', 12, 2, 0),
('9A1MXN', 'Sofia', 'Sanchez', 'Sandia', '305190059', '80023153', 'so2699@hotmail.com', 7, 2, 0),
('F9HNBH', 'Heistin', 'Rodriguez', 'Villareal', '87654321', '85985019', 'heistinfabian190798@gmail.com', 13, 3, 0),
('M5UDWS', 'Ana', 'Artiles', 'Sandoval', '5034774002', '65129845', 'anartiles01@gmail.com', 12, 2, 0),
('X7NERM', 'Alonso', 'Ramirez', 'Hernandez', '702700503', '65432178', 'Alonso777@gmail.com', 8, 2, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estado`
--

CREATE TABLE `estado` (
  `id_estado` int(11) NOT NULL,
  `nombre_estado` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `estado`
--

INSERT INTO `estado` (`id_estado`, `nombre_estado`) VALUES
(2, 'Activo'),
(3, 'Despedido');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ingresoempleados`
--

CREATE TABLE `ingresoempleados` (
  `id_ing` int(11) NOT NULL,
  `codigo` varchar(6) DEFAULT NULL,
  `laborando` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `ingresoempleados`
--

INSERT INTO `ingresoempleados` (`id_ing`, `codigo`, `laborando`) VALUES
(52, 'F9HNBH', 3),
(53, '20HBJB', 3),
(54, '123456', 3),
(55, '123456', 3),
(56, '123456', 3),
(57, '123456', 3),
(58, '123456', 3),
(59, '123456', 3),
(60, '123456', 3),
(61, '123456', 3),
(62, '123456', 3),
(63, '123456', 3),
(64, '123456', 3),
(65, '123456', 3),
(66, '123456', 3),
(67, '123456', 3),
(68, '123456', 3),
(69, '123456', 3),
(70, '123456', 3),
(71, '123456', 3),
(72, '123456', 3),
(73, '123456', 3),
(74, '20hbjb', 3),
(75, '123456', 3),
(76, '20HBJB', 3),
(77, '20hbjb', 3),
(78, '123456', 3),
(79, '20hbjb', 3),
(80, '20hbjb', 3),
(81, '123456', 3),
(82, '123456', 3),
(83, '123456', 3),
(84, '123456', 3),
(85, '123456', 3),
(86, '123456', 3),
(87, '123456', 3),
(88, '123456', 3),
(89, '123456', 3),
(90, '123456', 3),
(91, '123456', 3),
(92, '123456', 3),
(93, '20hbjb', 3),
(94, '123456', 3),
(95, '123456', 3),
(96, '123456', 3),
(97, '9A1MXN', 3),
(98, '9A1MXN', 3),
(99, '123456', 3),
(100, '9A1MXN', 3),
(101, '9A1MXN', 3),
(102, '9A1MXN', 3),
(103, '123456', 3),
(104, '9A1MXN', 3),
(105, 'F9HNBH', 3),
(106, 'F9HNBH', 3),
(107, 'f9hnbh', 3),
(108, 'F9HNBH', 3),
(109, '9A1MXN', 3),
(110, '123456', 3),
(111, '123456', 3),
(112, '123456', 3),
(113, '123456', 3),
(114, '123456', 3),
(115, '123456', 3),
(116, '123456', 3),
(117, '123456', 3),
(118, '123456', 3),
(119, '123456', 3),
(120, '9A1MXN', 3),
(121, '123456', 3),
(122, '9A1MXN', 3),
(123, '9A1MXN', 3),
(124, '9A1MXN', 3),
(125, 'F9HNBH', 3),
(126, '5GLHR2', 3),
(127, '5GLHR2', 3),
(128, '123456', 3),
(129, '123456', 3),
(130, '123456', 3),
(131, '123456', 3),
(132, '123456', 3),
(133, '123456', 3),
(134, '9A1MXN', 3),
(135, '9A1MXN', 3),
(136, '123456', 3),
(137, '5GLHR2', 3),
(138, '5GLHR2', 3),
(139, '123456', 3),
(140, '123456', 3),
(141, '123456', 3),
(142, '123456', 3),
(143, '123456', 3),
(144, '123456', 3),
(145, '123456', 3),
(146, '123456', 3),
(147, '123456', 3),
(148, '123456', 3),
(149, '123456', 3),
(150, '123456', 3),
(151, '123456', 3),
(152, '123456', 3),
(153, '123456', 3),
(154, '123456', 3),
(155, '123456', 3),
(156, '123456', 3),
(157, '123456', 3),
(158, '123456', 3),
(159, '123456', 3),
(160, '123456', 3),
(161, '123456', 3),
(162, '123456', 3),
(163, '123456', 3),
(164, '123456', 3),
(165, '123456', 3),
(166, '123456', 3),
(167, '5GLHR2', 3),
(168, '5GLHR2', 3),
(169, '5GLHR2', 3),
(170, '5GLHR2', 3),
(171, '123456', 3),
(172, '5GLHR2', 3),
(173, '9A1MXN', 3),
(174, '9A1MXN', 3),
(175, '5GLHR2', 3),
(176, '5GLHR2', 3),
(177, '5GLHR2', 3),
(178, 'F9HNBH', 3),
(179, '20HBJB', 3),
(180, '123456', 3),
(181, '123456', 3),
(182, '123456', 3),
(183, '123456', 3),
(184, '123456', 3),
(185, '123456', 3),
(186, '123456', 3),
(187, '123456', 3),
(188, '123456', 3),
(189, '123456', 3),
(190, '9A1MXN', 3),
(191, '9A1MXN', 3),
(192, '9A1MXN', 3),
(193, '9A1MXN', 3),
(194, '9A1MXN', 3),
(195, '9A1MXN', 3),
(196, '9A1MXN', 3),
(197, '9A1MXN', 3),
(198, '9A1MXN', 3),
(199, '9A1MXN', 3),
(200, '9A1MXN', 3),
(201, '9A1MXN', 3),
(202, '9A1MXN', 3),
(203, '9A1MXN', 3),
(204, '9A1MXN', 3),
(205, '9A1MXN', 3),
(206, '9A1MXN', 3),
(207, '9A1MXN', 3),
(208, '9A1MXN', 3),
(209, '9A1MXN', 3),
(210, '9A1MXN', 3),
(211, '9A1MXN', 3),
(212, '9A1MXN', 3),
(213, '9A1MXN', 3),
(214, '9A1MXN', 3),
(215, '9A1MXN', 3),
(216, '123456', 3),
(217, '123456', 3),
(218, '123456', 3),
(219, '123456', 3),
(220, '123456', 3),
(221, '5GLHR2', 3),
(222, '5GLHR2', 3),
(223, '123456', 3),
(224, '123456', 3),
(225, '20HBJB', 3),
(226, '123456', 3),
(227, 'F9HNBH', 3),
(228, 'F9HNBH', 3),
(229, '123456', 3),
(230, 'X7NERM', 3),
(231, '123456', 3),
(232, '123456', 3),
(233, '123456', 3),
(234, '123456', 3),
(235, '123456', 3),
(236, '123456', 3),
(237, '123456', 3),
(238, '123456', 3),
(239, '123456', 3),
(240, '123456', 3),
(241, '123456', 3),
(242, '123456', 3),
(243, '123456', 3),
(244, '5YC0ND', 3),
(245, '5YC0ND', 3),
(246, '5YC0ND', 3),
(247, '123456', 3),
(248, '123456', 3),
(249, '123456', 3),
(250, '123456', 3),
(251, '123456', 3),
(252, '123456', 3),
(253, '123456', 3),
(254, '123456', 3),
(255, '123456', 3),
(256, 'm5udws', 3),
(257, 'm5udws', 3),
(258, '123456', 3),
(259, '123456', 3),
(260, '123456', 3),
(261, 'F9HNBH', 3),
(262, 'F9HNBH', 3),
(263, '123456', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reportes`
--

CREATE TABLE `reportes` (
  `id_rep` int(11) NOT NULL,
  `cod_emp2` varchar(6) DEFAULT NULL,
  `horaEntrada` int(3) DEFAULT NULL,
  `horaSalida` int(3) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `laborando` int(3) DEFAULT NULL,
  `totalHorasLab` int(11) DEFAULT NULL,
  `salario` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `reportes`
--

INSERT INTO `reportes` (`id_rep`, `cod_emp2`, `horaEntrada`, `horaSalida`, `fecha`, `laborando`, `totalHorasLab`, `salario`) VALUES
(39, '123456', 20, 9, '2019-12-01', 0, 6, 240),
(40, '5GLHR2', 49, 56, '2019-12-01', 0, 7, 280),
(41, '9A1MXN', 50, 50, '2019-12-01', 0, 0, 0),
(42, 'F9HNBH', 59, 0, '2019-12-01', 0, -59, -2360),
(43, '20HBJB', 1, 2, '2019-12-01', 0, 1, 40),
(45, '9A1MXN', 15, 39, '2019-12-02', 0, 24, 720),
(46, '5GLHR2', 23, 24, '2019-12-02', 0, 1, 40),
(47, '20HBJB', 32, 33, '2019-12-02', 0, 1, 40),
(48, 'F9HNBH', 41, 41, '2019-12-02', 0, 0, 0),
(49, 'X7NERM', 4, 5, '2019-12-02', 0, 1, 50),
(52, '5YC0ND', 6, 7, '2019-12-02', 0, 1, 90),
(53, 'm5udws', 55, 0, '2019-12-02', 0, -55, 0),
(54, '123456', 16, 19, '2019-12-02', 0, 3, 120),
(55, 'F9HNBH', 57, 58, '2019-12-03', 0, 1, 40),
(56, '123456', 1, 1, '2019-12-03', 0, 0, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles`
--

CREATE TABLE `roles` (
  `id_rol` int(11) NOT NULL,
  `nombre_rol` varchar(50) NOT NULL,
  `pagoHora_rol` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `roles`
--

INSERT INTO `roles` (`id_rol`, `nombre_rol`, `pagoHora_rol`) VALUES
(3, 'Programador', 40),
(4, 'Tester tecnico', 35),
(5, 'Perito Informatico', 45),
(6, 'Scrum master', 38),
(7, 'Auditor', 30),
(8, 'Arquitecto software', 55),
(10, 'Informárico empresarial', 35),
(12, 'Narco', 85),
(13, 'Gestor turistico', 50);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `administradores`
--
ALTER TABLE `administradores`
  ADD PRIMARY KEY (`User`);

--
-- Indices de la tabla `empleados`
--
ALTER TABLE `empleados`
  ADD PRIMARY KEY (`cod_emp`),
  ADD KEY `estado_emp` (`estado_emp`),
  ADD KEY `rol_emp` (`rol_emp`);

--
-- Indices de la tabla `estado`
--
ALTER TABLE `estado`
  ADD PRIMARY KEY (`id_estado`);

--
-- Indices de la tabla `ingresoempleados`
--
ALTER TABLE `ingresoempleados`
  ADD PRIMARY KEY (`id_ing`);

--
-- Indices de la tabla `reportes`
--
ALTER TABLE `reportes`
  ADD PRIMARY KEY (`id_rep`),
  ADD KEY `cod_emp2` (`cod_emp2`);

--
-- Indices de la tabla `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id_rol`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `estado`
--
ALTER TABLE `estado`
  MODIFY `id_estado` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `ingresoempleados`
--
ALTER TABLE `ingresoempleados`
  MODIFY `id_ing` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=264;

--
-- AUTO_INCREMENT de la tabla `reportes`
--
ALTER TABLE `reportes`
  MODIFY `id_rep` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=57;

--
-- AUTO_INCREMENT de la tabla `roles`
--
ALTER TABLE `roles`
  MODIFY `id_rol` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `empleados`
--
ALTER TABLE `empleados`
  ADD CONSTRAINT `empleados_ibfk_1` FOREIGN KEY (`estado_emp`) REFERENCES `estado` (`id_estado`),
  ADD CONSTRAINT `empleados_ibfk_2` FOREIGN KEY (`rol_emp`) REFERENCES `roles` (`id_rol`);

--
-- Filtros para la tabla `reportes`
--
ALTER TABLE `reportes`
  ADD CONSTRAINT `reportes_ibfk_1` FOREIGN KEY (`cod_emp2`) REFERENCES `empleados` (`cod_emp`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
