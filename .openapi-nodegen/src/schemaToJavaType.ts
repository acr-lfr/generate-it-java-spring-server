const mapSchemaToJava: Record<string, string> = {
  string: 'String',
  number: 'java.math.BigDecimal',
  integer: 'Integer',
  boolean: 'Boolean',
  object: 'Object',
};

interface Schema {
  type?: string;
  items?: Record<string, any>;
}

/**
 * Tries to find the type for a schema
 * @param schema
 * @returns {string}
 */
export const schemaToJavaType = (schema: Schema = {}): string => {
  const { type = '' } = schema;
  if (type === 'array') {
    const { items = {} } = schema;

    return `java.util.List<${schemaToJavaType(items)}>`;
  }

  return mapSchemaToJava[type] || '';
};

export default schemaToJavaType;
