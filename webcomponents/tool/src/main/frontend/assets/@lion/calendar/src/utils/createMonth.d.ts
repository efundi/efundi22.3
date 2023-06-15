/**
 *
 * @param {Date} date
 * @param {Object} opts
 * @param {number} [opts.firstDayOfWeek]
 * @returns {import('../../types/day').Month}
 */
export function createMonth(date: Date, { firstDayOfWeek }?: {
    firstDayOfWeek: number;
}): import('../../types/day').Month;
