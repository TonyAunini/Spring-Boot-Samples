package com.restapi.assignment.trackinghistory.controller;

import java.util.Queue;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


public interface TrackingApi {

	
	
	@Operation(description = "Get Tracking History starting from last entry", operationId = "TrackingHistory", summary = "Get Upto 10 from Tracking History", tags = {
			"Track" })
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Success")
	})
	public ResponseEntity<Queue<String>>  getTrackingHistory();
	
	
	
	@Operation(description = "add the name and return last item", operationId = "TrackingValue", summary = "Store value in Tracking History", tags = {
	"Track" })
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Success")})
	public ResponseEntity<String> addTrackValue(@Parameter(description = "Name to Track") @PathVariable (value ="value") String _name);
	
}
