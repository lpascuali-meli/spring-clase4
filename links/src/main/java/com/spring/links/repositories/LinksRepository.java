package com.spring.links.repositories;

import com.spring.links.dtos.LinkDto;
import com.spring.links.dtos.LinkToCreateDto;
import com.spring.links.exceptions.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class LinksRepository implements ILinkRepository{
    private HashMap<Integer, LinkDto> links;
    private static int lastId = 0;

    public LinksRepository() {
        this.links = new HashMap<Integer, LinkDto>();
    }

    @Override
    public Integer create(LinkToCreateDto link) throws ApiException {
        LinkDto linkDtoToValidate = getLinkByUrl(link.getUrl());
        if (linkDtoToValidate != null) { throw new ApiException(HttpStatus.BAD_REQUEST, "La URL ya existe."); }
        int newId = lastId + 1;
        LinkDto newLink = new LinkDto(newId, link.getUrl(), link.getPassword(), 0, true);
        links.put(newId, newLink);
        lastId += 1;
        return newId;
    }

    @Override
    public LinkDto getLinkByUrl(String url) {
        for (Map.Entry<Integer, LinkDto> entry : links.entrySet()) {
            if (entry.getValue().getUrl().equals(url) && entry.getValue().isActivated()) {
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public LinkDto getLinkById(int id) throws ApiException {
        LinkDto link = links.get(id);
        if (link != null && link.isActivated()) {
            return link;
        } else {
            throw new ApiException(HttpStatus.NOT_FOUND, "La URL no existe.");
        }
    }

    @Override
    public void incrementCounter(int id) {
        LinkDto link = links.get(id);
        link.setAccessCounter(link.getAccessCounter() + 1);
    }

    @Override
    public void invalidateLink(int id) throws ApiException {
        LinkDto linkDto = links.get(id);
        if (linkDto == null) { throw new ApiException(HttpStatus.NOT_FOUND, "No existe el link indicado."); }
        linkDto.setActivated(false);
    }
}
