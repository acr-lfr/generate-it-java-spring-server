"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.trim = void 0;
/**
 * Cleans all spaces before and after the text
 * @param str
 * @returns {string}
 */
const trim = (str = '') => str.replace(/^\s+|\s+$/g, '');
exports.trim = trim;
exports.default = exports.trim;
