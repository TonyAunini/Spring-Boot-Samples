package com.restapi.assignment.trackinghistory.model;

import java.util.Deque;
import java.util.LinkedList;

import org.springframework.stereotype.Component;

@Component
public class TrackHistory {

	// The deque is used add/remove of the both ends (cyclic queue.)
	private Deque<String> trackingHistoryQueue;

	public TrackHistory() {
		/*
		 * Implementation of deque as Linked list complexity of Add is O(1) complexity
		 * of remove is O(1)
		 */

		/*
		 * Also we can implement deque as ArrayDeque complexity of Add is O(1)
		 * complexity of remove is O(n) But in our case we used removedlast -->
		 * complexity is O(1)
		 */

		this.trackingHistoryQueue = new LinkedList<>();
	}

	// Getter
	public Deque<String> getTrackingHistoryQueue() {

		return trackingHistoryQueue;
	}

	// Setter
	public void setTrackingHistoryQueue(Deque<String> trackingHistoryQueue) {
		this.trackingHistoryQueue = trackingHistoryQueue;
	}

}
