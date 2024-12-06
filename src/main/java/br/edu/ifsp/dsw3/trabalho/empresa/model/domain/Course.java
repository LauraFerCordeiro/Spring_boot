package br.edu.ifsp.dsw3.trabalho.empresa.model.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "courses")
public class Course implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "start_date", nullable = false, columnDefinition = "DATE")
    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate startDate;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "course")
    private List<PayCourse> pays;

    @OneToMany(mappedBy = "course")
    private List<Lesson> lessons;

    public Course(Long id, String name, LocalDate startDate, String category, String description, List<PayCourse> pays,
            List<Lesson> lessons) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.category = category;
        this.description = description;
        this.pays = pays;
        this.lessons = lessons;
    }

    public Course(String name, LocalDate startDate, String category, String description, List<PayCourse> pays,
            List<Lesson> lessons) {
        this.name = name;
        this.startDate = startDate;
        this.category = category;
        this.description = description;
        this.pays = pays;
        this.lessons = lessons;
    }

    public Course() {
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<PayCourse> getPays() {
        return pays;
    }

    public void setPays(List<PayCourse> pays) {
        this.pays = pays;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public Lesson getFirstLesson() {
        for (Lesson lesson : lessons) {
            if(lesson.getLessonNumber().equals(1)){
                return lesson;
            }
        }
        return null;
    }
}
