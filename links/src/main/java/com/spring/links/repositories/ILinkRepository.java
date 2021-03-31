package com.spring.links.repositories;

import com.spring.links.dtos.LinkDto;
import com.spring.links.dtos.LinkToCreateDto;
import com.spring.links.exceptions.ApiException;

public interface ILinkRepository {
    Integer create(LinkToCreateDto link) throws ApiException;
    LinkDto getLinkByUrl(String url);
    LinkDto getLinkById(int id) throws ApiException;
    void incrementCounter(int id);
    void invalidateLink(int id) throws ApiException;
}
