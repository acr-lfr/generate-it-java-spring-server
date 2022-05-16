/**
 * Cleans all spaces before and after the text
 * @param str
 * @returns {string}
 */
export const trim = (str = ''): string => str.replace(/^\s+|\s+$/g, '');

export default trim;
