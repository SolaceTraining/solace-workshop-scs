package com.solace.workshop;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tweet {
	private Timestamp timestamp;
	private UUID uuid;
	private String text;
	private String username;
	private ArrayList<String> hashtags;
	private ArrayList<String> userMentions;

	public Tweet() {
		timestamp = new Timestamp(System.currentTimeMillis());
		uuid = UUID.randomUUID();
	}

	public Tweet(String username, String text) {
		this();
		setText(text);
		setUsername(username);
		setHashtags(extractHashtags(text));
		setUserMentions(extractMentions(text));
	}

	// Method to extract user mentions (which start with @) from a text field
	private ArrayList<String> extractMentions(String text) {
		ArrayList<String> mentions = new ArrayList<String>();

		Pattern pattern = Pattern.compile("@\\w+");
		Matcher matcher = pattern.matcher(text);
		while (matcher.find()) {
			mentions.add(matcher.group());
		}
		return mentions;
	}

	// Method to extract hashtags (which start with #) from a text field
	private ArrayList<String> extractHashtags(String text) {
		ArrayList<String> hashtags = new ArrayList<String>();

		Pattern pattern = Pattern.compile("#\\w+");
		Matcher matcher = pattern.matcher(text);
		while (matcher.find()) {
			hashtags.add(matcher.group());
		}
		return hashtags;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void updateTimestamp() {
		this.timestamp = new Timestamp(System.currentTimeMillis());
	}

	public UUID getUuid() {
		return uuid;
	}

	public void updateUuid() {
		this.uuid = UUID.randomUUID();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public ArrayList<String> getHashtags() {
		return hashtags;
	}

	public void setHashtags(ArrayList<String> hashtags) {
		this.hashtags = hashtags;
	}

	public ArrayList<String> getUserMentions() {
		return userMentions;
	}

	public void setUserMentions(ArrayList<String> userMentions) {
		this.userMentions = userMentions;
	}

	@Override
	public String toString() {
		return "Tweet [timestamp=" + timestamp + ", uuid=" + uuid + ", text=" + text + ", username=" + username
				+ ", hashtags=" + hashtags + ", userMentions=" + userMentions + "]";
	}

}