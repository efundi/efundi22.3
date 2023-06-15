/**
 * @param {Date} date
 * @param {Object} opts
 * @param {number} [opts.firstDayOfWeek]
 * @returns {import('../../types/day').Week}
 */
export function createWeek(date: Date, { firstDayOfWeek }?: {
    firstDayOfWeek: number;
}): import('../../types/day').Week;
