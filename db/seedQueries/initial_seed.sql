CREATE TABLE "HotelApp".customer (
"ID" INTEGER PRIMARY KEY,
"username" VARCHAR(10),
"password" VARCHAR(20),
"mail" VARCHAR(20),
"name" VARCHAR(30),
"surname" VARCHAR(30),
"identificationNumber" VARCHAR(20),
"birthDate" VARCHAR(10),
"phone" VARCHAR(15),
"gender" SMALLINT
);

CREATE TABLE "HotelApp".hotel (
"ID" INTEGER PRIMARY KEY,
"name" VARCHAR(30),
"address" TEXT,
"website" VARCHAR(30),
"telNo" VARCHAR(15),
"fax" VARCHAR(15),
"rank" REAL
);

CREATE TABLE "HotelApp".room (
"ID" INTEGER PRIMARY KEY,
"type" VARCHAR(15),
"isFull" BOOLEAN,
"facilities" TEXT,
"description" TEXT,
"photo" TEXT,
"roomSize" INTEGER,
"currency" VARCHAR(15),
"price" REAL
);

CREATE TABLE "HotelApp".reservation (
"ID" INTEGER PRIMARY KEY,
"customerID" INTEGER references "HotelApp".customer("ID"),
"roomID" INTEGER references "HotelApp".room("ID"),
"hotelID" INTEGER references "HotelApp".hotel("ID"),
"checkInDate" VARCHAR(10),
"checkOutDate" VARCHAR(10)
);
