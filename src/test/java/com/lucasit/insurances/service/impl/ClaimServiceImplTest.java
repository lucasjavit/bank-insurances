package com.lucasit.insurances.service.impl;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.lucasit.insurances.model.Claim;
import com.lucasit.insurances.repository.ClaimRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ClaimServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ClaimServiceImplTest {
    @MockBean
    private ClaimRepository claimRepository;

    @Autowired
    private ClaimServiceImpl claimServiceImpl;


    @Test
    void testSave() {
        Claim claim = new Claim();
        when(claimRepository.save((Claim) any())).thenReturn(claim);
        assertSame(claim, claimServiceImpl.save(new Claim()));
        verify(claimRepository).save((Claim) any());
    }
}

