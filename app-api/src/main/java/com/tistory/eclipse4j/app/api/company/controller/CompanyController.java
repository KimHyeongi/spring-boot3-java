package com.tistory.eclipse4j.app.api.company.controller;

import com.tistory.eclipse4j.app.api.company.data.CompanyBody;
import com.tistory.eclipse4j.app.api.company.data.CompanyInfo;
import com.tistory.eclipse4j.app.api.company.service.CompanyFinder;
import com.tistory.eclipse4j.app.api.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
public class CompanyController {

	private final CompanyFinder companyFinder;

	@GetMapping(path = "/companies/{companyId}")
	public ApiResponse<CompanyInfo> find(@PathVariable("companyId") Long companyId) {
		CompanyInfo companyInfo = companyFinder.findCacheableById(companyId);
		return ApiResponse.success(companyInfo);
	}

	@GetMapping(path = "/companies/{companyId}/update")
	public HttpStatus update(@PathVariable("companyId") Long companyId,
							 @RequestBody CompanyBody companyBody) {
		return HttpStatus.OK;
	}

	@PostMapping(path = "/companies/create")
	public HttpStatus save(@RequestBody CompanyBody companyBody) {
		return HttpStatus.CREATED;
	}
}
