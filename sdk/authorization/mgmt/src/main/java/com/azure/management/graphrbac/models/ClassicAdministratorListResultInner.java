// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.management.graphrbac.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/** The ClassicAdministratorListResult model. */
@Fluent
public final class ClassicAdministratorListResultInner {
    /*
     * An array of administrators.
     */
    @JsonProperty(value = "value")
    private List<ClassicAdministratorInner> value;

    /*
     * The URL to use for getting the next set of results.
     */
    @JsonProperty(value = "nextLink")
    private String nextLink;

    /**
     * Get the value property: An array of administrators.
     *
     * @return the value value.
     */
    public List<ClassicAdministratorInner> value() {
        return this.value;
    }

    /**
     * Set the value property: An array of administrators.
     *
     * @param value the value value to set.
     * @return the ClassicAdministratorListResultInner object itself.
     */
    public ClassicAdministratorListResultInner withValue(List<ClassicAdministratorInner> value) {
        this.value = value;
        return this;
    }

    /**
     * Get the nextLink property: The URL to use for getting the next set of results.
     *
     * @return the nextLink value.
     */
    public String nextLink() {
        return this.nextLink;
    }

    /**
     * Set the nextLink property: The URL to use for getting the next set of results.
     *
     * @param nextLink the nextLink value to set.
     * @return the ClassicAdministratorListResultInner object itself.
     */
    public ClassicAdministratorListResultInner withNextLink(String nextLink) {
        this.nextLink = nextLink;
        return this;
    }
}
