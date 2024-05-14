package com.dokutahshikikan.laba3oop.Servlet;

import lombok.Getter;

public class Names {
    @Getter private String title;
    @Getter private String genre;
    @Getter private String time;
    @Getter private int amount;
    @Getter private String rate;

    public Names(String title, String genre, String time, String rate, int amount) {
        this.title = title;
        this.genre = genre;
        this.time = time;
        this.amount = amount;
        this.rate = rate;

    }

    @Override
    public boolean equals(Object obj) {
        Names names = (Names) obj;
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        if (!title.equals(names.title)) return false;
        if (!genre.equals(names.genre)) return false;
        if (!time.equals(names.time)) return false;
        if (!rate.equals(names.rate)) return false;
        return amount == names.amount;
    }

    @Override
    public int hashCode() {
        int result = (int) ((int)amount ^ ((int)amount >>> 32));
        result = 31 * result + title.hashCode();
        result = 31 * result + genre.hashCode();
        result = 31 * result + time.hashCode();
        result = 31 * result + rate.hashCode();
        return result;
    }
}