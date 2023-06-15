/** @typedef {import('../../types/LocalizeMixinTypes').NumberPostProcessor} NumberPostProcessor */
/**
 * Formats a number based on locale and options. It uses Intl for the formatting.
 *
 * @typedef {import('../../types/LocalizeMixinTypes').FormatNumberPart} FormatNumberPart
 * @typedef {import('@lion/localize/types/LocalizeMixinTypes').FormatNumberOptions} FormatOptions
 * @param {number} number Number to be formatted
 * @param {FormatOptions} [options] Intl options are available extended by roundMode and returnIfNaN
 * @returns {string}
 */
export function formatNumber(number: number, options?: import("../../types/LocalizeMixinTypes.js").FormatNumberOptions | undefined): string;
export type NumberPostProcessor = typeof import("../../types/LocalizeMixinTypes.js").NumberPostProcessorImplementation;
/**
 * Formats a number based on locale and options. It uses Intl for the formatting.
 */
export type FormatNumberPart = import("../../types/LocalizeMixinTypes.js").FormatNumberPart;
/**
 * Formats a number based on locale and options. It uses Intl for the formatting.
 */
export type FormatOptions = import("../../types/LocalizeMixinTypes.js").FormatNumberOptions;
