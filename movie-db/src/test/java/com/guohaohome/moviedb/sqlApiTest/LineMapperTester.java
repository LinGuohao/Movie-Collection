package com.guohaohome.moviedb.sqlApiTest;

import com.guohaohome.moviedb.dao.LineMapper;
import com.guohaohome.moviedb.sqlEntity.Line;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("com/guohaohome/moviedb/dao")
@FixMethodOrder(MethodSorters.JVM)
public class LineMapperTester {
    @Autowired
    LineMapper lineMapper;
    @Test
    public void testGetLines(){
        List<Line> lines = lineMapper.getLines("12345");
        assertEquals(2,lines.size());
        assertEquals("testSentence",lines.get(0).getSentence());
        assertEquals("testAuthor",lines.get(0).getAuthor());
        assertEquals("testLine_id",lines.get(0).getLine_id());
        assertEquals("testSentence2",lines.get(1).getSentence());
        assertEquals("testAuthor2",lines.get(1).getAuthor());
        assertEquals("testLine_id2",lines.get(1).getLine_id());
    }
    @Test
    public void insertLine(){
        Line line = new Line("testInsertLine","testInsertLineSentence","testInsertLineAuthor"
                                ,"testInsertLineLine_id");
        lineMapper.insertLine(line);
        List<Line>lines = lineMapper.getLines("testInsertLine");
        assertEquals(1,lines.size());
        assertEquals("testInsertLineSentence",lines.get(0).getSentence());
        assertEquals("testInsertLineAuthor",lines.get(0).getAuthor());
        assertEquals("testInsertLineLine_id",lines.get(0).getLine_id());
    }
    @Test
    public void deleteLine(){
        Line line = new Line("testDeleteLine","testDeleteLineSentence","testDeleteLineAuthor"
                ,"testDeleteLineLine_id");
        lineMapper.insertLine(line);
        List<Line>lines = lineMapper.getLines("testDeleteLine");
        assertEquals(1,lines.size());
        lineMapper.deleteLine("testDeleteLineLine_id");
        lines=lineMapper.getLines("testDeleteLine");
        assertEquals(0,lines.size());
    }
}
