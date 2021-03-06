// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.management.sql;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/** The PartnerInfo model. */
@Fluent
public final class PartnerInfo {
    /*
     * Resource identifier of the partner server.
     */
    @JsonProperty(value = "id", required = true)
    private String id;

    /*
     * Geo location of the partner server.
     */
    @JsonProperty(value = "location", access = JsonProperty.Access.WRITE_ONLY)
    private String location;

    /*
     * Replication role of the partner server.
     */
    @JsonProperty(value = "replicationRole", access = JsonProperty.Access.WRITE_ONLY)
    private FailoverGroupReplicationRole replicationRole;

    /**
     * Get the id property: Resource identifier of the partner server.
     *
     * @return the id value.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Set the id property: Resource identifier of the partner server.
     *
     * @param id the id value to set.
     * @return the PartnerInfo object itself.
     */
    public PartnerInfo withId(String id) {
        this.id = id;
        return this;
    }

    /**
     * Get the location property: Geo location of the partner server.
     *
     * @return the location value.
     */
    public String location() {
        return this.location;
    }

    /**
     * Get the replicationRole property: Replication role of the partner server.
     *
     * @return the replicationRole value.
     */
    public FailoverGroupReplicationRole replicationRole() {
        return this.replicationRole;
    }
}
