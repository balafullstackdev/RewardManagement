-- Inserting data into the customers table
INSERT INTO CUSTOMER(CUSTOMER_ID, FIRST_NAME,LAST_NAME,EMAIL,PHONE) 
VALUES 
  (1, 'Bala', 'A', 'bala.a@gmail.com', '603-555-1234'),
  (2, 'Jane', 'Doe', 'jane.doe@email.com', '603-555-5678'),
  (3, 'John', 'Paul', 'john.paul@email.com', '603-555-1278');

--Insert Into Transaciotn table 

INSERT INTO TRANSACTION(TRANSACTION_ID,CUSTOMER_ID,UPDATED_DT,AMOUNT) 
VALUES 
	(1,1,'2022-12-12',130),
	(2,1,'2022-12-01',40),
	(3,1,'2023-03-04',160),
	(4,1,'2023-03-01',90),
	(5,1,'2023-02-04',120),
	(6,1,'2023-02-05',165),
	(7,1,'2023-01-05',113),
	(8,2,'2023-01-27',80),
	(9,2,'2023-03-04',102),
	(11,2,'2022-12-12',120),
	(12,2,'2022-12-01',85),
	(13,2,'2023-03-04',160),
	(14,2,'2023-03-01',90),
	(15,2,'2023-02-04',120),
	(16,2,'2023-02-05',165),
	(17,2,'2022-12-05',113),
	(18,2,'2023-03-27',80),
	(19,2,'2023-03-04',102),
	(20,3,'2023-03-27',120);


COMMIT;