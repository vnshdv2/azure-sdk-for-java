/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.eventhubs.v2015_08_01.implementation;

import com.microsoft.azure.arm.resources.models.implementation.GroupableResourceCoreImpl;
import com.microsoft.azure.management.eventhubs.v2015_08_01.NamespaceResource;
import rx.Observable;
import com.microsoft.azure.management.eventhubs.v2015_08_01.NamespaceUpdateParameter;
import com.microsoft.azure.management.eventhubs.v2015_08_01.NamespaceCreateOrUpdateParameters;
import org.joda.time.DateTime;
import com.microsoft.azure.management.eventhubs.v2015_08_01.Sku;
import com.microsoft.azure.management.eventhubs.v2015_08_01.NamespaceState;
import rx.functions.Func1;

class NamespaceResourceImpl extends GroupableResourceCoreImpl<NamespaceResource, NamespaceResourceInner, NamespaceResourceImpl, EventHubsManager> implements NamespaceResource, NamespaceResource.Definition, NamespaceResource.Update {
    private NamespaceCreateOrUpdateParameters createParameter;
    private NamespaceUpdateParameter updateParameter;
    NamespaceResourceImpl(String name, NamespaceResourceInner inner, EventHubsManager manager) {
        super(name, inner, manager);
        this.createParameter = new NamespaceCreateOrUpdateParameters();
        this.updateParameter = new NamespaceUpdateParameter();
    }

    @Override
    public Observable<NamespaceResource> createResourceAsync() {
        NamespacesInner client = this.manager().inner().namespaces();
        this.createParameter.withLocation(inner().location());
        this.createParameter.withTags(inner().getTags());
        return client.createOrUpdateAsync(this.resourceGroupName(), this.name(), this.createParameter)
            .map(new Func1<NamespaceResourceInner, NamespaceResourceInner>() {
               @Override
               public NamespaceResourceInner call(NamespaceResourceInner resource) {
                   resetCreateUpdateParameters();
                   return resource;
               }
            })
            .map(innerToFluentMap(this));
    }

    @Override
    public Observable<NamespaceResource> updateResourceAsync() {
        NamespacesInner client = this.manager().inner().namespaces();
        return client.updateAsync(this.resourceGroupName(), this.name(), this.updateParameter)
            .map(new Func1<NamespaceResourceInner, NamespaceResourceInner>() {
               @Override
               public NamespaceResourceInner call(NamespaceResourceInner resource) {
                   resetCreateUpdateParameters();
                   return resource;
               }
            })
            .map(innerToFluentMap(this));
    }

    @Override
    protected Observable<NamespaceResourceInner> getInnerAsync() {
        NamespacesInner client = this.manager().inner().namespaces();
        return client.getByResourceGroupAsync(this.resourceGroupName(), this.name());
    }

    @Override
    public boolean isInCreateMode() {
        return this.inner().id() == null;
    }

    private void resetCreateUpdateParameters() {
        this.createParameter = new NamespaceCreateOrUpdateParameters();
        this.updateParameter = new NamespaceUpdateParameter();
    }

    @Override
    public DateTime createdAt() {
        return this.inner().createdAt();
    }

    @Override
    public Boolean enabled() {
        return this.inner().enabled();
    }

    @Override
    public String metricId() {
        return this.inner().metricId();
    }

    @Override
    public String provisioningState() {
        return this.inner().provisioningState();
    }

    @Override
    public String serviceBusEndpoint() {
        return this.inner().serviceBusEndpoint();
    }

    @Override
    public Sku sku() {
        return this.inner().sku();
    }

    @Override
    public NamespaceState status() {
        return this.inner().status();
    }

    @Override
    public DateTime updatedAt() {
        return this.inner().updatedAt();
    }

    @Override
    public NamespaceResourceImpl withCreatedAt(DateTime createdAt) {
        this.createParameter.withCreatedAt(createdAt);
        return this;
    }

    @Override
    public NamespaceResourceImpl withEnabled(Boolean enabled) {
        this.createParameter.withEnabled(enabled);
        return this;
    }

    @Override
    public NamespaceResourceImpl withProvisioningState(String provisioningState) {
        this.createParameter.withProvisioningState(provisioningState);
        return this;
    }

    @Override
    public NamespaceResourceImpl withServiceBusEndpoint(String serviceBusEndpoint) {
        this.createParameter.withServiceBusEndpoint(serviceBusEndpoint);
        return this;
    }

    @Override
    public NamespaceResourceImpl withStatus(NamespaceState status) {
        this.createParameter.withStatus(status);
        return this;
    }

    @Override
    public NamespaceResourceImpl withUpdatedAt(DateTime updatedAt) {
        this.createParameter.withUpdatedAt(updatedAt);
        return this;
    }

    @Override
    public NamespaceResourceImpl withSku(Sku sku) {
        if (isInCreateMode()) {
            this.createParameter.withSku(sku);
        } else {
            this.updateParameter.withSku(sku);
        }
        return this;
    }

}
