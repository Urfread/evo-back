package com.urfread.breaknews.core.controller;

import com.urfread.breaknews.core.common.entity.PracticeNote;
import com.urfread.breaknews.core.common.model.ResultData;
import com.urfread.breaknews.core.common.vo.PracticeQueryVO;
import com.urfread.breaknews.core.common.vo.PracticeVO;
import com.urfread.breaknews.core.service.PracticeNoteService;
import com.urfread.breaknews.core.service.TagService;

import com.urfread.breaknews.core.tag.TagUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/practice")
public class PracticeNoteController {

    private final PracticeNoteService practiceNoteService;
    private final TagService tagService;

    public PracticeNoteController(PracticeNoteService practiceNoteService, TagService tagService) {
        this.practiceNoteService = practiceNoteService;
        this.tagService = tagService;
    }
    @PostMapping("/create")
    public ResultData<PracticeNote> createPractice(@RequestBody PracticeNote practice, HttpServletRequest req) {
        practice.setUid(Long.parseLong((String) req.getAttribute("uid")));
        PracticeNote createdPractice = practiceNoteService.createArticle(practice);
        return ResultData.success(createdPractice);
    }
    @GetMapping("/find")
    public ResultData<PracticeQueryVO> findPractices(PracticeQueryVO practiceQueryVO, HttpServletRequest req) {
        String uid = (String) req.getAttribute("uid");
        // 通过 UID 获取文章
        List<PracticeNote> practiceNotes = practiceNoteService.findAllPracticesByUid(uid);
        // 根据查询参数过滤文章
        if (practiceQueryVO.getCategoryId() != null) {
            practiceNotes = practiceNotes.stream()
                    .filter(practiceNote -> practiceNote.getCategoryId().equals(practiceQueryVO.getCategoryId()))
                    .collect(Collectors.toList());
        }
//        if (practiceQueryVO.getState() != null) {
//            practiceNotes = practiceNotes.stream()
//                    .filter(practiceNote -> practiceNote.getState().equals(articleQueryVO.getState()))
//                    .collect(Collectors.toList());
//        }

        // 实现分页
        int total = practiceNotes.size();  // 总数
        int fromIndex = (practiceQueryVO.getPageNum() - 1) * practiceQueryVO.getPageSize(); // 起始索引
        int toIndex = Math.min(fromIndex + practiceQueryVO.getPageSize(), total); // 结束索引

        if (fromIndex > total) {
            practiceNotes = Collections.emptyList(); // 如果起始索引超出总数，返回空列表
        } else {
            practiceNotes = practiceNotes.subList(fromIndex, toIndex); // 获取分页后的文章列表
        }
        // 将 Practice 转换为 PracticeVO
        List<PracticeVO> practiceVOs = practiceNotes.stream()
                .map(practice -> PracticeVO.builder()
                        .id(practice.getId())
                        .uid(practice.getUid())
                        .question(practice.getQuestion())
                        .answer(practice.getAnswer())
                        .hint(practice.getHint())
                        .categoryId(practice.getCategoryId())
                        .createTime(practice.getCreatedTime())
                        .updateTime(practice.getUpdatedTime())
                        .deleted(practice.getDeleted())
                        // TODO:改Long
                        .tagStr(TagUtil.treeToTagString(tagService.getTagsByEntityId(practice.getId().intValue(), "practice"),true)) // 设置 tagStr 字段
                        .build())
                .collect(Collectors.toList());

        return ResultData.success(PracticeQueryVO.builder()
                .items(practiceVOs)
                .total(total)
                .build());
    }

    @DeleteMapping("/delete/{id}")
    public ResultData<String> deletePractice(@PathVariable Long id){
        practiceNoteService.deletePractice(id);
        return ResultData.success("success");
    }
    /**
     * 获取每日任务练习
     */
    @GetMapping("/daily")
    public ResultData<List<PracticeVO>> getDailyExercises(HttpServletRequest req) {
        String uid = (String) req.getAttribute("uid");

        // 模拟从数据库获取每日任务数据
        List<PracticeNote> dailyPractices = practiceNoteService.getDailyPracticesByUid(uid);

        // 模拟分页逻辑，具体数据可以根据实际需要进行分页处理
        List<PracticeVO> dailyPracticeVOs = dailyPractices.stream()
                .map(practice -> PracticeVO.builder()
                        .id(practice.getId())
                        .uid(practice.getUid())
                        .question(practice.getQuestion())
                        .answer(practice.getAnswer())
                        .hint(practice.getHint())
                        .categoryId(practice.getCategoryId())
                        .createTime(practice.getCreatedTime())
                        .updateTime(practice.getUpdatedTime())
                        .deleted(practice.getDeleted())
                        .tagStr(TagUtil.treeToTagString(tagService.getTagsByEntityId(practice.getId().intValue(), "practice"), true))
                        .build())
                .collect(Collectors.toList());

        return ResultData.success(dailyPracticeVOs);
    }

    /**
     * 获取ALL IN ONE 练习
     */
    @GetMapping("/allinone")
    public ResultData<List<PracticeVO>> getAllInOneExercises(HttpServletRequest req) {
        String uid = (String) req.getAttribute("uid");

        // 模拟从数据库获取 ALL IN ONE 的练习数据
        List<PracticeNote> allInOnePractices = practiceNoteService.getAllInOnePracticesByUid(uid);

        // 模拟分页逻辑，具体数据可以根据实际需要进行分页处理
        List<PracticeVO> allInOnePracticeVOs = allInOnePractices.stream()
                .map(practice -> PracticeVO.builder()
                        .id(practice.getId())
                        .uid(practice.getUid())
                        .question(practice.getQuestion())
                        .answer(practice.getAnswer())
                        .hint(practice.getHint())
                        .categoryId(practice.getCategoryId())
                        .createTime(practice.getCreatedTime())
                        .updateTime(practice.getUpdatedTime())
                        .deleted(practice.getDeleted())
                        .tagStr(TagUtil.treeToTagString(tagService.getTagsByEntityId(practice.getId().intValue(), "practice"), true))
                        .build())
                .collect(Collectors.toList());

        return ResultData.success(allInOnePracticeVOs);
    }
}
