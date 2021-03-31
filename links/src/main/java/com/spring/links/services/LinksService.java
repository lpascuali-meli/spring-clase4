package com.spring.links.services;

import com.spring.links.dtos.LinkDto;
import com.spring.links.dtos.LinkToCreateDto;
import com.spring.links.exceptions.ApiException;
import com.spring.links.repositories.LinksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class LinksService implements ILinksService {
    @Autowired
    private LinksRepository linksRepository;

    @Override
    public Integer createLink (LinkToCreateDto url) throws ApiException {
        if (!isValidUrl(url.getUrl())) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "URL incorrecta");
        }
        return linksRepository.create(url);
    }

    @Override
    public LinkDto getLinkById(int id) throws ApiException {
        return linksRepository.getLinkById(id);
    }

    @Override
    public LinkDto redirectLink(int id, String password) throws ApiException {
        LinkDto link = linksRepository.getLinkById(id);
        if (!link.getPassword().equals(password)) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Contraseña incorrecta.");
        }
        linksRepository.incrementCounter(id);
        return link;
    }

    @Override
    public void invalidateLink(int id) throws ApiException {
        linksRepository.invalidateLink(id);
    }

    private boolean isValidUrl(String url) {
        return url.matches("^https?:\\/\\/[\\w\\-]+(\\.[\\w\\-]+)+[/#?]?.*$");
    }
}
