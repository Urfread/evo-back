package com.urfread.breaknews.core.tag;

public class Main {
    public static void main(String[] args) {
        String input = "#阶段/计划/大纲, #意义/创造价值/经验分享,#意义/创造价值/陪伴成长";
        TagNode root = TagUtil.parseTagStringToTree(input); // 将标签串转换为标签树

        // 输出树形结构
        root.printTree("");

        // 转回字符串
        String output = TagUtil.treeToTagString(root,true);
        System.out.println("Tags as String: " + output);

        // 转回字符串
        output = TagUtil.treeToTagString(root,true);
        System.out.println("Tags as String: " + output);
    }
}
