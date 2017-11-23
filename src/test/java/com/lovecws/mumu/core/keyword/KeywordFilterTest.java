package com.lovecws.mumu.core.keyword;

import org.junit.Test;

import java.util.List;

/**
 * @author babymm
 * @version 1.0-SNAPSHOT
 * @Description: 关键词过滤
 * @date 2017-11-23 15:14
 */
public class KeywordFilterTest {

    @Test
    public void isKeyWords() {
        KeywordFilter keywordFilter = new KeywordFilter();
        boolean nb = keywordFilter.isKeyWords("sexlovecws");
        System.out.println(nb);
    }

    @Test
    public void getKeyWords() {
        KeywordFilter keywordFilter = new KeywordFilter();
        List<String> keyWords = keywordFilter.getKeyWords("sexlovecws");
        System.out.println(keyWords);
    }
}
