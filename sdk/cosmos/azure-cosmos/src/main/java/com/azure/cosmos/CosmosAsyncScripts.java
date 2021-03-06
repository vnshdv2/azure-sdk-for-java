// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
package com.azure.cosmos;

import com.azure.cosmos.implementation.StoredProcedure;
import com.azure.cosmos.implementation.Trigger;
import com.azure.cosmos.implementation.UserDefinedFunction;
import com.azure.cosmos.models.CosmosAsyncStoredProcedureResponse;
import com.azure.cosmos.models.CosmosAsyncTriggerResponse;
import com.azure.cosmos.models.CosmosAsyncUserDefinedFunctionResponse;
import com.azure.cosmos.models.CosmosStoredProcedureProperties;
import com.azure.cosmos.models.CosmosStoredProcedureRequestOptions;
import com.azure.cosmos.models.CosmosTriggerProperties;
import com.azure.cosmos.models.CosmosUserDefinedFunctionProperties;
import com.azure.cosmos.models.FeedOptions;
import com.azure.cosmos.models.ModelBridgeInternal;
import com.azure.cosmos.models.SqlQuerySpec;
import com.azure.cosmos.util.CosmosPagedFlux;
import com.azure.cosmos.util.UtilBridgeInternal;
import reactor.core.publisher.Mono;

import static com.azure.cosmos.implementation.Utils.setContinuationTokenAndMaxItemCount;

/**
 * The type Cosmos async scripts. This contains async methods to operate on cosmos scripts like UDFs, StoredProcedures
 * and Triggers
 */
public class CosmosAsyncScripts {
    private final CosmosAsyncContainer container;
    private final CosmosAsyncDatabase database;

    CosmosAsyncScripts(CosmosAsyncContainer container) {
        this.container = container;
        this.database = container.getDatabase();
    }
    /* CosmosAsyncStoredProcedure operations */

    /**
     * Creates a cosmos stored procedure.
     * <p>
     * After subscription the operation will be performed.
     * The {@link Mono} upon successful completion will contain a single cosmos stored procedure response with the
     * created cosmos stored procedure.
     * In case of failure the {@link Mono} will error.
     *
     * @param properties the cosmos stored procedure properties.
     * @return an {@link Mono} containing the single cosmos stored procedure resource response or an error.
     */
    public Mono<CosmosAsyncStoredProcedureResponse> createStoredProcedure(CosmosStoredProcedureProperties properties) {
        return this.createStoredProcedure(properties, new CosmosStoredProcedureRequestOptions());
    }

    /**
     * Creates a cosmos stored procedure.
     * <p>
     * After subscription the operation will be performed.
     * The {@link Mono} upon successful completion will contain a single cosmos stored procedure response with the
     * created cosmos stored procedure.
     * In case of failure the {@link Mono} will error.
     *
     * @param properties the cosmos stored procedure properties.
     * @param options the stored procedure request options.
     * @return an {@link Mono} containing the single cosmos stored procedure resource response or an error.
     */
    public Mono<CosmosAsyncStoredProcedureResponse> createStoredProcedure(
        CosmosStoredProcedureProperties properties,
        CosmosStoredProcedureRequestOptions options) {
        if (options == null) {
            options = new CosmosStoredProcedureRequestOptions();
        }
        StoredProcedure sProc = new StoredProcedure();
        sProc.setId(properties.getId());
        sProc.setBody(properties.getBody());
        return database.getDocClientWrapper()
                   .createStoredProcedure(container.getLink(), sProc, ModelBridgeInternal.toRequestOptions(options))
                   .map(response -> ModelBridgeInternal.createCosmosAsyncStoredProcedureResponse(response, this.container))
                   .single();
    }

    /**
     * Reads all cosmos stored procedures in a container.
     * <p>
     * After subscription the operation will be performed.
     * The {@link CosmosPagedFlux} will contain one or several feed response pages of the read cosmos stored
     * procedure properties.
     * In case of failure the {@link CosmosPagedFlux} will error.
     *
     * @param options the feed options.
     * @return a {@link CosmosPagedFlux} containing one or several feed response pages of the read cosmos stored 
     * procedures
     * properties or an error.
     */
    public CosmosPagedFlux<CosmosStoredProcedureProperties> readAllStoredProcedures(FeedOptions options) {
        return UtilBridgeInternal.createCosmosPagedFlux(pagedFluxOptions -> {
            setContinuationTokenAndMaxItemCount(pagedFluxOptions, options);
            return database.getDocClientWrapper()
                       .readStoredProcedures(container.getLink(), options)
                       .map(response -> BridgeInternal.createFeedResponse(
                           ModelBridgeInternal.getCosmosStoredProcedurePropertiesFromV2Results(response.getResults()),
                           response.getResponseHeaders()));
        });
    }

