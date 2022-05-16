import * as Schema from 'generate-it/build/interfaces';
import { findJavaTypeForResponse } from './findJavaTypeForResponse';

/**
 * Seeks all the possible returns for the Java types
 * @param operations
 * @returns {string[]} The found types
 */
export const findAllJavaTypesForResponses = (operations: Schema.Operations = []) => {
  const javaTypes = operations
    .flatMap((op) => op.path)
    .flatMap((path) => Object.values(path)) // To each method
    .map(findJavaTypeForResponse)
    .filter((javaType) => !!javaType);

  return [...new Set(javaTypes)];
};

export default findAllJavaTypesForResponses;
