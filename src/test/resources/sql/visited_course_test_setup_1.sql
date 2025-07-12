insert into courses(idx, route_idx, cycle, brd_div, created_at, modified_at) values("test0000","T_ROUTE_MNG0000000001", 0, 1, now(), now());

insert into dummies.add(VisitedCourse.builder()
                .courseId(TARGET_COURSE_ID).visitedAt(LocalDateTime.now()).userId(1L).startCoordsId(1L).endCoordsId(2L).distance(3.4).stars(1).review("리뷰1").isFinished(false).build());
        dummies.add(VisitedCourse.builder()
                .courseId(TARGET_COURSE_ID).visitedAt(LocalDateTime.now()).userId(1L).startCoordsId(1L).endCoordsId(2L).distance(9.1).stars(5).isFinished(true).build());
        dummies.add(VisitedCourse.builder()
                .courseId(TARGET_COURSE_ID).visitedAt(LocalDateTime.now()).userId(1L).startCoordsId(1L).endCoordsId(2L).distance(12.2).stars(7).review("리뷰3").isFinished(false).build());
        dummies.add(VisitedCourse.builder()
                .courseId(TARGET_COURSE_ID).visitedAt(LocalDateTime.now()).userId(1L).startCoordsId(1L).endCoordsId(2L).distance(3.1).stars(9).review("리뷰4").isFinished(true).build());
        dummies.add(VisitedCourse.builder()
                .courseId(TARGET_COURSE_ID).visitedAt(LocalDateTime.now()).userId(1L).startCoordsId(1L).endCoordsId(2L).distance(5.5).stars(10).isFinished(true).build());
        dummies.add(VisitedCourse.builder()
                .courseId(NONTARGET_COURSE_ID).visitedAt(LocalDateTime.now()).userId(1L).startCoordsId(1L).endCoordsId(2L).distance(5.5).stars(0).review("리뷰6").isFinished(true).build());
        visitedCourseRepository.saveAll(dummies);