package org.mindtree.practice.trainingprog.dto;

import java.util.List;

public class CricketBeanMain {
	
	private List<CricketSquadeBean> squad;
	private boolean cache;
	private int ttl;
	private int v;
	private CricketInfoProvider provider;
	private int creditsLeft;
	
	public List<CricketSquadeBean> getSquad() {
		return squad;
	}
	public void setSquade(List<CricketSquadeBean> squad) {
		this.squad = squad;
	}
	public boolean isCache() {
		return cache;
	}
	public void setCache(boolean cache) {
		this.cache = cache;
	}
	public int getTtl() {
		return ttl;
	}
	public void setTtl(int ttl) {
		this.ttl = ttl;
	}
	public int getV() {
		return v;
	}
	public void setV(int v) {
		this.v = v;
	}
	public CricketInfoProvider getProvider() {
		return provider;
	}
	public void setProvider(CricketInfoProvider provider) {
		this.provider = provider;
	}
	public int getCreditsLeft() {
		return creditsLeft;
	}
	public void setCreditsLeft(int creditsLeft) {
		this.creditsLeft = creditsLeft;
	}
}
