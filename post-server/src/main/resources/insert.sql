-- Delete all data from tables --
USE `smart_post_box`;
SET FOREIGN_KEY_CHECKS=0;

DELETE FROM `smart_post_box`.`post_box`;
DELETE FROM `smart_post_box`.`event`;
DELETE FROM `smart_post_box`.`event_log`;
SET FOREIGN_KEY_CHECKS=1;

COMMIT;

-- -----------------------------------------------------
-- Data for table `smart_post_box`.`post_box`
-- -----------------------------------------------------
USE `smart_post_box`;
INSERT INTO `smart_post_box`.`post_box` (`id`, `address`) VALUES (222850, 'П. Бровки 14');
INSERT INTO `smart_post_box`.`post_box` (`id`, `address`) VALUES (222851, 'Ф. Скорины 8 к.2');

-- -----------------------------------------------------
-- Data for table `smart_post_box`.`event`
-- -----------------------------------------------------
USE `smart_post_box`;

INSERT INTO `smart_post_box`.`event` (`id`, `name`) VALUES (1, 'REGISTRATION');
INSERT INTO `smart_post_box`.`event` (`id`, `name`) VALUES (2, 'KEEP_ALIVE');
INSERT INTO `smart_post_box`.`event` (`id`, `name`) VALUES (3, 'HAS_OPENED');
INSERT INTO `smart_post_box`.`event` (`id`, `name`) VALUES (4, 'HAS_CLOSED');
INSERT INTO `smart_post_box`.`event` (`id`, `name`) VALUES (5, 'QUANTITY_CHANGED');
INSERT INTO `smart_post_box`.`event` (`id`, `name`) VALUES (6, 'LOG');


-- -----------------------------------------------------
-- Data for table `smart_post_box`.`event_log`
-- -----------------------------------------------------
USE `smart_post_box`;
INSERT INTO `smart_post_box`.`event_log` (`id`, `id_post_box`, `id_event`, `quantity`, `time`) VALUES (1, 222850, 1, NULL, 1234567891);

COMMIT;