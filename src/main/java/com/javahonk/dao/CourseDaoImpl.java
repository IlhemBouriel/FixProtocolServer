package com.javahonk.dao;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.javahonk.model.CourtChange;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class CourseDaoImpl implements CourseChangeDao {

    @PersistenceContext
    private EntityManager entityManager;

    public CourtChange findById(int id) {
        return entityManager.find(CourtChange.class, (int) id);
    }

    public void saveCourseChange(CourtChange CourtChange) {
        entityManager.persist(CourtChange);
    }

    public void updateCourseChange(CourtChange courtChange) {
        CourtChange courtChangeT = findById(courtChange.getId());
        if (courtChangeT != null) {
            courtChangeT.setName(courtChange.getName());
            courtChangeT.setBas(courtChange.getBas());
            courtChangeT.setHaut(courtChange.getHaut());
            courtChangeT.setClose(courtChange.getClose());
            courtChangeT.setOpen(courtChange.getOpen());
            courtChangeT.setVariation(courtChange.getVariation());
            courtChangeT.setVolume_dt(courtChange.getVolume_dt());
            courtChangeT.setVolume_title(courtChange.getVolume_title());
            entityManager.flush();
        }
    }

    public void deleteCourseChangeById(int id) {
        entityManager.remove(findById(id));
    }

    public List<CourtChange> findAllCourseChanges() {
        String hql = "FROM CourtChange as court_changes";
        return (List<CourtChange>) entityManager.createQuery(hql).getResultList();
    }

}
