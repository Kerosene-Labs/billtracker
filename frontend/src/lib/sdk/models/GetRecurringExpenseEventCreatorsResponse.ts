/* tslint:disable */
/* eslint-disable */
/**
 * API Documentation
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * The version of the OpenAPI document: 1.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

import { mapValues } from '../runtime';
import type { RecurringExpenseEventCreator } from './RecurringExpenseEventCreator';
import {
    RecurringExpenseEventCreatorFromJSON,
    RecurringExpenseEventCreatorFromJSONTyped,
    RecurringExpenseEventCreatorToJSON,
    RecurringExpenseEventCreatorToJSONTyped,
} from './RecurringExpenseEventCreator';

/**
 * 
 * @export
 * @interface GetRecurringExpenseEventCreatorsResponse
 */
export interface GetRecurringExpenseEventCreatorsResponse {
    /**
     * 
     * @type {Array<RecurringExpenseEventCreator>}
     * @memberof GetRecurringExpenseEventCreatorsResponse
     */
    recurringExpenseEventCreators: Array<RecurringExpenseEventCreator>;
}

/**
 * Check if a given object implements the GetRecurringExpenseEventCreatorsResponse interface.
 */
export function instanceOfGetRecurringExpenseEventCreatorsResponse(value: object): value is GetRecurringExpenseEventCreatorsResponse {
    if (!('recurringExpenseEventCreators' in value) || value['recurringExpenseEventCreators'] === undefined) return false;
    return true;
}

export function GetRecurringExpenseEventCreatorsResponseFromJSON(json: any): GetRecurringExpenseEventCreatorsResponse {
    return GetRecurringExpenseEventCreatorsResponseFromJSONTyped(json, false);
}

export function GetRecurringExpenseEventCreatorsResponseFromJSONTyped(json: any, ignoreDiscriminator: boolean): GetRecurringExpenseEventCreatorsResponse {
    if (json == null) {
        return json;
    }
    return {
        
        'recurringExpenseEventCreators': ((json['recurringExpenseEventCreators'] as Array<any>).map(RecurringExpenseEventCreatorFromJSON)),
    };
}

export function GetRecurringExpenseEventCreatorsResponseToJSON(json: any): GetRecurringExpenseEventCreatorsResponse {
    return GetRecurringExpenseEventCreatorsResponseToJSONTyped(json, false);
}

export function GetRecurringExpenseEventCreatorsResponseToJSONTyped(value?: GetRecurringExpenseEventCreatorsResponse | null, ignoreDiscriminator: boolean = false): any {
    if (value == null) {
        return value;
    }

    return {
        
        'recurringExpenseEventCreators': ((value['recurringExpenseEventCreators'] as Array<any>).map(RecurringExpenseEventCreatorToJSON)),
    };
}

