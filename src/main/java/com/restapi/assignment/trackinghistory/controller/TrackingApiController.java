package com.restapi.assignment.trackinghistory.controller;

import java.util.Deque;
import java.util.Queue;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Parameter;

import com.restapi.assignment.trackinghistory.model.TrackHistory;



@RestController
@RequestMapping("/track")
public class TrackingApiController implements TrackingApi{

	 private TrackHistory trackinghistory = new TrackHistory();
	    
		
		@GetMapping(path ="/getHistory" )
		public ResponseEntity<Queue<String>> getTrackingHistory()
		{	try {
			 return ResponseEntity.ok().body(trackinghistory.getTrackingHistoryQueue());
		        }
		    catch (Exception ex)
	        	{
		   	return ResponseEntity.internalServerError().body(null);
		       }
		}
		
		@GetMapping(path ="/trackValue/{value}")
		public ResponseEntity<String> addTrackValue(@Parameter(description = "Name to Track") @PathVariable (value ="value") String _name) 
		{
			try {
			Deque<String> tempQueue = trackinghistory.getTrackingHistoryQueue();
			String response = tempQueue.peek();
			
			// This Condition to avoid adding Unlimited number of Values
			if (tempQueue.size() == 10)
			   tempQueue.removeLast();
			tempQueue.offerFirst(_name);
			return ResponseEntity.ok().body(response);
			}
			catch (Exception ex)
			{
				return ResponseEntity.internalServerError().body(null);

			}
		}
		
	
}
