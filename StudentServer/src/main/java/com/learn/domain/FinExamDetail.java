package com.learn.domain;

import org.springframework.stereotype.Repository;

@Repository
public class FinExamDetail {
    private Integer student_id;
    private Integer subject_id;

    public Integer getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Integer student_id) {
        this.student_id = student_id;
    }

    public Integer getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(Integer subject_id) {
        this.subject_id = subject_id;
    }
}
