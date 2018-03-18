-- -----------------------------------------------------
-- Schema smart_post_box
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `smart_post_box` ;

-- -----------------------------------------------------
-- Schema smart_post_box
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `smart_post_box` DEFAULT CHARACTER SET utf8 ;
USE `smart_post_box` ;

-- -----------------------------------------------------
-- Table `smart_post_box`.`post_box`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `smart_post_box`.`post_box` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `id_address` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `postbox_to_address_idx` (`id_address` ASC),
  CONSTRAINT `postbox_to_address`
    FOREIGN KEY (`id_address`)
    REFERENCES `smart_post_box`.`address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `smart_post_box`.`event`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `smart_post_box`.`event` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


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
  INDEX `event_history_to_event_idx` (`id_event` ASC),
  INDEX `event_history_to_post_box_idx` (`id_post_box` ASC),
  CONSTRAINT `event_history_to_subscription`
    FOREIGN KEY (`id_post_box`)
    REFERENCES `smart_post_box`.`post_box` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `event_history_to_post_box`
    FOREIGN KEY (`id_event`)
    REFERENCES `smart_post_box`.`event` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Data for table `smart_post_box`.`user`
-- -----------------------------------------------------
START TRANSACTION;
USE `smart_post_box`;
INSERT INTO `smart_post_box`.`user` (`id`, `name`, `surname`, `login`, `id_address`) VALUES (1, 'admin', 'admin', 'admin', DEFAULT);

COMMIT;


-- -----------------------------------------------------
-- Data for table `smart_post_box`.`post_box`
-- -----------------------------------------------------
START TRANSACTION;
USE `smart_post_box`;
INSERT INTO `smart_post_box`.`post_box` (`id`, `id_address`) VALUES (222850, П. Бровки 14);
INSERT INTO `smart_post_box`.`post_box` (`id`, `id_address`) VALUES (222851, Ф. Скорины 8 к.2);

COMMIT;

-- -----------------------------------------------------
-- Data for table `smart_post_box`.`event`
-- -----------------------------------------------------
START TRANSACTION;
USE `smart_post_box`;
INSERT INTO `smart_post_box`.`event` (`id`, `name`) VALUES (1, 'REGISTRATION');
INSERT INTO `smart_post_box`.`event` (`id`, `name`) VALUES (2, 'KEEP_ALIVE');
INSERT INTO `smart_post_box`.`event` (`id`, `name`) VALUES (3, 'HAS_OPENED');
INSERT INTO `smart_post_box`.`event` (`id`, `name`) VALUES (4, 'HAS_CLOSED');
INSERT INTO `smart_post_box`.`event` (`id`, `name`) VALUES (5, 'QUANTITY_CHANGED');
INSERT INTO `smart_post_box`.`event` (`id`, `name`) VALUES (6, 'LOG');

COMMIT;


-- -----------------------------------------------------
-- Data for table `smart_post_box`.`event_log`
-- -----------------------------------------------------
START TRANSACTION;
USE `smart_post_box`;
INSERT INTO `smart_post_box`.`event_log` (`id`, `id_post_box`, `id_event`, `quantity`, `time`) VALUES (1, 1, 1, NULL, DEFAULT);

COMMIT;

