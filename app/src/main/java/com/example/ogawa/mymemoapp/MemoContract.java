package com.example.ogawa.mymemoapp;

/**
 * Created by ogawa on 15/06/24.
 */
public final class MemoContract {

    public MemoContract() {}

    public static abstract class Memos implements BasicColumns {
        public static final String TABLE_NAME = "memos";
        public static final String COL_TITLE = "title";
        public static final String COL_BODY = "body";
        public static final String COL_CREATE = "created";
        public static final String COL_UPDATED = "updated";

    }

}
