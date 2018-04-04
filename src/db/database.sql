
-- MySQL Script generated by MySQL Workbench
-- Sun Feb  4 15:58:21 2018
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`department` Деканат
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`department` (
  `department_id` INT NOT NULL AUTO_INCREMENT,
  `name` TEXT(100) NOT NULL,
  `building_number` INT NOT NULL,
  PRIMARY KEY (`department_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`teacher` Викладач
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`teacher` (
  `teacher_id` INT NOT NULL AUTO_INCREMENT,
  `department_id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `role` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`teacher_id`, `department_id`),
  INDEX `fk_teacher_department_idx` (`department_id` ASC),
  CONSTRAINT `fk_teacher_department`
    FOREIGN KEY (`department_id`)
    REFERENCES `mydb`.`department` (`department_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`course` Курс
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`course` (
  `course_id` INT NOT NULL AUTO_INCREMENT,
  `department_id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `lections` INT NOT NULL,
  `seminars` INT NOT NULL,
  `conclusion` TINYINT(1) NOT NULL,
  `credits` INT NOT NULL,
  `obligatory` TINYINT NOT NULL,
  PRIMARY KEY (`course_id`, `department_id`),
  INDEX `fk_course_department1_idx` (`department_id` ASC),
  CONSTRAINT `fk_course_department1`
    FOREIGN KEY (`department_id`)
    REFERENCES `mydb`.`department` (`department_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`semester`  Семестр
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`semester` (
  `semester_id` INT NOT NULL AUTO_INCREMENT,
  `year` INT NOT NULL,
  `semester` TINYINT(1) NOT NULL,
  PRIMARY KEY (`semester_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`lesson` Заняття, що відбувається для потоку або окремої групи
-- Заняття окремої групи має посилання на заняття потоку
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`lesson` (
  `lesson_id` INT NOT NULL AUTO_INCREMENT,
  `type` TINYINT(1) NOT NULL,
  `teacher_id` INT NOT NULL,
  `course_id` INT NOT NULL,
  `thread_name` VARCHAR(45) NULL,
  `thread_id` INT NULL,
  `group_number` INT NULL,
  `semester_id` INT NOT NULL,
  PRIMARY KEY (`lesson_id`, `teacher_id`, `course_id`, `semester_id`),
  INDEX `fk_lesson_teacher1_idx` (`teacher_id` ASC),
  INDEX `fk_lesson_course1_idx` (`course_id` ASC),
  INDEX `fk_lesson_lecture_id_idx` (`thread_id` ASC),
  INDEX `fk_lesson_semester_id_idx` (`semester_id` ASC),
  CONSTRAINT `fk_lesson_teacher1`
    FOREIGN KEY (`teacher_id`)
    REFERENCES `mydb`.`teacher` (`teacher_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_lesson_course1`
    FOREIGN KEY (`course_id`)
    REFERENCES `mydb`.`course` (`course_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_lesson_lecture_id`
    FOREIGN KEY (`thread_id`)
    REFERENCES `mydb`.`lesson` (`lesson_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_lesson_semester_id`
    FOREIGN KEY (`semester_id`)
    REFERENCES `mydb`.`semester` (`semester_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`student` Студент
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`student` (
  `student_id` INT NOT NULL AUTO_INCREMENT,
  `surname` VARCHAR(45) NOT NULL,
  `speciality` VARCHAR(45) NOT NULL,
  `startdate` DATE NOT NULL,
  `enddate` DATE NULL,
  `enddate_reason` TINYINT(1) NULL,
  `credits` INT ZEROFILL NOT NULL,
  PRIMARY KEY (`student_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`group_has_student` Група студентів: join-таблиця Cтудент-Заняття
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`group_has_student` (
  `group_id` INT NOT NULL,
  `student_id` INT NOT NULL,
  `grade` INT NULL,
  PRIMARY KEY (`group_id`, `student_id`),
  INDEX `fk_group_has_student_student1_idx` (`student_id` ASC),
  CONSTRAINT `fk_group_has_student_group1`
    FOREIGN KEY (`group_id`)
    REFERENCES `mydb`.`lesson` (`lesson_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_group_has_student_student1`
    FOREIGN KEY (`student_id`)
    REFERENCES `mydb`.`student` (`student_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`week` Тиждень
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`week` (
  `week_id` INT NOT NULL AUTO_INCREMENT,
  `number` INT NOT NULL,
  `start` DATE NOT NULL,
  `end` DATE NOT NULL,
  `semester_id` INT NOT NULL,
  PRIMARY KEY (`week_id`, `semester_id`),
  INDEX `fk_week_semester1_idx` (`semester_id` ASC),
  CONSTRAINT `fk_week_semester1`
    FOREIGN KEY (`semester_id`)
    REFERENCES `mydb`.`semester` (`semester_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`schedule` Розклад
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`schedule` (
  `schedule_id` INT NOT NULL AUTO_INCREMENT,
  `day` TINYINT(1) NOT NULL,
  `lesson_number` INT NOT NULL,
  `week_id` INT NOT NULL,
  PRIMARY KEY (`schedule_id`, `week_id`),
  INDEX `fk_schedule_week_id_idx` (`week_id` ASC),
  CONSTRAINT `fk_schedule_week_id`
    FOREIGN KEY (`week_id`)
    REFERENCES `mydb`.`week` (`week_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`class` Аудиторія
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`class` (
  `class_id` INT NOT NULL AUTO_INCREMENT,
  `building` INT NOT NULL,
  `number` INT NOT NULL,
  `capacity` INT NOT NULL,
  PRIMARY KEY (`class_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`lesson_has_schedule` Розклад занять: join-таблиця Заняття-Розклад
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`lesson_has_schedule` (
  `lesson_id` INT NOT NULL,
  `schedule_id` INT NOT NULL,
  `class_id` INT NOT NULL,
  PRIMARY KEY (`lesson_id`, `schedule_id`, `class_id`),
  INDEX `fk_lesson_has_schedule_schedule1_idx` (`schedule_id` ASC),
  INDEX `fk_lesson_has_schedule_lesson1_idx` (`lesson_id` ASC),
  INDEX `fk_lesson_has_schedule_class_id_idx` (`class_id` ASC),
  CONSTRAINT `fk_lesson_has_schedule_lesson1`
    FOREIGN KEY (`lesson_id`)
    REFERENCES `mydb`.`lesson` (`lesson_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_lesson_has_schedule_schedule1`
    FOREIGN KEY (`schedule_id`)
    REFERENCES `mydb`.`schedule` (`schedule_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_lesson_has_schedule_class_id`
    FOREIGN KEY (`class_id`)
    REFERENCES `mydb`.`class` (`class_id`)
    ON DELETE RESTRICT 
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`course_has_precondition_course` Курс-передумова: join-таблиця Курс-Курс
-- -----------------------------------------------------
/* CREATE TABLE IF NOT EXISTS `mydb`.`course_has_precondition_course` (
  `course_id` INT NOT NULL,
  `precondition_course_id` INT NOT NULL,
  PRIMARY KEY (`course_id`, `precondition_course_id`),
  INDEX `fk_course_has_precondition_course_course1_idx` (`course_id` ASC),
  CONSTRAINT `fk_course_has_precondition_course_course1`
    FOREIGN KEY (`course_id`)
    REFERENCES `mydb`.`course` (`course_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_course_has_precondition_course_precondition_course1`
    FOREIGN KEY (`course_id` , `precondition_course_id`)
    REFERENCES `mydb`.`course` (`course_id` , `course_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;
*/

-- -----------------------------------------------------
-- Table `mydb`.`group_exam` Іспит: для групи може бути визначена аудиторія та дата проведення іспиту
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`group_exam` (
  `group_id` INT NOT NULL,
  `class_id` INT NOT NULL,
  `date` DATETIME NULL,
  PRIMARY KEY (`group_id`, `class_id`),
  INDEX `group_exam_class_id_idx` (`class_id` ASC),
  CONSTRAINT `group_exam_group_id`
    FOREIGN KEY (`group_id`)
    REFERENCES `mydb`.`lesson` (`lesson_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `group_exam_class_id`
    FOREIGN KEY (`class_id`)
    REFERENCES `mydb`.`class` (`class_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
