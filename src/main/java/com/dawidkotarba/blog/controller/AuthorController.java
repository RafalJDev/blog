package com.dawidkotarba.blog.controller;

import com.dawidkotarba.blog.facade.AuthorFacade;
import com.dawidkotarba.blog.model.dto.impl.AuthorDto;
import io.vavr.collection.Set;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

import static com.dawidkotarba.blog.constants.BlogConstants.API_PREFIX;

@RestController
@RequestMapping(value = API_PREFIX + "authors")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthorController {

    private final AuthorFacade authorFacade;

    @Inject
    public AuthorController(final AuthorFacade authorFacade) {
        this.authorFacade = authorFacade;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<AuthorDto> findAllStartsWith(@RequestParam final String username) {
        return authorFacade.findAllByUsernameStartingWithIgnoreCase(username);
    }
}
