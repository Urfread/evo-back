package com.urfread.breaknews.core.tag;

import com.urfread.breaknews.core.common.entity.Tag;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TagUtil {
    public static TagNode parseTagStringToTree(String input) {
        // 参考标签串 "#阶段/计划/大纲, #意义/创造价值/经验分享,#意义/创造价值/陪伴成长"
        String[] rawTags = input.split(",\\s*"); // 使用逗号分隔字符串，同时允许逗号后有零个或多个空格
        TagNode root = new TagNode("root"); // 创建root节点

        // 遍历每一个长标签串
        for (String rawTag : rawTags) {
            String cleanedTag = rawTag.replace("#", "").trim();
            String[] parts = cleanedTag.split("/"); // 按照斜杠分割

            TagNode currentNode = root;
            for (String part : parts) {
                // 创建或获取当前层级的节点
                currentNode = findOrCreateChild(currentNode, part); // 更新当前节点为新创建的节点
            }
        }

        return root;
    }
    private static TagNode findOrCreateChild(TagNode parent, String content) {
        /*
        什么情况下不会创建新节点，而是直接返回？
        父节点的子节点中，已经包含了内容为content的节点。
         */
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

    /**
     * 将标签树转换为字符串表示，支持选择只输出完整路径（叶子节点路径）。
     *
     * @param root         标签树的根节点，类型为 TagNode。如果为 null，返回 null。
     * @param onlyComplete 一个布尔值，决定是否只包含完整路径：
     *                     - true：仅输出从根节点到叶子节点的完整路径。
     *                     - false：输出从根节点到所有节点的路径。
     * @return 构建好的标签字符串，格式如 "#标签/路径, #标签/路径"，如果没有节点，返回空字符串。
     */
    public static String treeToTagString(TagNode root, boolean onlyComplete) {
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

    /**
     * 辅助方法：递归构建标签树的字符串表示。
     *
     * @param node         当前处理的节点，类型为 TagNode。
     * @param sb           用于构建最终字符串的 StringBuilder 实例。
     * @param prefix       当前路径的前缀，表示从根节点到当前节点的路径。
     *                     - 如果为空，则表示当前节点是根节点的直接子节点。
     * @param onlyComplete 一个布尔值，决定是否只包含完整路径：
     *                     - true：仅当当前节点为叶子节点时，路径会被添加到结果中。
     *                     - false：无论是否为叶子节点，所有路径都会被添加。
     */
    private static void buildString(TagNode node, StringBuilder sb, String prefix,boolean onlyComplete) {
        // 遍历当前节点的所有子节点
        for (TagNode child : node.getChildren()) {
            // 构建当前路径。如果 prefix 为空，使用子节点内容；否则，添加到现有路径
            String currentPath = prefix.isEmpty() ? child.getContent() : prefix + "/" + child.getContent();
            // 在当前路径前添加 "#"，并添加到 StringBuilder 中
            if(onlyComplete) {
                if(child.getChildren().isEmpty()) sb.append("#").append(currentPath).append(", ");
            }
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
    public static String treeToTagString(List<Tag> tags, boolean onlyComplete){
        return treeToTagString(buildTagTree(tags),onlyComplete);
    }

}
