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
import type { IntroductorySettings } from './IntroductorySettings';
import {
    IntroductorySettingsFromJSON,
    IntroductorySettingsFromJSONTyped,
    IntroductorySettingsToJSON,
    IntroductorySettingsToJSONTyped,
} from './IntroductorySettings';

/**
 * 
 * @export
 * @interface GetIntroductorySettingsResponse
 */
export interface GetIntroductorySettingsResponse {
    /**
     * 
     * @type {IntroductorySettings}
     * @memberof GetIntroductorySettingsResponse
     */
    introductorySettings: IntroductorySettings;
}

/**
 * Check if a given object implements the GetIntroductorySettingsResponse interface.
 */
export function instanceOfGetIntroductorySettingsResponse(value: object): value is GetIntroductorySettingsResponse {
    if (!('introductorySettings' in value) || value['introductorySettings'] === undefined) return false;
    return true;
}

export function GetIntroductorySettingsResponseFromJSON(json: any): GetIntroductorySettingsResponse {
    return GetIntroductorySettingsResponseFromJSONTyped(json, false);
}

export function GetIntroductorySettingsResponseFromJSONTyped(json: any, ignoreDiscriminator: boolean): GetIntroductorySettingsResponse {
    if (json == null) {
        return json;
    }
    return {
        
        'introductorySettings': IntroductorySettingsFromJSON(json['introductorySettings']),
    };
}

export function GetIntroductorySettingsResponseToJSON(json: any): GetIntroductorySettingsResponse {
    return GetIntroductorySettingsResponseToJSONTyped(json, false);
}

export function GetIntroductorySettingsResponseToJSONTyped(value?: GetIntroductorySettingsResponse | null, ignoreDiscriminator: boolean = false): any {
    if (value == null) {
        return value;
    }

    return {
        
        'introductorySettings': IntroductorySettingsToJSON(value['introductorySettings']),
    };
}

