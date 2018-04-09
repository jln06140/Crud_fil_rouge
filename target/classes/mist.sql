-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  lun. 09 avr. 2018 à 21:18
-- Version du serveur :  5.7.19
-- Version de PHP :  5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `mist`
--

-- --------------------------------------------------------

--
-- Structure de la table `affaire`
--

DROP TABLE IF EXISTS `affaire`;
CREATE TABLE IF NOT EXISTS `affaire` (
  `id` smallint(5) NOT NULL AUTO_INCREMENT,
  `id_agent` smallint(5) NOT NULL,
  `titre` varchar(45) NOT NULL,
  `date_ouverture` varchar(25) NOT NULL,
  `status` varchar(10) NOT NULL,
  `date_cloture` varchar(25) DEFAULT NULL,
  `description` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `affaire`
--

INSERT INTO `affaire` (`id`, `id_agent`, `titre`, `date_ouverture`, `status`, `date_cloture`, `description`) VALUES
(1, 1, 'Entrée Test', '23 septembre 2002', 'Ouverte', NULL, 'Test résumé'),
(2, 1, 'Plus dure sera la chute', '23 septembre 2002', 'Ouverte', NULL, 'Résumé1'),
(3, 2, 'Apparences trompeuses', '30 septembre 2002', 'Ouverte', NULL, 'Résumé2'),
(4, 3, 'Le Prix de la liberté', '7 octobre 2002', 'Ouverte', NULL, 'Résumé3'),
(5, 4, 'Les Dessous de Miami', '14 octobre 2002', 'Ouverte', NULL, 'Résumé4');

-- --------------------------------------------------------

--
-- Structure de la table `agent`
--

DROP TABLE IF EXISTS `agent`;
CREATE TABLE IF NOT EXISTS `agent` (
  `id` bigint(11) NOT NULL,
  `email` varchar(50) CHARACTER SET latin1 NOT NULL,
  `motdepasse` varchar(60) CHARACTER SET latin1 NOT NULL,
  `id_profil` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3wa50if079jnq26tuftbfc853` (`id_profil`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `agent`
--

INSERT INTO `agent` (`id`, `email`, `motdepasse`, `id_profil`) VALUES
(19, 'sev@yahoo.fr', '', 2),
(20, 'bernard.c@free.fr', 'azerty', 1),
(21, 'rene@gmail.com', 'azert', 1);

-- --------------------------------------------------------

--
-- Structure de la table `arme`
--

DROP TABLE IF EXISTS `arme`;
CREATE TABLE IF NOT EXISTS `arme` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type_arme` varchar(255) NOT NULL,
  `type_munition` varchar(255) NOT NULL,
  `nom_arme` varchar(255) NOT NULL,
  `numero_serie_arme` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `arme`
--

INSERT INTO `arme` (`id`, `type_arme`, `type_munition`, `nom_arme`, `numero_serie_arme`) VALUES
(4, 'Semi-Automatique', '22', 'Beretta', 'EX15430984'),
(5, 'Fusil à pompe', '12', 'Winchester', 'LP43GP543');

-- --------------------------------------------------------

--
-- Structure de la table `condamnation`
--

DROP TABLE IF EXISTS `condamnation`;
CREATE TABLE IF NOT EXISTS `condamnation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `motif` varchar(60) CHARACTER SET latin1 NOT NULL,
  `date_condamnation` date NOT NULL,
  `id_suspect_cond` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_suspect_cond` (`id_suspect_cond`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `condamnation`
--

INSERT INTO `condamnation` (`id`, `motif`, `date_condamnation`, `id_suspect_cond`) VALUES
(1, 'cambriolage appartement avenue de paris', '2018-03-05', 1);

-- --------------------------------------------------------

--
-- Structure de la table `document`
--

DROP TABLE IF EXISTS `document`;
CREATE TABLE IF NOT EXISTS `document` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(20) CHARACTER SET latin1 NOT NULL,
  `numero` varchar(30) CHARACTER SET latin1 NOT NULL,
  `date_emission` date DEFAULT NULL,
  `id_suspect` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_suspect_doc` (`id_suspect`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `document`
--

INSERT INTO `document` (`id`, `type`, `numero`, `date_emission`, `id_suspect`) VALUES
(1, 'carte identite', 'PMOO23456', '2008-03-09', 1),
(2, 'permis', 'PM23232323', '2014-12-10', 1);

-- --------------------------------------------------------

--
-- Structure de la table `personne`
--

DROP TABLE IF EXISTS `personne`;
CREATE TABLE IF NOT EXISTS `personne` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) CHARACTER SET latin1 NOT NULL,
  `prenom` varchar(255) CHARACTER SET latin1 NOT NULL,
  `date_naissance` date DEFAULT NULL,
  `adresse` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `ville` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `personne`
--

INSERT INTO `personne` (`id`, `nom`, `prenom`, `date_naissance`, `adresse`, `ville`) VALUES
(1, 'baba', 'ali', '2000-03-06', 'rue des roisiers', 'paris'),
(3, 'doe', 'john', '2008-03-06', 'rue seminaire', 'vence'),
(4, 'dupont', 'roger', '2000-01-17', 'avenue orangers', 'vence'),
(18, 'contant', 'noel', NULL, 'rue du paradis', 'Nice'),
(19, 'lorand', 'severine', NULL, '14 rue de la paix', 'bastia'),
(20, 'cartou', 'bernard', NULL, '1 chemin de la gaude', 'saint jeannet'),
(21, 'caribou', 'rené', NULL, 'rue du quebec', 'quebec');

-- --------------------------------------------------------

--
-- Structure de la table `profil`
--

DROP TABLE IF EXISTS `profil`;
CREATE TABLE IF NOT EXISTS `profil` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `profil`
--

INSERT INTO `profil` (`id`, `libelle`) VALUES
(1, 'ADMIN'),
(2, 'UTILISATEUR');

-- --------------------------------------------------------

--
-- Structure de la table `suspect`
--

DROP TABLE IF EXISTS `suspect`;
CREATE TABLE IF NOT EXISTS `suspect` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `suspect`
--

INSERT INTO `suspect` (`id`) VALUES
(1),
(3),
(4);

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `motdepasse` varchar(60) NOT NULL,
  `id_profil` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2rpdpv15clle66lxyvro27vuh` (`id_profil`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `nom`, `prenom`, `email`, `motdepasse`, `id_profil`) VALUES
(4, 'Jordan', 'Suzanne', 'fdq@fdz.fr', 'cvdsq', 1);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `agent`
--
ALTER TABLE `agent`
  ADD CONSTRAINT `FK3wa50if079jnq26tuftbfc853` FOREIGN KEY (`id_profil`) REFERENCES `profil` (`id`),
  ADD CONSTRAINT `FK5l52l9gsudn8wyev6wmeku516` FOREIGN KEY (`id`) REFERENCES `personne` (`id`),
  ADD CONSTRAINT `fk_id_personne_agent` FOREIGN KEY (`id`) REFERENCES `personne` (`id`),
  ADD CONSTRAINT `fk_id_profil_agent` FOREIGN KEY (`id_profil`) REFERENCES `profil` (`id`);

--
-- Contraintes pour la table `condamnation`
--
ALTER TABLE `condamnation`
  ADD CONSTRAINT `FKl73xch4oouxem3kyb03r4iwd8` FOREIGN KEY (`id_suspect_cond`) REFERENCES `suspect` (`id`),
  ADD CONSTRAINT `fk_id_suspect_cond` FOREIGN KEY (`id_suspect_cond`) REFERENCES `suspect` (`id`);

--
-- Contraintes pour la table `document`
--
ALTER TABLE `document`
  ADD CONSTRAINT `FKondljdp8jrpg1jqghqy706v3w` FOREIGN KEY (`id_suspect`) REFERENCES `suspect` (`id`),
  ADD CONSTRAINT `fk_id_suspect` FOREIGN KEY (`id_suspect`) REFERENCES `suspect` (`id`);

--
-- Contraintes pour la table `suspect`
--
ALTER TABLE `suspect`
  ADD CONSTRAINT `FKo5ym7qqdr9v09a2a8r1hbmuex` FOREIGN KEY (`id`) REFERENCES `personne` (`id`),
  ADD CONSTRAINT `fk_id_suspectt` FOREIGN KEY (`id`) REFERENCES `personne` (`id`);

--
-- Contraintes pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD CONSTRAINT `FK2rpdpv15clle66lxyvro27vuh` FOREIGN KEY (`id_profil`) REFERENCES `profil` (`id`),
  ADD CONSTRAINT `fk_id_profil` FOREIGN KEY (`id_profil`) REFERENCES `profil` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
