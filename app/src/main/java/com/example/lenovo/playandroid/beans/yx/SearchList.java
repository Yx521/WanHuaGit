package com.example.lenovo.playandroid.beans.yx;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lenovo on 2019/3/5.
 */

public class SearchList implements Serializable{
    /**
     * data : {"curPage":1,"datas":[{"apkLink":"","author":"鸿洋","chapterId":408,"chapterName":"鸿洋","Collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":7371,"link":"https://mp.weixin.qq.com/s/JcviqDZ8To3ZEL2H0kVukA","niceDate":"2018-10-18","origin":"","projectLink":"","publishTime":1539792000000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"androidx <em class='highlight'>你好<\/em> ，android.support 再见。","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"承香墨影","chapterId":411,"chapterName":"承香墨影","Collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":4829,"link":"http://mp.weixin.qq.com/s?__biz=MzIxNjc0ODExMA==&mid=2247485082&idx=1&sn=de8916de175b7aa9e39e24bf7d3a89ba&chksm=97851fbba0f296adbd1a5d533f043270c812ff5d9189007ce0c4a3b10bcb127159a3c966f830&scene=38#wechat_redirect","niceDate":"2018-01-02","origin":"","projectLink":"","publishTime":1514822400000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/411/1"}],"title":"2018 <em class='highlight'>你好<\/em>，年度的关键字：爆款！！！","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"承香墨影","chapterId":411,"chapterName":"承香墨影","Collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":4752,"link":"http://mp.weixin.qq.com/s?__biz=MzIxNjc0ODExMA==&mid=2247485079&idx=1&sn=6ebe1f401b04bf9a7da6ade0920f2286&chksm=97851fb6a0f296a039a420347878eb1a6d49828143f180e3bf72fc114808aaa6d99e25a71ffc&scene=38#wechat_redirect","niceDate":"2017-12-31","origin":"","projectLink":"","publishTime":1514649600000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/411/1"}],"title":"最后的 2017，2018 <em class='highlight'>你好<\/em>！","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"code小生","chapterId":414,"chapterName":"code小生","Collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":5437,"link":"http://mp.weixin.qq.com/s?__biz=MzIxNzU1Nzk3OQ==&mid=2247485247&idx=1&sn=5d04c142d2594b689bbfd4fd60a03a94&chksm=97f6b98ba081309d719fb4b694883c4d6dd083f039a61ed1e2dddbc3384b44b2607e102dd8b4&scene=38#wechat_redirect","niceDate":"2017-08-22","origin":"","projectLink":"","publishTime":1503331200000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/414/1"}],"title":"<em class='highlight'>你好<\/em>,芒果!使用 RxKotlin 开发的 Dribbble App.","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"谷歌开发者","chapterId":415,"chapterName":"谷歌开发者","Collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":6297,"link":"http://mp.weixin.qq.com/s?__biz=MzAwODY4OTk2Mg==&mid=2652043811&idx=1&sn=9a82030889e1a2838b7961ce92f94673&chksm=808d5a66b7fad37087764daddb62ac3d6670a9278c89305aec67a6ccd03563929c005fdf6e61&scene=38#wechat_redirect","niceDate":"2017-06-06","origin":"","projectLink":"","publishTime":1496678400000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/415/1"}],"title":"PNaCl 再见，WebAssembly <em class='highlight'>你好<\/em>！","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"code小生","chapterId":414,"chapterName":"code小生","Collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":5468,"link":"http://mp.weixin.qq.com/s?__biz=MzIxNzU1Nzk3OQ==&mid=2247484377&idx=1&sn=6fb67ffbc62d4a01861cdd024787f6e1&chksm=97f6bd6da081347bc5a2a89f6b0c951d122b90c6fbaa16a1bb15e190630efb70f36acabc202b&scene=38#wechat_redirect","niceDate":"2017-04-19","origin":"","projectLink":"","publishTime":1492531200000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/414/1"}],"title":"从未如此惊艳！<em class='highlight'>你好<\/em>，SuperTextView","type":0,"userId":-1,"visible":1,"zan":0}],"offset":0,"over":true,"pageCount":1,"size":20,"total":6}
     * errorCode : 0
     * errorMsg :
     */

