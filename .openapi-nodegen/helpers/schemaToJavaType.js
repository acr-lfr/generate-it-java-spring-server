"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.schemaToJavaType = void 0;
const mapSchemaToJava = {
    string: 'String',
    number: 'java.math.BigDecimal',
    integer: 'Integer',
    boolean: 'Boolean',
    object: 'Object',
};
/**
 * Tries to find the type for a schema
 * @param schema
 * @returns {string}
 */
const schemaToJavaType = (schema = {}) => {
    const { type = '' } = schema;
    if (type === 'array') {
        const { items = {} } = schema;
        return `java.util.List<${(0, exports.schemaToJavaType)(items)}>`;
    }
    return mapSchemaToJava[type] || '';
};
exports.schemaToJavaType = schemaToJavaType;
exports.default = exports.schemaToJavaType;
