use javaproject;

INSERT INTO wxy_station (station_id, station_name) VALUES
    (1, "Portland"),
    (2, "Boston"),
    (3, "New York"),
    (4, "Baltimore"),
    (5, "Richmond"),
    (6, "Orlando"),
    (7, "Miami"),
    (8, "Pittsburgh"),
    (9, "Ann Arbor"),
    (10, "Detroit"),
    (11, "Chicago"),
    (12, "Washington DC"),
    (13, "Atlanta"),
    (14, "Nashville"),
    (15, "New Orleans");



INSERT INTO WXY_PATH (PATH_ID) VALUES 
    (1), (2), 
    (3), (4), 
    (5), (6), 
    (7), (8), 
    (9), (10);

-- Dec.23 -Dec.29 
INSERT INTO wxy_path_station (path_id, station_id, station_type, start_time, a_seats_avialable, b_seats_avialable, c_seats_avialable) VALUES

-- Dec. 23 line1 north to south
(1, 1, "start", '2024-12-23 07:00:00', 10, 20, 70),
(1, 2, "stop", '2024-12-23 09:00:00', 10, 20, 70),
(1, 3, "stop", '2024-12-23 11:00:00', 10, 20, 70),
(1, 4,  "stop", '2024-12-23 13:00:00', 10, 20, 70),
(1, 5, "stop", '2024-12-23 15:00:00', 10, 20, 70),
(1, 6, "stop", '2024-12-23 17:00:00', 10, 20, 70),
(1, 7, "end", '2024-12-23 19:00:00', 10, 20, 70),
-- Dec. 23 line2 south to north
(2, 7, "start", '2024-12-23 07:00:00', 10, 20, 70),
(2, 6, "stop", '2024-12-23 09:00:00', 10, 20, 70),
(2, 5, "stop", '2024-12-23 11:00:00', 10, 20, 70),
(2, 4,  "stop", '2024-12-23 13:00:00', 10, 20, 70),
(2, 3, "stop", '2024-12-23 15:00:00', 10, 20, 70),
(2, 2, "stop", '2024-12-23 17:00:00', 10, 20, 70),
(2, 1, "end", '2024-12-23 19:00:00', 10, 20, 70),
-- Dec. 23 line 3 east to west
(3, 3, "start", '2024-12-23 07:00:00', 10, 20, 70),
(3, 8, "stop", '2024-12-23 10:00:00', 10, 20, 70),
(3, 9, "stop", '2024-12-23 13:00:00', 100, 20, 70),
(3, 10, "end", '2024-12-23 16:00:00', 10, 20, 70),
-- Dec. 23 line 4 west to east
(4, 10, "start", '2024-12-23 07:00:00', 10, 20, 70),
(4, 9, "stop", '2024-12-23 10:00:00', 10, 20, 70),
(4, 8, "stop", '2024-12-23 13:00:00', 10, 20, 70),
(4, 3, "end", '2024-12-23 16:00:00', 10, 20, 70),
-- Dec. 23 line 5 east to west
(5, 4, "start", '2024-12-23 07:00:00', 10, 20, 70),
(5, 12, "stop", '2024-12-23 09:00:00', 10, 20, 70),
(5, 8, "stop", '2024-12-23 11:00:00', 10, 20, 70),
(5, 9, "stop", '2024-12-23 13:00:00', 10, 20, 70),
(5, 11, "end", '2024-12-23 15:00:00', 10, 20, 70),
 -- Dec. 23 line 6 west to east
(6, 11, "start", '2024-12-23 07:00:00', 10, 20, 70),
(6, 9, "stop", '2024-12-23 09:00:00', 10, 20, 70),
(6, 8, "stop", '2024-12-23 11:00:00', 10, 20, 70),
(6, 12, "stop", '2024-12-23 13:00:00', 10, 20, 70),
(6, 4, "end", '2024-12-23 15:00:00', 10, 20, 70),
-- Dec. 23 line 7 east to west
(7, 5, "start", '2024-12-23 07:00:00', 10, 20, 70),
(7, 13, "stop", '2024-12-23 10:00:00', 10, 20, 70),
(7, 14, "end", '2024-12-23 13:00:00', 10, 20, 70),
-- Dec. 23 line 8 west to east
(8, 14, "start", '2024-12-23 07:00:00', 10, 20, 70),
(8, 13, "stop", '2024-12-23 10:00:00', 10, 20, 70),
(8, 5, "end", '2024-12-23 13:00:00', 10, 20, 70),
-- Dec. 23 line 9 north to south
(9, 10, "start", '2024-12-23 07:00:00', 10, 20, 70),
(9, 9, "stop", '2024-12-23 10:00:00', 10, 20, 70),
(9, 11, "stop", '2024-12-23 13:00:00', 10, 20, 70),
(9, 14, "stop", '2024-12-23 15:00:00', 10, 20, 70),
(9, 15, "end", '2024-12-23 18:00:00', 10, 20, 70),
-- Dec. 23 line 10 north to south
(10, 15, "start", '2024-12-23 07:00:00', 10, 20, 70),
(10, 14, "stop", '2024-12-23 10:00:00', 10, 20, 70),
(10, 11, "stop", '2024-12-23 13:00:00', 10, 20, 70),
(10, 9, "stop", '2024-12-23 15:00:00', 10, 20, 70),
(10, 10, "end", '2024-12-23 18:00:00', 10, 20, 70),

