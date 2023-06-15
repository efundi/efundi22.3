/** @typedef {import('../../types/LocalizeMixinTypes').DatePostProcessor} DatePostProcessor */
/**
 * Formats date based on locale and options
 *
 * @param {Date} date
 * @param {import('@lion/localize/types/LocalizeMixinTypes').FormatDateOptions} [options] Intl options are available
 * @returns {string}
 */
export function formatDate(date: Date, options?: import("../../types/LocalizeMixinTypes.js").FormatDateOptions | undefined): string;
export type DatePostProcessor = typeof import("../../types/LocalizeMixinTypes.js").DatePostProcessorImplementation;
