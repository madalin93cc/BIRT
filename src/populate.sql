INSERT INTO user
VALUES (1, "Bucuresti user1", "BCR", "user1cif", "User1 name", "IBANUSER1", "Customer"),
    (2, "Bucuresti user2", "ING", "user2cif", "User2 name", "IBANUSER2", "Provider");

INSERT INTO invoice
VALUES (1, CURRENT_DATE , 1, 1, 2),
    (2, CURRENT_DATE , 2, 1, 2);

INSERT INTO service
VALUES (1, "service1", 1, 1, 1, 24, 1),
    (2, "service2", 2, 2, 2, 24, 1),
    (3, "service3", 3, 3, 3, 24, 2),
    (4, "service4", 4, 4, 4, 24, 2);