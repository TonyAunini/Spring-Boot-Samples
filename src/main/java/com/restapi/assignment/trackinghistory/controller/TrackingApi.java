package com.restapi.assignment.trackinghistory.controller;

import java.util.Deque;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestParam;

import com.restapi.assignment.trackinghistory.response.AddValueResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Validated
public interface TrackingApi {

	@Operation(description = "Get Tracking History starting from last entry", operationId = "TrackingHistory", summary = "Get Upto 10 from Tracking History", tags = {
			"Track" })
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Success") })
	public ResponseEntity<Deque<String>> getTrackingHistory();

	@Operation(description = "add the name and return last item", operationId = "TrackingValue", summary = "Store value in Tracking History", tags = {
			"Track" })
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Success") })
	public ResponseEntity<AddValueResponse> addTrackValue(
		 @Parameter(description = "Name to Track")  @RequestParam(value = "input", required = true)  @Size(max=1000,message = "Size must me less than 1000") @NotBlank String _name);
}
