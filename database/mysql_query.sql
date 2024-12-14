with DepartStation AS(
    SELECT
        ps.path_id,
        ps.station_id,
        s.station_name,
        ps.start_time AS depart_time
    FROM
        wxy_path_station ps
    JOIN
        wxy_station s ON ps.station_id = s.station_id
    WHERE
        s.station_name = "Pittsburgh" and DATE(ps.start_time) = '2024-12-23'
),
ArrivalStation AS(
    SELECT
        ps.path_id,
        ps.station_id,
        s.station_name,
        ps.start_time AS arrival_time
    FROM
        wxy_path_station ps
    JOIN
        wxy_station s ON ps.station_id = s.station_id
    WHERE
        s.station_name = "Ann Arbor" and DATE(ps.start_time) = '2024-12-23'
),
ValidPaths AS(
SELECT
    d.path_id
FROM
    DepartStation d
JOIN
    ArrivalStation a ON d.path_id = a.path_id
WHERE
    d.depart_time < a.arrival_time
)
SELECT
    ps.path_id,
    s.station_name,
    ps.start_time,
    ps.a_seats_avialable,
    ps.b_seats_avialable,
    ps.c_seats_avialable
FROM
    wxy_path_station ps
JOIN
    wxy_station s ON ps.station_id = s.station_id
WHERE
    ps.path_id IN (SELECT path_id FROM ValidPaths)
AND
    DATE(ps.start_time) = '2024-12-23'
ORDER BY 
    ps.path_id, ps.start_time;