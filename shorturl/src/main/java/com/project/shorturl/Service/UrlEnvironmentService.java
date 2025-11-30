package com.project.shorturl.Service;

import org.springframework.stereotype.Service;

@Service
public class UrlEnvironmentService {

    public String getBaseUrl() {
        // Codespace environment variables
        String codespace = System.getenv("CODESPACE_NAME");
        String domain = System.getenv("GITHUB_CODESPACES_PORT_FORWARDING_DOMAIN");

        if (codespace != null && domain != null) {
            // Example:
            // https://<codespace>-8080.<domain>
            return "https://" + codespace + "-8080." + domain;
        }

        // Default local environment
        return "http://localhost:8080";
    }
}
