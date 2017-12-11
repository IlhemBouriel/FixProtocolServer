package com.javahonk.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javahonk.dao.CourseChangeDao;
import com.javahonk.model.CourtChange;
import com.javahonk.utilities.CourtParser;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CourtChangeService {
    private final CourseChangeDao dao;
    private final CourtParser courtParser;

    @Autowired
    public CourtChangeService(CourseChangeDao dao, CourtParser courtParser) {
        this.courtParser = courtParser;
        this.dao = dao;
    }

    public List<CourtChange> findAllCourseChanges() {
        List<CourtChange> courtChanges = dao.findAllCourseChanges();
        if (courtChanges.size() == 0 || (courtChanges.get(0).getCreatedAt()!=null)&&courtChanges.get(0).getCreatedAt().toLocalDateTime().plusHours(24).isBefore(LocalDateTime.now())) {
            courtChanges = this.courtParser.getAllCourts();
            this.refreshAllCourt(courtChanges);
        }
        return courtChanges;
    }

    @Async
    @Transactional
    protected void refreshAllCourt(List<CourtChange> courtChangeList) {
        this.deleteAllCourseChanges();
        for (CourtChange courtChange : courtChangeList) {
            this.saveCourseChange(courtChange);
            System.out.println("court added");
        }
    }

    public CourtChange findById(int id) {
        return dao.findById(id);
    }

    public boolean isCourseChangeExist(CourtChange CourtChange) {
        return findById(CourtChange.getId()) != null;
    }

    public void saveCourseChange(CourtChange CourtChange) {
        dao.saveCourseChange(CourtChange);
    }

    public void updateCourseChange(CourtChange currentCourtChange) {
        dao.updateCourseChange(currentCourtChange);
    }

    public void deleteCourseChangeById(int id) {
        dao.deleteCourseChangeById(id);
    }

    public void deleteAllCourseChanges() {
        for (CourtChange CourtChange : dao.findAllCourseChanges()) {
            deleteCourseChangeById(CourtChange.getId());
        }
    }
}
