package com.restapi.assignment.trackinghistory.model;

import java.util.ArrayDeque;
import java.util.Deque;

public class TrackHistory {

	private Deque<String> trackingHistoryQueue ;

	public TrackHistory()
	{
		this.trackingHistoryQueue = new  ArrayDeque<>(10);
	}

	public Deque<String> getTrackingHistoryQueue() {
		return trackingHistoryQueue;
	}

	public void setTrackingHistoryQueue(Deque<String> trackingHistoryQueue) {
		this.trackingHistoryQueue = trackingHistoryQueue;
	}
	
	
}
