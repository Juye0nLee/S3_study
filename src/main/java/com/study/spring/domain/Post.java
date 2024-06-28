package com.study.spring.domain;

import com.study.spring.util.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="POSTS")
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="POST_ID")
    private Long postId;

    @Column(name="POST_TITLE")
    private String postTitle;

    @Column(name="POST_CONTENT")
    private String postContent;

    @Column(name="USER_NAME")
    private String userName;

    @Column(name="POST_IMG_PATH")
    private String postImgPath;
}