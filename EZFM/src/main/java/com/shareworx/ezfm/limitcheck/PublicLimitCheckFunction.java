package com.shareworx.ezfm.limitcheck;

import org.springframework.stereotype.Component;

import com.dms365.ezfm.limitcheck.function.LimitCheckFunction;

@Component
public class PublicLimitCheckFunction implements LimitCheckFunction {

	@Override
	public boolean limitCheck(Class<?> type) {
		return true;
	}

	@Override
	public Object errorHandler(Class<?> type) {
		return null;
	}

}
