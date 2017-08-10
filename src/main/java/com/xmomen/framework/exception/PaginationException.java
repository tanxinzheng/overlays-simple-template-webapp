package com.xmomen.framework.exception;

/**
 * Created by tanxinzheng on 17/6/10.
 */
public class PaginationException extends IllegalArgumentException {

    public static final String NOT_PAGINATION_PARAMETER = "Pagination parameter limit,offset must be > 0";

    public PaginationException(String s) {
        super(s);
    }
}
