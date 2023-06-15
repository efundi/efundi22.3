/**
 * @typedef {import('../../../../types/LocalizeMixinTypes').FormatNumberPart} FormatNumberPart
 * @param {FormatNumberPart[]} formattedParts
 * @param {import('../../../../types/LocalizeMixinTypes').FormatNumberOptions} [options]
 * @returns {FormatNumberPart[]}
 */
export function forceTryCurrencyCode(formattedParts: FormatNumberPart[], { currency, currencyDisplay }?: import("../../../../types/LocalizeMixinTypes").FormatNumberOptions | undefined): FormatNumberPart[];
export type FormatNumberPart = import("../../../../types/LocalizeMixinTypes").FormatNumberPart;
