package br.edu.ifsp.dsw3.trabalho.empresa.model.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
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

    @Column(name = "video", nullable = true)
    @Lob
    private byte[] video;

    private String nameVideo;
    
    private String typeVideo;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    public Lesson(Long id, String name, LocalDate release, byte[] video, String nameVideo, String typeVideo,
            Course course) {
        this.id = id;
        this.name = name;
        this.release = release;
        this.video = video;
        this.nameVideo = nameVideo;
        this.typeVideo = typeVideo;
        this.course = course;
    }

    public Lesson(String name, LocalDate release, byte[] video, String nameVideo, String typeVideo, Course course) {
        this.name = name;
        this.release = release;
        this.video = video;
        this.nameVideo = nameVideo;
        this.typeVideo = typeVideo;
        this.course = course;
    }

    public Lesson(String name, LocalDate release, Course course) {
        this.name = name;
        this.release = release;
        this.course = course;
    }

    public Lesson() {
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

    public byte[] getVideo() {
        return video;
    }

    public void setVideo(byte[] video) {
        this.video = video;
    }

    public String getNameVideo() {
        return nameVideo;
    }

    public void setNameVideo(String nameVideo) {
        this.nameVideo = nameVideo;
    }

    public String getTypeVideo() {
        return typeVideo;
    }

    public void setTypeVideo(String typeVideo) {
        this.typeVideo = typeVideo;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Lesson [id=" + id + ", name=" + name + ", release=" + release + ", video=" + Arrays.toString(video)
                + ", nameVideo=" + nameVideo + ", typeVideo=" + typeVideo + ", course=" + course + "]";
    }
}
