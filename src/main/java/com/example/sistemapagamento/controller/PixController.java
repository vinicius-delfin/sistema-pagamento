package com.example.sistemapagamento.controller;

import com.example.sistemapagamento.dto.PixChargeRequest;
import com.example.sistemapagamento.service.PixService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pix")
@RequiredArgsConstructor
public class PixController {

    private final PixService pixService;

    @GetMapping
    public ResponseEntity pixCreateEVP() {
        JSONObject response = this.pixService.pixCreateEVP();
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(response.toString());
    }

    @PostMapping
    public ResponseEntity pixCreateCharge(@RequestBody PixChargeRequest pixChargeRequest) {
        JSONObject response = this.pixService.pixCreateCharge(pixChargeRequest);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(response.toString());
    }
}
