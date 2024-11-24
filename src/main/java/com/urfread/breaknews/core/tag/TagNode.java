package com.urfread.breaknews.core.tag;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TagNode {
    private String content; // 标签内容
    private final List<TagNode> children ; // 子标签列表

    public TagNode(){
        children = new LinkedList<>();
    }
    public TagNode(String content, List<TagNode> children) {
        this.content = content;
        this.children = children;
    }

    public TagNode(String content) {
        this.content = content;
        children = new LinkedList<>();
    }

    public void addChild(TagNode child) {
        children.add(child);
    }

    // 输出树形结构的方法
    public void printTree(String prefix) {
        System.out.println(prefix + content);
        for (TagNode child : children) {
            child.printTree(prefix + " / ");
        }
    }

    public List<TagNode> getChildren() {
        return children;
    }

    public String getContent() {
        return content;
    }
}
