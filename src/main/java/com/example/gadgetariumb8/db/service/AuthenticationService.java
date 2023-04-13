package com.example.gadgetariumb8.db.service;

import com.example.gadgetariumb8.dto.request.AuthenticateRequest;
import com.example.gadgetariumb8.dto.request.RegisterRequest;
import com.example.gadgetariumb8.dto.response.AuthenticationResponse;

/**
 * @author kurstan
 * @created at 13.04.2023 10:00
 */
public interface AuthenticationService {

    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticateRequest request);
}
