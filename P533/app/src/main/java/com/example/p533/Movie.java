package com.example.p533;

public class Movie {
    String rank;
    String openDt;
    String movieNm;
    String showCnt;

    public String getShowCnt() {
        return showCnt;
    }

    public void setShowCnt(String showCnt) {
        this.showCnt = showCnt;
    }

    public Movie(String rank, String openDt, String movieNm, String showCnt) {
        this.rank = rank;
        this.openDt = openDt;
        this.movieNm = movieNm;
        this.showCnt = showCnt;
    }

    public Movie() {
    }

    public Movie(String rank, String openDt, String movieNm) {
        this.rank = rank;
        this.openDt = openDt;
        this.movieNm = movieNm;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getOpenDt() {
        return openDt;
    }

    public void setOpenDt(String openDt) {
        this.openDt = openDt;
    }

    public String getMovieNm() {
        return movieNm;
    }

    public void setMovieNm(String movieNm) {
        this.movieNm = movieNm;
    }
}
