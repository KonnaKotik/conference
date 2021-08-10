package ru.itis.conference.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.itis.conference.form.UserCreateForm;
import ru.itis.conference.service.AdminService;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/userlist")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @ApiOperation("Get all active users")
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')") // перепроверить доступ (или сделать на уровне фронта)
    public ResponseEntity<List<UserCreateForm>> getAllActiveUsers(@RequestHeader("Authorization") String authorization) {
        return ResponseEntity.ok().body(adminService.getListUser());
    }

    @ApiOperation("Create users")
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestHeader("Authorization") String authorization, @Valid @RequestBody UserCreateForm userCreateForm) {
        adminService.createUser(userCreateForm);
    }

    @ApiOperation("Delete users")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id) {
        adminService.deleteUser(id);
    }

    @ApiOperation("Update users")
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id,
                           @Valid @RequestBody UserCreateForm userCreateForm) {
        adminService.updateUser(id, userCreateForm);
    }
}
