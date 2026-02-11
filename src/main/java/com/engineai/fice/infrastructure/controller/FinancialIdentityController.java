package com.engineai.fice.infrastructure.controller;

import com.engineai.fice.application.service.FinancialIdentityService;
import com.engineai.fice.domain.model.FinancialIdentity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/financial-identity")
public class FinancialIdentityController {

    private final FinancialIdentityService service;

    public FinancialIdentityController(FinancialIdentityService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<FinancialIdentity> create(@RequestBody CreateFinancialIdentityRequest request) {

        FinancialIdentity identity = service.create(
                request.getUserId(),
                request.getIncomeType(),
                request.getIncomeStabilityScore(),
                request.getRiskTolerance(),
                request.getDecisionStyle()
        );

        return new ResponseEntity<>(identity, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public FinancialIdentity getByUserId(@PathVariable Long userId) {
        return service.getByUserId(userId);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<FinancialIdentity> update(
            @PathVariable Long userId,
            @RequestBody FinancialIdentity updatedData
    ) {
        FinancialIdentity updated = service.update(userId, updatedData);
        return ResponseEntity.ok(updated);
    }

}
