/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.appservice.v2016_08_01.implementation;

import com.microsoft.azure.management.appservice.v2016_08_01.PremierAddOn;
import com.microsoft.azure.arm.model.implementation.CreatableUpdatableImpl;
import rx.Observable;
import java.util.Map;

class PremierAddOnImpl extends CreatableUpdatableImpl<PremierAddOn, PremierAddOnInner, PremierAddOnImpl> implements PremierAddOn, PremierAddOn.Definition, PremierAddOn.Update {
    private final AppServiceManager manager;
    private String resourceGroupName;
    private String name;
    private String premierAddOnName;

    PremierAddOnImpl(String name, AppServiceManager manager) {
        super(name, new PremierAddOnInner());
        this.manager = manager;
        // Set resource name
        this.premierAddOnName = name;
        //
    }

    PremierAddOnImpl(PremierAddOnInner inner, AppServiceManager manager) {
        super(inner.name(), inner);
        this.manager = manager;
        // Set resource name
        this.premierAddOnName = inner.name();
        // set resource ancestor and positional variables
        this.resourceGroupName = IdParsingUtils.getValueFromIdByName(inner.id(), "resourceGroups");
        this.name = IdParsingUtils.getValueFromIdByName(inner.id(), "sites");
        this.premierAddOnName = IdParsingUtils.getValueFromIdByName(inner.id(), "premieraddons");
        //
    }

    @Override
    public AppServiceManager manager() {
        return this.manager;
    }

    @Override
    public Observable<PremierAddOn> createResourceAsync() {
        WebAppsInner client = this.manager().inner().webApps();
        return client.addPremierAddOnAsync(this.resourceGroupName, this.name, this.premierAddOnName, this.inner())
            .map(innerToFluentMap(this));
    }

    @Override
    public Observable<PremierAddOn> updateResourceAsync() {
        WebAppsInner client = this.manager().inner().webApps();
        return client.addPremierAddOnAsync(this.resourceGroupName, this.name, this.premierAddOnName, this.inner())
            .map(innerToFluentMap(this));
    }

    @Override
    protected Observable<PremierAddOnInner> getInnerAsync() {
        WebAppsInner client = this.manager().inner().webApps();
        return client.getPremierAddOnAsync(this.resourceGroupName, this.name, this.premierAddOnName);
    }

    @Override
    public boolean isInCreateMode() {
        return this.inner().id() == null;
    }


    @Override
    public String id() {
        return this.inner().id();
    }

    @Override
    public String kind() {
        return this.inner().kind();
    }

    @Override
    public String location() {
        return this.inner().location();
    }

    @Override
    public String marketplaceOffer() {
        return this.inner().marketplaceOffer();
    }

    @Override
    public String marketplacePublisher() {
        return this.inner().marketplacePublisher();
    }

    @Override
    public String name() {
        return this.inner().name();
    }

    @Override
    public String premierAddOnLocation() {
        return this.inner().premierAddOnLocation();
    }

    @Override
    public String premierAddOnName() {
        return this.inner().premierAddOnName();
    }

    @Override
    public Map<String, String> premierAddOnTags() {
        return this.inner().premierAddOnTags();
    }

    @Override
    public String product() {
        return this.inner().product();
    }

    @Override
    public String sku() {
        return this.inner().sku();
    }

    @Override
    public Map<String, String> tags() {
        return this.inner().getTags();
    }

    @Override
    public String type() {
        return this.inner().type();
    }

    @Override
    public String vendor() {
        return this.inner().vendor();
    }

    @Override
    public PremierAddOnImpl withExistingSite(String resourceGroupName, String name) {
        this.resourceGroupName = resourceGroupName;
        this.name = name;
        return this;
    }

    @Override
    public PremierAddOnImpl withLocation(String location) {
        this.inner().withLocation(location);
        return this;
    }

    @Override
    public PremierAddOnImpl withKind(String kind) {
        this.inner().withKind(kind);
        return this;
    }

    @Override
    public PremierAddOnImpl withMarketplaceOffer(String marketplaceOffer) {
        this.inner().withMarketplaceOffer(marketplaceOffer);
        return this;
    }

    @Override
    public PremierAddOnImpl withMarketplacePublisher(String marketplacePublisher) {
        this.inner().withMarketplacePublisher(marketplacePublisher);
        return this;
    }

    @Override
    public PremierAddOnImpl withPremierAddOnLocation(String premierAddOnLocation) {
        this.inner().withPremierAddOnLocation(premierAddOnLocation);
        return this;
    }

    @Override
    public PremierAddOnImpl withPremierAddOnName(String premierAddOnName) {
        this.inner().withPremierAddOnName(premierAddOnName);
        return this;
    }

    @Override
    public PremierAddOnImpl withPremierAddOnTags(Map<String, String> premierAddOnTags) {
        this.inner().withPremierAddOnTags(premierAddOnTags);
        return this;
    }

    @Override
    public PremierAddOnImpl withProduct(String product) {
        this.inner().withProduct(product);
        return this;
    }

    @Override
    public PremierAddOnImpl withSku(String sku) {
        this.inner().withSku(sku);
        return this;
    }

    @Override
    public PremierAddOnImpl withTags(Map<String, String> tags) {
        this.inner().withTags(tags);
        return this;
    }

    @Override
    public PremierAddOnImpl withVendor(String vendor) {
        this.inner().withVendor(vendor);
        return this;
    }

}
