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

    @ManyToOne
    @JoinColumn(name="ID")
    private Member member;

    @Column(name="POST_TITLE")
    private String postTitle;

    @Column(name="POST_CONTENT")
    private String postContent;

    @Column(name="POST_IMG_PATH")
    private String postImgPath;

    //연관관계 설정
    public void createPost(Member member){
        this.member = member;
    }
}