package com.lewis.sprinbootvue.biz.mybatis.entity;

public class BookAriticle {

    private Long ArticleId;

    private String Uuid;

    private Long BookId;

    private String Title;

    private Long CreateTime;

    private Long UpdateTime;

    private Integer Status;

    private String ContentKey;

    private String EncryptContentKey;

    private String EncryptKey;

    private Integer WordCount;

    private Integer ArticleIndex;

    private Integer Level;

    private String PublishVersion;

    public Long getArticleId() {
        return ArticleId;
    }

    public void setArticleId(Long articleId) {
        ArticleId = articleId;
    }

    public String getUuid() {
        return Uuid;
    }

    public void setUuid(String uuid) {
        Uuid = uuid;
    }

    public Long getBookId() {
        return BookId;
    }

    public void setBookId(Long bookId) {
        BookId = bookId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public Long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Long createTime) {
        CreateTime = createTime;
    }

    public Long getUpdateTime() {
        return UpdateTime;
    }

    public void setUpdateTime(Long updateTime) {
        UpdateTime = updateTime;
    }

    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer status) {
        Status = status;
    }

    public String getContentKey() {
        return ContentKey;
    }

    public void setContentKey(String contentKey) {
        ContentKey = contentKey;
    }

    public String getEncryptContentKey() {
        return EncryptContentKey;
    }

    public void setEncryptContentKey(String encryptContentKey) {
        EncryptContentKey = encryptContentKey;
    }

    public String getEncryptKey() {
        return EncryptKey;
    }

    public void setEncryptKey(String encryptKey) {
        EncryptKey = encryptKey;
    }

    public Integer getWordCount() {
        return WordCount;
    }

    public void setWordCount(Integer wordCount) {
        WordCount = wordCount;
    }

    public Integer getArticleIndex() {
        return ArticleIndex;
    }

    public void setArticleIndex(Integer articleIndex) {
        ArticleIndex = articleIndex;
    }

    public Integer getLevel() {
        return Level;
    }

    public void setLevel(Integer level) {
        Level = level;
    }

    public String getPublishVersion() {
        return PublishVersion;
    }

    public void setPublishVersion(String publishVersion) {
        PublishVersion = publishVersion;
    }

    @Override
    public String toString() {
        return "BookAriticle{" +
                "ArticleId=" + ArticleId +
                ", Uuid='" + Uuid + '\'' +
                ", BookId=" + BookId +
                ", Title='" + Title + '\'' +
                ", CreateTime=" + CreateTime +
                ", UpdateTime=" + UpdateTime +
                ", Status=" + Status +
                ", ContentKey='" + ContentKey + '\'' +
                ", EncryptContentKey='" + EncryptContentKey + '\'' +
                ", EncryptKey='" + EncryptKey + '\'' +
                ", WordCount=" + WordCount +
                ", ArticleIndex=" + ArticleIndex +
                ", Level=" + Level +
                ", PublishVersion='" + PublishVersion + '\'' +
                '}';
    }
}
