package com.urfread.breaknews.core.tag;

import com.urfread.breaknews.core.common.entity.Tag;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TagUtil {
    public static TagNode parseTags(String input) {
        String[] rawTags = input.split(",\\s*");
        TagNode root = new TagNode("root"); // 可选，作为根节点

        for (String rawTag : rawTags) {
            String cleanedTag = rawTag.replace("#", "").trim();
            String[] parts = cleanedTag.split("/"); // 按照斜杠分割

            TagNode currentNode = root;
            for (String part : parts) {
                // 创建或获取当前层级的节点
                TagNode childNode = findOrCreateChild(currentNode, part);
                currentNode = childNode; // 更新当前节点为新创建的节点
            }
        }

        return root;
    }
    private static TagNode findOrCreateChild(TagNode parent, String content) {
        if(!parent.getChildren().isEmpty())
            // 检查当前节点的子节点中是否已有该内容的节点
            for (TagNode child : parent.getChildren()) {
                if (child.getContent().equals(content)) {
                    return child; // 找到已存在的节点
                }
            }
        // 如果不存在，则创建新节点并添加为子节点
        TagNode newChild = new TagNode(content);
        parent.addChild(newChild);
        return newChild;
    }
    public static String tagsToString(TagNode root,boolean onlyComplete) {
        if(root==null)return null;
        // 创建一个 StringBuilder 用于构建结果字符串
        StringBuilder sb = new StringBuilder();
        // 调用 buildString 方法开始构建字符串
        buildString(root, sb, "",onlyComplete);
        // 删除最后的逗号和空格，以获得整洁的输出
        if (!sb.isEmpty()) {
            sb.setLength(sb.length() - 2);
        }
        // 返回构建好的标签字符串
        return sb.toString();
    }
    private static void buildString(TagNode node, StringBuilder sb, String prefix,boolean onlyComplete) {
        // 遍历当前节点的所有子节点
        for (TagNode child : node.getChildren()) {
            // 构建当前路径。如果 prefix 为空，使用子节点内容；否则，添加到现有路径
            String currentPath = prefix.isEmpty() ? child.getContent() : prefix + "/" + child.getContent();
            // 将当前路径前添加 "#"，并附加到 StringBuilder 中
            if(onlyComplete) {if(child.getChildren().isEmpty()) sb.append("#").append(currentPath).append(", ");}
            else sb.append("#").append(currentPath).append(", ");

            // 递归调用 buildString 方法以处理当前子节点的子节点
            buildString(child, sb, currentPath,onlyComplete);
        }
    }
    public static TagNode buildTagTree(List<Tag> tags) {
        if(tags.isEmpty())return null;
        // 创建一个 Map 来存储每个 TagNode
        Map<Integer, TagNode> tagNodeMap = new HashMap<>();
        TagNode root = new TagNode("root");
        Integer rootId=tags.stream().filter(tag -> tag.getContent().equals("root")).findFirst().get().getId();
        // 创建 TagNode 并放入 Map
        for (Tag tag : tags) {
            TagNode tagNode = new TagNode(tag.getContent());
            tagNodeMap.put(tag.getId(), tagNode);

            // 如果当前 Tag 有父节点，找到父节点并添加为子节点
            if (tag.getParent() != null) {
                Integer parentId=tag.getParent().getId();
                if(parentId.equals(rootId)){
                    root.addChild(tagNode);
                }else{
                    TagNode parentNode = tagNodeMap.get(parentId);
                    if (parentNode != null) {
                        parentNode.addChild(tagNode);
                    }
                }
            }
        }
        return root; // 返回树的根节点
    }
    public static String tagsToString(List<Tag> tags,boolean onlyComplete){
        return tagsToString(buildTagTree(tags),onlyComplete);
    }

}
