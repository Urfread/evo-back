package com.urfread.breaknews.core.test.dao;

import static org.junit.jupiter.api.Assertions.*;

import com.urfread.breaknews.core.common.entity.PracticeNote;
import com.urfread.breaknews.core.repository.PracticeNoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;

@SpringBootTest
public class PracticeNoteRepositoryTest {

    @Autowired
    private PracticeNoteRepository practiceNoteRepository;

    private PracticeNote practiceNote;

    @BeforeEach
    public void setUp() {
        // 初始化测试数据
        practiceNote = PracticeNote.builder()
                .uid(1L)
                .categoryId(1L)
                .question("What is Java?")
                .answer("Java is a programming language.")
                .build();

        // 保存数据
        practiceNote = practiceNoteRepository.save(practiceNote);
    }

    @Test
    public void testCreate() {
        // 测试保存（增）
        assertNotNull(practiceNote.getId(), "Practice note should be saved and have an ID.");
    }

    @Test
    public void testRead() {
        // 测试读取（查）
        Optional<PracticeNote> retrievedNote = practiceNoteRepository.findById(practiceNote.getId());
        assertTrue(retrievedNote.isPresent(), "Practice note should be retrieved.");
        assertEquals(practiceNote.getQuestion(), retrievedNote.get().getQuestion(), "Questions should match.");
    }

    @Test
    public void testUpdate() {
        // 测试更新（改）
        practiceNote.setQuestion("What is Spring?");
        practiceNote.setAnswer("Spring is a framework for Java.");
        practiceNote = practiceNoteRepository.save(practiceNote);  // 更新实体

        Optional<PracticeNote> updatedNote = practiceNoteRepository.findById(practiceNote.getId());
        assertTrue(updatedNote.isPresent(), "Updated practice note should exist.");
        assertEquals("What is Spring?", updatedNote.get().getQuestion(), "Question should be updated.");
    }

    @Test
    public void testDelete() {
        // 测试删除（删）
        Long noteId = practiceNote.getId();
        practiceNoteRepository.deleteById(noteId);

        Optional<PracticeNote> deletedNote = practiceNoteRepository.findById(noteId);
        assertFalse(deletedNote.isPresent(), "Practice note should be deleted.");
    }
}
