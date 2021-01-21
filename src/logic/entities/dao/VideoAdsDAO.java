package logic.entities.dao;

import logic.entities.factory.Factory;
import logic.entities.model.VideoAds;

public class VideoAdsDAO {

	private VideoAdsDAO() {}
	
	public static VideoAds selectVideoAds() {
		return Factory.getVideoAds();
	}
}
