package org.infinite.ims.post.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.infinite.ims.user.model.User;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "post")
@Data
public class Post {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;
    private String slug;

    @Column(nullable = false)
    @Size(min = 10,max = 500,message = "Enter a minimum length of 10 characters or maximum length of 500 characters")
    private String content;
    private String excerpt;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String featuredImage;
    private int viewsCount;
    private int likesCount;
    private boolean isPinned;
    private boolean isFeatured;
    private LocalDateTime createDate;
    private LocalDateTime updatedDate;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;
}
