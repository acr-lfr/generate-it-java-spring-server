interface Path {
  'x-response-definitions': Record<string, any>;
}

/**
 * Seeks the 'x-response-definitions' to find a successful definition
 * @param path
 * @returns {*|string}
 */
export const findJavaTypeForResponse = (path: Path): string => {
  const definitions = path['x-response-definitions'] || {};
  const swaggerReturnDefinition = definitions['200'] || definitions['201'];

  return swaggerReturnDefinition || '';
};

export default findJavaTypeForResponse;
