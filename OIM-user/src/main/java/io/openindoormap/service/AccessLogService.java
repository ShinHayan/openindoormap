package io.openindoormap.service;

import io.openindoormap.domain.accesslog.AccessLog;

/**
 * 로그 처리
 * @author jeongdae
 *
 */
public interface AccessLogService {
	
	/**
	 * 모든 서비스 요청에 대한 이력
	 * @param accessLog
	 * @return
	 */
	public int insertAccessLog(AccessLog accessLog);
}