    private DataBean data;
    private int errorCode;
    private String errorMsg;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public static class DataBean implements Serializable{
        /**
         * curPage : 1
         * datas : [{"apkLink":"","author":"鸿洋","chapterId":408,"chapterName":"鸿洋","Collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":7371,"link":"https://mp.weixin.qq.com/s/JcviqDZ8To3ZEL2H0kVukA","niceDate":"2018-10-18","origin":"","projectLink":"","publishTime":1539792000000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"androidx <em class='highlight'>你好<\/em> ，android.support 再见。","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"承香墨影","chapterId":411,"chapterName":"承香墨影","Collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":4829,"link":"http://mp.weixin.qq.com/s?__biz=MzIxNjc0ODExMA==&mid=2247485082&idx=1&sn=de8916de175b7aa9e39e24bf7d3a89ba&chksm=97851fbba0f296adbd1a5d533f043270c812ff5d9189007ce0c4a3b10bcb127159a3c966f830&scene=38#wechat_redirect","niceDate":"2018-01-02","origin":"","projectLink":"","publishTime":1514822400000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/411/1"}],"title":"2018 <em class='highlight'>你好<\/em>，年度的关键字：爆款！！！","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"承香墨影","chapterId":411,"chapterName":"承香墨影","Collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":4752,"link":"http://mp.weixin.qq.com/s?__biz=MzIxNjc0ODExMA==&mid=2247485079&idx=1&sn=6ebe1f401b04bf9a7da6ade0920f2286&chksm=97851fb6a0f296a039a420347878eb1a6d49828143f180e3bf72fc114808aaa6d99e25a71ffc&scene=38#wechat_redirect","niceDate":"2017-12-31","origin":"","projectLink":"","publishTime":1514649600000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/411/1"}],"title":"最后的 2017，2018 <em class='highlight'>你好<\/em>！","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"code小生","chapterId":414,"chapterName":"code小生","Collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":5437,"link":"http://mp.weixin.qq.com/s?__biz=MzIxNzU1Nzk3OQ==&mid=2247485247&idx=1&sn=5d04c142d2594b689bbfd4fd60a03a94&chksm=97f6b98ba081309d719fb4b694883c4d6dd083f039a61ed1e2dddbc3384b44b2607e102dd8b4&scene=38#wechat_redirect","niceDate":"2017-08-22","origin":"","projectLink":"","publishTime":1503331200000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/414/1"}],"title":"<em class='highlight'>你好<\/em>,芒果!使用 RxKotlin 开发的 Dribbble App.","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"谷歌开发者","chapterId":415,"chapterName":"谷歌开发者","Collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":6297,"link":"http://mp.weixin.qq.com/s?__biz=MzAwODY4OTk2Mg==&mid=2652043811&idx=1&sn=9a82030889e1a2838b7961ce92f94673&chksm=808d5a66b7fad37087764daddb62ac3d6670a9278c89305aec67a6ccd03563929c005fdf6e61&scene=38#wechat_redirect","niceDate":"2017-06-06","origin":"","projectLink":"","publishTime":1496678400000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/415/1"}],"title":"PNaCl 再见，WebAssembly <em class='highlight'>你好<\/em>！","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"code小生","chapterId":414,"chapterName":"code小生","Collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":5468,"link":"http://mp.weixin.qq.com/s?__biz=MzIxNzU1Nzk3OQ==&mid=2247484377&idx=1&sn=6fb67ffbc62d4a01861cdd024787f6e1&chksm=97f6bd6da081347bc5a2a89f6b0c951d122b90c6fbaa16a1bb15e190630efb70f36acabc202b&scene=38#wechat_redirect","niceDate":"2017-04-19","origin":"","projectLink":"","publishTime":1492531200000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/414/1"}],"title":"从未如此惊艳！<em class='highlight'>你好<\/em>，SuperTextView","type":0,"userId":-1,"visible":1,"zan":0}]
         * offset : 0
         * over : true
         * pageCount : 1
         * size : 20
         * total : 6
         */

        private int curPage;
        private int offset;
        private boolean over;
        private int pageCount;
        private int size;
        private int total;
        private List<DatasBean> datas;

        public int getCurPage() {
            return curPage;
        }

        public void setCurPage(int curPage) {
            this.curPage = curPage;
        }

        public int getOffset() {
            return offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public boolean isOver() {
            return over;
        }

        public void setOver(boolean over) {
            this.over = over;
        }

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<DatasBean> getDatas() {
            return datas;
        }

        public void setDatas(List<DatasBean> datas) {
            this.datas = datas;
        }

        public static class DatasBean implements Serializable{
            /**
             * apkLink :
             * author : 鸿洋
             * chapterId : 408
             * chapterName : 鸿洋
             * Collect : false
             * courseId : 13
             * desc :
             * envelopePic :
             * fresh : false
             * id : 7371
             * link : https://mp.weixin.qq.com/s/JcviqDZ8To3ZEL2H0kVukA
             * niceDate : 2018-10-18
             * origin :
             * projectLink :
             * publishTime : 1539792000000
             * superChapterId : 408
             * superChapterName : 公众号
             * tags : [{"name":"公众号","url":"/wxarticle/list/408/1"}]
             * title : androidx <em class='highlight'>你好</em> ，android.support 再见。
             * type : 0
             * userId : -1
             * visible : 1
             * zan : 0
             */

            private String apkLink;
            private String author;
            private int chapterId;
            private String chapterName;
            private boolean collect;
            private int courseId;
            private String desc;
            private String envelopePic;
            private boolean fresh;
            private int id;
            private String link;
            private String niceDate;
            private String origin;
            private String projectLink;
            private long publishTime;
            private int superChapterId;
            private String superChapterName;
            private String title;
            private int type;
            private int userId;
            private int visible;
            private int zan;
            private List<TagsBean> tags;

            public String getApkLink() {
                return apkLink;
            }

            public void setApkLink(String apkLink) {
                this.apkLink = apkLink;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public int getChapterId() {
                return chapterId;
            }

            public void setChapterId(int chapterId) {
                this.chapterId = chapterId;
            }

            public String getChapterName() {
                return chapterName;
            }

            public void setChapterName(String chapterName) {
                this.chapterName = chapterName;
            }

            public boolean isCollect() {
                return collect;
            }

            public void setCollect(boolean collect) {
                this.collect = collect;
            }

            public int getCourseId() {
                return courseId;
            }

            public void setCourseId(int courseId) {
                this.courseId = courseId;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getEnvelopePic() {
                return envelopePic;
            }

            public void setEnvelopePic(String envelopePic) {
                this.envelopePic = envelopePic;
            }

            public boolean isFresh() {
                return fresh;
            }

            public void setFresh(boolean fresh) {
                this.fresh = fresh;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public String getNiceDate() {
                return niceDate;
            }

            public void setNiceDate(String niceDate) {
                this.niceDate = niceDate;
            }

            public String getOrigin() {
                return origin;
            }

            public void setOrigin(String origin) {
                this.origin = origin;
            }

            public String getProjectLink() {
                return projectLink;
            }

            public void setProjectLink(String projectLink) {
                this.projectLink = projectLink;
            }

            public long getPublishTime() {
                return publishTime;
            }

            public void setPublishTime(long publishTime) {
                this.publishTime = publishTime;
            }

            public int getSuperChapterId() {
                return superChapterId;
            }

            public void setSuperChapterId(int superChapterId) {
                this.superChapterId = superChapterId;
            }

            public String getSuperChapterName() {
                return superChapterName;
            }

            public void setSuperChapterName(String superChapterName) {
                this.superChapterName = superChapterName;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public int getVisible() {
                return visible;
            }

            public void setVisible(int visible) {
                this.visible = visible;
            }

            public int getZan() {
                return zan;
            }

            public void setZan(int zan) {
                this.zan = zan;
            }

            public List<TagsBean> getTags() {
                return tags;
            }

            public void setTags(List<TagsBean> tags) {
                this.tags = tags;
            }

            public static class TagsBean implements Serializable{
                /**
                 * name : 公众号
                 * url : /wxarticle/list/408/1
                 */

                private String name;
                private String url;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }
            }
        }
    }
}
