package com.hello.jin.wechat.message;

import java.util.List;

import com.hello.jin.wechat.base.Article;

/**
 * ͼ����Ϣ
 *
 */
public class NewsMessage extends BaseMessage {
    // ͼ����Ϣ����������Ϊ10������
    private int ArticleCount;
    // ����ͼ����Ϣ��Ϣ��Ĭ�ϵ�һ��itemΪ��ͼ
    private List<Article> Articles;

    public int getArticleCount() {
        return ArticleCount;
    }

    public void setArticleCount(int articleCount) {
        ArticleCount = articleCount;
    }

    public List<Article> getArticles() {
        return Articles;
    }
}

