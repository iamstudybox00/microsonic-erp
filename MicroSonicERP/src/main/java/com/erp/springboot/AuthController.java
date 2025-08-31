package com.erp.springboot;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

 @GetMapping("/me")
 public ResponseEntity<?> getCurrentUser(Authentication authentication) {
     if (authentication != null && authentication.isAuthenticated()) {
         return ResponseEntity.ok(authentication.getName()); // 로그인한 사용자 ID 반환
     } else {
         return ResponseEntity.status(401).body("Not authenticated");
     }
 }
}

