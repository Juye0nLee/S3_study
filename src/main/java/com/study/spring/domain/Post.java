package com.study.spring.domain;

import com.study.spring.util.entity.BaseEntity;
import com.study.spring.util.entity.ImageEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

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

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ImageEntity> postImgPath;

    //연관관계 설정
    public void createPost(Member member){
        this.member = member;
    }

    //이미지 경로 추가
    public void addImage(ImageEntity image){
        postImgPath.add(image);
        image.setPost(this);
    }
}