/**
 * To filter out some added characters in IE
 *
 * @param {string} str
 * @param {string} [locale='']
 * @param {import('@lion/localize/types/LocalizeMixinTypes').FormatDateOptions} [options] Intl options are available
 * @returns {string}
 */
export function normalizeIntlDate(str: string, locale?: string | undefined, { weekday, year, month, day }?: import("../../../types/LocalizeMixinTypes").FormatDateOptions | undefined): string;
