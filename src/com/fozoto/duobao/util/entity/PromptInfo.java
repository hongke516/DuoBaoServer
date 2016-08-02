package com.fozoto.duobao.util.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by qingyan on 16-1-18.
 */
@Component("PromptInfo")
@Scope("prototype")
public class PromptInfo implements Serializable {

    public static final String PROMPT_INFO = "promptInfo";
    /**
     * 友好提示内容
     */
    private String message;
    /**
     * 友好提示标题
     */
    private String title;
    /**
     * 友好提示图片
     */
    private int imageName;
    /**
     * 要去的页面
     */
    private String togo;

    public PromptInfo(String message, String title, int imageName, String togo) {
        this.message = message;
        this.title = title;
        this.imageName = imageName;
        this.togo = togo;
    }

    //默认构造函数
    public PromptInfo() {
    }

    /**
     * 提示信息
     * @param message 信息
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 添加提示标题
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 添加一个图片的索引
     * @param imageName 图片
     */
    public void setImageName(int imageName) {
        this.imageName = imageName;
    }

    /**
     * 要去的页面
     * @param togo 页面
     */
    public void setTogo(String togo) {
        this.togo = togo;
    }

    public String getMessage() {
        return message;
    }

    public String getTitle() {
        return title;
    }

    public int getImageName() {
        return imageName;
    }

    public String getTogo() {
        return togo;
    }
}
