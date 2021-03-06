// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.management.appservice;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.JsonFlatten;
import com.fasterxml.jackson.annotation.JsonProperty;

/** The StaticSiteResetPropertiesARMResource model. */
@JsonFlatten
@Fluent
public class StaticSiteResetPropertiesARMResource extends ProxyOnlyResource {
    /*
     * The token which proves admin privileges to the repository.
     */
    @JsonProperty(value = "properties.repositoryToken")
    private String repositoryToken;

    /*
     * Determines whether the repository should be updated with the new
     * properties.
     */
    @JsonProperty(value = "properties.shouldUpdateRepository")
    private Boolean shouldUpdateRepository;

    /**
     * Get the repositoryToken property: The token which proves admin privileges to the repository.
     *
     * @return the repositoryToken value.
     */
    public String repositoryToken() {
        return this.repositoryToken;
    }

    /**
     * Set the repositoryToken property: The token which proves admin privileges to the repository.
     *
     * @param repositoryToken the repositoryToken value to set.
     * @return the StaticSiteResetPropertiesARMResource object itself.
     */
    public StaticSiteResetPropertiesARMResource withRepositoryToken(String repositoryToken) {
        this.repositoryToken = repositoryToken;
        return this;
    }

    /**
     * Get the shouldUpdateRepository property: Determines whether the repository should be updated with the new
     * properties.
     *
     * @return the shouldUpdateRepository value.
     */
    public Boolean shouldUpdateRepository() {
        return this.shouldUpdateRepository;
    }

    /**
     * Set the shouldUpdateRepository property: Determines whether the repository should be updated with the new
     * properties.
     *
     * @param shouldUpdateRepository the shouldUpdateRepository value to set.
     * @return the StaticSiteResetPropertiesARMResource object itself.
     */
    public StaticSiteResetPropertiesARMResource withShouldUpdateRepository(Boolean shouldUpdateRepository) {
        this.shouldUpdateRepository = shouldUpdateRepository;
        return this;
    }
}
