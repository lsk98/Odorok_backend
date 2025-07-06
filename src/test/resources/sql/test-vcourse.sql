use odorok_db;
-- users.id = 15

insert into visited_courses(course_id, visited_at, user_id, start_coords_id, end_coords_id, distance, stars, review, is_finished)
    values (1, now(), 15, 1, 2, 3.4, 5, '리뷰1', 0), (1, now(), 15, 3, 4, 2.8, 2, '리뷰2', 0), (1, now(), 15, 5, 6, 10.1, 8, '리뷰3', 1),
    (1, now(), 15, 7, 8, 7.3, 6, '리뷰4', 1), (1, now(), 15, 9, 10, 1.0, 4, '리뷰5', 0), (1, now(), 15, 11, 12, 15.1, 9, '리뷰6', 1);
