package br.edu.ifsp.dsw3.trabalho.empresa.model.domain;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "lessons")
public class Lesson implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "release", nullable = false, columnDefinition = "DATE")
    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate release;

    @Column(nullable = false)
    private String lessonLink;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    public Lesson(Long id, String name, LocalDate release, String lesson_link, Course course) {
        this.id = id;
        this.name = name;
        this.release = release;
        this.lessonLink = lesson_link;
        this.course =  course;
    }

    public Lesson(String name, LocalDate release, String lesson_link, Course course) {
        this.name = name;
        this.release = release;
        this.lessonLink = lesson_link;
        this.course =  course;
    }

    public Lesson(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getRelease() {
        return release;
    }

    public void setRelease(LocalDate release) {
        this.release = release;
    }

    public String getLessonLink() {
        return lessonLink;
    }

    public void setLessonLink(String lesson_link) {
        this.lessonLink = lesson_link;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
