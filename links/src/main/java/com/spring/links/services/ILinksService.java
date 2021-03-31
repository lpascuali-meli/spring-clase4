package com.spring.links.services;

import com.spring.links.dtos.LinkDto;
import com.spring.links.dtos.LinkToCreateDto;
import com.spring.links.exceptions.ApiException;

public interface ILinksService {
    public Integer createLink (LinkToCreateDto url) throws ApiException;
    public LinkDto getLinkById(int id) throws ApiException;
    public LinkDto redirectLink(int id, String password) throws ApiException;
    public void invalidateLink(int id) throws ApiException;
}
