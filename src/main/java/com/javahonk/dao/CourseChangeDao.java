package com.javahonk.dao;



import java.util.List;

import com.javahonk.model.CourtChange;



public interface CourseChangeDao {
    CourtChange findById(int id);

    void saveCourseChange(CourtChange user);

    void updateCourseChange(CourtChange user);

    void deleteCourseChangeById(int id);

    List<CourtChange> findAllCourseChanges();
}
