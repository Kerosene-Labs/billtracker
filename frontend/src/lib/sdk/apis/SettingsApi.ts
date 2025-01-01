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


import * as runtime from '../runtime';
import type {
  GetIntroductorySettingsResponse,
  SetIntroductorySettingsRequest,
} from '../models/index';
import {
    GetIntroductorySettingsResponseFromJSON,
    GetIntroductorySettingsResponseToJSON,
    SetIntroductorySettingsRequestFromJSON,
    SetIntroductorySettingsRequestToJSON,
} from '../models/index';

export interface SetIntroductionsRequest {
    setIntroductorySettingsRequest: SetIntroductorySettingsRequest;
}

/**
 * 
 */
export class SettingsApi extends runtime.BaseAPI {

    /**
     */
    async getIntroductionsRaw(initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<runtime.ApiResponse<GetIntroductorySettingsResponse>> {
        const queryParameters: any = {};

        const headerParameters: runtime.HTTPHeaders = {};

        if (this.configuration && this.configuration.accessToken) {
            const token = this.configuration.accessToken;
            const tokenString = await token("bearerAuth", []);

            if (tokenString) {
                headerParameters["Authorization"] = `Bearer ${tokenString}`;
            }
        }
        const response = await this.request({
            path: `/settings/introductions`,
            method: 'GET',
            headers: headerParameters,
            query: queryParameters,
        }, initOverrides);

        return new runtime.JSONApiResponse(response, (jsonValue) => GetIntroductorySettingsResponseFromJSON(jsonValue));
    }

    /**
     */
    async getIntroductions(initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<GetIntroductorySettingsResponse> {
        const response = await this.getIntroductionsRaw(initOverrides);
        return await response.value();
    }

    /**
     */
    async setIntroductionsRaw(requestParameters: SetIntroductionsRequest, initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<runtime.ApiResponse<void>> {
        if (requestParameters['setIntroductorySettingsRequest'] == null) {
            throw new runtime.RequiredError(
                'setIntroductorySettingsRequest',
                'Required parameter "setIntroductorySettingsRequest" was null or undefined when calling setIntroductions().'
            );
        }

        const queryParameters: any = {};

        const headerParameters: runtime.HTTPHeaders = {};

        headerParameters['Content-Type'] = 'application/json';

        if (this.configuration && this.configuration.accessToken) {
            const token = this.configuration.accessToken;
            const tokenString = await token("bearerAuth", []);

            if (tokenString) {
                headerParameters["Authorization"] = `Bearer ${tokenString}`;
            }
        }
        const response = await this.request({
            path: `/settings/introductions`,
            method: 'PUT',
            headers: headerParameters,
            query: queryParameters,
            body: SetIntroductorySettingsRequestToJSON(requestParameters['setIntroductorySettingsRequest']),
        }, initOverrides);

        return new runtime.VoidApiResponse(response);
    }

    /**
     */
    async setIntroductions(requestParameters: SetIntroductionsRequest, initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<void> {
        await this.setIntroductionsRaw(requestParameters, initOverrides);
    }

}