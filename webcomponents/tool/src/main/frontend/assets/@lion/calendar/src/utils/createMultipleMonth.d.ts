/**
 *
 * @param {Date} date
 * @return {{months: import('../../types/day').Month[]}}
 */
export function createMultipleMonth(date: Date, { firstDayOfWeek, pastMonths, futureMonths }?: {
    firstDayOfWeek?: number | undefined;
    pastMonths?: number | undefined;
    futureMonths?: number | undefined;
}): {
    months: import('../../types/day').Month[];
};
