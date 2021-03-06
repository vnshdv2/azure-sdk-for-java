// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.management.compute.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/** The SnapshotList model. */
@Fluent
public final class SnapshotListInner {
    /*
     * A list of snapshots.
     */
    @JsonProperty(value = "value", required = true)
    private List<SnapshotInner> value;

    /*
     * The uri to fetch the next page of snapshots. Call ListNext() with this
     * to fetch the next page of snapshots.
     */
    @JsonProperty(value = "nextLink")
    private String nextLink;

    /**
     * Get the value property: A list of snapshots.
     *
     * @return the value value.
     */
    public List<SnapshotInner> value() {
        return this.value;
    }

    /**
     * Set the value property: A list of snapshots.
     *
     * @param value the value value to set.
     * @return the SnapshotListInner object itself.
     */
    public SnapshotListInner withValue(List<SnapshotInner> value) {
        this.value = value;
        return this;
    }

    /**
     * Get the nextLink property: The uri to fetch the next page of snapshots. Call ListNext() with this to fetch the
     * next page of snapshots.
     *
     * @return the nextLink value.
     */
    public String nextLink() {
        return this.nextLink;
    }

    /**
     * Set the nextLink property: The uri to fetch the next page of snapshots. Call ListNext() with this to fetch the
     * next page of snapshots.
     *
     * @param nextLink the nextLink value to set.
     * @return the SnapshotListInner object itself.
     */
    public SnapshotListInner withNextLink(String nextLink) {
        this.nextLink = nextLink;
        return this;
    }
}
