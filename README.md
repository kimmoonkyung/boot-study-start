# boot-study-start
부트 공부시작

mysql db 생성 쿼리문
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema study
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `study` ;

-- -----------------------------------------------------
-- Schema study
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `study` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin ;
USE `study` ;

-- -----------------------------------------------------
-- Table `study`.`admin_user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `study`.`admin_user` ;

CREATE TABLE IF NOT EXISTS `study`.`admin_user` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'index',
  `account` VARCHAR(12) NOT NULL,
  `password` VARCHAR(50) NOT NULL,
  `status` VARCHAR(50) NOT NULL,
  `role` VARCHAR(50) NOT NULL,
  `last_login_at` DATETIME NULL DEFAULT NULL,
  `login_fail_count` INT NULL DEFAULT NULL,
  `password_updated_at` datetime DEFAULT NULL,
  `registered_at` DATETIME NULL DEFAULT NULL,
  `unregistered_at` DATETIME NULL DEFAULT NULL,
  `created_at` DATETIME NOT NULL,
  `created_by` VARCHAR(20) NOT NULL,
  `updated_at` DATETIME NULL DEFAULT NULL,
  `updated_by` VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;


-- -----------------------------------------------------
-- Table `study`.`category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `study`.`category` ;

CREATE TABLE IF NOT EXISTS `study`.`category` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(50) NOT NULL,
  `title` VARCHAR(100) NULL DEFAULT NULL,
  `created_at` DATETIME NOT NULL,
  `created_by` VARCHAR(20) NOT NULL,
  `updated_at` DATETIME NULL DEFAULT NULL,
  `updated_by` VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;


-- -----------------------------------------------------
-- Table `study`.`partner`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `study`.`partner` ;

CREATE TABLE IF NOT EXISTS `study`.`partner` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL DEFAULT NULL,
  `status` VARCHAR(45) NULL DEFAULT NULL,
  `address` VARCHAR(100) NULL DEFAULT NULL,
  `call_center` VARCHAR(13) NULL DEFAULT NULL,
  `partner_number` VARCHAR(13) NULL DEFAULT NULL,
  `business_number` VARCHAR(16) NULL DEFAULT NULL,
  `ceo_name` VARCHAR(20) NULL DEFAULT NULL,
  `registered_at` DATETIME NULL DEFAULT NULL,
  `unregistered_at` DATETIME NULL DEFAULT NULL,
  `created_at` DATETIME NOT NULL,
  `created_by` VARCHAR(20) NOT NULL,
  `updated_at` DATETIME NULL DEFAULT NULL,
  `updated_by` VARCHAR(20) NULL DEFAULT NULL,
  `category_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;


-- -----------------------------------------------------
-- Table `study`.`item`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `study`.`item` ;

CREATE TABLE IF NOT EXISTS `study`.`item` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `status` VARCHAR(50) NULL DEFAULT NULL,
  `name` VARCHAR(45) NOT NULL,
  `title` VARCHAR(100) NOT NULL,
  `content` TEXT NOT NULL,
  `price` DECIMAL(12,4) NOT NULL,
  `brand_name` VARCHAR(45) NULL DEFAULT NULL,
  `registered_at` VARCHAR(45) NULL DEFAULT NULL,
  `unregistered_at` DATETIME NULL DEFAULT NULL,
  `created_at` DATETIME NULL DEFAULT NULL,
  `created_by` VARCHAR(20) NULL DEFAULT NULL,
  `updated_at` DATETIME NULL DEFAULT NULL,
  `updated_by` VARCHAR(20) NULL DEFAULT NULL,
  `partner_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;


-- -----------------------------------------------------
-- Table `study`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `study`.`user` ;

CREATE TABLE IF NOT EXISTS `study`.`user` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'index',
  `account` VARCHAR(12) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `status` VARCHAR(50) NOT NULL,
  `email` VARCHAR(100) NULL DEFAULT NULL,
  `phone_number` VARCHAR(13) NOT NULL,
  `registered_at` DATETIME NULL DEFAULT NULL,
  `unregistered_at` DATETIME NULL DEFAULT NULL,
  `created_at` DATETIME NOT NULL,
  `created_by` VARCHAR(20) NOT NULL,
  `updated_at` DATETIME NULL DEFAULT NULL,
  `updated_by` VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;


