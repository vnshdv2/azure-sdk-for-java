// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.management.graphrbac.models;

import com.azure.core.annotation.Fluent;
import com.azure.management.graphrbac.ConsentType;
import com.fasterxml.jackson.annotation.JsonProperty;

/** The OAuth2PermissionGrant model. */
@Fluent
public final class OAuth2PermissionGrantInner {
    /*
     * Microsoft.DirectoryServices.OAuth2PermissionGrant
     */
    @JsonProperty(value = "odata.type")
    private String odataType;

    /*
     * The id of the resource's service principal granted consent to
     * impersonate the user when accessing the resource (represented by the
     * resourceId property).
     */
    @JsonProperty(value = "clientId")
    private String clientId;

    /*
     * The id of the permission grant
     */
    @JsonProperty(value = "objectId")
    private String objectId;

    /*
     * Indicates if consent was provided by the administrator (on behalf of the
     * organization) or by an individual.
     */
    @JsonProperty(value = "consentType")
    private ConsentType consentType;

    /*
     * When consent type is Principal, this property specifies the id of the
     * user that granted consent and applies only for that user.
     */
    @JsonProperty(value = "principalId")
    private String principalId;

    /*
     * Object Id of the resource you want to grant
     */
    @JsonProperty(value = "resourceId")
    private String resourceId;

    /*
     * Specifies the value of the scope claim that the resource application
     * should expect in the OAuth 2.0 access token. For example, User.Read
     */
    @JsonProperty(value = "scope")
    private String scope;

    /*
     * Start time for TTL
     */
    @JsonProperty(value = "startTime")
    private String startTime;

    /*
     * Expiry time for TTL
     */
    @JsonProperty(value = "expiryTime")
    private String expiryTime;

    /**
     * Get the odataType property: Microsoft.DirectoryServices.OAuth2PermissionGrant.
     *
     * @return the odataType value.
     */
    public String odataType() {
        return this.odataType;
    }

    /**
     * Set the odataType property: Microsoft.DirectoryServices.OAuth2PermissionGrant.
     *
     * @param odataType the odataType value to set.
     * @return the OAuth2PermissionGrantInner object itself.
     */
    public OAuth2PermissionGrantInner withOdataType(String odataType) {
        this.odataType = odataType;
        return this;
    }

    /**
     * Get the clientId property: The id of the resource's service principal granted consent to impersonate the user
     * when accessing the resource (represented by the resourceId property).
     *
     * @return the clientId value.
     */
    public String clientId() {
        return this.clientId;
    }

    /**
     * Set the clientId property: The id of the resource's service principal granted consent to impersonate the user
     * when accessing the resource (represented by the resourceId property).
     *
     * @param clientId the clientId value to set.
     * @return the OAuth2PermissionGrantInner object itself.
     */
    public OAuth2PermissionGrantInner withClientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    /**
     * Get the objectId property: The id of the permission grant.
     *
     * @return the objectId value.
     */
    public String objectId() {
        return this.objectId;
    }

    /**
     * Set the objectId property: The id of the permission grant.
     *
     * @param objectId the objectId value to set.
     * @return the OAuth2PermissionGrantInner object itself.
     */
    public OAuth2PermissionGrantInner withObjectId(String objectId) {
        this.objectId = objectId;
        return this;
    }

    /**
     * Get the consentType property: Indicates if consent was provided by the administrator (on behalf of the
     * organization) or by an individual.
     *
     * @return the consentType value.
     */
    public ConsentType consentType() {
        return this.consentType;
    }

    /**
     * Set the consentType property: Indicates if consent was provided by the administrator (on behalf of the
     * organization) or by an individual.
     *
     * @param consentType the consentType value to set.
     * @return the OAuth2PermissionGrantInner object itself.
     */
    public OAuth2PermissionGrantInner withConsentType(ConsentType consentType) {
        this.consentType = consentType;
        return this;
    }

    /**
     * Get the principalId property: When consent type is Principal, this property specifies the id of the user that
     * granted consent and applies only for that user.
     *
     * @return the principalId value.
     */
    public String principalId() {
        return this.principalId;
    }

    /**
     * Set the principalId property: When consent type is Principal, this property specifies the id of the user that
     * granted consent and applies only for that user.
     *
     * @param principalId the principalId value to set.
     * @return the OAuth2PermissionGrantInner object itself.
     */
    public OAuth2PermissionGrantInner withPrincipalId(String principalId) {
        this.principalId = principalId;
        return this;
    }

    /**
     * Get the resourceId property: Object Id of the resource you want to grant.
     *
     * @return the resourceId value.
     */
    public String resourceId() {
        return this.resourceId;
    }

    /**
     * Set the resourceId property: Object Id of the resource you want to grant.
     *
     * @param resourceId the resourceId value to set.
     * @return the OAuth2PermissionGrantInner object itself.
     */
    public OAuth2PermissionGrantInner withResourceId(String resourceId) {
        this.resourceId = resourceId;
        return this;
    }

    /**
     * Get the scope property: Specifies the value of the scope claim that the resource application should expect in the
     * OAuth 2.0 access token. For example, User.Read.
     *
     * @return the scope value.
     */
    public String scope() {
        return this.scope;
    }

    /**
     * Set the scope property: Specifies the value of the scope claim that the resource application should expect in the
     * OAuth 2.0 access token. For example, User.Read.
     *
     * @param scope the scope value to set.
     * @return the OAuth2PermissionGrantInner object itself.
     */
    public OAuth2PermissionGrantInner withScope(String scope) {
        this.scope = scope;
        return this;
    }

    /**
     * Get the startTime property: Start time for TTL.
     *
     * @return the startTime value.
     */
    public String startTime() {
        return this.startTime;
    }

    /**
     * Set the startTime property: Start time for TTL.
     *
     * @param startTime the startTime value to set.
     * @return the OAuth2PermissionGrantInner object itself.
     */
    public OAuth2PermissionGrantInner withStartTime(String startTime) {
        this.startTime = startTime;
        return this;
    }

    /**
     * Get the expiryTime property: Expiry time for TTL.
     *
     * @return the expiryTime value.
     */
    public String expiryTime() {
        return this.expiryTime;
    }

    /**
     * Set the expiryTime property: Expiry time for TTL.
     *
     * @param expiryTime the expiryTime value to set.
     * @return the OAuth2PermissionGrantInner object itself.
     */
    public OAuth2PermissionGrantInner withExpiryTime(String expiryTime) {
        this.expiryTime = expiryTime;
        return this;
    }
}
