"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.findAllJavaTypesForBodies = void 0;
const findJavaTypeForBody_1 = require("./findJavaTypeForBody");
/**
 * Seeks all the possible Java types for bodies
 * @param operations
 * @returns {string[]} The found types
 */
const findAllJavaTypesForBodies = (operations = []) => {
    const javaTypes = operations
        .flatMap((op) => op.path)
        .flatMap((path) => Object.values(path)) // To each method
        .filter((method) => method.requestBody && method['x-request-definitions'])
        .map((method) => (0, findJavaTypeForBody_1.findJavaTypeForBody)(method))
        .filter((javaType) => !!javaType);
    return [...new Set(javaTypes)];
};
exports.findAllJavaTypesForBodies = findAllJavaTypesForBodies;
exports.default = exports.findAllJavaTypesForBodies;
