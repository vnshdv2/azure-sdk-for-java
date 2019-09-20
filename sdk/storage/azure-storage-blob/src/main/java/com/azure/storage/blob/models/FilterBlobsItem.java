// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.storage.blob.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * The FilterBlobsItem model.
 */
@JacksonXmlRootElement(localName = "Blob")
@Fluent
public final class FilterBlobsItem {
    /*
     * The name property.
     */
    @JsonProperty(value = "Name")
    private String name;

    /*
     * The containerName property.
     */
    @JsonProperty(value = "ContainerName")
    private String containerName;

    /*
     * The tagValue property.
     */
    @JsonProperty(value = "TagValue")
    private String tagValue;

    /**
     * Get the name property: The name property.
     *
     * @return the name value.
     */
    public String name() {
        return this.name;
    }

    /**
     * Set the name property: The name property.
     *
     * @param name the name value to set.
     * @return the FilterBlobsItem object itself.
     */
    public FilterBlobsItem name(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get the containerName property: The containerName property.
     *
     * @return the containerName value.
     */
    public String containerName() {
        return this.containerName;
    }

    /**
     * Set the containerName property: The containerName property.
     *
     * @param containerName the containerName value to set.
     * @return the FilterBlobsItem object itself.
     */
    public FilterBlobsItem containerName(String containerName) {
        this.containerName = containerName;
        return this;
    }

    /**
     * Get the tagValue property: The tagValue property.
     *
     * @return the tagValue value.
     */
    public String tagValue() {
        return this.tagValue;
    }

    /**
     * Set the tagValue property: The tagValue property.
     *
     * @param tagValue the tagValue value to set.
     * @return the FilterBlobsItem object itself.
     */
    public FilterBlobsItem tagValue(String tagValue) {
        this.tagValue = tagValue;
        return this;
    }
}
