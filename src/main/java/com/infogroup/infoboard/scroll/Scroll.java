package com.infogroup.infoboard.scroll;

import com.infogroup.infoboard.InfoBoardReborn;
import org.bukkit.ChatColor;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Scroll {
	private InfoBoardReborn plugin;
	private String message, originalMessage;

	private ChatColor color = ChatColor.RESET;

	private int width, row;
	private int position, pause = 0;

	/**
	 * Create a new scroller
	 *
	 * @param message
	 * @param row
	 * @param width
	 */
	public Scroll(InfoBoardReborn plugin, String message, int row, int width) {
		//TODO Fix colors when scrolling
		this.plugin = plugin;
		this.row = row;
		this.width = width;
		this.originalMessage = message;
		StringBuilder builder = new StringBuilder(message);
		while (builder.length() <= (width * 2)) {
			builder.append("          ").append(message);
		}
		String string = builder.toString();

		string = translateMsg(string);

		this.message = this.plugin.getMessages().getColored(string);
	}

	/**
	 * Get the scrolled message
	 *
	 * @return String
	 */
	public String getMessage() {
	//TODO fix the removing of the color char one a move
		String message = this.message.substring(position, Math.min(this.message.length(), (width - 2) + position));
		char COLORCHAR = '&';

		//need a check to see if the previous message had a color at 0 location
		if (message.charAt(0) == COLORCHAR) {
			color = ChatColor.getByChar(message.charAt(1));
		} else {
			message = message.substring(1, message.length());
			message = "" + color + message;
		}
		if (message.charAt(message.length() - 1) == COLORCHAR) {
			message = message.substring(0, message.length() - 2);
			message = message + " ";
		}
		return message;

	}

	/**
	 * Get the row
	 *
	 * @return Integer
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Move position up one unless it's being paused for 3 counts first
	 */
	public void next() {
		if ((position == 0) && (pause != 3)) {
			pause++;
		} else {
			position++;
			pause = 0;
			if (position == (originalMessage.length() + 10)) {
				position = 0;
			}
		}
	}

	/**
	 * adds the color code after each letter in the message
	 *
	 * @param msg
	 * @return
	 */
	public String translateMsg(String msg){
		//TODO FINISH color code fix (not finished)

		//works for "&7Hello &cWorld!"
		String result = Arrays.asList(msg.split(" ")).stream().map(word -> {
			String prefix = msg.substring(0, 2);

			return Arrays.stream(msg.substring(2).split(""))
					.collect(Collectors.joining(prefix, prefix, ""));
		}).collect(Collectors.joining(" "));



		String[] split = msg.split("&");
		for(int i=0; i < split.length; i++){
			String color = null;
			if (split[i].length()==1){
				color = "&" + split[i];
				continue;
			}else{
				color = color + "&" + split[i].substring(0,1);
			}
			//add all letter with color code
			result = color + result;
		}


		//return the msg
		return result;
	}
}
