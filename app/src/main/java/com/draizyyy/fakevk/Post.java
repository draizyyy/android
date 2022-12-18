package com.draizyyy.fakevk;

public class Post {
    private final String name;
    private final String text;
    private final String time;
    private int ImageIcoId;
    private int ImagePostPhotoId;
    private int likes;
    private int comments;
    private int reposts;
    private boolean licked = false;

    public String getRandom(int min, int max) {
        Integer value;
        do {
            value = (int)(Math.random() * (max - min)) + min;
            System.out.println(value);
        } while(value == 0);
        return value.toString();
    }

    public Post(String name, String time, String text, int ImageIcoId, int ImagePostPhotoId) {
        this.name = name;
        this.time = time;
        this.text = text;
        this.likes = Integer.parseInt(getRandom(400, 998));
        this.comments = Integer.parseInt(getRandom(200, 798));
        this.reposts = Integer.parseInt(getRandom(100, 488));
        this.ImageIcoId = ImageIcoId;
        this.ImagePostPhotoId = ImagePostPhotoId;
    }

    public boolean isLicked() {
        return licked;
    }

    public void like() {
        licked = !licked;
        this.likes = isLicked() ? this.likes + 1 : this.likes - 1;
    }

    public int getImageIcoId() {
        return ImageIcoId;
    }

    public int getImagePostPhotoId() {
        return ImagePostPhotoId;
    }

    public String getComments() {
        return String.valueOf(comments);
    }

    public String getLikes() {
        return String.valueOf(likes);
    }

    public String getReposts() {
        return String.valueOf(reposts);
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    public String getTime() {
        return time;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void setReposts(int reposts) {
        this.reposts = reposts;
    }
}
