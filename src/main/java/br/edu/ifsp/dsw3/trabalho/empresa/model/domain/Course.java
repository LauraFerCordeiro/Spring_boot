package br.edu.ifsp.dsw3.trabalho.empresa.model.domain;

import java.io.Serializable;
import java.math.BigDecimal;
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

    @Column(name = "end_date", nullable = false, columnDefinition = "DATE")
    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate endDate;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String description;

    @Column(name = "cost", nullable = false, columnDefinition = "DECIMAL")
    private BigDecimal cost;

    @Column(name = "course_link", nullable = false)
    private String courseLink;

    @OneToMany(mappedBy = "course")
    private List<PayCourse> pays;

    @OneToMany(mappedBy = "course")
    private List<Lesson> lessons;
    
    public Course(String name, LocalDate startDate, LocalDate endDate, String category, String description,
            BigDecimal cost, String courseLink, List<PayCourse> pays, List<Lesson> lessons) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.category = category;
        this.description = description;
        this.cost = cost;
        this.courseLink = courseLink;
        this.pays = pays;
        this.lessons = lessons;
    }

    public Course(Long id, String name, LocalDate startDate, LocalDate endDate, String category, String description,
            BigDecimal cost, String courseLink, List<PayCourse> pays, List<Lesson> lessons) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.category = category;
        this.description = description;
        this.cost = cost;
        this.courseLink = courseLink;
        this.pays = pays;
        this.lessons = lessons;
    }

    public Course(Long id, String name, LocalDate startDate, LocalDate endDate, String category, String description,
            BigDecimal cost, List<PayCourse> pays) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.category = category;
        this.description = description;
        this.cost = cost;
        this.pays = pays;
    }

    public Course(String name, LocalDate startDate, LocalDate endDate, String category, String description,
            BigDecimal cost, List<PayCourse> pays) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.category = category;
        this.description = description;
        this.cost = cost;
        this.pays = pays;
    }

    public Course(String name, LocalDate startDate, LocalDate endDate, String category, String description,
            BigDecimal cost) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.category = category;
        this.description = description;
        this.cost = cost;
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

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
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

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public List<PayCourse> getPays() {
        return pays;
    }

    public void setPays(List<PayCourse> pays) {
        this.pays = pays;
    }

    public String getCourseLink() {
        return courseLink;
    }

    public void setCourseLink(String courseLink) {
        this.courseLink = courseLink;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

}
