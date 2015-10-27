/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 * 
 * Code generated by Microsoft (R) AutoRest Code Generator 0.11.0.0
 * Changes may cause incorrect behavior and will be lost if the code is
 * regenerated.
 */

package fixtures.lro.models;

import com.microsoft.rest.Resource;
import com.microsoft.rest.SubResource;

/**
 * The OperationResult model.
 */
public class OperationResult {
    /**
     * The status of the request. Possible values for this property include:
     * 'Succeeded', 'Failed', 'canceled', 'Accepted', 'Creating', 'Created',
     * 'Updating', 'Updated', 'Deleting', 'Deleted', 'OK'.
     */
    private String status;

    /**
     * The error property.
     */
    private OperationResultError error;

    /**
     * Get the status value.
     *
     * @return the status value
     */ 
    public String getStatus() {
        return this.status;
    }

    /**
     * Set the status value.
     *
     * @param status the status value to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Get the error value.
     *
     * @return the error value
     */ 
    public OperationResultError getError() {
        return this.error;
    }

    /**
     * Set the error value.
     *
     * @param error the error value to set
     */
    public void setError(OperationResultError error) {
        this.error = error;
    }

}