-- Dec. 24 line1 north to south
(1, 1, "start", '2024-12-24 07:00:00', 10, 20, 70),
(1, 2, "stop", '2024-12-24 09:00:00', 10, 20, 70),
(1, 3, "stop", '2024-12-24 11:00:00', 10, 20, 70),
(1, 4,  "stop", '2024-12-24 13:00:00', 10, 20, 70),
(1, 5, "stop", '2024-12-24 15:00:00', 10, 20, 70),
(1, 6, "stop", '2024-12-24 17:00:00', 10, 20, 70),
(1, 7, "end", '2024-12-24 19:00:00', 10, 20, 70),
-- Dec. 24 line2 south to north
(2, 7, "start", '2024-12-24 07:00:00', 10, 20, 70),
(2, 6, "stop", '2024-12-24 09:00:00', 10, 20, 70),
(2, 5, "stop", '2024-12-24 11:00:00', 10, 20, 70),
(2, 4,  "stop", '2024-12-24 13:00:00', 10, 20, 70),
(2, 3, "stop", '2024-12-24 15:00:00', 10, 20, 70),
(2, 2, "stop", '2024-12-24 17:00:00', 10, 20, 70),
(2, 1, "end", '2024-12-24 19:00:00', 10, 20, 70),
-- Dec. 24 line 3 east to west
(3, 3, "start", '2024-12-24 07:00:00', 10, 20, 70),
(3, 8, "stop", '2024-12-24 10:00:00', 10, 20, 70),
(3, 9, "stop", '2024-12-24 13:00:00', 10, 20, 70),
(3, 10, "end", '2024-12-24 16:00:00', 10, 20, 70),
-- Dec. 24 line 4 west to east
(4, 10, "start", '2024-12-24 07:00:00', 10, 20, 70),
(4, 9, "stop", '2024-12-24 10:00:00', 10, 20, 70),
(4, 8, "stop", '2024-12-24 13:00:00', 10, 20, 70),
(4, 3, "end", '2024-12-24 16:00:00', 10, 20, 70),
-- Dec. 24 line 5 east to west
(5, 4, "start", '2024-12-24 07:00:00', 10, 20, 70),
(5, 12, "stop", '2024-12-24 09:00:00', 10, 20, 70),
(5, 8, "stop", '2024-12-24 11:00:00', 10, 20, 70),
(5, 9, "stop", '2024-12-24 13:00:00', 10, 20, 70),
(5, 11, "end", '2024-12-24 15:00:00', 10, 20, 70),
 -- Dec. 24 line 6 west to east
(6, 11, "start", '2024-12-24 07:00:00', 10, 20, 70),
(6, 9, "stop", '2024-12-24 09:00:00', 10, 20, 70),
(6, 8, "stop", '2024-12-24 11:00:00', 10, 20, 70),
(6, 12, "stop", '2024-12-24 13:00:00', 10, 20, 70),
(6, 4, "end", '2024-12-24 15:00:00', 10, 20, 70),
-- Dec. 24 line 7 east to west
(7, 5, "start", '2024-12-24 07:00:00', 10, 20, 70),
(7, 13, "stop", '2024-12-24 10:00:00', 10, 20, 70),
(7, 14, "end", '2024-12-24 13:00:00', 10, 20, 70),
-- Dec. 24 line 8 west to east
(8, 14, "start", '2024-12-24 07:00:00', 10, 20, 70),
(8, 13, "stop", '2024-12-24 10:00:00', 10, 20, 70),
(8, 5, "end", '2024-12-24 13:00:00', 10, 20, 70),
-- Dec. 24 line 9 north to south
(9, 10, "start", '2024-12-24 07:00:00', 10, 20, 70),
(9, 9, "stop", '2024-12-24 10:00:00', 10, 20, 70),
(9, 11, "stop", '2024-12-24 13:00:00', 10, 20, 70),
(9, 14, "stop", '2024-12-24 15:00:00', 10, 20, 70),
(9, 15, "end", '2024-12-24 18:00:00', 10, 20, 70),
-- Dec. 24 line 10 north to south
(10, 15, "start", '2024-12-24 07:00:00', 10, 20, 70),
(10, 14, "stop", '2024-12-24 10:00:00', 10, 20, 70),
(10, 11, "stop", '2024-12-24 13:00:00', 10, 20, 70),
(10, 9, "stop", '2024-12-24 15:00:00', 10, 20, 70),
(10, 10, "end", '2024-12-24 18:00:00', 10, 20, 70);


 