    /**
     * Query for stored procedures in a container.
     * <p>
     * After subscription the operation will be performed.
     * The {@link CosmosPagedFlux} will contain one or several feed response pages of the obtained stored procedures.
     * In case of failure the {@link CosmosPagedFlux} will error.
     *
     * @param query the the query.
     * @param options the feed options.
     * @return a {@link CosmosPagedFlux} containing one or several feed response pages of the obtained stored 
     * procedures or
     * an error.
     */
    public CosmosPagedFlux<CosmosStoredProcedureProperties> queryStoredProcedures(
        String query,
        FeedOptions options) {
        return queryStoredProcedures(new SqlQuerySpec(query), options);
    }

    /**
     * Query for stored procedures in a container.
     * <p>
     * After subscription the operation will be performed.
     * The {@link CosmosPagedFlux} will contain one or several feed response pages of the obtained stored procedures.
     * In case of failure the {@link CosmosPagedFlux} will error.
     *
     * @param querySpec the SQL query specification.
     * @param options the feed options.
     * @return a {@link CosmosPagedFlux} containing one or several feed response pages of the obtained stored 
     * procedures or
     * an error.
     */
    public CosmosPagedFlux<CosmosStoredProcedureProperties> queryStoredProcedures(
        SqlQuerySpec querySpec,
        FeedOptions options) {
        return UtilBridgeInternal.createCosmosPagedFlux(pagedFluxOptions -> {
            setContinuationTokenAndMaxItemCount(pagedFluxOptions, options);
            return database.getDocClientWrapper()
                       .queryStoredProcedures(container.getLink(), querySpec, options)
                       .map(response -> BridgeInternal.createFeedResponse(
                           ModelBridgeInternal.getCosmosStoredProcedurePropertiesFromV2Results(response.getResults()),
                           response.getResponseHeaders()));
        });
    }

    /**
     * Gets a CosmosAsyncStoredProcedure object without making a service call
     *
     * @param id id of the stored procedure
     * @return a cosmos stored procedure
     */
    public CosmosAsyncStoredProcedure getStoredProcedure(String id) {
        return new CosmosAsyncStoredProcedure(id, this.container);
    }


    /* UDF Operations */

    /**
     * Creates a cosmos user defined function.
     * <p>
     * After subscription the operation will be performed.
     * The {@link Mono} upon successful completion will contain a single cosmos user defined function response.
     * In case of failure the {@link Mono} will error.
     *
     * @param properties the cosmos user defined function properties
     * @return an {@link Mono} containing the single resource response with the created user defined function or an
     * error.
     */
    public Mono<CosmosAsyncUserDefinedFunctionResponse> createUserDefinedFunction(
        CosmosUserDefinedFunctionProperties properties) {
        UserDefinedFunction udf = new UserDefinedFunction();
        udf.setId(properties.getId());
        udf.setBody(properties.getBody());

        return database.getDocClientWrapper()
                   .createUserDefinedFunction(container.getLink(), udf, null)
                   .map(response -> ModelBridgeInternal.createCosmosAsyncUserDefinedFunctionResponse(response, this.container)).single();
    }

    /**
     * Reads all cosmos user defined functions in the container
     * <p>
     * After subscription the operation will be performed.
     * The {@link CosmosPagedFlux} will contain one or several feed response pages of the read user defined functions.
     * In case of failure the {@link CosmosPagedFlux} will error.
     *
     * @param options the feed options.
     * @return a {@link CosmosPagedFlux} containing one or several feed response pages of the read user defined 
     * functions or an
     * error.
     */
    public CosmosPagedFlux<CosmosUserDefinedFunctionProperties> readAllUserDefinedFunctions(FeedOptions options) {
        return UtilBridgeInternal.createCosmosPagedFlux(pagedFluxOptions -> {
            setContinuationTokenAndMaxItemCount(pagedFluxOptions, options);
            return database.getDocClientWrapper()
                       .readUserDefinedFunctions(container.getLink(), options)
                       .map(response -> BridgeInternal.createFeedResponse(
                           ModelBridgeInternal.getCosmosUserDefinedFunctionPropertiesFromV2Results(response.getResults()),
                           response.getResponseHeaders()));
        });
    }

    /**
     * Query for user defined functions in the container.
     * <p>
     * After subscription the operation will be performed.
     * The {@link CosmosPagedFlux} will contain one or several feed response pages of the obtained user defined 
     * functions.
     * In case of failure the {@link CosmosPagedFlux} will error.
     *
     * @param query the query.
     * @param options the feed options.
     * @return a {@link CosmosPagedFlux} containing one or several feed response pages of the obtained user defined 
     * functions
     * or an error.
     */
    public CosmosPagedFlux<CosmosUserDefinedFunctionProperties> queryUserDefinedFunctions(
        String query,
        FeedOptions options) {
        return queryUserDefinedFunctions(new SqlQuerySpec(query), options);
    }

