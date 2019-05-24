/**
  * This is the movie rental  shop management system database.  *
  * It consists of customers, titles, membership, tables.
  */
  
  /** Drop database if exists MovieRentals. */
  DROP DATABASE IF EXISTS MovieRentals;
  
  /** Create database MovieRentals if not exists. */
  CREATE DATABASE IF NOT EXISTS MovieRentals;
  
  /** Use this database for this query. */
  USE MovieRentals;
  
  /** Drop Customers table if exists. */
  DROP TABLE IF EXISTS Customers;
  
  /** Create Customers table if not exists. */
  CREATE TABLE IF NOT EXISTS Customers(
  CustomerID INT(11) NOT NULL AUTO_INCREMENT,
  FirstName VARCHAR(80) NOT NULL,
  LastName VARCHAR(80) NOT NULL,
  PhoneNumber CHAR(20) NOT NULL,
  PRIMARY KEY (CustomerID),
  UNIQUE KEY (PhoneNumber)
  ) ENGINE=InnoDB;
  
      /** Drop GenreType table if exists. */
   DROP TABLE IF EXISTS GenreType;
   
   /** Create GenreType table if not exists. */
   CREATE TABLE IF NOT EXISTS GenreType(
   TitleID INT(11) NOT NULL AUTO_INCREMENT,
   Genre VARCHAR(20) NOT NULL,
   TitleName VARCHAR(255) NOT NULL,
   YearOfRelease INT(4) NOT NULL,
   Format VARCHAR(20) NOT NULL,
   NumbersInStock INT NOT NULL,
   CustomerID INT(11) NULL,
   PRIMARY KEY (TitleID),
   FOREIGN KEY(CustomerID) REFERENCES Customers(CustomerID)
   ON UPDATE CASCADE
   ON DELETE CASCADE
   ) ENGINE=InnoDB;
      
   /** Drop MembershipCard if exists. */
   DROP TABLE IF EXISTS MembershipCard;
   
   /** Create MembershipCard if not exists. */
   CREATE TABLE IF NOT EXISTS MembershipCard(
   MembershipCardID INT(20) NOT NULL AUTO_INCREMENT,
   Subscribed VARCHAR(20),
   CurrentPoint INT(20),
   RewardPoint INT(20),
   CustomerID INT(11) NOT NULL,
   PRIMARY KEY (MembershipCardID),
   FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
   ON UPDATE CASCADE
   ON DELETE RESTRICT
   ) ENGINE=InnoDB;

/** Drop CustomerRented table if exists. */
   DROP TABLE IF EXISTS CustomerRented;

/** Create CustomerRented if not exists. */
   CREATE TABLE IF NOT EXISTS CustomerRented(
   CustomerRentedID INT(11) NOT NULL AUTO_INCREMENT,
   CustomerID INT(11) NOT NULL,
   TitleName VARCHAR(255) NULL,
   RentQuantity INT(11) NOT NULL,
   RentDate DATE NULL,
   FOREIGN KEY(CustomerID) REFERENCES Customers(CustomerID)
   ON UPDATE CASCADE
   ON DELETE CASCADE,
   PRIMARY KEY(CustomerRentedID)
   ) ENGINE=InnoDB;
   

/** Insert some records into Customers for testing. */
INSERT INTO Customers VALUES(
900001, 'Jessie', 'Wong', '0871234567'),
(0, "Dane", "Chan", '0867654321'),
(0, 'Belle', 'Washington', '0123004040');

/** Insert some records into GenreType table for testing .*/
INSERT INTO GenreType VALUES(
   100001, 'Music', 'total eclipse of my heart', 1988, 'CD', 12, 900002),
   (0, 'Music Video', 'white angel', 2002, 'Blue-Ray', 23, 900001),
   (0, 'Live Video', 'hero', 2003, 'DVD', 3, 900003);
   
   /** Insert some records into MembershipCard table for testing .*/
   INSERT INTO MembershipCard VALUES(
   700100, 'Premium', 220, 100, 900001),
   (0, 'Live Video', 150, 20, 900002);
   
   /** Insert some records into CustomerRented table for testing .*/
   INSERT INTO CustomerRented VALUES(
   500100, 900001, 'total eclipse of my heart', 1, '2019-05-11');
   