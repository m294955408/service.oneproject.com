package com.shuma.oneproject.entity;

/**
 * @author 马文韬
 * @version V1.0
 * @date 2017/12/28
 * @desc 博文
 */
public class Post {

    /**
     * 自增ID
     */
    private int id;

    /**
     * 标题
     */
    private String title;

    /**
     * 文章分类
     */
    private int type;

    /**
     * 内容
     */
    private String content;

    /**
     * 标签
     */
    private String tags;

    /**
     * 摘要
     */
    private String summary;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
