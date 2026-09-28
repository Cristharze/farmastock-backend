package com.farmastock.shared.application;

import org.springframework.stereotype.Service;

@Service
public class ProjectInfoService {

    public String projectName() {
        return "FARMASTOCK BACKEND";
    }

    public String backendStage() {
        return "SPRING BOOT BASE - CAPITULO 03";
    }
}