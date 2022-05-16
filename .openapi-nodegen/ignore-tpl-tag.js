/**
 * Override template tag checking - generate-it will load this same class from require.cache instead of disk.
 * This prevents needing to re-tag the template every time generate-it updates, even if nothing would change.
 */
const { default: templateFetch } = require('./node_modules/generate-it/build/lib/template/TemplateFetch');
templateFetch.logTagWarning = () => null;