    /**
     * Query for user defined functions in the container.
     * <p>
     * After subscription the operation will be performed.
     * The {@link CosmosPagedFlux} will contain one or several feed response pages of the obtained user defined 
     * functions.
     * In case of failure the {@link CosmosPagedFlux} will error.
     *
     * @param querySpec the SQL query specification.
     * @param options the feed options.
     * @return a {@link CosmosPagedFlux} containing one or several feed response pages of the obtained user defined 
     * functions
     * or an error.
     */
    public CosmosPagedFlux<CosmosUserDefinedFunctionProperties> queryUserDefinedFunctions(
        SqlQuerySpec querySpec,
        FeedOptions options) {
        return UtilBridgeInternal.createCosmosPagedFlux(pagedFluxOptions -> {
            setContinuationTokenAndMaxItemCount(pagedFluxOptions, options);
            return database.getDocClientWrapper()
                       .queryUserDefinedFunctions(container.getLink(), querySpec, options)
                       .map(response -> BridgeInternal.createFeedResponse(
                           ModelBridgeInternal.getCosmosUserDefinedFunctionPropertiesFromV2Results(response.getResults()),
                           response.getResponseHeaders()));
        });
    }

    /**
     * Gets a CosmosAsyncUserDefinedFunction object without making a service call
     *
     * @param id id of the user defined function
     * @return a cosmos user defined function
     */
    public CosmosAsyncUserDefinedFunction getUserDefinedFunction(String id) {
        return new CosmosAsyncUserDefinedFunction(id, this.container);
    }

    /* Trigger Operations */

    /**
     * Creates a Cosmos trigger.
     * <p>
     * After subscription the operation will be performed.
     * The {@link Mono} upon successful completion will contain a cosmos trigger response
     * In case of failure the {@link Mono} will error.
     *
     * @param properties the cosmos trigger properties
     * @return an {@link Mono} containing the single resource response with the created trigger or an error.
     */
    public Mono<CosmosAsyncTriggerResponse> createTrigger(CosmosTriggerProperties properties) {
        Trigger trigger = new Trigger(ModelBridgeInternal.toJsonFromJsonSerializable(properties));

        return database.getDocClientWrapper()
                   .createTrigger(container.getLink(), trigger, null)
                   .map(response -> ModelBridgeInternal.createCosmosAsyncTriggerResponse(response, this.container))
                   .single();
    }

    /**
     * Reads all triggers in a container
     * <p>
     * After subscription the operation will be performed.
     * The {@link CosmosPagedFlux} will contain one or several feed response pages of the read cosmos trigger 
     * properties.
     * In case of failure the {@link CosmosPagedFlux} will error.
     *
     * @param options the feed options.
     * @return a {@link CosmosPagedFlux} containing one or several feed response pages of the read cosmos rigger 
     * properties or
     * an error.
     */
    public CosmosPagedFlux<CosmosTriggerProperties> readAllTriggers(FeedOptions options) {
        return UtilBridgeInternal.createCosmosPagedFlux(pagedFluxOptions -> {
            setContinuationTokenAndMaxItemCount(pagedFluxOptions, options);
            return database.getDocClientWrapper()
                       .readTriggers(container.getLink(), options)
                       .map(response -> BridgeInternal.createFeedResponse(
                           ModelBridgeInternal.getCosmosTriggerPropertiesFromV2Results(response.getResults()),
                           response.getResponseHeaders()));
        });
    }

    /**
     * Query for triggers in the container
     * <p>
     * After subscription the operation will be performed.
     * The {@link CosmosPagedFlux} will contain one or several feed response pages of the obtained triggers.
     * In case of failure the {@link CosmosPagedFlux} will error.
     *
     * @param query the query.
     * @param options the feed options.
     * @return a {@link CosmosPagedFlux} containing one or several feed response pages of the obtained triggers or an
     * error.
     */
    public CosmosPagedFlux<CosmosTriggerProperties> queryTriggers(String query, FeedOptions options) {
        return queryTriggers(new SqlQuerySpec(query), options);
    }

    /**
     * Query for triggers in the container
     * <p>
     * After subscription the operation will be performed.
     * The {@link CosmosPagedFlux} will contain one or several feed response pages of the obtained triggers.
     * In case of failure the {@link CosmosPagedFlux} will error.
     *
     * @param querySpec the SQL query specification.
     * @param options the feed options.
     * @return a {@link CosmosPagedFlux} containing one or several feed response pages of the obtained triggers or an
     * error.
     */
    public CosmosPagedFlux<CosmosTriggerProperties> queryTriggers(
        SqlQuerySpec querySpec,
        FeedOptions options) {
        return UtilBridgeInternal.createCosmosPagedFlux(pagedFluxOptions -> {
            setContinuationTokenAndMaxItemCount(pagedFluxOptions, options);
            return database.getDocClientWrapper()
                       .queryTriggers(container.getLink(), querySpec, options)
                       .map(response -> BridgeInternal.createFeedResponse(
                           ModelBridgeInternal.getCosmosTriggerPropertiesFromV2Results(response.getResults()),
                           response.getResponseHeaders()));
        });
    }

    /**
     * Gets a CosmosAsyncTrigger object without making a service call
     *
     * @param id id of the cosmos trigger
     * @return a cosmos trigger
     */
    public CosmosAsyncTrigger getTrigger(String id) {
        return new CosmosAsyncTrigger(id, this.container);
    }

}