-- -----------------------------------------------------
-- Table `study`.`order_group`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `study`.`order_group` ;

CREATE TABLE IF NOT EXISTS `study`.`order_group` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `status` VARCHAR(50) NOT NULL,
  `order_type` VARCHAR(50) NOT NULL,
  `rev_address` VARCHAR(100) NULL DEFAULT NULL,
  `rev_name` VARCHAR(50) NULL DEFAULT NULL,
  `payment_type` VARCHAR(50) NULL DEFAULT NULL,
  `total_price` DECIMAL(12,4) NULL DEFAULT NULL,
  `total_quantity` INT NULL DEFAULT NULL,
  `order_at` DATETIME NULL DEFAULT NULL,
  `arrival_date` DATETIME NULL DEFAULT NULL,
  `created_at` DATETIME NULL DEFAULT NULL,
  `created_by` VARCHAR(20) NULL DEFAULT NULL,
  `updated_at` DATETIME NULL DEFAULT NULL,
  `updated_by` VARCHAR(20) NULL DEFAULT NULL,
  `user_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;


-- -----------------------------------------------------
-- Table `study`.`order_detail`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `study`.`order_detail` ;

CREATE TABLE IF NOT EXISTS `study`.`order_detail` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `status` VARCHAR(50) NOT NULL,
  `arrival_date` DATETIME NOT NULL,
  `quantity` INT NULL DEFAULT NULL,
  `total_price` DECIMAL(12,4) NULL DEFAULT NULL,
  `created_at` DATETIME NOT NULL,
  `created_by` VARCHAR(20) NOT NULL,
  `updated_at` DATETIME NULL DEFAULT NULL,
  `updated_by` VARCHAR(20) NULL DEFAULT NULL,
  `order_group_id` BIGINT NOT NULL,
  `item_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

---------------------------------------------------------------------------------------------------------
item create json data

{
    "transaction_time": "2021-05-29T22:17:18.646814",
    "result_code": "OK",
    "description": "OK",
    "data": {
        "status": "REGISTERED",
        "name": "갤럭시 서페이스",
        "title": "갤럭시",
        "content": "2021년 여름 한정 세일",
        "price": 998000 ,
        "brand_name": "삼성소프트",
        "partner_id": 1
    }
}
---------------------------------------------------------------------------------------------------------

---------------------------------------------------------------------------------------------------------
orderGroup create json data

{
    "transaction_time": "2021-05-29T23:30:18.646814",
    "result_code": "OK",
    "description": "OK",
    "data": {
        "status": "COMPLETE",
        "order_type": "ALL"
        "rev_address": "서울시 서초구",
        "rev_name": "노충내",
        "payment_type": "CASH",
        "total_price": 75000 ,
        "total_quantity": 3,
        "order_at": "2021-05-29T23:30:18.646814",
        "user_id": 2
    }
}
---------------------------------------------------------------------------------------------------------

---------------------------------------------------------------------------------------------------------
    CRUD API 로직
        C > Request 데이터(body)를 받아와서
            body로 엔티티를 빌드해주고.
            빌드 해준 엔티티를 뉴 엔티티로 repository에 세이브 시킨다.
            세이브 해준 뉴엔티티는 response로 배출
        R > id를 받아와서
            repository에서 findById 해주고
            .map(있으면 response 리턴 한다. 무엇을? OK를 )
            .orElesGet(없으면 response에 Error를 리턴한다.)
        U > request 데이터(body)를 받아와서
            body에 있는 id로 찾되,
            .map(엔티티가 있으면 body의 데이터로 set 시켜주고 셋 시킨 엔티티를 리턴시킨다)
            .map(리턴 된 엔티티를 repository에 세이브 시킨다.)
            .수정이 정상적으로 실행 되었으면 엔티티를 리스폰스 해준다.
            .없으면 에러를 리스폰스 한다.
        D > id를 받아와서
            repository에서 id로 찾아주고
            .있으면 delete 시키고 Ok를 리턴한다.
            .없으면 에러 리턴
---------------------------------------------------------------------------------------------------------

---------------------------------------------------------------------------------------------------------
테스트 코드로 DB에 샘플 데이터 INSERT
카테고리 -> 파트너 -> 아이템 -> 유저 -> 오더디테일(그룹도 같이 생성됨)
    또는 sql 파일 실행
---------------------------------------------------------------------------------------------------------

    http://localhost:8080/pages
