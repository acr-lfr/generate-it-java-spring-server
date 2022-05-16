const regex = /:(\w+)(?=\/|$)/gm;

/**
 * Transforms the pattern ':param' into '{param}'
 * @param str the path, for example '/vehicles/:vin/:cbs'
 * @returns {string}
 */
export const swaggerToSpringPath = (str = ''): string => str.replace(regex, '{$1}');

export default swaggerToSpringPath;
