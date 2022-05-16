import * as Schema from 'generate-it/build/interfaces';
import { findJavaTypeForBody } from './findJavaTypeForBody';

/**
 * Seeks all the possible Java types for bodies
 * @param operations
 * @returns {string[]} The found types
 */
export const findAllJavaTypesForBodies = (operations: Schema.Operations = []) => {
  const javaTypes = operations
    .flatMap((op) => op.path)
    .flatMap((path) => Object.values(path)) // To each method
    .filter((method) => method.requestBody && method['x-request-definitions'])
    .map((method) => findJavaTypeForBody(method))
    .filter((javaType) => !!javaType);

  return [...new Set(javaTypes)];
};

export default findAllJavaTypesForBodies;
