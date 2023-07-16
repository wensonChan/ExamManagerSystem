package com.learn.domain;

public class ExamArrangement {
    private Integer arrange_id;
    private String name;
    private Integer teacher_id;
    private String room;
    private String start_time;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(Integer teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public Integer getArrange_id() {
        return arrange_id;
    }

    public void setArrange_id(Integer arrange_id) {
        this.arrange_id = arrange_id;
    }

    private String end_time;
}
