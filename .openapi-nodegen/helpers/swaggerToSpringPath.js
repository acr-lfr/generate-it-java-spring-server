"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.swaggerToSpringPath = void 0;
const regex = /:(\w+)(?=\/|$)/gm;
/**
 * Transforms the pattern ':param' into '{param}'
 * @param str the path, for example '/vehicles/:vin/:cbs'
 * @returns {string}
 */
const swaggerToSpringPath = (str = '') => str.replace(regex, '{$1}');
exports.swaggerToSpringPath = swaggerToSpringPath;
exports.default = exports.swaggerToSpringPath;
