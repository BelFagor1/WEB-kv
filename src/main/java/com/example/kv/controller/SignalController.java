package com.example.kv.controller;

import edu.emory.mathcs.jtransforms.fft.DoubleFFT_1D;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SignalController {
    @GetMapping("notReady")
    public String notReady() {
        return "process";
    }
    @GetMapping("/signal")
    public ResponseEntity<String> getSignal() throws IOException {
        Resource resource = new ClassPathResource("test1.json"); // файл в resources
        String signalData = Files.readString(resource.getFile().toPath());
        return ResponseEntity.ok(signalData);
    }
    @PostMapping("/process")
    public ResponseEntity<List<Double>> processSignal(@RequestBody double[] signal) {
        try {
            int n = signal.length;
            DoubleFFT_1D fft = new DoubleFFT_1D(n);
            double[] complexSignal = new double[n * 2];
            System.arraycopy(signal, 0, complexSignal, 0, n);
            fft.realForwardFull(complexSignal);

            List<Double> magnitudes = new ArrayList<>();
            for (int i = 0; i < n / 2; i++) {
                double real = complexSignal[2 * i];
                double imag = complexSignal[2 * i + 1];
                double magnitude = Math.sqrt(real * real + imag * imag);
                magnitudes.add(magnitude);
            }
            return ResponseEntity.ok(magnitudes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
