package logic.entity.dao;

import logic.entity.factory.Factory;
import logic.entity.model.VideoAds;

public class VideoAdsDAO {

	private VideoAdsDAO() {}
	
	public static VideoAds selectVideoAds() {
		return Factory.getVideoAds();
	}
}
