"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.findAllJavaTypesForResponses = void 0;
const findJavaTypeForResponse_1 = require("./findJavaTypeForResponse");
/**
 * Seeks all the possible returns for the Java types
 * @param operations
 * @returns {string[]} The found types
 */
const findAllJavaTypesForResponses = (operations = []) => {
    const javaTypes = operations
        .flatMap((op) => op.path)
        .flatMap((path) => Object.values(path)) // To each method
        .map(findJavaTypeForResponse_1.findJavaTypeForResponse)
        .filter((javaType) => !!javaType);
    return [...new Set(javaTypes)];
};
exports.findAllJavaTypesForResponses = findAllJavaTypesForResponses;
exports.default = exports.findAllJavaTypesForResponses;
