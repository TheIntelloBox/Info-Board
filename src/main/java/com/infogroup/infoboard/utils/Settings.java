package com.infogroup.infoboard.utils;

import com.infogroup.infoboard.InfoBoardReborn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Settings {
	private InfoBoardReborn plugin;

	public List<String> changeable = new ArrayList<>();
	public List<String> conditions = new ArrayList<>();

	public Settings(InfoBoardReborn plugin) {
		this.plugin = plugin;
		loadChangeable();
		loadConditions();
	}

	/**
	 * Determines if the rank has valid scoreboard
	 *
	 * @param rotation
	 * @return true/false
	 */
	public boolean doesGlobalHaveScoreBoard(int rotation) {
		boolean hasBoard = false;
		for (String s : plugin.getFm().getFile("board").getConfigurationSection("InfoBoard." + String.valueOf(rotation))
				.getKeys(true)) {
			if (!s.contains(".")) {
				if (s.equals("global")) {
					hasBoard = true;
					break;
				}
			}
		}
		return hasBoard;
	}

	/**
	 * Determines if the rank has valid scoreboard
	 *
	 * @param rotation
	 * @param world
	 * @param rank
	 * @return true/false
	 */
	public boolean doesRankHaveScoreBoard(int rotation, String world, String rank) {
		boolean hasBoard = false;
		for (String s : plugin.getFm().getFile("board")
				.getConfigurationSection("InfoBoard." + String.valueOf(rotation) + "." + world).getKeys(true)) {
			if (!s.contains(".")) {
				if (s.equals(rank)) {
					hasBoard = true;
					break;
				}
			}
		}
		return hasBoard;
	}

	/**
	 * Determines if the world given has a valid scoreboard
	 *
	 * @param rotation
	 * @param world
	 * @return true/false
	 */
	public boolean doesWorldHaveScoreBoard(int rotation, String world) {
		boolean hasBoard = false;
		for (String s : plugin.getFm().getFile("board").getConfigurationSection("InfoBoard." + String.valueOf(rotation))
				.getKeys(true)) {
			if (!s.contains(".")) {
				if (s.equalsIgnoreCase(world)) {
					hasBoard = true;
					break;
				}
			}
		}
		return hasBoard;
	}

	/**
	 * Get the list of blocked regions for WorldGuard
	 *
	 * @return list
	 */
	public List<String> getRegionsDisabled() {
		return plugin.getFm().getFile("config").getStringList("WorldGuard.Prevent Showing In");
	}

	/**
	 * Determine if the world given is blocked
	 *
	 * @param world
	 * @return
	 */
	public boolean isWorldDisabled(String world) {
		return plugin.getFm().getFile("config").getStringList("Disabled Worlds").contains(world) || (world == null);
	}

	/**
	 * Get if scrolling is enabled
	 *
	 * @return true/false
	 */
	public boolean scrollingEnabled() {
		return plugin.getFm().getFile("config").getBoolean("Scrolling Text.Enable");
	}

	/**
	 * Get if changeable text is enabled
	 * 
	 * @return true/false
	 */
	public boolean changeableTextEnabled() {
		return plugin.getFm().getFile("config").getBoolean("Changeable Text.Enable");
	}

	/**
	 * Get if conditions are enabled
	 *
	 * @return true/false
	 */
	public boolean conditionsEnabled(){
		return plugin.getFm().getFile("config").getBoolean("Condition.Enabled");
	}
	/**
	 * Get if updater is enabled
	 * 
	 * @return true/false
	 */
	public boolean updater() {
		return plugin.getFm().getFile("config").getBoolean("Updater");
	}

	/**
	 * Get if Debugger is enabled
	 * 
	 * @return true/false
	 */
	public boolean debug() {
		return plugin.getFm().getFile("config").getBoolean("Debug");
	}

	/**
	 * if a static board should be used
	 *
	 * @return
	 */
	public boolean staticBoard() {
		return plugin.getFm().getFile("config").getBoolean("Static Board");
	}

	/**
	 * Get the lines for give changeable
	 * 
	 * @return ArrayList
	 */
	public ArrayList<String> getText(String changeable) {
		ArrayList<String> lines = new ArrayList<>();
		lines.addAll(plugin.getFm().getFile("config")
				.getStringList("Changeable Text.Changeables." + changeable + ".text"));
		return lines;
	}

	/**
	 * Loads the changeable's in a list.
	 */
	public void loadChangeable() {
		changeable.clear();
		changeable.addAll(plugin.getFm().getFile("config").getConfigurationSection("Changeable Text.Changeables")
				.getKeys(false));
	}

	/**
	 * Gets the changeable's list
	 * 
	 * @return List
	 */
	public List<String> getChangeable() {
		return this.changeable;
	}

	/**
	 * Get's the time for the given changeable
	 * 
	 * @return integer
	 */
	public Integer getInterval(String changeable) {
		return plugin.getFm().getFile("config").getInt("Changeable Text.Changeables." + changeable + ".interval");
	}

	/**
	 * Get the changeable Option
	 * @param CH
	 * @return
	 */
	public String getChangeableOption(String CH){
		return plugin.getFm().getFile("config").getString("Changeable Text.Changeables."+ CH + ".option");
	}


    /**
     * Gets the condition list
	 *
     * @return
     */
	public List<String> getConditions(){
	    return this.conditions;
    }

    /**
     * Loads the conditions in a list.
     */
    public void loadConditions(){
	    conditions.clear();
	    conditions.addAll(plugin.getFm().getFile("config").getConfigurationSection("Condition.Conditions")
                .getKeys(false));
    }

	/**
	 * Gets the given condition their interval
	 *
	 * @param con
	 * @return
	 */
    public Integer getConInterval(String con){
	    return plugin.getFm().getFile("config").getInt("Condition.Conditions." + con +". interval");
    }

	/**
	 * Gets the given condition their possible answers
	 *
	 * @param condition
	 * @return
	 */
    public Map<String, String> getConText(String condition) {
		//TODO FIX
		Map<String, String> answers= new HashMap<>();

		return answers;
	}

}
