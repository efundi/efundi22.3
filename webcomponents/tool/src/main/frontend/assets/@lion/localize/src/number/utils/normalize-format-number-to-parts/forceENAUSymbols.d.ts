/**
 * Change the symbols for locale 'en-AU', due to bug in Chrome
 *
 * @typedef {import('../../../../types/LocalizeMixinTypes').FormatNumberPart} FormatNumberPart
 * @param {FormatNumberPart[]} formattedParts
 * @param {import('../../../../types/LocalizeMixinTypes').FormatNumberOptions} [options]
 * @returns {FormatNumberPart[]}
 */
export function forceENAUSymbols(formattedParts: FormatNumberPart[], { currency, currencyDisplay }?: import("../../../../types/LocalizeMixinTypes").FormatNumberOptions | undefined): FormatNumberPart[];
/**
 * Change the symbols for locale 'en-AU', due to bug in Chrome
 */
export type FormatNumberPart = import("../../../../types/LocalizeMixinTypes").FormatNumberPart;
