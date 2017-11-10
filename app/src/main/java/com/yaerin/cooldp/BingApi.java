package com.yaerin.cooldp;

import java.util.List;

/**
 * Created by yaerin on 7/28/17.
 */

public class BingApi {

    /**
     * 必应日图API
     * url1: "https://cn.bing.com/HPImageArchive.aspx?format=js&n=1&idx=";
     *   param: n: 图片数量
     *   param: idx: 前idx天的图
     * url2: "http://cn.bing.com/ImageResolution.aspx?w=1920&h=1080";
     *   param: w: 宽度
     *   param: h: 高度
     */

    /**
     * images : [{"startdate":"20170727","fullstartdate":"201707271600","enddate":"20170728","url":"/az/hprichbg/rb/TempleStreet_ZH-CN7471755280_1920x1080.jpg","urlbase":"/az/hprichbg/rb/TempleStreet_ZH-CN7471755280","copyright":"香港油麻地庙街夜市 (© Peter Stewart/500px)","copyrightlink":"http://www.bing.com/search?q=%E5%BA%99%E8%A1%97%E5%A4%9C%E5%B8%82&form=hpcapt&mkt=zh-cn","quiz":"/search?q=Bing+homepage+quiz&filters=WQOskey:%22HPQuiz_20170727_TempleStreet%22&FORM=HPQUIZ","wp":true,"hsh":"c938ba8a09fc7c9d9232891eef4d5794","drk":1,"top":1,"bot":1,"hs":[]}]
     * tooltips : {"loading":"正在載入...","previous":"上一個影像","next":"下一個影像","walle":"此圖片無法下載作為桌布。","walls":"下載此影像。禁止將此影像用於桌布以外的用途。"}
     */

    private TooltipsBean tooltips;
    private List<ImagesBean> images;

    public TooltipsBean getTooltips() {
        return tooltips;
    }

    public void setTooltips(TooltipsBean tooltips) {
        this.tooltips = tooltips;
    }

    public List<ImagesBean> getImages() {
        return images;
    }

    public void setImages(List<ImagesBean> images) {
        this.images = images;
    }

    public static class TooltipsBean {
        /**
         * loading : 正在載入...
         * previous : 上一個影像
         * next : 下一個影像
         * walle : 此圖片無法下載作為桌布。
         * walls : 下載此影像。禁止將此影像用於桌布以外的用途。
         */

        private String loading;
        private String previous;
        private String next;
        private String walle;
        private String walls;

        public String getLoading() {
            return loading;
        }

        public void setLoading(String loading) {
            this.loading = loading;
        }

        public String getPrevious() {
            return previous;
        }

        public void setPrevious(String previous) {
            this.previous = previous;
        }

        public String getNext() {
            return next;
        }

        public void setNext(String next) {
            this.next = next;
        }

        public String getWalle() {
            return walle;
        }

        public void setWalle(String walle) {
            this.walle = walle;
        }

        public String getWalls() {
            return walls;
        }

        public void setWalls(String walls) {
            this.walls = walls;
        }
    }

    public static class ImagesBean {
        /**
         * startdate : 20170727
         * fullstartdate : 201707271600
         * enddate : 20170728
         * url : /az/hprichbg/rb/TempleStreet_ZH-CN7471755280_1920x1080.jpg
         * urlbase : /az/hprichbg/rb/TempleStreet_ZH-CN7471755280
         * copyright : 香港油麻地庙街夜市 (© Peter Stewart/500px)
         * copyrightlink : http://www.bing.com/search?q=%E5%BA%99%E8%A1%97%E5%A4%9C%E5%B8%82&form=hpcapt&mkt=zh-cn
         * quiz : /search?q=Bing+homepage+quiz&filters=WQOskey:%22HPQuiz_20170727_TempleStreet%22&FORM=HPQUIZ
         * wp : true
         * hsh : c938ba8a09fc7c9d9232891eef4d5794
         * drk : 1
         * top : 1
         * bot : 1
         * hs : []
         */

        private String startdate;
        private String fullstartdate;
        private String enddate;
        private String url;
        private String urlbase;
        private String copyright;
        private String copyrightlink;
        private String quiz;
        private boolean wp;
        private String hsh;
        private int drk;
        private int top;
        private int bot;
        private List<?> hs;

        public String getStartdate() {
            return startdate;
        }

        public void setStartdate(String startdate) {
            this.startdate = startdate;
        }

        public String getFullstartdate() {
            return fullstartdate;
        }

        public void setFullstartdate(String fullstartdate) {
            this.fullstartdate = fullstartdate;
        }

        public String getEnddate() {
            return enddate;
        }

        public void setEnddate(String enddate) {
            this.enddate = enddate;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUrlbase() {
            return urlbase;
        }

        public void setUrlbase(String urlbase) {
            this.urlbase = urlbase;
        }

        public String getCopyright() {
            return copyright;
        }

        public void setCopyright(String copyright) {
            this.copyright = copyright;
        }

        public String getCopyrightlink() {
            return copyrightlink;
        }

        public void setCopyrightlink(String copyrightlink) {
            this.copyrightlink = copyrightlink;
        }

        public String getQuiz() {
            return quiz;
        }

        public void setQuiz(String quiz) {
            this.quiz = quiz;
        }

        public boolean isWp() {
            return wp;
        }

        public void setWp(boolean wp) {
            this.wp = wp;
        }

        public String getHsh() {
            return hsh;
        }

        public void setHsh(String hsh) {
            this.hsh = hsh;
        }

        public int getDrk() {
            return drk;
        }

        public void setDrk(int drk) {
            this.drk = drk;
        }

        public int getTop() {
            return top;
        }

        public void setTop(int top) {
            this.top = top;
        }

        public int getBot() {
            return bot;
        }

        public void setBot(int bot) {
            this.bot = bot;
        }

        public List<?> getHs() {
            return hs;
        }

        public void setHs(List<?> hs) {
            this.hs = hs;
        }
    }
}
