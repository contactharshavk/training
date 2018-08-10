package org.mindtree.practice.trainingprog.dto;

import java.util.List;

public class CricketSquadeBean {
	
	private List<CricketPlayersName> players;
	private String name;
	
	public List<CricketPlayersName> getPlayers() {
		return players;
	}
	public void setPlayers(List<CricketPlayersName> players) {
		this.players = players;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
