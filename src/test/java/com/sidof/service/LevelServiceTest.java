package com.sidof.service;

import com.sidof.model.Level;
import com.sidof.repo.LevelRepo;
import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extension;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class LevelServiceTest {
    private LevelService underTest;
   @Mock
   private LevelRepo levelRepo;
    @BeforeEach
    void setUp() {
        underTest = new LevelService(levelRepo);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void save() throws BadRequestException {
//        given
        Level level = new Level(1L, "LEVEL 1");
        underTest.save(level);
//        when
        ArgumentCaptor<Level> argumentCaptor = ArgumentCaptor.forClass(Level.class);
        verify(levelRepo).save(argumentCaptor.capture());
        Level argumentCaptorValue = argumentCaptor.getValue();
//        then
        assertEquals(level,argumentCaptorValue);
    }

    @Test
    @Disabled
    void getLevel() {
    }

    @Test
    void getLevels() {
    }
}