package com.restapi.assignment.trackinghistory.controller;

import java.util.Deque;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Parameter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import com.restapi.assignment.trackinghistory.model.TrackHistory;
import com.restapi.assignment.trackinghistory.response.AddValueResponse;

@RestController
@RequestMapping("/track")
@Validated
public class TrackingApiController implements TrackingApi {

	@Autowired
	private TrackHistory trackingHistory;

	/*
	 * First API to get up to 10 elements in the queue Output is Deque LinkedList as
	 * Json response The Path is /track/getHistory
	 */

	@GetMapping(path = "/getHistory", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Deque<String>> getTrackingHistory() {
		try {
			// Return the list order from newest to oldest
			return ResponseEntity.ok(trackingHistory.getTrackingHistoryQueue());
		} catch (Exception ex) {
			// return Internal Server Error (500) if any exception is raised
			return ResponseEntity.internalServerError().body(null);
		}
	}

	/*
	 * Second API to add value to the queue Input is String value limited to 1000
	 * characters and not empty Output is the previous entry (for first time the
	 * output is null) The Path is /track/trackValue?input={value}
	 */

	@GetMapping(path = "/trackValue")
	public ResponseEntity<AddValueResponse> addTrackValue(
			@Parameter(description = "Name to Track") @RequestParam(value = "input", required = true) @Size(max = 1000, message = "Size must me less than 1000") @NotBlank String _name) {
		try {
			// Create Local variable
			Deque<String> tempQueue = trackingHistory.getTrackingHistoryQueue();

			// Get previous value
			String response = tempQueue.peek();

			// This Condition to avoid adding Unlimited number of Values
			if (tempQueue.size() == 10)
				// Remove last item, the complexity is O(1)
				tempQueue.removeLast();

			// add new item at the begging of queue, the complexity is O(1)
			tempQueue.offerFirst(_name);

			// return response as JSON object
			return ResponseEntity.ok(new AddValueResponse(response));
		} catch (Exception ex) {
			// return Internal Server Error (500) if any exception is raised
			return ResponseEntity.internalServerError().body(null);
		}
	}

	
	/* Method to handle Validation Exception */
	@ExceptionHandler(ConstraintViolationException.class)
	protected ResponseEntity<String> handleDMSRESTException(ConstraintViolationException objException) {

		return ResponseEntity.badRequest().body(objException.getMessage());
	}

}
