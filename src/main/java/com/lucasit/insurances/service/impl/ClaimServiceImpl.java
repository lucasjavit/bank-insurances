package com.lucasit.insurances.service.impl;

import com.lucasit.insurances.model.Claim;
import com.lucasit.insurances.repository.ClaimRepository;
import com.lucasit.insurances.service.ClaimService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClaimServiceImpl implements ClaimService {


    private final ClaimRepository claimRepository;

    @Override
    public Claim save(Claim claim) {
        return claimRepository.save(claim);
    }
}
