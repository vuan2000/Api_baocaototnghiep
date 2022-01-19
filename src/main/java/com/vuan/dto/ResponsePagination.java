package com.vuan.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ResponsePagination {
    private int _page;
    private int _limit;
    private  long _totalRows;

    public ResponsePagination() {
    }

    public ResponsePagination(int _page, int _limit, long _totalRows) {
        this._page = _page;
        this._limit = _limit;
        this._totalRows = _totalRows;
    }
}
