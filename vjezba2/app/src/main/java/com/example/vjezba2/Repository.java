package com.example.vjezba2;

public class Repository {
    private String ownerAvatarUrl;
    private String repoName;
    private int starsCount;

    public Repository(String ownerAvatarUrl, String repoName, int starsCount) {
        this.ownerAvatarUrl = ownerAvatarUrl;
        this.repoName = repoName;
        this.starsCount = starsCount;
    }

    public String getOwnerAvatarUrl() {
        return ownerAvatarUrl;
    }

    public void setOwnerAvatarUrl(String ownerAvatarUrl) {
        this.ownerAvatarUrl = ownerAvatarUrl;
    }

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    public int getStarsCount() {
        return starsCount;
    }

    public void setStarsCount(int starsCount) {
        this.starsCount = starsCount;
    }
}
