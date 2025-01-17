package io.openindoormap.service;

import io.openindoormap.domain.policy.GeoPolicy;

/**
 * 2D, 3D 운영 정책
 * @author jeongdae
 *
 */
public interface GeoPolicyService {
	
	/**
	 * 운영 정책 정보
	 * @return
	 */
	GeoPolicy getGeoPolicy();
}
