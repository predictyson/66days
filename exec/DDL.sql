-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema 66days
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema 66days
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `66days` ;
USE `66days` ;

-- -----------------------------------------------------
-- Table `66days`.`animal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `66days`.`animal` (
  `animal_id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(20) COLLATE 'utf8mb3_bin' NOT NULL,
  `image_path` VARCHAR(260) COLLATE 'utf8mb3_bin' NOT NULL,
  PRIMARY KEY (`animal_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 26;


-- -----------------------------------------------------
-- Table `66days`.`group`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `66days`.`group` (
  `group_id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(20) COLLATE 'utf8mb3_bin' NOT NULL,
  `description` VARCHAR(100) COLLATE 'utf8mb3_bin' NOT NULL,
  `max_member_count` INT NOT NULL,
  `image_path` VARCHAR(260) COLLATE 'utf8mb3_bin' NULL DEFAULT NULL,
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME NULL DEFAULT NULL,
  `is_deleted` TINYINT(1) NOT NULL DEFAULT '0',
  `owner_id` BINARY(16) NOT NULL,
  PRIMARY KEY (`group_id`),
  INDEX `group_name_idx` (`name` ASC) VISIBLE,
  INDEX `group_created_at_idx` (`created_at` DESC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 26;


-- -----------------------------------------------------
-- Table `66days`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `66days`.`user` (
  `user_id` BINARY(16) NOT NULL,
  `tier_id` BIGINT NOT NULL DEFAULT '1',
  `animal_id` BIGINT NOT NULL,
  `email` VARCHAR(320) COLLATE 'utf8mb3_bin' NOT NULL,
  `profile_image_path` VARCHAR(260) COLLATE 'utf8mb3_bin' NULL DEFAULT '/users/basic_profile.png',
  `nickname` VARCHAR(12) CHARACTER SET 'utf8mb3' NOT NULL,
  `exp` BIGINT NOT NULL DEFAULT '0',
  `point` BIGINT NOT NULL DEFAULT '0',
  `github_url` VARCHAR(260) COLLATE 'utf8mb3_bin' NULL DEFAULT NULL,
  `blog_url` VARCHAR(260) COLLATE 'utf8mb3_bin' NULL DEFAULT NULL,
  `social` VARCHAR(5) COLLATE 'utf8mb3_bin' NOT NULL DEFAULT 'KAKAO',
  `role_type` VARCHAR(5) COLLATE 'utf8mb3_bin' NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `nickname_UNIQUE` (`nickname` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `66days`.`group_member`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `66days`.`group_member` (
  `user_id` BINARY(16) NOT NULL,
  `group_id` BIGINT NOT NULL,
  `authority` VARCHAR(16) COLLATE 'utf8mb3_bin' NOT NULL,
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME NULL DEFAULT NULL,
  `is_deleted` TINYINT(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`user_id`, `group_id`),
  INDEX `fk_group_member_user_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_group_member_group_idx` (`group_id` ASC) VISIBLE,
  CONSTRAINT `fk_group_member_group`
    FOREIGN KEY (`group_id`)
    REFERENCES `66days`.`group` (`group_id`),
  CONSTRAINT `fk_group_member_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `66days`.`user` (`user_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `66days`.`article`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `66days`.`article` (
  `article_id` BIGINT NOT NULL AUTO_INCREMENT,
  `group_id` BIGINT NOT NULL,
  `user_id` BINARY(16) NOT NULL,
  `title` VARCHAR(100) COLLATE 'utf8mb3_bin' NOT NULL,
  `content` VARCHAR(500) COLLATE 'utf8mb3_bin' NOT NULL,
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME NULL DEFAULT NULL,
  `is_deleted` TINYINT(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`article_id`),
  INDEX `article_group_is_deleted_created_at_idx` (`group_id` ASC, `is_deleted` ASC, `created_at` DESC) VISIBLE,
  INDEX `fk_article_group_member_idx` (`user_id` ASC, `group_id` ASC) VISIBLE,
  CONSTRAINT `fk_article_group_member`
    FOREIGN KEY (`user_id` , `group_id`)
    REFERENCES `66days`.`group_member` (`user_id` , `group_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 46;


-- -----------------------------------------------------
-- Table `66days`.`badge`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `66days`.`badge` (
  `badge_id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(20) COLLATE 'utf8mb3_bin' NOT NULL,
  `description` VARCHAR(100) COLLATE 'utf8mb3_bin' NOT NULL,
  `image_path` VARCHAR(260) COLLATE 'utf8mb3_bin' NOT NULL,
  PRIMARY KEY (`badge_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 6;


-- -----------------------------------------------------
-- Table `66days`.`challenge`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `66days`.`challenge` (
  `challenge_id` BIGINT NOT NULL AUTO_INCREMENT,
  `badge_id` BIGINT NOT NULL,
  `topic` VARCHAR(16) COLLATE 'utf8mb3_bin' NOT NULL,
  `badge_image` VARCHAR(50) COLLATE 'utf8mb3_bin' NOT NULL,
  PRIMARY KEY (`challenge_id`),
  INDEX `fk_challenge_badge_idx` (`badge_id` ASC) VISIBLE,
  CONSTRAINT `fk_challenge_badge`
    FOREIGN KEY (`badge_id`)
    REFERENCES `66days`.`badge` (`badge_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 6;


-- -----------------------------------------------------
-- Table `66days`.`group_achievement`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `66days`.`group_achievement` (
  `group_id` BIGINT NOT NULL,
  `challenge_id` BIGINT NOT NULL,
  `achievement_count` INT NOT NULL DEFAULT '0',
  PRIMARY KEY (`group_id`, `challenge_id`),
  INDEX `fk_group_achievement_challenge_idx` (`challenge_id` ASC) VISIBLE,
  CONSTRAINT `fk_group_achievement_challenge`
    FOREIGN KEY (`challenge_id`)
    REFERENCES `66days`.`challenge` (`challenge_id`),
  CONSTRAINT `fk_group_achievement_group`
    FOREIGN KEY (`group_id`)
    REFERENCES `66days`.`group` (`group_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `66days`.`group_application`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `66days`.`group_application` (
  `group_application_id` BIGINT NOT NULL AUTO_INCREMENT,
  `user_id` BINARY(16) NOT NULL,
  `group_id` BIGINT NOT NULL,
  `applied_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `state` VARCHAR(16) COLLATE 'utf8mb3_bin' NOT NULL,
  PRIMARY KEY (`group_application_id`),
  INDEX `group_application_group_id_state` (`group_id` ASC, `state` ASC) VISIBLE,
  INDEX `fk_group_application_group_idx` (`group_id` ASC) VISIBLE,
  INDEX `fk_group_application_user_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_group_application_group`
    FOREIGN KEY (`group_id`)
    REFERENCES `66days`.`group` (`group_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_group_application_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `66days`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 9;


-- -----------------------------------------------------
-- Table `66days`.`group_challenge`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `66days`.`group_challenge` (
  `group_challenge_id` BIGINT NOT NULL AUTO_INCREMENT,
  `challenge_id` BIGINT NOT NULL,
  `group_id` BIGINT NOT NULL,
  `content` VARCHAR(100) COLLATE 'utf8mb3_bin' NOT NULL,
  `start_at` DATETIME NOT NULL,
  `end_at` DATETIME NOT NULL,
  `state` VARCHAR(16) COLLATE 'utf8mb3_bin' NOT NULL,
  `available_freezing_count` INT NOT NULL DEFAULT '0',
  `max_member_count` INT NOT NULL,
  `challenge_name` VARCHAR(256) COLLATE 'utf8mb3_bin' NOT NULL,
  PRIMARY KEY (`group_challenge_id`),
  INDEX `group_challenge_group_id_state_idx` (`group_id` ASC, `state` ASC) VISIBLE,
  INDEX `fk_group_challenge_challenge_idx` (`challenge_id` ASC) VISIBLE,
  INDEX `fk_group_challenge_group_idx` (`group_id` ASC) VISIBLE,
  CONSTRAINT `fk_group_challenge_challenge`
    FOREIGN KEY (`challenge_id`)
    REFERENCES `66days`.`challenge` (`challenge_id`),
  CONSTRAINT `fk_group_challenge_group`
    FOREIGN KEY (`group_id`)
    REFERENCES `66days`.`group` (`group_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 13;


-- -----------------------------------------------------
-- Table `66days`.`group_challenge_application`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `66days`.`group_challenge_application` (
  `application_id` BIGINT NOT NULL AUTO_INCREMENT,
  `group_challenge_id` BIGINT NOT NULL,
  `user_id` BINARY(16) NOT NULL,
  `state` VARCHAR(10) COLLATE 'utf8mb3_bin' NOT NULL,
  `applied_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`application_id`),
  INDEX `user_id` (`user_id` ASC) VISIBLE,
  INDEX `group_challenge_application_group_challenge_id_state` (`group_challenge_id` ASC, `state` ASC) VISIBLE,
  INDEX `group_challenge_id` (`group_challenge_id` ASC) VISIBLE,
  CONSTRAINT `group_challenge_application_ibfk_1`
    FOREIGN KEY (`group_challenge_id`)
    REFERENCES `66days`.`group_challenge` (`group_challenge_id`),
  CONSTRAINT `group_challenge_application_ibfk_2`
    FOREIGN KEY (`user_id`)
    REFERENCES `66days`.`user` (`user_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4;


-- -----------------------------------------------------
-- Table `66days`.`group_challenge_member`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `66days`.`group_challenge_member` (
  `user_id` BINARY(16) NOT NULL,
  `group_challenge_id` BIGINT NOT NULL,
  PRIMARY KEY (`user_id`, `group_challenge_id`),
  INDEX `fk_group_challenge_member_group_challenge_idx` (`group_challenge_id` ASC) VISIBLE,
  CONSTRAINT `fk_group_challenge_member_group_challenge`
    FOREIGN KEY (`group_challenge_id`)
    REFERENCES `66days`.`group_challenge` (`group_challenge_id`),
  CONSTRAINT `fk_group_challenge_member_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `66days`.`user` (`user_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `66days`.`group_comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `66days`.`group_comment` (
  `comment_id` BIGINT NOT NULL AUTO_INCREMENT,
  `article_id` BIGINT NOT NULL,
  `user_id` BINARY(16) NOT NULL,
  `group_id` BIGINT NOT NULL,
  `content` VARCHAR(200) COLLATE 'utf8mb3_bin' NOT NULL,
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `is_deleted` TINYINT(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`comment_id`),
  INDEX `group_comment_article_is_deleted_created_at_idx` (`article_id` ASC, `is_deleted` ASC, `created_at` DESC) VISIBLE,
  INDEX `fk_group_comment_article_idx` (`article_id` ASC) VISIBLE,
  INDEX `fk_group_comment_group_member_idx` (`user_id` ASC, `group_id` ASC) VISIBLE,
  CONSTRAINT `fk_group_comment_article`
    FOREIGN KEY (`article_id`)
    REFERENCES `66days`.`article` (`article_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_group_comment_group_member`
    FOREIGN KEY (`user_id` , `group_id`)
    REFERENCES `66days`.`group_member` (`user_id` , `group_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 14;


-- -----------------------------------------------------
-- Table `66days`.`item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `66days`.`item` (
  `item_id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(20) COLLATE 'utf8mb3_bin' NOT NULL,
  `description` VARCHAR(100) COLLATE 'utf8mb3_bin' NOT NULL,
  `price` INT NOT NULL,
  `image_path` VARCHAR(260) COLLATE 'utf8mb3_bin' NOT NULL,
  PRIMARY KEY (`item_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2;


-- -----------------------------------------------------
-- Table `66days`.`inventory`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `66days`.`inventory` (
  `user_id` BINARY(16) NOT NULL,
  `item_id` BIGINT NOT NULL,
  `quantity` INT NOT NULL DEFAULT '1',
  PRIMARY KEY (`user_id`, `item_id`),
  INDEX `fk_inventory_item` (`item_id` ASC) VISIBLE,
  CONSTRAINT `fk_inventory_item`
    FOREIGN KEY (`item_id`)
    REFERENCES `66days`.`item` (`item_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_inventory_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `66days`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `66days`.`my_achievement`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `66days`.`my_achievement` (
  `user_id` BINARY(16) NOT NULL,
  `challenge_id` BIGINT NOT NULL,
  `achievement_count` INT NOT NULL DEFAULT '0',
  PRIMARY KEY (`user_id`, `challenge_id`),
  INDEX `fk_my_achievement_challenge_idx` (`challenge_id` ASC) VISIBLE,
  CONSTRAINT `fk_my_achievement_challenge`
    FOREIGN KEY (`challenge_id`)
    REFERENCES `66days`.`challenge` (`challenge_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_my_achievement_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `66days`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `66days`.`my_challenge`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `66days`.`my_challenge` (
  `my_challenge_id` BIGINT NOT NULL AUTO_INCREMENT,
  `user_id` BINARY(16) NOT NULL,
  `challenge_id` BIGINT NOT NULL,
  `content` VARCHAR(100) COLLATE 'utf8mb3_bin' NOT NULL,
  `start_at` DATETIME NOT NULL,
  `end_at` DATETIME NOT NULL,
  `state` VARCHAR(16) COLLATE 'utf8mb3_bin' NOT NULL,
  `challenge_name` VARCHAR(256) COLLATE 'utf8mb3_bin' NOT NULL,
  PRIMARY KEY (`my_challenge_id`),
  INDEX `my_challenge_user_id_state_idx` (`user_id` ASC, `state` ASC) VISIBLE,
  INDEX `fk_personal_challenge_user_idx` (`user_id` ASC) INVISIBLE,
  INDEX `fk_personal_challenge_challenge_idx` (`challenge_id` ASC) VISIBLE,
  CONSTRAINT `fk_personal_challenge_challenge`
    FOREIGN KEY (`challenge_id`)
    REFERENCES `66days`.`challenge` (`challenge_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_personal_challenge_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `66days`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 8;


-- -----------------------------------------------------
-- Table `66days`.`tier`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `66days`.`tier` (
  `tier_id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(20) COLLATE 'utf8mb3_bin' NOT NULL,
  `image_path` VARCHAR(260) COLLATE 'utf8mb3_bin' NOT NULL,
  `title` VARCHAR(20) COLLATE 'utf8mb3_bin' NOT NULL,
  `required_exp` BIGINT NOT NULL,
  PRIMARY KEY (`tier_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 7;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
