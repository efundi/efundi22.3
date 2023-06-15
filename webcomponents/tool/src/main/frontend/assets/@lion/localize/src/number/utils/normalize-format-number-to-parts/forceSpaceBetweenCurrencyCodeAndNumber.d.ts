/**
 * When in some locales there is no space between currency and amount it is added
 *
 * @typedef {import('../../../../types/LocalizeMixinTypes').FormatNumberPart} FormatNumberPart
 * @param {FormatNumberPart[]} formattedParts
 * @param {import('../../../../types/LocalizeMixinTypes').FormatNumberOptions} [options]
 * @returns {FormatNumberPart[]}
 */
export function forceSpaceBetweenCurrencyCodeAndNumber(formattedParts: FormatNumberPart[], { currency, currencyDisplay }?: import("../../../../types/LocalizeMixinTypes").FormatNumberOptions | undefined): FormatNumberPart[];
/**
 * When in some locales there is no space between currency and amount it is added
 */
export type FormatNumberPart = import("../../../../types/LocalizeMixinTypes").FormatNumberPart;
