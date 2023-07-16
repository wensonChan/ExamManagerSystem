package com.learn.vo;

public class FinExamDetailVO {
    private String title;
    private String content;
    private String stu_answer;
    private Integer get_score;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStu_answer() {
        return stu_answer;
    }

    public void setStu_answer(String stu_answer) {
        this.stu_answer = stu_answer;
    }

    public Integer getGet_score() {
        return get_score;
    }

    public void setGet_score(Integer get_score) {
        this.get_score = get_score;
    }
}
