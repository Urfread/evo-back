package com.urfread.breaknews.core.controller;

import com.urfread.breaknews.core.common.model.ResultData;
import com.urfread.breaknews.core.common.entity.Article;
import com.urfread.breaknews.core.common.vo.ArticleQueryVO;
import com.urfread.breaknews.core.common.vo.ArticleVO;
import com.urfread.breaknews.core.service.ArticleService;
import com.urfread.breaknews.core.service.TagService;
import com.urfread.breaknews.core.tag.TagUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;
    private final TagService tagService;

    @Autowired
    public ArticleController(ArticleService articleService, TagService tagService) {
        this.articleService = articleService;
        this.tagService = tagService;
    }
    @GetMapping("/find")
    public ResultData<ArticleQueryVO> findArticles(ArticleQueryVO articleQueryVO, HttpServletRequest req) {
        String uid = (String) req.getAttribute("uid");
        // 通过 UID 获取文章
        List<Article> articles = articleService.findAllArticlesByUid(uid);
        // 根据查询参数过滤文章
        if (articleQueryVO.getCategoryId() != null) {
            articles = articles.stream()
                    .filter(article -> article.getCategoryId().equals(articleQueryVO.getCategoryId()))
                    .collect(Collectors.toList());
        }
        if (articleQueryVO.getState() != null) {
            articles = articles.stream()
                    .filter(article -> article.getState().equals(articleQueryVO.getState()))
                    .collect(Collectors.toList());
        }

        // 实现分页
        int total = articles.size();  // 总数
        int fromIndex = (articleQueryVO.getPageNum() - 1) * articleQueryVO.getPageSize(); // 起始索引
        int toIndex = Math.min(fromIndex + articleQueryVO.getPageSize(), total); // 结束索引

        if (fromIndex > total) {
            articles = Collections.emptyList(); // 如果起始索引超出总数，返回空列表
        } else {
            articles = articles.subList(fromIndex, toIndex); // 获取分页后的文章列表
        }
        // 将 Article 转换为 ArticleVO
        List<ArticleVO> articleVOS = articles.stream()
                .map(article -> ArticleVO.builder()
                        .id(article.getId())
                        .uid(article.getUid())
                        .title(article.getTitle())
                        .content(article.getContent())
                        .coverImg(article.getCoverImg())
                        .state(article.getState())
                        .categoryId(article.getCategoryId())
                        .createTime(article.getCreateTime())
                        .updateTime(article.getUpdateTime())
                        .deleted(article.getDeleted())
                        .tagStr(TagUtil.treeToTagString(tagService.getTagsByEntityId(article.getId(), "article"),true)) // 设置 tagStr 字段
                        .build())
                .collect(Collectors.toList());

        return ResultData.success(ArticleQueryVO.builder()
                .items(articleVOS)
                .total(total)
                .build());
    }
    @PostMapping("/create")
    public ResultData<Article> createArticle(@RequestBody Article article, HttpServletRequest req) {
        article.setUid(Integer.parseInt((String) req.getAttribute("uid")));
        Article createdArticle = articleService.createArticle(article);
        return ResultData.success(createdArticle);
    }
    @PutMapping("/update")
    public ResultData<Article> updateArticle(@RequestBody Article article, HttpServletRequest req) {
        article.setUid(Integer.parseInt((String) req.getAttribute("uid")));
        Article updatedArticle = articleService.updateArticle(article);
        return ResultData.success(updatedArticle);
    }
    @DeleteMapping("/delete/{id}")
    public ResultData<Void> deleteArticle(@PathVariable("id") Integer id, HttpServletRequest req) {
        articleService.deleteArticle(id, Integer.parseInt((String) req.getAttribute("uid")));
        return ResultData.success(null);
    }
}
