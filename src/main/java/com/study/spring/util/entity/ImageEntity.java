package com.study.spring.util.entity;

import com.study.spring.domain.Post;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IMAGE_ID")
    private int imageId;

    @Column(name="IMAGE_URL")
    private String imageUrl;

    //다:1 연관관계 -> 하나의 게시물에는 여러개의 사진을 업로드 할 수 있음
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID")
    private Post post;

    //이건 뭘까?
    @Column(name = "IMAGE_NUM")
    private int imageNum;

    @Builder
    public  ImageEntity(Post post , String imageUrl) {
        this.post = post;
        this.imageUrl = imageUrl;
    }
}
