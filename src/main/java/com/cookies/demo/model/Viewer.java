package com.cookies.demo.model;

import javax.persistence.*;
import java.sql.Blob;

/**
 * Created by akshay on 4/2/20
 *
 * @author akshay
 * Viewer
 */
@Entity
@Table(name="viewer")
public class Viewer {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    String viewerId;
    String publicIp;
    String userAgent;

    String image;
    //custom_image



    public Integer getId() {
        return id;
    }

    public Viewer setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getViewerId() {
        return viewerId;
    }

    public Viewer setViewerId(String viewerId) {
        this.viewerId = viewerId;
        return this;
    }

    public String getPublicIp() {
        return publicIp;
    }

    public Viewer setPublicIp(String publicIp) {
        this.publicIp = publicIp;
        return this;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public Viewer setUserAgent(String userAgent) {
        this.userAgent = userAgent;
        return this;
    }

    public String getImage() {
        return image;
    }

    public Viewer setImage(String image) {
        this.image = image;
        return this;
    }
}
