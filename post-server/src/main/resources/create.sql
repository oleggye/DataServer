-- -----------------------------------------------------
-- Schema smart_post_box
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `smart_post_box` ;

-- -----------------------------------------------------
-- Schema smart_post_box
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `smart_post_box`;
USE `smart_post_box` ;

-- -----------------------------------------------------
-- Table `smart_post_box`.`post_box`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `smart_post_box`.`post_box` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `address` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `smart_post_box`.`event`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `smart_post_box`.`event` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `smart_post_box`.`event_log`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `smart_post_box`.`event_log` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `id_post_box` INT UNSIGNED NOT NULL,
  `id_event` INT UNSIGNED NOT NULL,
  `quantity` INT UNSIGNED NULL,
  `time` MEDIUMTEXT NOT NULL,
  PRIMARY KEY (`id`),

 foreign key (`id_post_box`) references `smart_post_box`.`post_box` (`id`),
 foreign key (`id_event`) references `smart_post_box`.`event` (`id`))
ENGINE = InnoDB;