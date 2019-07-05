/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.resourcehealth.v2015_01_01;

import com.microsoft.azure.arm.resources.collection.SupportsListingByResourceGroup;
import com.microsoft.azure.arm.collection.SupportsListing;
import rx.Observable;
import com.microsoft.azure.management.resourcehealth.v2015_01_01.implementation.AvailabilityStatusesInner;
import com.microsoft.azure.arm.model.HasInner;

/**
 * Type representing AvailabilityStatuses.
 */
public interface AvailabilityStatuses extends SupportsListingByResourceGroup<AvailabilityStatus>, SupportsListing<AvailabilityStatus>, HasInner<AvailabilityStatusesInner> {
    /**
     * Gets current availability status for a single resource.
     *
     * @param resourceUri The fully qualified ID of the resource, including the resource name and resource type. Currently the API support not nested and one nesting level resource types : /subscriptions/{subscriptionId}/resourceGroups/{resource-group-name}/providers/{resource-provider-name}/{resource-type}/{resource-name} and /subscriptions/{subscriptionId}/resourceGroups/{resourceGroupName}/providers/{resource-provider-name}/{parentResourceType}/{parentResourceName}/{resourceType}/{resourceName}
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable for the request
     */
    Observable<AvailabilityStatus> getByResourceAsync(String resourceUri);

    /**
     * Lists the historical availability statuses for a single resource. Use the nextLink property in the response to get the next page of availability status.
     *
     * @param resourceUri The fully qualified ID of the resource, including the resource name and resource type. Currently the API support not nested and one nesting level resource types : /subscriptions/{subscriptionId}/resourceGroups/{resource-group-name}/providers/{resource-provider-name}/{resource-type}/{resource-name} and /subscriptions/{subscriptionId}/resourceGroups/{resourceGroupName}/providers/{resource-provider-name}/{parentResourceType}/{parentResourceName}/{resourceType}/{resourceName}
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable for the request
     */
    Observable<AvailabilityStatus> list1Async(final String resourceUri);

}