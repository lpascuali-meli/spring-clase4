package com.spring.links.controllers;

import com.spring.links.dtos.LinkDto;
import com.spring.links.dtos.LinkToCreateDto;
import com.spring.links.exceptions.ApiException;
import com.spring.links.services.LinksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/links")
public class LinksController {
    @Autowired
    private LinksService linksService;

    @PostMapping("")
    public ResponseEntity<Integer> createLink(@RequestBody LinkToCreateDto link) throws ApiException {
        int id = linksService.createLink(link);
        return new ResponseEntity<Integer>(id, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LinkDto> getById(@PathVariable int id) throws ApiException {
        LinkDto link = linksService.getLinkById(id);
        return new ResponseEntity<LinkDto>(link, HttpStatus.OK);
    }

    @GetMapping("/redirect/{id}")
    public RedirectView redirectById(@PathVariable int id, @RequestParam String password) throws ApiException {
        LinkDto link = linksService.redirectLink(id, password);
        return new RedirectView(link.getUrl());
    }

    @PostMapping("/invalidate/{id}")
    public ResponseEntity invalidateById(@PathVariable int id) throws ApiException {
        linksService.invalidateLink(id);
        return new ResponseEntity("Link deshabilitado", HttpStatus.OK);
    }
}
