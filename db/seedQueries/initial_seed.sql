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
"hotelID" INTEGER references "HotelApp".hotel("ID"),
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


INSERT INTO "HotelApp".hotel
("ID", "name", address, website, "telNo", fax, "rank")
VALUES(1, 'Aparthotel Colombo Roma', 'Via Cristoforo Colombo, 112, 00154 Roma RM, İtalya', 'https://www.aparthotelcolomboroma.it/', '+390698376099', '', 8.8);

INSERT INTO "HotelApp".room
("ID", "hotelID", "type", "isFull", facilities, description, photo, "roomSize", currency, price)
VALUES(1, 1, 'TwinRoom', false, 
'Desk,
Safety deposit box,
Upper floors accessible by elevator,
Flat-screen TV,
Wake up service/Alarm clock,
Towels,
Socket near the bed,
TV,
Hypoallergenic,
Linen,
Minibar,
Tile/marble floor,
Electric kettle,
Heating,
Wardrobe or closet,
Soundproofing,
Satellite channels,
Air conditioning,
Clothes rack', 
'Offering free toiletries, this double room includes a private bathroom with a shower, a bidet and a hairdryer. The double room offers air conditioning, soundproof walls, a minibar, a wardrobe, as well as a flat-screen TV with satellite channels.',
'https://cf.bstatic.com/xdata/images/hotel/max1024x768/500109161.jpg?k=245406fb7800972b0d5af0f72ac88264eb7d1ee91d6012d588029f0b5a39adeb&o=',
21, 'TL', 2782);


INSERT INTO "HotelApp".room
("ID", "hotelID", "type", "isFull", facilities, description, photo, "roomSize", currency, price)
VALUES(2, 1, 'Triple', false,
'Kitchenette,
Refrigerator,
Microwave,
Kitchenware,
Minibar,
Electric kettle,
Stovetop,
Dining area,
Dining table,
Desk,
Safety deposit box,
Dining table,
Upper floors accessible by elevator,
Flat-screen TV,
Wake up service/Alarm clock,
Sofa,
Towels,
Socket near the bed,
Microwave,
TV,
Refrigerator,
Hypoallergenic,
Linen,
Minibar,
Stovetop,
Tile/marble floor,
Kitchenware,
Kitchenette,
Electric kettle,
Sofa bed,
Heating,
Wardrobe or closet,
Soundproofing,
Satellite channels,
Air conditioning,
Dining area,
Clothes rack', 
'Providing free toiletries, this studio includes a private bathroom with a shower, a bidet and a hairdryer. In the well-equipped kitchenette, guests will find a stovetop, a refrigerator, kitchenware and a microwave. This air-conditioned studio consists of a dining area, a flat-screen TV with satellite channels soundproof walls and a minibar.',
'https://cf.bstatic.com/xdata/images/hotel/max1024x768/500962033.jpg?k=4914b8acd074d298ef936fcc9c4f3218d5785628754a61ef364ea98750760f31&o=',
27, 'TL', 4051);


INSERT INTO "HotelApp".room
("ID", "hotelID", "type", "isFull", facilities, description, photo, "roomSize", currency, price)
VALUES(3, 1, 'Quad', false,
'Kitchenette,
Refrigerator,
Microwave,
Kitchenware,
Minibar,
Electric kettle,
Stovetop,
Dining area,
Dining table,
Desk,
Safety deposit box,
Dining table,
Upper floors accessible by elevator,
Flat-screen TV,
Wake up service/Alarm clock,
Sofa,
Towels,
Socket near the bed,
Microwave,
TV,
Refrigerator,
Hypoallergenic,
Linen,
Minibar,
Stovetop,
Tile/marble floor,
Kitchenware,
Kitchenette,
Electric kettle,
Sofa bed,
Heating,
Wardrobe or closet,
Soundproofing,
Satellite channels,
Air conditioning,
Dining area,
Clothes rack', 
'The air-conditioned apartment features 2 bedrooms and 1 bathroom with a shower and a bidet. Guests can make meals in the kitchenette that comes with a stovetop, a refrigerator, kitchenware and a microwave. The apartment provides a flat-screen TV with satellite channels, soundproof walls, a minibar, a seating area as well as city views. The unit offers 4 beds.',
'https://cf.bstatic.com/xdata/images/hotel/max1024x768/500110067.jpg?k=453d460d9497e7f05c5c54a0c27f7a2ddfd66888b6c5118842d2f161d0bf38a5&o=',
56, 'TL', 6687);

INSERT INTO "HotelApp".room
("ID", "hotelID", "type", "isFull", facilities, description, photo, "roomSize", currency, price)
VALUES(4, 1, 'OnePersonRoom', false,
'Desk,
Safety deposit box,
Upper floors accessible by elevator,
Flat-screen TV,
Wake up service/Alarm clock,
Towels,
Socket near the bed,
TV,
Hypoallergenic,
Linen,
Minibar,
Tile/marble floor,
Electric kettle,
Heating,
Wardrobe or closet,
Soundproofing,
Satellite channels,
Air conditioning,
Clothes rack', 
'Offering free toiletries, this double room includes a private bathroom with a shower, a bidet and a hairdryer. The double room offers air conditioning, soundproof walls, a minibar, a wardrobe, as well as a flat-screen TV with satellite channels.',
'https://cf.bstatic.com/xdata/images/hotel/max1024x768/500109165.jpg?k=96bd09ae5614fe30fd5a7871d77ca3a19116e6e6dd58b496a44258309cb8c72a&o=',
21, 'TL', 2620);


INSERT INTO "HotelApp".room
("ID", "hotelID", "type", "isFull", facilities, description, photo, "roomSize", currency, price)
VALUES(5, 1, 'OnePersonRoom', false,
'Desk,
Safety deposit box,
Upper floors accessible by elevator,
Flat-screen TV,
Wake up service/Alarm clock,
Towels,
Socket near the bed,
TV,
Hypoallergenic,
Linen,
Minibar,
Tile/marble floor,
Electric kettle,
Heating,
Wardrobe or closet,
Soundproofing,
Satellite channels,
Air conditioning,
Clothes rack', 
'Offering free toiletries, this double room includes a private bathroom with a shower, a bidet and a hairdryer. The double room offers air conditioning, soundproof walls, a minibar, a wardrobe, as well as a flat-screen TV with satellite channels.',
'https://cf.bstatic.com/xdata/images/hotel/max1024x768/500109165.jpg?k=96bd09ae5614fe30fd5a7871d77ca3a19116e6e6dd58b496a44258309cb8c72a&o=',
21, 'TL', 2929);
