package com.shareworx.ezfm.webservice.fm.out;

import javax.jws.WebService;

@WebService
public interface IBasisDataService {
	public String getValue(String name);

	public String getBasisDateList(String tableName, String lastUpdateTime, int pageNum);
}
