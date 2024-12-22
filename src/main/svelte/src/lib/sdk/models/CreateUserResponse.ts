/* tslint:disable */
/* eslint-disable */
/**
 * OpenAPI definition
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * The version of the OpenAPI document: v0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

import { mapValues } from '../runtime';
/**
 * 
 * @export
 * @interface CreateUserResponse
 */
export interface CreateUserResponse {
    /**
     * 
     * @type {string}
     * @memberof CreateUserResponse
     */
    message?: string;
    /**
     * 
     * @type {string}
     * @memberof CreateUserResponse
     */
    userId?: string;
}

/**
 * Check if a given object implements the CreateUserResponse interface.
 */
export function instanceOfCreateUserResponse(value: object): value is CreateUserResponse {
    return true;
}

export function CreateUserResponseFromJSON(json: any): CreateUserResponse {
    return CreateUserResponseFromJSONTyped(json, false);
}

export function CreateUserResponseFromJSONTyped(json: any, ignoreDiscriminator: boolean): CreateUserResponse {
    if (json == null) {
        return json;
    }
    return {
        
        'message': json['message'] == null ? undefined : json['message'],
        'userId': json['userId'] == null ? undefined : json['userId'],
    };
}

export function CreateUserResponseToJSON(json: any): CreateUserResponse {
    return CreateUserResponseToJSONTyped(json, false);
}

export function CreateUserResponseToJSONTyped(value?: CreateUserResponse | null, ignoreDiscriminator: boolean = false): any {
    if (value == null) {
        return value;
    }

    return {
        
        'message': value['message'],
        'userId': value['userId'],
    };
}
