"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.findJavaTypeForResponse = void 0;
/**
 * Seeks the 'x-response-definitions' to find a successful definition
 * @param path
 * @returns {*|string}
 */
const findJavaTypeForResponse = (path) => {
    const definitions = path['x-response-definitions'] || {};
    const swaggerReturnDefinition = definitions['200'] || definitions['201'];
    return swaggerReturnDefinition || '';
};
exports.findJavaTypeForResponse = findJavaTypeForResponse;
exports.default = exports.findJavaTypeForResponse;
