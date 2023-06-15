/**
 * Round the number based on the options
 *
 * @param {number} number
 * @param {string} roundMode
 * @throws {Error} roundMode can only be round|floor|ceiling
 * @returns {number}
 */
export function roundNumber(number: number, roundMode: string): number;
/**
 * Splits a number up in parts for integer, fraction, group, literal, decimal and currency.
 *
 * @typedef {import('../../types/LocalizeMixinTypes').FormatNumberPart} FormatNumberPart
 * @param {number} number Number to split up
 * @param {import('../../types/LocalizeMixinTypes').FormatNumberOptions} [options] Intl options are available extended by roundMode,returnIfNaN
 * @returns {string | FormatNumberPart[]} Array with parts or (an empty string or returnIfNaN if not a number)
 */
export function formatNumberToParts(number: number, options?: import("../../types/LocalizeMixinTypes.js").FormatNumberOptions | undefined): string | FormatNumberPart[];
/**
 * Splits a number up in parts for integer, fraction, group, literal, decimal and currency.
 */
export type FormatNumberPart = import("../../types/LocalizeMixinTypes.js").FormatNumberPart;
