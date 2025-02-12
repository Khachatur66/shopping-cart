package com.shoppingcart.controllers;

import com.shoppingcart.exceptions.BadRequestException;
import com.shoppingcart.exceptions.NotFoundException;
import com.shoppingcart.entities.Authority;
import com.shoppingcart.services.AuthorityService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("authority")
public class AuthorityController {

    private final AuthorityService authorityService;

    public AuthorityController(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'CUSTOMER')")
    @GetMapping("/{id}")
    public ResponseEntity<Authority> getById(@PathVariable("id") int id) throws NotFoundException {
        return ResponseEntity.ok(authorityService.getById(id));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'CUSTOMER')")
    @GetMapping
    public ResponseEntity<List<Authority>> getAll() {
        return ResponseEntity.ok(authorityService.getAll());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody Authority authority) throws BadRequestException {
        authorityService.save(authority);
        return ResponseEntity.ok().build();
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping
    public ResponseEntity<Void> update(@Valid @RequestBody Authority authority) {
        authorityService.update(authority);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam int id) {
        authorityService.delete(id);
        return ResponseEntity.ok().build();
    }

}
